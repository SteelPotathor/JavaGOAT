package com.example.javagoat.Model;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

/** The data files committed in the repository must stay readable by the current code. */
class ShippedDataTest {

    @Test
    void committedProfilesCanBeLoaded() {
        System.clearProperty(ModelMatch.DATA_DIR_PROPERTY); // default directory, relative to the project root
        assertTrue(Files.exists(ModelMatch.profilesPath()), "run the tests from the project root");

        ModelMatch.loadData();

        assertFalse(ModelProfile.profileHashMap.isEmpty());
        int count = ModelProfile.profileHashMap.size();
        ModelProfile.profileHashMap.forEach((id, profile) -> assertEquals(id, profile.getIdentity().getNoId()));
        ModelMatch.stockDistance.values().forEach(row -> assertEquals(count, row.size()));
    }
}
