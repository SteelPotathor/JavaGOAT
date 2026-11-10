package com.example.javagoat.Model;

import com.example.javagoat.Model.names.NameGenerator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/** One test per bug fixed in the model during the revival. */
class RegressionTest {

    @Test
    void removeMatchRemovesTheMatchOnBothSides() {
        Profile a = TestProfiles.profile(1, Biology.sex.MALE, 30, Biology.sex.FEMALE, 25, 35);
        Profile b = TestProfiles.profile(2, Biology.sex.FEMALE, 30, Biology.sex.MALE, 25, 35);
        a.getModelHisto().addMatch(a, b);
        a.getModelHisto().removeMatch(a, b);
        assertTrue(a.getModelHisto().getStockHisto().isEmpty());
        assertTrue(b.getModelHisto().getStockHisto().isEmpty());
    }

    @Test
    void priorityDependsOnTheDateOfTheLastMatch() {
        HashMap<Integer, Profile> backup = ModelProfile.profileHashMap;
        try {
            ModelProfile.profileHashMap = new HashMap<>();
            long day = 24L * 3600 * 1000;
            int[][] cases = {{3, 3}, {10, 2}, {20, 1}};  // {days since the last match, expected priority}
            for (int[] c : cases) {
                Profile p = TestProfiles.profile(c[0], Biology.sex.MALE, 30, Biology.sex.FEMALE, 25, 35);
                p.getModelHisto().getStockHisto().put(new TupleHistoHashMap(999), new Date(System.currentTimeMillis() - c[0] * day));
                ModelProfile.profileHashMap.put(c[0], p);
            }
            Profile neverMatched = TestProfiles.profile(50, Biology.sex.MALE, 30, Biology.sex.FEMALE, 25, 35);
            ModelProfile.profileHashMap.put(50, neverMatched);

            new CheckPriorityStart().initPriority();

            // With the old "/ 36000000" (10 hours instead of 1), a match 10 days ago still gave priority 3
            assertEquals(3, ModelProfile.profileHashMap.get(3).getPriority());
            assertEquals(2, ModelProfile.profileHashMap.get(10).getPriority());
            assertEquals(1, ModelProfile.profileHashMap.get(20).getPriority());
            assertEquals(1, neverMatched.getPriority());
        } finally {
            ModelProfile.profileHashMap = backup;
        }
    }

    @Test
    void typedSizesAreStoredAsTyped() {
        Profile man = TestProfiles.profile(1, Biology.sex.MALE, 30, Biology.sex.MALE, 25, 35);
        assertEquals(175, man.getPhysicalAttributes().getSize(), "no hidden +15 cm for men");
    }

    @Test
    void nameGeneratorUsesTheWholeFile() {
        NameGenerator generator = new NameGenerator(Biology.sex.FEMALE, new Random(1));
        Set<String> names = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            String[] name = generator.getRandomName();
            assertEquals(2, name.length);
            names.add(name[0] + " " + name[1]);
        }
        assertTrue(names.size() > 500, "only ~65 names could be drawn before, got " + names.size());
    }

    @Test
    void notificationsAtTheSameMillisecondAreAllKept() {
        int before = ModelNotification.getStockNotification().size();
        Date now = new Date();
        ModelNotification notifications = new ModelNotification();
        notifications.addNotification(now, "first");
        notifications.addNotification(now, "second");
        assertEquals(before + 2, ModelNotification.getStockNotification().size());
    }
}
