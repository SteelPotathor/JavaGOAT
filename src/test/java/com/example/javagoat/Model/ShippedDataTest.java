package com.example.javagoat.Model;

import com.example.javagoat.Model.matching.Matcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** The data committed in the repository (data/profiles.json) must load and keep the showcase scenarios. */
class ShippedDataTest {

    @BeforeAll
    static void loadShippedData() {
        System.clearProperty(ModelMatch.DATA_DIR_PROPERTY); // default directory "data", relative to the project root
        assertTrue(Files.exists(ModelMatch.profilesPath()), "run the tests from the project root");
        ModelMatch.loadData();
    }

    @AfterAll
    static void forget() {
        ModelProfile.profileHashMap.clear();
    }

    @Test
    void shippedProfilesLoad() {
        assertTrue(ModelProfile.profileHashMap.size() >= 100);
        ModelProfile.profileHashMap.forEach((id, profile) -> assertEquals(id, profile.getIdentity().getNoId()));
        assertEquals("Camille", ModelProfile.profileHashMap.get(1).getIdentity().getFirstname());
    }

    @Test
    void showcaseScenarioSurvivesAmongTheGeneratedProfiles() {
        List<Matcher.Suggestion> forCamille = new ModelMatch().getSuggestions(1);
        assertEquals("Thomas", forCamille.get(0).candidate().getIdentity().getFirstname());
        assertTrue(forCamille.stream().noneMatch(s -> s.candidate().getIdentity().getNoId() == 3), "Hugo is eliminated");
    }
}
