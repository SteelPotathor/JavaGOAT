package com.example.javagoat.Model.matching;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MatchingSettingsTest {

    @TempDir
    Path dir;

    @Test
    void defaultsAreComplete() {
        MatchingSettings settings = MatchingSettings.defaults();
        for (String criterion : MatchingSettings.CRITERIA) {
            assertTrue(settings.weight(criterion) >= 0, criterion);
        }
        assertEquals(0, settings.importanceWeight(Importance.INDIFFERENT));
        assertTrue(settings.importanceWeight(Importance.IMPORTANT) > settings.importanceWeight(Importance.NICE_TO_HAVE));
    }

    @Test
    void anOverrideFileChangesOnlyItsKeys() throws Exception {
        Path file = dir.resolve(MatchingSettings.FILE_NAME);
        Files.writeString(file, "weight.age=10\n");
        MatchingSettings settings = MatchingSettings.load(file);
        assertEquals(10, settings.weight("age"));
        assertEquals(MatchingSettings.defaults().weight("size"), settings.weight("size"));
    }

    @Test
    void missingOverrideFileMeansDefaults() {
        assertEquals(MatchingSettings.defaults().toString(), MatchingSettings.load(dir.resolve("absent.properties")).toString());
    }

    @Test
    void invalidValuesAreRejectedWithTheirKey() throws Exception {
        Path file = dir.resolve(MatchingSettings.FILE_NAME);
        Files.writeString(file, "weight.age=-1\ntolerance.size=abc\nordinal.stepPenalty=2\n");
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> MatchingSettings.load(file));
        assertTrue(e.getMessage().contains("weight.age"), e.getMessage());
        assertTrue(e.getMessage().contains("tolerance.size"), e.getMessage());
        assertTrue(e.getMessage().contains("ordinal.stepPenalty"), e.getMessage());
    }

    @Test
    void unknownKeysAreRejected() throws Exception {
        Path file = dir.resolve(MatchingSettings.FILE_NAME);
        Files.writeString(file, "weigth.age=2\n");
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> MatchingSettings.load(file));
        assertTrue(e.getMessage().contains("weigth.age"));
    }
}
