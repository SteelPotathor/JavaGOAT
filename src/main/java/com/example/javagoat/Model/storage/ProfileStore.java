package com.example.javagoat.Model.storage;

import com.example.javagoat.Model.*;
import com.example.javagoat.Model.matching.ChoiceCriterion;
import com.example.javagoat.Model.matching.Importance;
import com.example.javagoat.Model.matching.RangeCriterion;
import com.example.javagoat.Model.storage.JsonModel.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Reads and writes the profiles as JSON (format documented in data/README.md).
 * <p>
 * Reading validates the whole file and reports every problem with the profile and field concerned;
 * nothing is loaded if one problem is found.
 */
public final class ProfileStore {

    private static final int MAX_REPORTED_PROBLEMS = 25;
    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    private ProfileStore() {
    }

    /** Thrown when a file cannot be read or does not follow the format. */
    public static final class DataFormatException extends RuntimeException {
        public DataFormatException(String message) {
            super(message);
        }

        public DataFormatException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // ================================================================== reading

    /** @return the profiles by id, with both sides of every match and the feedback counters filled */
    public static LinkedHashMap<Integer, Profile> read(Path file) {
        AgencyFile agency;
        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            agency = GSON.fromJson(reader, AgencyFile.class);
        } catch (IOException e) {
            throw new DataFormatException("Cannot read " + file + ": " + e.getMessage(), e);
        } catch (JsonParseException e) {
            throw new DataFormatException("Invalid JSON in " + file + ": " + e.getMessage(), e);
        }
        if (agency == null || agency.profiles() == null) {
            throw new DataFormatException(file + ": a JSON object with a \"profiles\" array is expected");
        }
        if (!Objects.equals(agency.version(), JsonModel.VERSION)) {
            throw new DataFormatException(file + ": \"version\" must be " + JsonModel.VERSION + " (found " + agency.version() + ")");
        }
        return new Converter(file).toProfiles(agency.profiles());
    }

    private static final class Converter {
        private final Path file;
        private final List<String> problems = new ArrayList<>();
        private String where;

        Converter(Path file) {
            this.file = file;
        }

        LinkedHashMap<Integer, Profile> toProfiles(List<ProfileRecord> records) {
            LinkedHashMap<Integer, Profile> profiles = new LinkedHashMap<>();
            Map<Integer, List<MatchRecord>> matches = new HashMap<>();
            for (int i = 0; i < records.size(); i++) {
                ProfileRecord r = records.get(i);
                where = "profile #" + (i + 1) + (r == null ? "" : " (id " + r.id() + ", " + r.firstname() + " " + r.lastname() + ")");
                if (r == null) {
                    problem("empty entry");
                    continue;
                }
                Profile profile = toProfile(r);
                if (profile == null) {
                    continue;
                }
                if (profiles.putIfAbsent(r.id(), profile) != null) {
                    problem("id " + r.id() + " is used twice");
                }
                matches.put(r.id(), r.matches() == null ? List.of() : r.matches());
            }
            if (problems.isEmpty()) {
                linkMatches(profiles, matches);
            }
            if (!problems.isEmpty()) {
                List<String> shown = problems.subList(0, Math.min(problems.size(), MAX_REPORTED_PROBLEMS));
                throw new DataFormatException(problems.size() + " problem(s) in " + file + ":\n- " + String.join("\n- ", shown)
                        + (problems.size() > shown.size() ? "\n- ..." : ""));
            }
            return profiles;
        }

        private Profile toProfile(ProfileRecord r) {
            int before = problems.size();
            Integer id = required(r.id(), "id");
            if (id != null && id <= 0) problem("id must be positive");
            String firstname = requiredText(r.firstname(), "firstname");
            String lastname = requiredText(r.lastname(), "lastname");
            Biology.sex sex = enumValue(Biology.sex.class, r.sex(), "sex", true);
            Integer age = range(r.age(), "age", 18, 120);
            Biology.ethnicity ethnicity = enumValue(Biology.ethnicity.class, r.ethnicity(), "ethnicity", true);
            Integer iq = range(r.iq(), "iq", 40, 200);
            Integer size = range(r.size(), "size", 100, 250);
            PhysicalAttributes.hairColor hairColor = enumValue(PhysicalAttributes.hairColor.class, r.hairColor(), "hairColor", true);
            PhysicalAttributes.hairType hairType = enumValue(PhysicalAttributes.hairType.class, r.hairType(), "hairType", true);
            PhysicalAttributes.hairLength hairLength = enumValue(PhysicalAttributes.hairLength.class, r.hairLength(), "hairLength", true);
            LifeStyle.smoker smoker = enumValue(LifeStyle.smoker.class, r.smoker(), "smoker", true);
            LifeStyle.alcohol alcohol = enumValue(LifeStyle.alcohol.class, r.alcohol(), "alcohol", true);
            LifeStyle.athlete athlete = enumValue(LifeStyle.athlete.class, r.athlete(), "athlete", true);
            LifeStyle.feed feed = enumValue(LifeStyle.feed.class, r.feed(), "feed", true);
            LifeStyle.bodyBuild bodyBuild = enumValue(LifeStyle.bodyBuild.class, r.bodyBuild(), "bodyBuild", true);
            LifeStyle.religion religion = enumValue(LifeStyle.religion.class, r.religion(), "religion", true);
            HashSet<Passion.video_games> games = new HashSet<>(enumList(Passion.video_games.class, r.videoGames(), "videoGames"));
            HashSet<Passion.miscellaneous> passions = new HashSet<>(enumList(Passion.miscellaneous.class, r.passions(), "passions"));
            Preferences preferences = toPreferences(r.preferences());
            if (problems.size() > before) {
                return null;
            }
            Identity identity = new Identity(age, sex, ethnicity, iq, lastname, firstname, id);
            PhysicalAttributes physical = new PhysicalAttributes(size, hairColor, hairType, hairLength);
            LifeStyle lifeStyle = new LifeStyle(smoker, athlete, feed, bodyBuild, religion, alcohol);
            String picture = r.picture() == null || r.picture().isBlank() ? ImageLoader.DEFAULT_PROFILE_IMAGE : r.picture();
            return new Profile(identity, physical, lifeStyle, preferences, new Passion(games, passions), picture);
        }

        private Preferences toPreferences(PreferencesRecord r) {
            Preferences preferences = new Preferences();
            if (r == null) {
                problem("\"preferences\" is missing (at least \"sexes\" is needed)");
                return preferences;
            }
            List<Biology.sex> sexes = enumList(Biology.sex.class, r.sexes(), "preferences.sexes");
            if (sexes.isEmpty()) {
                problem("preferences.sexes must contain MALE and/or FEMALE");
            }
            preferences.setSexes(new HashSet<>(sexes));
            preferences.setAge(rangeCriterion(r.age(), "age", Preferences.DEFAULT_AGE));
            preferences.setSize(rangeCriterion(r.size(), "size", Preferences.DEFAULT_SIZE));
            preferences.setIq(rangeCriterion(r.iq(), "iq", Preferences.DEFAULT_IQ));
            preferences.setHairColor(choice(PhysicalAttributes.hairColor.class, r.hairColor(), "hairColor"));
            preferences.setHairType(choice(PhysicalAttributes.hairType.class, r.hairType(), "hairType"));
            preferences.setHairLength(choice(PhysicalAttributes.hairLength.class, r.hairLength(), "hairLength"));
            preferences.setEthnicity(choice(Biology.ethnicity.class, r.ethnicity(), "ethnicity"));
            preferences.setBodyBuild(choice(LifeStyle.bodyBuild.class, r.bodyBuild(), "bodyBuild"));
            preferences.setSmoker(choice(LifeStyle.smoker.class, r.smoker(), "smoker"));
            preferences.setAlcohol(choice(LifeStyle.alcohol.class, r.alcohol(), "alcohol"));
            preferences.setAthlete(choice(LifeStyle.athlete.class, r.athlete(), "athlete"));
            preferences.setFeed(choice(LifeStyle.feed.class, r.feed(), "feed"));
            preferences.setReligion(choice(LifeStyle.religion.class, r.religion(), "religion"));
            return preferences;
        }

        private RangeCriterion rangeCriterion(RangeRecord r, String name, RangeCriterion defaults) {
            if (r == null) {
                return defaults;
            }
            String field = "preferences." + name;
            Importance importance = enumValue(Importance.class, r.importance(), field + ".importance", true);
            if (importance == null) {
                return defaults;
            }
            int min = r.min() != null ? r.min() : defaults.min();
            int max = r.max() != null ? r.max() : defaults.max();
            if (importance != Importance.INDIFFERENT && (r.min() == null || r.max() == null)) {
                problem(field + ": \"min\" and \"max\" are needed when the importance is " + importance.name());
                return defaults;
            }
            if (min > max) {
                problem(field + ": min (" + min + ") is greater than max (" + max + ")");
                return defaults;
            }
            return new RangeCriterion(min, max, importance);
        }

        private <E extends Enum<E>> ChoiceCriterion<E> choice(Class<E> type, ChoiceRecord r, String name) {
            if (r == null) {
                return ChoiceCriterion.indifferent(type);
            }
            String field = "preferences." + name;
            Importance importance = enumValue(Importance.class, r.importance(), field + ".importance", true);
            List<E> accepted = enumList(type, r.accepted(), field + ".accepted");
            if (importance == null) {
                return ChoiceCriterion.indifferent(type);
            }
            if (accepted.isEmpty() && importance != Importance.INDIFFERENT) {
                problem(field + ": \"accepted\" needs at least one value when the importance is " + importance.name());
                return ChoiceCriterion.indifferent(type);
            }
            return new ChoiceCriterion<>(type, accepted, importance);
        }

        /** Adds the missing side of each match, then derives the feedback counters from the votes. */
        private void linkMatches(Map<Integer, Profile> profiles, Map<Integer, List<MatchRecord>> matches) {
            for (Map.Entry<Integer, List<MatchRecord>> entry : matches.entrySet()) {
                Profile owner = profiles.get(entry.getKey());
                where = "profile id " + entry.getKey() + " (" + owner.getIdentity().getFirstname() + " " + owner.getIdentity().getLastname() + ")";
                for (MatchRecord m : entry.getValue()) {
                    Integer with = required(m.with(), "matches.with");
                    if (with == null) continue;
                    Profile other = profiles.get(with);
                    if (other == null || other == owner) {
                        problem("matches: unknown profile id " + with);
                        continue;
                    }
                    Date date = parseDate(m.date());
                    if (date == null) continue;
                    TupleHistoHashMap tuple = new TupleHistoHashMap(with);
                    if (m.vote() != null) {
                        switch (m.vote()) {
                            case "POSITIVE" -> { tuple.setVoted(true); tuple.setVote(1); }
                            case "NEGATIVE" -> { tuple.setVoted(true); tuple.setVote(2); }
                            default -> problem("matches.vote must be POSITIVE, NEGATIVE or absent (found '" + m.vote() + "')");
                        }
                    }
                    owner.getModelHisto().getStockHisto().put(tuple, date);
                    // The other side of the match, without vote, if the file only gives one side
                    other.getModelHisto().getStockHisto().putIfAbsent(new TupleHistoHashMap(owner.getIdentity().getNoId()), date);
                }
            }
            for (Profile voter : profiles.values()) {
                voter.getModelHisto().getStockHisto().keySet().stream().filter(TupleHistoHashMap::isVoted).forEach(t -> {
                    Profile rated = profiles.get(t.getId());
                    rated.setTotal(rated.getTotal() + 1);
                    if (t.getVote() == 1) {
                        rated.setPositif(rated.getPositif() + 1);
                    }
                });
            }
        }

        private Date parseDate(String text) {
            if (text == null) {
                problem("matches.date is missing");
                return null;
            }
            try {
                LocalDateTime dateTime = text.length() == 10 ? LocalDate.parse(text).atTime(12, 0) : LocalDateTime.parse(text, DATE_TIME);
                return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
            } catch (DateTimeParseException e) {
                problem("matches.date '" + text + "' must look like 2026-11-10 or 2026-11-10T18:30");
                return null;
            }
        }

        private <T> T required(T value, String field) {
            if (value == null) problem(field + " is missing");
            return value;
        }

        private String requiredText(String value, String field) {
            if (value == null || value.isBlank()) {
                problem(field + " is missing");
                return null;
            }
            return value.strip();
        }

        private Integer range(Integer value, String field, int min, int max) {
            if (required(value, field) == null) return null;
            if (value < min || value > max) {
                problem(field + " = " + value + " must be between " + min + " and " + max);
                return null;
            }
            return value;
        }

        private <E extends Enum<E>> E enumValue(Class<E> type, String value, String field, boolean mandatory) {
            if (value == null) {
                if (mandatory) problem(field + " is missing (one of " + Arrays.toString(type.getEnumConstants()).replace(", ", "|") + ")");
                return null;
            }
            try {
                return Enum.valueOf(type, value.strip());
            } catch (IllegalArgumentException e) {
                problem(field + ": '" + value + "' is not one of " + names(type));
                return null;
            }
        }

        private <E extends Enum<E>> List<E> enumList(Class<E> type, List<String> values, String field) {
            if (values == null) return List.of();
            List<E> result = new ArrayList<>();
            for (String value : values) {
                E parsed = enumValue(type, value, field, true);
                if (parsed != null) result.add(parsed);
            }
            return result;
        }

        private void problem(String message) {
            problems.add(where + ": " + message);
        }
    }

    private static String names(Class<? extends Enum<?>> type) {
        return Arrays.stream(type.getEnumConstants()).map(Enum::name).toList().toString();
    }

    // ================================================================== writing

    /** Writes the profiles atomically, one profile per line (readable diffs, compact file). */
    public static void write(Path file, Collection<Profile> profiles, String comment) throws IOException {
        Files.createDirectories(file.toAbsolutePath().getParent());
        Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
        List<Profile> sorted = profiles.stream().sorted(Comparator.comparingInt(p -> p.getIdentity().getNoId())).toList();
        try (Writer writer = Files.newBufferedWriter(tmp, StandardCharsets.UTF_8)) {
            writer.write("{\n  \"version\": " + JsonModel.VERSION + ",\n");
            if (comment != null) {
                writer.write("  \"comment\": " + GSON.toJson(comment) + ",\n");
            }
            writer.write("  \"profiles\": [\n");
            for (int i = 0; i < sorted.size(); i++) {
                writer.write("    " + GSON.toJson(toRecord(sorted.get(i))) + (i < sorted.size() - 1 ? ",\n" : "\n"));
            }
            writer.write("  ]\n}\n");
        }
        try {
            Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException e) {
            Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static ProfileRecord toRecord(Profile p) {
        Identity id = p.getIdentity();
        PhysicalAttributes physical = p.getPhysicalAttributes();
        LifeStyle life = p.getLifeStyle();
        List<MatchRecord> matches = p.getModelHisto().getStockHisto().entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getKey().getId()))
                .map(e -> new MatchRecord(e.getKey().getId(), formatDate(e.getValue()),
                        !e.getKey().isVoted() ? null : e.getKey().getVote() == 1 ? "POSITIVE" : "NEGATIVE"))
                .toList();
        return new ProfileRecord(id.getNoId(), id.getFirstname(), id.getLastname(), id.getBsex().name(), id.getAge(),
                id.getBethnicity().name(), id.getQi(), physical.getSize(), physical.getHairColor().name(),
                physical.getHairType().name(), physical.getHairLength().name(), life.getLSsmoker().name(),
                life.getLSalcohol().name(), life.getLSathlete().name(), life.getLSfeed().name(),
                life.getLSbodyBuild().name(), life.getLSreligion().name(),
                sortedNames(p.getPassion().getPassionVG()), sortedNames(p.getPassion().getPassionM()),
                ImageLoader.isDefaultReference(p.getImageView()) ? null : p.getImageView(),
                toRecord(p.getPreferences()), matches.isEmpty() ? null : matches);
    }

    private static PreferencesRecord toRecord(Preferences p) {
        return new PreferencesRecord(sortedNames(p.getSexes()), range(p.getAge()), range(p.getSize()), range(p.getIq()),
                choice(p.getHairColor()), choice(p.getHairType()), choice(p.getHairLength()), choice(p.getEthnicity()),
                choice(p.getBodyBuild()), choice(p.getSmoker()), choice(p.getAlcohol()), choice(p.getAthlete()),
                choice(p.getFeed()), choice(p.getReligion()));
    }

    // INDIFFERENT criteria are omitted (null) to keep the file short
    private static RangeRecord range(RangeCriterion c) {
        return c.importance() == Importance.INDIFFERENT ? null : new RangeRecord(c.min(), c.max(), c.importance().name());
    }

    private static ChoiceRecord choice(ChoiceCriterion<?> c) {
        return c.importance() == Importance.INDIFFERENT ? null : new ChoiceRecord(sortedNames(c.accepted()), c.importance().name());
    }

    private static List<String> sortedNames(Collection<? extends Enum<?>> values) {
        return values.stream().sorted(Comparator.comparingInt(e -> e.ordinal())).map(e -> e.name()).toList();
    }

    private static String formatDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).truncatedTo(ChronoUnit.MINUTES).format(DATE_TIME);
    }
}
