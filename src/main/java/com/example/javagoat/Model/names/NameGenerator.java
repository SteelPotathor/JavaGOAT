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
 * <p>
 * The previous version read the file through a working-directory path and only seeked into its
 * first 1000 bytes, so about 65 of the 10 000 names could ever be drawn.
 */
public class NameGenerator {

    private static final Random RANDOM = new Random();
    private static List<String> femaleNames;
    private static List<String> maleNames;

    private final List<String> names;

    public NameGenerator(Biology biology) {
        this.names = biology.getBsex() == Biology.sex.FEMALE ? femaleNames() : maleNames();
    }

    /** @return {firstname, lastname} */
    public String[] getRandomName() {
        return names.get(RANDOM.nextInt(names.size())).split(" ", 2);
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
