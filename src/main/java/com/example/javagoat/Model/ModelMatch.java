package com.example.javagoat.Model;

import com.example.javagoat.Model.matching.Matcher;
import com.example.javagoat.Model.matching.MatchingSettings;
import com.example.javagoat.Model.storage.CalendarStore;
import com.example.javagoat.Model.storage.ProfileStore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
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
    public static final String CALENDAR_FILE = "calendar.json";
    /**
     * Sub-directory of the data directory holding the data shipped with the project. The application works on
     * data/profiles.json and data/calendar.json (not versioned); when one of them does not exist yet, it starts
     * from the shipped copy in data/seed/. Deleting a working file therefore resets it.
     */
    public static final String SEED_DIR = "seed";
    /** Number of suggestions shown by the matching page (it has 5 places). */
    public static final int SUGGESTIONS = 5;

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

    public static Path calendarPath() {
        return dataDirectory().resolve(CALENDAR_FILE);
    }

    /** The working file if it exists, otherwise the shipped one (data/seed/...), otherwise null. */
    static Path sourceOf(Path dir, String fileName) {
        Path working = dir.resolve(fileName);
        if (Files.exists(working)) {
            return working;
        }
        Path seed = dir.resolve(SEED_DIR).resolve(fileName);
        return Files.exists(seed) ? seed : null;
    }

    /**
     * Loads the matching settings and the profiles. Called once at start-up.
     * <ul>
     *     <li>data directory missing: {@link IllegalStateException} (the app is probably not started from the project root);</li>
     *     <li>working file missing: start from the shipped copy (data/seed/), or with no data at all;</li>
     *     <li>invalid settings, profile or calendar file: exception listing the problems, nothing is overwritten.</li>
     * </ul>
     */
    public static synchronized void loadData() {
        Path dir = dataDirectory();
        if (!Files.isDirectory(dir)) {
            throw new IllegalStateException("Data directory not found: " + dir
                    + "\nStart the application from the project root, or set -D" + DATA_DIR_PROPERTY + "=<directory>.");
        }
        matcher = new Matcher(MatchingSettings.load(dir.resolve(MatchingSettings.FILE_NAME)));

        Path file = sourceOf(dir, PROFILES_FILE);
        HashMap<Integer, Profile> loaded = new HashMap<>();
        if (file != null) {
            loaded.putAll(ProfileStore.read(file));
            System.out.println("[JavaGOAT] " + loaded.size() + " profiles loaded from " + file);
        } else {
            System.out.println("[JavaGOAT] No profile file in " + dir + ": starting with an empty agency.");
        }
        Path calendarFile = sourceOf(dir, CALENDAR_FILE);
        List<AgendaEvent> events = calendarFile == null ? List.of() : CalendarStore.read(calendarFile);
        if (calendarFile != null) {
            System.out.println("[JavaGOAT] " + events.size() + " calendar events loaded from " + calendarFile);
        }
        // Nothing is replaced before both files have been read successfully
        ModelProfile.profileHashMap = loaded;
        ModelCalendar.setEvents(events);
        dataLoaded = true;
    }

    /** Saves the profiles and the calendar atomically: a crash during a write cannot corrupt the existing files. */
    public static synchronized void saveData() throws IOException {
        Path target = profilesPath();
        ProfileStore.write(target, ModelProfile.profileHashMap.values(), null);
        System.out.println("[JavaGOAT] " + ModelProfile.profileHashMap.size() + " profiles saved to " + target);
        List<AgendaEvent> events = ModelCalendar.events();
        CalendarStore.write(calendarPath(), events);
        System.out.println("[JavaGOAT] " + events.size() + " calendar events saved to " + calendarPath());
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
    }

    public void removeProfile(Profile p) {
        int id = p.getIdentity().getNoId();
        ModelProfile.profileHashMap.remove(id);
        // No dangling match left in the history of the other profiles
        for (Profile other : ModelProfile.profileHashMap.values()) {
            other.getModelHisto().getStockHisto().remove(new TupleHistoHashMap(id));
        }
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

    // ------------------------------------------------------------------ statistics (dashboard)

    /** Number of matches made on this day (computed from the saved match dates, so it survives a restart). */
    public static int matchesOn(LocalDate day) {
        long sides = ModelProfile.profileHashMap.values().stream()
                .flatMap(p -> p.getModelHisto().getStockHisto().values().stream())
                .filter(date -> LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault()).equals(day))
                .count();
        return (int) (sides / 2); // every match is stored by both people
    }

    /** Number of profiles registered on this day (profiles without a creation date are not counted). */
    public static int profilesCreatedOn(LocalDate day) {
        return (int) ModelProfile.profileHashMap.values().stream().filter(p -> day.equals(p.getCreated())).count();
    }

    @Override
    public String toString() {
        return "ModelMatch{dataDirectory='" + dataDirectory() + "', profiles=" + ModelProfile.profileHashMap.size() + '}';
    }
}
