package com.example.javagoat.Model;

import com.example.javagoat.Model.matching.Matcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The data shipped in the repository (data/seed/) must load and keep the showcase scenarios.
 * The working files (data/profiles.json...) are not used: they change whenever the application is used.
 */
class ShippedDataTest {

    @BeforeAll
    static void loadShippedData() {
        Path seed = Path.of(ModelMatch.DEFAULT_DATA_DIR, ModelMatch.SEED_DIR);
        assertTrue(Files.exists(seed.resolve(ModelMatch.PROFILES_FILE)), "run the tests from the project root");
        System.setProperty(ModelMatch.DATA_DIR_PROPERTY, seed.toString());
        ModelMatch.loadData();
    }

    @AfterAll
    static void forget() {
        System.clearProperty(ModelMatch.DATA_DIR_PROPERTY);
        ModelProfile.profileHashMap.clear();
        ModelCalendar.setEvents(List.of());
    }

    @Test
    void shippedProfilesLoad() {
        assertTrue(ModelProfile.profileHashMap.size() >= 100);
        ModelProfile.profileHashMap.forEach((id, profile) -> assertEquals(id, profile.getIdentity().getNoId()));
        assertEquals("Camille", ModelProfile.profileHashMap.get(1).getIdentity().getFirstname());
        assertFalse(ModelCalendar.events().isEmpty(), "the shipped calendar is loaded too");
    }

    @Test
    void showcaseScenarioSurvivesAmongTheGeneratedProfiles() {
        List<Matcher.Suggestion> forCamille = new ModelMatch().getSuggestions(1);
        assertEquals("Thomas", forCamille.get(0).candidate().getIdentity().getFirstname());
        assertTrue(forCamille.stream().noneMatch(s -> s.candidate().getIdentity().getNoId() == 3), "Hugo is eliminated");
    }
}
