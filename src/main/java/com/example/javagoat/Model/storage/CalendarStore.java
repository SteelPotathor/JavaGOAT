package com.example.javagoat.Model.storage;

import com.calendarfx.model.Calendar;
import com.example.javagoat.Model.AgendaEvent;
import com.example.javagoat.Model.ModelCalendar;
import com.example.javagoat.Model.storage.JsonModel.CalendarFile;
import com.example.javagoat.Model.storage.JsonModel.EventRecord;
import com.example.javagoat.Model.storage.ProfileStore.DataFormatException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/** Reads and writes the calendar events as JSON (data/calendar.json, format documented in data/README.md). */
public final class CalendarStore {

    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    private CalendarStore() {
    }

    /** @throws DataFormatException listing every problem (event and field) if the file is invalid */
    public static List<AgendaEvent> read(Path file) {
        CalendarFile calendar;
        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            calendar = GSON.fromJson(reader, CalendarFile.class);
        } catch (IOException e) {
            throw new DataFormatException("Cannot read " + file + ": " + e.getMessage(), e);
        } catch (JsonParseException e) {
            throw new DataFormatException("Invalid JSON in " + file + ": " + e.getMessage(), e);
        }
        if (calendar == null || calendar.events() == null) {
            throw new DataFormatException(file + ": a JSON object with an \"events\" array is expected");
        }
        if (!Objects.equals(calendar.version(), JsonModel.CALENDAR_VERSION)) {
            throw new DataFormatException(file + ": \"version\" must be " + JsonModel.CALENDAR_VERSION
                    + " (found " + calendar.version() + ")");
        }
        List<String> problems = new ArrayList<>();
        List<AgendaEvent> events = new ArrayList<>();
        for (int i = 0; i < calendar.events().size(); i++) {
            EventRecord r = calendar.events().get(i);
            String where = "event #" + (i + 1) + (r == null || r.title() == null ? "" : " (" + r.title() + ")") + ": ";
            if (r == null) {
                problems.add(where + "empty entry");
                continue;
            }
            int before = problems.size();
            if (r.title() == null) {
                problems.add(where + "title is missing");
            }
            if (r.calendar() == null || !ModelCalendar.CALENDARS.contains(r.calendar())) {
                problems.add(where + "calendar '" + r.calendar() + "' must be one of " + ModelCalendar.CALENDARS);
            }
            boolean fullDay = Boolean.TRUE.equals(r.fullDay());
            LocalDateTime start = parse(r.start(), "start", where, problems);
            LocalDateTime end = parse(r.end(), "end", where, problems);
            if (start != null && end != null && end.isBefore(start)) {
                problems.add(where + "end is before start");
            }
            if (problems.size() > before) {
                continue;
            }
            AgendaEvent event = new AgendaEvent(r.title(), r.calendar(), start, end, fullDay, r.location(), r.recurrence());
            if (event.recurrence() != null && !validRecurrence(event)) {
                problems.add(where + "recurrence '" + r.recurrence() + "' is not a valid rule (e.g. RRULE:FREQ=WEEKLY)");
                continue;
            }
            events.add(event);
        }
        if (!problems.isEmpty()) {
            throw new DataFormatException(problems.size() + " problem(s) in " + file + ":\n- " + String.join("\n- ", problems));
        }
        return events;
    }

    /** Writes the events atomically, sorted by start, one event per line. */
    public static void write(Path file, List<AgendaEvent> events) throws IOException {
        List<AgendaEvent> sorted = events.stream()
                .sorted(Comparator.comparing(AgendaEvent::start).thenComparing(AgendaEvent::title)).toList();
        StringBuilder json = new StringBuilder("{\n  \"version\": " + JsonModel.CALENDAR_VERSION + ",\n  \"events\": [\n");
        for (int i = 0; i < sorted.size(); i++) {
            AgendaEvent e = sorted.get(i);
            EventRecord r = new EventRecord(e.title(), e.calendar(), format(e.start()), format(e.end()),
                    e.fullDay() ? Boolean.TRUE : null, e.location(), e.recurrence());
            json.append("    ").append(GSON.toJson(r)).append(i < sorted.size() - 1 ? ",\n" : "\n");
        }
        json.append("  ]\n}\n");
        ProfileStore.writeAtomically(file, json.toString());
    }

    private static LocalDateTime parse(String text, String field, String where, List<String> problems) {
        if (text == null) {
            problems.add(where + field + " is missing");
            return null;
        }
        try {
            return text.length() == 10 ? LocalDate.parse(text).atStartOfDay() : LocalDateTime.parse(text, DATE_TIME);
        } catch (DateTimeParseException e) {
            problems.add(where + field + " '" + text + "' must look like 2026-11-10T18:30 (or 2026-11-10)");
            return null;
        }
    }

    private static String format(LocalDateTime dateTime) {
        return dateTime.truncatedTo(ChronoUnit.MINUTES).format(DATE_TIME);
    }

    // The calendar library ignores or rejects an invalid rule only when it shows the event: check it now
    private static boolean validRecurrence(AgendaEvent event) {
        if (!event.recurrence().startsWith("RRULE:")) {
            return false;
        }
        try {
            Calendar<String> calendar = new Calendar<>("check");
            calendar.addEntry(ModelCalendar.toEntry(event));
            LocalDate day = event.start().toLocalDate();
            calendar.findEntries(day, day.plusDays(31), ZoneId.systemDefault());
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
