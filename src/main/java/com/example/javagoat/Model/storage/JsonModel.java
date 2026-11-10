package com.example.javagoat.Model.storage;

import java.util.List;

/**
 * Shape of data/profiles.json (and of data/showcase-profiles.json). Kept separate from the domain classes
 * so that the file format stays stable and readable by humans. See data/README.md for the documentation.
 * Boxed types are used so that a missing value can be detected (Gson leaves it null).
 */
final class JsonModel {

    static final int VERSION = 2;
    static final int CALENDAR_VERSION = 1;

    private JsonModel() {
    }

    record AgencyFile(Integer version, String comment, List<ProfileRecord> profiles) {
    }

    record ProfileRecord(Integer id, String firstname, String lastname, String sex, Integer age, String ethnicity,
                         Integer iq, Integer size, String hairColor, String hairType, String hairLength,
                         String smoker, String alcohol, String athlete, String feed, String bodyBuild,
                         String religion, List<String> videoGames, List<String> passions, String picture, String created,
                         PreferencesRecord preferences, List<MatchRecord> matches) {
    }

    /** Omitted criteria are INDIFFERENT. */
    record PreferencesRecord(List<String> sexes, RangeRecord age, RangeRecord size, RangeRecord iq,
                             ChoiceRecord hairColor, ChoiceRecord hairType, ChoiceRecord hairLength,
                             ChoiceRecord ethnicity, ChoiceRecord bodyBuild, ChoiceRecord smoker,
                             ChoiceRecord alcohol, ChoiceRecord athlete, ChoiceRecord feed, ChoiceRecord religion) {
    }

    record RangeRecord(Integer min, Integer max, String importance) {
    }

    record ChoiceRecord(List<String> accepted, String importance) {
    }

    /** A match seen from the profile that contains it; 'vote' is the opinion of that profile about the other one. */
    record MatchRecord(Integer with, String date, String vote) {
    }

    /** Shape of data/calendar.json. */
    record CalendarFile(Integer version, List<EventRecord> events) {
    }

    record EventRecord(String title, String calendar, String start, String end, Boolean fullDay, String location,
                       String recurrence) {
    }
}
