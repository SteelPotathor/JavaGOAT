package com.example.javagoat.Model.matching;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Weights and tolerances of the matching algorithm.
 * <p>
 * Defaults: matching.properties next to this class (documented there). Optional overrides:
 * {@code <data directory>/matching.properties}. Every value is validated when loaded.
 */
public final class MatchingSettings {

    public static final String FILE_NAME = "matching.properties";

    public static final List<String> CRITERIA = List.of("age", "size", "iq", "hairColor", "hairType", "hairLength",
            "ethnicity", "bodyBuild", "smoker", "alcohol", "athlete", "feed", "religion", "passions");
    public static final List<String> RANGES = List.of("age", "size", "iq");

    private final Map<String, Double> values;

    private MatchingSettings(Map<String, Double> values) {
        this.values = Map.copyOf(values);
    }

    /** Default settings only. */
    public static MatchingSettings defaults() {
        return new MatchingSettings(parse(loadDefaults(), "built-in " + FILE_NAME));
    }

    /** Default settings, overridden by 'overrideFile' if it exists. */
    public static MatchingSettings load(Path overrideFile) {
        Properties properties = loadDefaults();
        String source = "built-in " + FILE_NAME;
        if (Files.exists(overrideFile)) {
            Properties overrides = new Properties();
            try (Reader reader = Files.newBufferedReader(overrideFile, StandardCharsets.UTF_8)) {
                overrides.load(reader);
            } catch (IOException e) {
                throw new IllegalStateException("Cannot read " + overrideFile + ": " + e.getMessage(), e);
            }
            Set<String> unknown = new TreeSet<>(overrides.stringPropertyNames());
            unknown.removeAll(properties.stringPropertyNames());
            if (!unknown.isEmpty()) {
                throw new IllegalStateException("Unknown key(s) " + unknown + " in " + overrideFile
                        + "\nKnown keys: " + new TreeSet<>(properties.stringPropertyNames()));
            }
            properties.putAll(overrides);
            source = overrideFile.toString();
            System.out.println("[JavaGOAT] Matching settings overridden by " + overrideFile + ": " + overrides.stringPropertyNames());
        }
        return new MatchingSettings(parse(properties, source));
    }

    private static Properties loadDefaults() {
        Properties properties = new Properties();
        try (InputStream in = MatchingSettings.class.getResourceAsStream(FILE_NAME)) {
            if (in == null) {
                throw new IllegalStateException("Missing resource " + FILE_NAME + " next to " + MatchingSettings.class.getName());
            }
            properties.load(new java.io.InputStreamReader(in, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read the built-in " + FILE_NAME, e);
        }
        return properties;
    }

    private static Map<String, Double> parse(Properties properties, String source) {
        Map<String, Double> parsed = new HashMap<>();
        List<String> problems = new ArrayList<>();
        for (String key : properties.stringPropertyNames()) {
            String raw = properties.getProperty(key).strip();
            try {
                parsed.put(key, Double.parseDouble(raw));
            } catch (NumberFormatException e) {
                problems.add(key + " = '" + raw + "' is not a number");
            }
        }
        for (String criterion : CRITERIA) {
            check(parsed, problems, "weight." + criterion, 0, Double.MAX_VALUE);
        }
        for (Importance importance : Importance.values()) {
            if (importance != Importance.INDIFFERENT) {
                check(parsed, problems, "importance." + importance.name(), 0, Double.MAX_VALUE);
            }
        }
        for (String range : RANGES) {
            check(parsed, problems, "tolerance." + range, Double.MIN_VALUE, Double.MAX_VALUE);
        }
        check(parsed, problems, "ordinal.stepPenalty", Double.MIN_VALUE, 1);
        if (!problems.isEmpty()) {
            throw new IllegalStateException("Invalid matching settings (" + source + "):\n- " + String.join("\n- ", problems));
        }
        return parsed;
    }

    private static void check(Map<String, Double> values, List<String> problems, String key, double min, double max) {
        Double value = values.get(key);
        if (value == null) {
            problems.add(key + " is missing");
        } else if (value < min || value > max || value.isNaN()) {
            problems.add(key + " = " + value + " is out of range" + (min == 0 ? " (must be >= 0)" : max == 1 ? " (must be in ]0, 1])" : " (must be > 0)"));
        }
    }

    public double weight(String criterion) {
        return get("weight." + criterion);
    }

    public double importanceWeight(Importance importance) {
        return importance == Importance.INDIFFERENT ? 0 : get("importance." + importance.name());
    }

    public double tolerance(String range) {
        return get("tolerance." + range);
    }

    public double stepPenalty() {
        return get("ordinal.stepPenalty");
    }

    private double get(String key) {
        Double value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Unknown matching setting " + key);
        }
        return value;
    }

    @Override
    public String toString() {
        return new TreeMap<>(values).toString();
    }
}
