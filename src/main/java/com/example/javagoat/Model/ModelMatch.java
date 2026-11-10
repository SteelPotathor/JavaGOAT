package com.example.javagoat.Model;

import com.example.javagoat.Model.matching.Matcher;
import com.example.javagoat.Model.matching.MatchingSettings;
import com.example.javagoat.Model.storage.ProfileStore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Entry point of the model for the controllers: loading/saving the agency, profile changes and suggestions.
 * The profiles themselves live in the static {@link ModelProfile#profileHashMap} shared by all controllers.
 */
public class ModelMatch {

    /** System property that overrides the data directory (default: "data", relative to the project root). */
    public static final String DATA_DIR_PROPERTY = "javagoat.dataDir";
    public static final String DEFAULT_DATA_DIR = "data";
    public static final String PROFILES_FILE = "profiles.json";
    /** Number of suggestions shown by the matching page (it has 5 places). */
    public static final int SUGGESTIONS = 5;

    public static int createCounter = 0;
    private static boolean dataLoaded = false;
    private static Matcher matcher = new Matcher(MatchingSettings.defaults());

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
     * Loads the matching settings and the profiles. Called once at start-up.
     * <ul>
     *     <li>data directory missing: {@link IllegalStateException} (the app is probably not started from the project root);</li>
     *     <li>profile file missing: start with no profile (it will be created on the first save);</li>
     *     <li>invalid settings or profile file: exception listing the problems, nothing is overwritten.</li>
     * </ul>
     */
    public static synchronized void loadData() {
        Path dir = dataDirectory();
        if (!Files.isDirectory(dir)) {
            throw new IllegalStateException("Data directory not found: " + dir
                    + "\nStart the application from the project root, or set -D" + DATA_DIR_PROPERTY + "=<directory>.");
        }
        matcher = new Matcher(MatchingSettings.load(dir.resolve(MatchingSettings.FILE_NAME)));

        Path file = dir.resolve(PROFILES_FILE);
        HashMap<Integer, Profile> loaded = new HashMap<>();
        if (Files.exists(file)) {
            loaded.putAll(ProfileStore.read(file));
            System.out.println("[JavaGOAT] " + loaded.size() + " profiles loaded from " + file);
        } else {
            System.out.println("[JavaGOAT] No profile file at " + file + ": starting with an empty agency.");
        }
        ModelProfile.profileHashMap = loaded;
        dataLoaded = true;
    }

    /** Saves the profiles atomically: a crash during the write cannot corrupt the existing file. */
    public static synchronized void saveData() throws IOException {
        Path target = profilesPath();
        ProfileStore.write(target, ModelProfile.profileHashMap.values(), null);
        System.out.println("[JavaGOAT] " + ModelProfile.profileHashMap.size() + " profiles saved to " + target);
    }

    public static Matcher matcher() {
        return matcher;
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
        ModelProfile.profileHashMap.put(id, p);
        modelN.addNotification(new Date(), "Profile created : " + p.getIdentity().getLastname() + " " + p.getIdentity().getFirstname());
        createCounter++;
    }

    public void removeProfile(Profile p) {
        int id = p.getIdentity().getNoId();
        ModelProfile.profileHashMap.remove(id);
        // No dangling match left in the history of the other profiles
        for (Profile other : ModelProfile.profileHashMap.values()) {
            other.getModelHisto().getStockHisto().remove(new TupleHistoHashMap(id));
        }
        createCounter--;
    }

    /** Must be called after the attributes of 'profile' have been modified in place. */
    public void editProfile(Profile profile) {
        ModelProfile.profileHashMap.put(profile.getIdentity().getNoId(), profile);
        modelN.addNotification(new Date(), "Profile edited : " + profile.getIdentity().getLastname() + " " + profile.getIdentity().getFirstname());
    }

    // ------------------------------------------------------------------ matching

    /** The best {@link #SUGGESTIONS} candidates for a profile, excluding the people it was already matched with. */
    public List<Matcher.Suggestion> getSuggestions(int noProfile) {
        return getSuggestions(noProfile, SUGGESTIONS);
    }

    public List<Matcher.Suggestion> getSuggestions(int noProfile, int howMany) {
        Profile selected = ModelProfile.profileHashMap.get(noProfile);
        if (selected == null) {
            return List.of();
        }
        Map<TupleHistoHashMap, Date> history = selected.getModelHisto().getStockHisto();
        return matcher.suggestions(selected, ModelProfile.profileHashMap.values(), howMany,
                candidate -> history.containsKey(new TupleHistoHashMap(candidate.getIdentity().getNoId())));
    }

    // ------------------------------------------------------------------ accessors

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
        return "ModelMatch{dataDirectory='" + dataDirectory() + "', profiles=" + ModelProfile.profileHashMap.size() + '}';
    }
}
