package com.example.javagoat.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ModelMatchTest {

    @TempDir
    Path dataDir;

    @BeforeEach
    void useTemporaryDataDirectory() {
        System.setProperty(ModelMatch.DATA_DIR_PROPERTY, dataDir.toString());
        ModelMatch.loadData(); // no file yet: empty agency
    }

    @AfterEach
    void restoreDataDirectory() {
        System.clearProperty(ModelMatch.DATA_DIR_PROPERTY);
    }

    private static ModelMatch modelWithProfiles(int count) {
        ModelMatch model = new ModelMatch();
        for (int i = 0; i < count; i++) {
            Biology.sex sex = i % 2 == 0 ? Biology.sex.MALE : Biology.sex.FEMALE;
            Biology.sex other = sex == Biology.sex.MALE ? Biology.sex.FEMALE : Biology.sex.MALE;
            model.addProfile(TestProfiles.profile(ModelMatch.nextId(), sex, 20 + i, other, 25 + i));
        }
        return model;
    }

    @Test
    void missingProfileFileStartsEmpty() {
        assertTrue(ModelProfile.profileHashMap.isEmpty());
        assertTrue(ModelMatch.stockDistance.isEmpty());
    }

    @Test
    void missingDataDirectoryIsAnError() {
        System.setProperty(ModelMatch.DATA_DIR_PROPERTY, dataDir.resolve("does-not-exist").toString());
        IllegalStateException e = assertThrows(IllegalStateException.class, ModelMatch::loadData);
        assertTrue(e.getMessage().contains("Data directory not found"));
    }

    @Test
    void unreadableProfileFileIsAnErrorAndIsNotOverwritten() throws Exception {
        Path file = dataDir.resolve(ModelMatch.PROFILES_FILE);
        Files.writeString(file, "not a serialized map");
        assertThrows(IllegalStateException.class, ModelMatch::loadData);
        assertEquals("not a serialized map", Files.readString(file));
    }

    @Test
    void distanceRowsContainEveryProfile() {
        modelWithProfiles(40);
        for (TreeSet<TupleTreeSet> row : ModelMatch.stockDistance.values()) {
            assertEquals(40, row.size(), "each row must hold one distance per profile");
        }
    }

    @Test
    void newIdsStayUniqueAfterARemoval() {
        ModelMatch model = modelWithProfiles(5);
        model.removeProfile(ModelProfile.profileHashMap.get(2));
        // size() + 1 would give 5, which is still used
        assertEquals(6, ModelMatch.nextId());
        for (TreeSet<TupleTreeSet> row : ModelMatch.stockDistance.values()) {
            assertTrue(row.stream().noneMatch(t -> t.getId() == 2));
            assertEquals(4, row.size());
        }
    }

    @Test
    void editingAProfileUpdatesItsVectorsAndDistances() {
        ModelMatch model = modelWithProfiles(10);
        Profile profile = ModelProfile.profileHashMap.get(3);
        float before = profile.getVectPreferences().getxAge();

        profile.getPreferences().getBiology().setAge(80);
        model.editProfile(profile);

        assertNotEquals(before, profile.getVectPreferences().getxAge(), "vectors must follow the new preferences");
        Map<Integer, TreeSet<TupleTreeSet>> incremental = Map.copyOf(ModelMatch.stockDistance);
        ModelMatch.rebuildDistances();
        assertEquals(ModelMatch.stockDistance, incremental, "incremental update must equal a full recomputation");
    }

    @Test
    void knnReturnsNearestCompatibleProfilesFirst() {
        ModelMatch model = modelWithProfiles(20);
        List<Profile> result = List.copyOf(model.getKNN(1, 5).keySet());
        assertEquals(5, result.size());
        Profile selected = ModelProfile.profileHashMap.get(1);
        for (int i = 1; i < result.size(); i++) {
            assertTrue(selected.getDistance(result.get(i - 1)) <= selected.getDistance(result.get(i)));
        }
        assertTrue(result.stream().allMatch(p -> p.getIdentity().getBsex() == Biology.sex.FEMALE));
    }

    @Test
    void alreadyMatchedProfilesAreNotSuggestedAgain() {
        ModelMatch model = modelWithProfiles(20);
        Profile selected = ModelProfile.profileHashMap.get(1);
        Profile first = model.getKNN(1, 1).keySet().iterator().next();
        selected.getModelHisto().addMatch(selected, first);
        assertFalse(model.getKNN(1, 5).containsKey(first));
    }

    @Test
    void saveThenLoadKeepsTheProfiles() throws Exception {
        modelWithProfiles(12);
        ModelMatch.saveData();
        assertFalse(Files.exists(dataDir.resolve(ModelMatch.PROFILES_FILE + ".tmp")));

        ModelProfile.profileHashMap.clear();
        ModelMatch.loadData();
        assertEquals(12, ModelProfile.profileHashMap.size());
        assertEquals("First7", ModelProfile.profileHashMap.get(7).getIdentity().getFirstname());
    }
}
