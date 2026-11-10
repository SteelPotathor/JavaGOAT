package com.example.javagoat.Model;

import com.example.javagoat.Model.names.NameGenerator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/** One test per bug fixed in the model (see the git history of the 'revival' branch). */
class RegressionTest {

    @Test
    void closeDistancesAreAllKeptInTheTreeSet() {
        TreeSet<TupleTreeSet> set = new TreeSet<>();
        float[] distances = {1.2f, 1.5f, 1.9f, 1.5f};
        for (int id = 0; id < distances.length; id++) {
            TupleTreeSet t = new TupleTreeSet();
            t.setId(id);
            t.setDistance(distances[id]);
            set.add(t);
        }
        assertEquals(4, set.size(), "the old compareTo considered all these entries equal");
        assertEquals(List.of(0, 1, 3, 2), set.stream().map(TupleTreeSet::getId).toList());
    }

    @Test
    void removeMatchRemovesTheMatchOnBothSides() {
        Profile a = TestProfiles.profile(1, Biology.sex.MALE, 30, Biology.sex.FEMALE, 30);
        Profile b = TestProfiles.profile(2, Biology.sex.FEMALE, 30, Biology.sex.MALE, 30);
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
                Profile p = TestProfiles.profile(c[0], Biology.sex.MALE, 30, Biology.sex.FEMALE, 30);
                p.getModelHisto().getStockHisto().put(new TupleHistoHashMap(999), new Date(System.currentTimeMillis() - c[0] * day));
                ModelProfile.profileHashMap.put(c[0], p);
            }
            Profile neverMatched = TestProfiles.profile(50, Biology.sex.MALE, 30, Biology.sex.FEMALE, 30);
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
        Profile man = TestProfiles.profile(1, Biology.sex.MALE, 30, Biology.sex.MALE, 30);
        assertEquals(175, man.getPhysicalAttributes().getSize(), "no hidden +15 cm for men");
        assertEquals(170, man.getPreferences().getPhysicalAttributes().getSize(), "no hidden +15 cm for a preferred man");
    }

    @Test
    void nameGeneratorUsesTheWholeFile() {
        Biology woman = new Biology(30, Biology.sex.FEMALE, Biology.ethnicity.WHITE, 100);
        NameGenerator generator = new NameGenerator(woman);
        Set<String> names = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            String[] name = generator.getRandomName();
            assertEquals(2, name.length);
            names.add(name[0] + " " + name[1]);
        }
        assertTrue(names.size() > 500, "only ~65 names could be drawn before, got " + names.size());
    }

    @Test
    void generatedIqFollowsANormalDistribution() {
        Biology biology = new Biology();
        Set<Integer> values = new HashSet<>();
        double sum = 0;
        int n = 5000;
        for (int i = 0; i < n; i++) {
            int iq = biology.gauss_law_simulation(100, 15);
            values.add(iq);
            sum += iq;
        }
        assertTrue(values.size() > 40, "the old version produced almost only 85, 100 and 115");
        assertEquals(100, sum / n, 2.0);
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
