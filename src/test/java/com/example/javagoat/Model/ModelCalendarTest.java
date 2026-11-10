package com.example.javagoat.Model;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelCalendarTest {

    private static AgendaEvent event(String title, LocalDateTime start, LocalDateTime end, String rule) {
        return new AgendaEvent(title, ModelCalendar.WORK, start, end, false, null, rule);
    }

    @AfterEach
    void forget() {
        ModelCalendar.setEvents(List.of());
    }

    @Test
    void eventsOfTheDayIncludeRecurringAndMultiDayEvents() {
        ModelCalendar.setEvents(List.of(
                event("Breakfast", LocalDateTime.of(2026, 11, 10, 8, 0), LocalDateTime.of(2026, 11, 10, 9, 0), null),
                event("Course", LocalDateTime.of(2026, 11, 10, 16, 0), LocalDateTime.of(2026, 11, 10, 17, 0), null),
                event("Seminar", LocalDateTime.of(2026, 11, 9, 9, 0), LocalDateTime.of(2026, 11, 11, 18, 0), null),
                event("Review", LocalDateTime.of(2026, 11, 2, 14, 0), LocalDateTime.of(2026, 11, 2, 15, 0),
                        "RRULE:FREQ=WEEKLY;BYDAY=MO")));

        assertEquals(3, ModelCalendar.eventsOn(LocalDate.of(2026, 11, 10)), "breakfast, course, seminar (day 2)");
        assertEquals(2, ModelCalendar.eventsOn(LocalDate.of(2026, 11, 9)), "seminar + weekly review");
        assertEquals(1, ModelCalendar.eventsOn(LocalDate.of(2026, 12, 7)), "weekly review only");
        assertEquals(0, ModelCalendar.eventsOn(LocalDate.of(2026, 12, 8)));
    }

    @Test
    void entryConversionKeepsEverything() {
        AgendaEvent original = new AgendaEvent("Speed dating", ModelCalendar.PERSONAL, LocalDateTime.of(2026, 11, 27, 19, 0),
                LocalDateTime.of(2026, 11, 27, 22, 0), false, "Agency lounge", "RRULE:FREQ=MONTHLY");
        Calendar<String> personal = new Calendar<>(ModelCalendar.PERSONAL);
        Entry<String> entry = ModelCalendar.toEntry(original);
        personal.addEntry(entry);
        assertEquals(original, ModelCalendar.fromEntry(entry));
    }

    @Test
    void shippedCalendarHasEventsToday() {
        // data/seed/calendar.json is written around the reference date of the shipped data (Tuesday 2026-11-10)
        ModelCalendar.setEvents(com.example.javagoat.Model.storage.CalendarStore.read(java.nio.file.Path.of("data", "seed", "calendar.json")));
        assertEquals(2, ModelCalendar.eventsOn(LocalDate.of(2026, 11, 10)));
        assertEquals(2, ModelCalendar.eventsOn(LocalDate.of(2026, 11, 16)), "meeting + weekly matching review");
    }
}
