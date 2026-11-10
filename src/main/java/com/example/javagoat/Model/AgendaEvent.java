package com.example.javagoat.Model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * An event of the agency calendar, independent of the calendar library (stored in data/calendar.json).
 *
 * @param calendar   one of {@link ModelCalendar#CALENDARS}
 * @param location   optional (null)
 * @param recurrence optional RFC 2445 rule as written by the calendar, e.g. "RRULE:FREQ=WEEKLY;BYDAY=MO"
 */
public record AgendaEvent(String title, String calendar, LocalDateTime start, LocalDateTime end, boolean fullDay,
                          String location, String recurrence) {

    public AgendaEvent {
        Objects.requireNonNull(title, "title");
        Objects.requireNonNull(calendar, "calendar");
        Objects.requireNonNull(start, "start");
        Objects.requireNonNull(end, "end");
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("The end of '" + title + "' is before its start");
        }
        location = blankToNull(location);
        recurrence = blankToNull(recurrence);
    }

    private static String blankToNull(String text) {
        return text == null || text.isBlank() ? null : text;
    }
}
