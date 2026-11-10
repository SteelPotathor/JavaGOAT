package com.example.javagoat.Model.names;

import com.example.javagoat.Model.Biology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

/**
 * Random "Firstname Lastname" taken from female.csv / male.csv (classpath resources).
 * The caller provides the Random, so that a seeded generator always produces the same names.
 */
public class NameGenerator {

    private static List<String> femaleNames;
    private static List<String> maleNames;

    private final List<String> names;
    private final Random random;

    public NameGenerator(Biology.sex sex, Random random) {
        this.names = sex == Biology.sex.FEMALE ? femaleNames() : maleNames();
        this.random = random;
    }

    /** @return {firstname, lastname} */
    public String[] getRandomName() {
        return names.get(random.nextInt(names.size())).split(" ", 2);
    }

    private static synchronized List<String> femaleNames() {
        if (femaleNames == null) {
            femaleNames = load("female.csv");
        }
        return femaleNames;
    }

    private static synchronized List<String> maleNames() {
        if (maleNames == null) {
            maleNames = load("male.csv");
        }
        return maleNames;
    }

    private static List<String> load(String resource) {
        InputStream stream = NameGenerator.class.getResourceAsStream(resource);
        if (stream == null) {
            throw new IllegalStateException("Missing resource " + resource + " next to " + NameGenerator.class.getName());
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            List<String> lines = reader.lines().map(String::strip).filter(line -> line.contains(" ")).toList();
            if (lines.isEmpty()) {
                throw new IllegalStateException("No name found in " + resource);
            }
            return lines;
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot read " + resource, e);
        }
    }
}
