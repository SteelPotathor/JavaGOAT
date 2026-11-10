package com.example.javagoat.Model.storage;

import com.example.javagoat.Model.Profile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProfileStoreTest {

    @TempDir
    Path dir;

    private static final String VALID_PROFILE = """
            {"id": 1, "firstname": "Ana", "lastname": "Lopez", "sex": "FEMALE", "age": 30, "ethnicity": "LATINO", "iq": 100,
             "size": 165, "hairColor": "BLACK", "hairType": "CURLY", "hairLength": "LONG",
             "smoker": "NEVER", "alcohol": "NEVER", "athlete": "SPORTY", "feed": "VEGAN", "bodyBuild": "SKINNY", "religion": "OTHER",
             "preferences": {"sexes": ["MALE"]}}""";

    private Path write(String profilesJson) throws Exception {
        Path file = dir.resolve("profiles.json");
        Files.writeString(file, "{\"version\": 2, \"profiles\": [" + profilesJson + "]}");
        return file;
    }

    @Test
    void writeThenReadGivesTheSameData() throws Exception {
        Map<Integer, Profile> showcase = ProfileStore.read(Path.of("data", "showcase-profiles.json"));
        Path copy = dir.resolve("copy.json");
        ProfileStore.write(copy, showcase.values(), null);
        Map<Integer, Profile> reread = ProfileStore.read(copy);

        assertEquals(showcase.keySet(), reread.keySet());
        for (Integer id : showcase.keySet()) {
            Profile a = showcase.get(id), b = reread.get(id);
            assertEquals(a.getIdentity().toString(), b.getIdentity().toString());
            assertEquals(a.getPreferences().toString(), b.getPreferences().toString());
            assertEquals(a.getPassion().getPassionM(), b.getPassion().getPassionM());
            assertEquals(a.getModelHisto().getStockHisto().size(), b.getModelHisto().getStockHisto().size());
        }
        ProfileStore.write(dir.resolve("copy2.json"), reread.values(), null);
        assertEquals(Files.readString(copy), Files.readString(dir.resolve("copy2.json")), "writing is stable");
    }

    @Test
    void omittedOptionalFieldsGetDefaults() throws Exception {
        Profile ana = ProfileStore.read(write(VALID_PROFILE)).get(1);
        assertTrue(ana.getPassion().getPassionVG().isEmpty());
        assertEquals("default", ana.getImageView());
        assertEquals("INDIFFERENT", ana.getPreferences().getAge().importance().name());
    }

    @Test
    void everyProblemIsReportedWithItsProfileAndField() throws Exception {
        String broken = VALID_PROFILE.replace("\"BLACK\"", "\"BLAK\"").replace("\"age\": 30", "\"age\": 12")
                .replace("\"sexes\": [\"MALE\"]", "\"sexes\": [], \"smoker\": {\"accepted\": [], \"importance\": \"IMPORTANT\"}");
        ProfileStore.DataFormatException e = assertThrows(ProfileStore.DataFormatException.class, () -> ProfileStore.read(write(broken)));
        String message = e.getMessage();
        assertTrue(message.contains("Ana Lopez"), message);
        assertTrue(message.contains("hairColor: 'BLAK'"), message);
        assertTrue(message.contains("age = 12"), message);
        assertTrue(message.contains("preferences.sexes"), message);
        assertTrue(message.contains("preferences.smoker"), message);
    }

    @Test
    void duplicateIdsAndUnknownMatchesAreRejected() throws Exception {
        assertThrows(ProfileStore.DataFormatException.class, () -> ProfileStore.read(write(VALID_PROFILE + "," + VALID_PROFILE)));
        String withMatch = VALID_PROFILE.replace("\"preferences\"", "\"matches\": [{\"with\": 42, \"date\": \"2026-01-01\"}], \"preferences\"");
        ProfileStore.DataFormatException e = assertThrows(ProfileStore.DataFormatException.class, () -> ProfileStore.read(write(withMatch)));
        assertTrue(e.getMessage().contains("unknown profile id 42"), e.getMessage());
    }

    @Test
    void wrongVersionIsRejected() throws Exception {
        Path file = dir.resolve("old.json");
        Files.writeString(file, "{\"version\": 1, \"profiles\": []}");
        assertThrows(ProfileStore.DataFormatException.class, () -> ProfileStore.read(file));
    }
}
