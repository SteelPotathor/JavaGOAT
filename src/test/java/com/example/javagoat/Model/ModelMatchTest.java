package com.example.javagoat.Model;

import com.example.javagoat.Model.matching.Matcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

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

    /** Alternately a man looking for women and a woman looking for men, aged 20, 21, 22... */
    private static ModelMatch modelWithProfiles(int count) {
        ModelMatch model = new ModelMatch();
        for (int i = 0; i < count; i++) {
            Biology.sex sex = i % 2 == 0 ? Biology.sex.MALE : Biology.sex.FEMALE;
            Biology.sex other = sex == Biology.sex.MALE ? Biology.sex.FEMALE : Biology.sex.MALE;
            model.addProfile(TestProfiles.profile(ModelMatch.nextId(), sex, 20 + i, other, 20, 40));
        }
        return model;
    }

    @Test
    void missingProfileFileStartsEmpty() {
        assertTrue(ModelProfile.profileHashMap.isEmpty());
    }

    @Test
    void missingDataDirectoryIsAnError() {
        System.setProperty(ModelMatch.DATA_DIR_PROPERTY, dataDir.resolve("does-not-exist").toString());
        IllegalStateException e = assertThrows(IllegalStateException.class, ModelMatch::loadData);
        assertTrue(e.getMessage().contains("Data directory not found"));
    }

    @Test
    void invalidProfileFileIsAnErrorAndIsNotOverwritten() throws Exception {
        Path file = dataDir.resolve(ModelMatch.PROFILES_FILE);
        Files.writeString(file, "not json at all");
        assertThrows(RuntimeException.class, ModelMatch::loadData);
        assertEquals("not json at all", Files.readString(file));
    }

    @Test
    void newIdsStayUniqueAfterARemoval() {
        ModelMatch model = modelWithProfiles(5);
        model.removeProfile(ModelProfile.profileHashMap.get(2));
        assertEquals(6, ModelMatch.nextId(), "size() + 1 would give 5, which is still used");
    }

    @Test
    void removingAProfileRemovesItFromTheHistoryOfOthers() {
        ModelMatch model = modelWithProfiles(4);
        Profile a = ModelProfile.profileHashMap.get(1);
        Profile b = ModelProfile.profileHashMap.get(2);
        a.getModelHisto().addMatch(a, b);
        model.removeProfile(b);
        assertTrue(a.getModelHisto().getStockHisto().isEmpty());
    }

    @Test
    void suggestionsAreSortedAndExcludeAlreadyMatchedPeople() {
        ModelMatch model = modelWithProfiles(20);
        List<Matcher.Suggestion> suggestions = model.getSuggestions(1);
        assertEquals(ModelMatch.SUGGESTIONS, suggestions.size());
        for (int i = 1; i < suggestions.size(); i++) {
            assertTrue(suggestions.get(i - 1).compatibility() >= suggestions.get(i).compatibility());
        }
        assertTrue(suggestions.stream().allMatch(s -> s.candidate().getIdentity().getBsex() == Biology.sex.FEMALE));

        Profile selected = ModelProfile.profileHashMap.get(1);
        Profile first = suggestions.get(0).candidate();
        selected.getModelHisto().addMatch(selected, first);
        assertTrue(model.getSuggestions(1).stream().noneMatch(s -> s.candidate() == first));
    }

    @Test
    void saveThenLoadKeepsProfilesMatchesAndVotes() throws Exception {
        modelWithProfiles(12);
        Profile a = ModelProfile.profileHashMap.get(1);
        Profile b = ModelProfile.profileHashMap.get(2);
        a.getModelHisto().addMatch(a, b);
        a.getModelHisto().getStockHisto().keySet().iterator().next().setVoted(true); // a votes...
        a.getModelHisto().getStockHisto().keySet().iterator().next().setVote(1);     // ...positively for b

        ModelMatch.saveData();
        assertFalse(Files.exists(dataDir.resolve(ModelMatch.PROFILES_FILE + ".tmp")));
        ModelProfile.profileHashMap.clear();
        ModelMatch.loadData();

        assertEquals(12, ModelProfile.profileHashMap.size());
        assertEquals("First7", ModelProfile.profileHashMap.get(7).getIdentity().getFirstname());
        Profile reloadedB = ModelProfile.profileHashMap.get(2);
        assertEquals(1, reloadedB.getModelHisto().getStockHisto().size(), "both sides of the match are kept");
        assertEquals(1.0, reloadedB.getTotal());
        assertEquals(100.0, reloadedB.getRatio(), "feedback is derived from the votes");
    }

    @Test
    void workingFilesStartFromTheShippedSeedAndAreSavedNextToIt() throws Exception {
        Path seed = Files.createDirectories(dataDir.resolve(ModelMatch.SEED_DIR));
        Files.copy(Path.of("data", "showcase-profiles.json"), seed.resolve(ModelMatch.PROFILES_FILE));
        Files.copy(Path.of("data", "seed", "calendar.json"), seed.resolve(ModelMatch.CALENDAR_FILE));
        String seedProfiles = Files.readString(seed.resolve(ModelMatch.PROFILES_FILE));

        ModelMatch.loadData();
        assertEquals(12, ModelProfile.profileHashMap.size());
        assertFalse(ModelCalendar.events().isEmpty());

        new ModelMatch().removeProfile(ModelProfile.profileHashMap.get(12)); // also removes the match with Margaux (11)
        ModelCalendar.setEvents(ModelCalendar.events().subList(0, 1));
        ModelMatch.saveData();
        assertEquals(seedProfiles, Files.readString(seed.resolve(ModelMatch.PROFILES_FILE)), "the seed is never modified");

        ModelMatch.loadData(); // now from the working files
        assertEquals(11, ModelProfile.profileHashMap.size());
        assertEquals(1, ModelCalendar.events().size());
        ModelCalendar.setEvents(List.of());
    }

    @Test
    void todayStatisticsComeFromTheSavedDates() throws Exception {
        modelWithProfiles(6);
        LocalDate today = LocalDate.now();
        Profile a = ModelProfile.profileHashMap.get(1), b = ModelProfile.profileHashMap.get(2);
        Profile c = ModelProfile.profileHashMap.get(3), d = ModelProfile.profileHashMap.get(4);
        a.getModelHisto().addMatch(a, b);                                     // today
        c.getModelHisto().addMatch(c, d, Date.from(Instant.now().minus(3, ChronoUnit.DAYS)));
        ModelProfile.profileHashMap.get(5).setCreated(today);
        ModelProfile.profileHashMap.get(6).setCreated(today.minusDays(1));

        assertEquals(1, ModelMatch.matchesOn(today), "a match is stored by both people but counted once");
        assertEquals(1, ModelMatch.matchesOn(today.minusDays(3)));
        assertEquals(1, ModelMatch.profilesCreatedOn(today));

        ModelMatch.saveData();
        ModelMatch.loadData();
        assertEquals(1, ModelMatch.matchesOn(today), "survives a restart");
        assertEquals(1, ModelMatch.profilesCreatedOn(today), "survives a restart");
    }

    @Test
    void invalidMatchingSettingsOverrideIsAnError() throws Exception {
        Files.writeString(dataDir.resolve("matching.properties"), "weight.agee=2\n");
        IllegalStateException e = assertThrows(IllegalStateException.class, ModelMatch::loadData);
        assertTrue(e.getMessage().contains("weight.agee"));
    }
}
