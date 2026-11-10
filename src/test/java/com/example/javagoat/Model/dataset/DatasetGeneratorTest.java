package com.example.javagoat.Model.dataset;

import com.example.javagoat.Model.Biology;
import com.example.javagoat.Model.ModelMatch;
import com.example.javagoat.Model.ModelProfile;
import com.example.javagoat.Model.Profile;
import com.example.javagoat.Model.storage.ProfileStore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DatasetGeneratorTest {

    @TempDir
    static Path dir;
    static Map<Integer, Profile> profiles;
    static List<Profile> generated;

    private static Path generate(String name, long seed) throws Exception {
        Path output = dir.resolve(name);
        DatasetGenerator.main(new String[]{"--count=300", "--seed=" + seed, "--reference-date=2026-11-10",
                "--showcase=data/showcase-profiles.json", "--output=" + output});
        return output;
    }

    @BeforeAll
    static void generateOnce() throws Exception {
        profiles = ProfileStore.read(generate("a.json", 42));
        generated = profiles.values().stream().filter(p -> p.getIdentity().getNoId() > 12).toList();
    }

    @Test
    void sameSeedGivesTheSameFile() throws Exception {
        assertEquals(Files.readString(dir.resolve("a.json")), Files.readString(generate("b.json", 42)));
        assertNotEquals(Files.readString(dir.resolve("a.json")), Files.readString(generate("c.json", 43)));
    }

    @Test
    void showcaseProfilesComeFirstUnchanged() {
        Map<Integer, Profile> showcase = ProfileStore.read(Path.of("data", "showcase-profiles.json"));
        assertEquals(300, profiles.size());
        for (int id = 1; id <= 12; id++) {
            assertEquals(showcase.get(id).getIdentity().toString(), profiles.get(id).getIdentity().toString());
            assertEquals(showcase.get(id).getPreferences().toString(), profiles.get(id).getPreferences().toString());
        }
    }

    @Test
    void distributionsAreRealistic() {
        long heterosexual = generated.stream().filter(p -> !p.getPreferences().getSexes().contains(p.getIdentity().getBsex())).count();
        assertTrue(heterosexual > 0.8 * generated.size(), "heterosexual: " + heterosexual);

        assertTrue(generated.stream().map(p -> p.getIdentity().getQi()).distinct().count() > 40, "IQ must not take only a few values");
        double women = average(generated, Biology.sex.FEMALE);
        double men = average(generated, Biology.sex.MALE);
        assertTrue(men - women > 8, "men " + men + " cm, women " + women + " cm");

        assertTrue(generated.stream().allMatch(p -> p.getPreferences().getAge().min() <= p.getIdentity().getAge()
                && p.getIdentity().getAge() <= p.getPreferences().getAge().max()), "the age range contains the person's own age");
        assertTrue(generated.stream().allMatch(p -> !p.getPassion().getPassionVG().isEmpty() && p.getPassion().getPassionM().size() >= 3));
        Set<String> names = generated.stream().map(p -> p.getIdentity().getFirstname() + " " + p.getIdentity().getLastname()).collect(Collectors.toSet());
        assertEquals(generated.size(), names.size(), "names are unique");
    }

    @Test
    void almostEveryoneGetsSuggestionsAndSomeHaveAHistory() {
        HashMap<Integer, Profile> backup = ModelProfile.profileHashMap;
        try {
            ModelProfile.profileHashMap = new HashMap<>(profiles);
            ModelMatch model = new ModelMatch();
            long withSuggestions = generated.stream().filter(p -> !model.getSuggestions(p.getIdentity().getNoId()).isEmpty()).count();
            assertTrue(withSuggestions > 0.9 * generated.size(), "with suggestions: " + withSuggestions + "/" + generated.size());
        } finally {
            ModelProfile.profileHashMap = backup;
        }
        long withHistory = generated.stream().filter(p -> !p.getModelHisto().getStockHisto().isEmpty()).count();
        assertTrue(withHistory > 0.1 * generated.size(), "with history: " + withHistory);
    }

    private static double average(List<Profile> list, Biology.sex sex) {
        return list.stream().filter(p -> p.getIdentity().getBsex() == sex).mapToInt(p -> p.getPhysicalAttributes().getSize()).average().orElseThrow();
    }
}
