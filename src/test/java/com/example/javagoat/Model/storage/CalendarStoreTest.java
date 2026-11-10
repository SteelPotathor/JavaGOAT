package com.example.javagoat.Model.storage;

import com.example.javagoat.Model.AgendaEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalendarStoreTest {

    @TempDir
    Path dir;

    private Path write(String eventsJson) throws Exception {
        Path file = dir.resolve("calendar.json");
        Files.writeString(file, "{\"version\": 1, \"events\": [" + eventsJson + "]}");
        return file;
    }

    @Test
    void writeThenReadGivesTheSameEvents() throws Exception {
        List<AgendaEvent> events = List.of(
                new AgendaEvent("Rachel's birthday", "Personal", LocalDateTime.of(2026, 11, 29, 14, 0),
                        LocalDateTime.of(2026, 11, 29, 22, 30), false, null, null),
                new AgendaEvent("Matching review", "Work", LocalDateTime.of(2026, 11, 2, 14, 0),
                        LocalDateTime.of(2026, 11, 2, 15, 0), false, "Agency office", "RRULE:FREQ=WEEKLY;BYDAY=MO"),
                new AgendaEvent("Anniversary", "Work", LocalDateTime.of(2026, 11, 14, 0, 0),
                        LocalDateTime.of(2026, 11, 14, 23, 59), true, null, null));
        Path file = dir.resolve("calendar.json");
        CalendarStore.write(file, events);

        List<AgendaEvent> reread = CalendarStore.read(file);
        assertEquals(3, reread.size());
        assertEquals("Matching review", reread.get(0).title(), "sorted by start");
        assertTrue(reread.containsAll(events));
        assertFalse(Files.readString(file).contains("\"fullDay\":false"), "default values are omitted");
    }

    @Test
    void shippedCalendarIsValid() {
        assertFalse(CalendarStore.read(Path.of("data", "seed", "calendar.json")).isEmpty());
    }

    @Test
    void everyProblemIsReported() throws Exception {
        Path file = write("""
                {"title": "No calendar", "start": "2026-11-10T08:00", "end": "2026-11-10T09:00"},
                {"title": "Bad date", "calendar": "Work", "start": "10/11/2026", "end": "2026-11-10T09:00"},
                {"title": "Backwards", "calendar": "Work", "start": "2026-11-10T10:00", "end": "2026-11-10T09:00"},
                {"title": "Bad rule", "calendar": "Work", "start": "2026-11-10T10:00", "end": "2026-11-10T11:00", "recurrence": "every monday"}""");
        ProfileStore.DataFormatException e = assertThrows(ProfileStore.DataFormatException.class, () -> CalendarStore.read(file));
        assertTrue(e.getMessage().contains("4 problem(s)"), e.getMessage());
        assertTrue(e.getMessage().contains("calendar 'null' must be one of [Work, Personal]"), e.getMessage());
        assertTrue(e.getMessage().contains("start '10/11/2026'"), e.getMessage());
        assertTrue(e.getMessage().contains("(Backwards): end is before start"), e.getMessage());
        assertTrue(e.getMessage().contains("recurrence 'every monday'"), e.getMessage());
    }

    @Test
    void wrongVersionIsRejected() throws Exception {
        Path file = dir.resolve("calendar.json");
        Files.writeString(file, "{\"version\": 7, \"events\": []}");
        assertThrows(ProfileStore.DataFormatException.class, () -> CalendarStore.read(file));
    }
}
