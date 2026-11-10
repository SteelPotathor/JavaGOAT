package com.example.javagoat.Model;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ModelMatch implements Serializable {

    // Frozen value: keeps the existing .dat files readable after code changes.
    private static final long serialVersionUID = -4521288369943704302L;

    /** System property that overrides the data directory (default: the directory used by the original project). */
    public static final String DATA_DIR_PROPERTY = "javagoat.dataDir";
    public static final String DEFAULT_DATA_DIR = "src/main/java/com/example/javagoat/Model/storage";
    public static final String PROFILES_FILE = "Profiles.dat";

    public static HashMap<Integer /*id*/, TreeSet<TupleTreeSet>> stockDistance = new HashMap<>();
    public static int createCounter = 0;
    private static boolean dataLoaded = false;

    public ModelProfile modelP;
    public ModelNotification modelN = new ModelNotification();


    public ModelMatch() {
        modelP = new ModelProfile();
        if (!dataLoaded) {
            loadData();
        }
    }

    // ------------------------------------------------------------------ persistence

    public static Path dataDirectory() {
        return Paths.get(System.getProperty(DATA_DIR_PROPERTY, DEFAULT_DATA_DIR)).toAbsolutePath().normalize();
    }

    public static Path profilesPath() {
        return dataDirectory().resolve(PROFILES_FILE);
    }

    /**
     * Loads the profiles and rebuilds the distance table. Called once at start-up.
     * <ul>
     *     <li>data directory missing: {@link IllegalStateException} (the app is probably not started from the project root);</li>
     *     <li>profile file missing: start with no profile (it will be created on the first save);</li>
     *     <li>profile file unreadable: {@link IllegalStateException}, nothing is overwritten.</li>
     * </ul>
     */
    @SuppressWarnings("unchecked")
    public static synchronized void loadData() {
        Path dir = dataDirectory();
        if (!Files.isDirectory(dir)) {
            throw new IllegalStateException("Data directory not found: " + dir
                    + "\nStart the application from the project root, or set -D" + DATA_DIR_PROPERTY + "=<directory>.");
        }
        Path profiles = dir.resolve(PROFILES_FILE);
        HashMap<Integer, Profile> loaded;
        if (Files.exists(profiles)) {
            try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(profiles)))) {
                loaded = (HashMap<Integer, Profile>) in.readObject();
            } catch (IOException | ClassNotFoundException | ClassCastException e) {
                throw new IllegalStateException("Cannot read " + profiles + " (" + e + ")", e);
            }
            System.out.println("[JavaGOAT] " + loaded.size() + " profiles loaded from " + profiles);
        } else {
            loaded = new HashMap<>();
            System.out.println("[JavaGOAT] No profile file at " + profiles + ": starting with an empty agency.");
        }
        ModelProfile.profileHashMap = loaded;
        rebuildDistances();
        dataLoaded = true;
    }

    /** Saves the profiles atomically: a crash during the write cannot corrupt the existing file. */
    public static synchronized void saveData() throws IOException {
        Path target = profilesPath();
        Files.createDirectories(target.getParent());
        Path tmp = target.resolveSibling(PROFILES_FILE + ".tmp");
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(tmp)))) {
            out.writeObject(ModelProfile.profileHashMap);
        }
        try {
            Files.move(tmp, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException e) {
            Files.move(tmp, target, StandardCopyOption.REPLACE_EXISTING);
        }
        System.out.println("[JavaGOAT] " + ModelProfile.profileHashMap.size() + " profiles saved to " + target);
    }

    /**
     * The distance table is derived data: it is recomputed from the profiles instead of being read from
     * Distances.dat, whose content was incomplete (see TupleTreeSet#compareTo).
     */
    public static void rebuildDistances() {
        long start = System.nanoTime();
        HashMap<Integer, TreeSet<TupleTreeSet>> table = new HashMap<>();
        for (Profile profile : ModelProfile.profileHashMap.values()) {
            profile.refreshVectors();
        }
        for (Profile profile : ModelProfile.profileHashMap.values()) {
            table.put(profile.getIdentity().getNoId(), distancesFrom(profile));
        }
        stockDistance = table;
        System.out.printf("[JavaGOAT] Distance table rebuilt in %d ms%n", (System.nanoTime() - start) / 1_000_000);
    }

    private static TreeSet<TupleTreeSet> distancesFrom(Profile profile) {
        TreeSet<TupleTreeSet> set = new TreeSet<>();
        for (Profile other : ModelProfile.profileHashMap.values()) {
            set.add(new TupleTreeSet(other.getIdentity().getNoId(), profile, other));
        }
        return set;
    }

    /** First free id. Ids must stay unique even after a profile has been removed. */
    public static int nextId() {
        return ModelProfile.profileHashMap.keySet().stream().mapToInt(Integer::intValue).max().orElse(0) + 1;
    }

    // ------------------------------------------------------------------ profiles

    public void addProfile(Profile p) {
        int id = p.getIdentity().getNoId();
        if (ModelProfile.profileHashMap.containsKey(id)) {
            throw new IllegalArgumentException("A profile with id " + id + " already exists");
        }
        p.refreshVectors();
        ModelProfile.profileHashMap.put(id, p);

        // Distance from every existing profile to 'p', then the whole row of 'p'
        for (Map.Entry<Integer, TreeSet<TupleTreeSet>> entry : stockDistance.entrySet()) {
            Profile profile = ModelProfile.profileHashMap.get(entry.getKey());
            entry.getValue().add(new TupleTreeSet(id, profile, p));
        }
        stockDistance.put(id, distancesFrom(p));

        modelN.addNotification(new Date(), "Profile created : " + p.getIdentity().getLastname() + " " + p.getIdentity().getFirstname());
        createCounter++;
    }

    public void removeProfile(Profile p) {
        int id = p.getIdentity().getNoId();
        ModelProfile.profileHashMap.remove(id);
        stockDistance.remove(id);
        for (TreeSet<TupleTreeSet> treeSet : stockDistance.values()) {
            treeSet.removeIf(t -> t.getId() == id);
        }
        createCounter--;
    }

    /** Must be called after the attributes of 'profile' have been modified in place. */
    public void editProfile(Profile profile) {
        int id = profile.getIdentity().getNoId();
        profile.refreshVectors();
        ModelProfile.profileHashMap.put(id, profile);

        // The old distances cannot be found by value any more: remove them by id, then recompute
        for (Map.Entry<Integer, TreeSet<TupleTreeSet>> entry : stockDistance.entrySet()) {
            if (entry.getKey() != id) {
                TreeSet<TupleTreeSet> treeSet = entry.getValue();
                treeSet.removeIf(t -> t.getId() == id);
                treeSet.add(new TupleTreeSet(id, ModelProfile.profileHashMap.get(entry.getKey()), profile));
            }
        }
        stockDistance.put(id, distancesFrom(profile));

        modelN.addNotification(new Date(), "Profile edited : " + profile.getIdentity().getLastname() + " " + profile.getIdentity().getFirstname());
    }

    // ------------------------------------------------------------------ matching

    /**
     * Returns at most 'howMany' compatible profiles, nearest first, each with its number of common passions.
     */
    public LinkedHashMap<Profile, Integer> getKNN(int noProfile, int howMany) {
        Profile selected = ModelProfile.profileHashMap.get(noProfile);
        TreeSet<TupleTreeSet> treeSetD = stockDistance.get(noProfile);
        LinkedHashMap<Profile, Integer> knnProfiles = new LinkedHashMap<>();
        if (selected == null || treeSetD == null) {
            return knnProfiles;
        }
        HashMap<TupleHistoHashMap, Date> hashMapH = selected.getModelHisto().getStockHisto();

        Iterator<TupleTreeSet> itr = treeSetD.iterator();
        while (knnProfiles.size() < howMany && itr.hasNext()) {
            int candidateId = itr.next().getId();
            Profile candidate = ModelProfile.profileHashMap.get(candidateId);
            if (candidate == null || candidateId == noProfile
                    || (hashMapH != null && matchesContainsId(hashMapH, candidateId))) {
                continue;
            }
            boolean mutualSexPreference = selected.getPreferences().getBiology().getBsex() == candidate.getIdentity().getBsex()
                    && candidate.getPreferences().getBiology().getBsex() == selected.getIdentity().getBsex();
            if (mutualSexPreference) {
                knnProfiles.put(candidate, commonPassions(selected, candidate));
            }
        }
        return knnProfiles;
    }

    private static int commonPassions(Profile a, Profile b) {
        int counter = (int) b.getPassion().getPassionM().stream().filter(a.getPassion().getPassionM()::contains).count();
        counter += (int) b.getPassion().getPassionVG().stream().filter(a.getPassion().getPassionVG()::contains).count();
        return counter;
    }

    // This function allows to know if a profile has already been matched with another profile (in order to avoid to match it again)
    public boolean matchesContainsId(HashMap<TupleHistoHashMap, Date> stockHisto, int id) {
        return stockHisto.containsKey(new TupleHistoHashMap(id));
    }

    // ------------------------------------------------------------------ accessors

    public HashMap<Integer, TreeSet<TupleTreeSet>> getStockDistance() {
        return stockDistance;
    }

    public void setStockDistance(HashMap<Integer, TreeSet<TupleTreeSet>> stockD) {
        stockDistance = stockD;
    }

    public ModelProfile getModelP() {
        return modelP;
    }

    public void setModelP(ModelProfile modelProfile) {
        modelP = modelProfile;
    }

    public static int getCreateCounter() {
        return createCounter;
    }

    public static void setCreateCounter(int createCounter) {
        ModelMatch.createCounter = createCounter;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ModelMatch{");
        sb.append("modelP=").append(modelP);
        sb.append(", modelN=").append(modelN);
        sb.append(", dataDirectory='").append(dataDirectory()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
