package com.example.javagoat.Model;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * The events of the agency calendar, shared by the calendar page (which edits them) and the dashboard (which
 * counts today's events). Loaded and saved with the profiles by {@link ModelMatch}.
 */
public final class ModelCalendar {

    public static final String WORK = "Work";
    public static final String PERSONAL = "Personal";
    /** The calendars shown by the calendar page, in this order. */
    public static final List<String> CALENDARS = List.of(WORK, PERSONAL);

    private static List<AgendaEvent> events = new ArrayList<>();

    private ModelCalendar() {
    }

    public static synchronized List<AgendaEvent> events() {
        return List.copyOf(events);
    }

    public static synchronized void setEvents(Collection<AgendaEvent> newEvents) {
        events = new ArrayList<>(newEvents);
    }

    /** Number of events taking place (at least partly) on this day, repetitions of recurring events included. */
    public static int eventsOn(LocalDate day) {
        Calendar<String> calendar = new Calendar<>("count");
        for (AgendaEvent event : events()) {
            calendar.addEntry(toEntry(event));
        }
        return calendar.findEntries(day, day, ZoneId.systemDefault()).getOrDefault(day, List.of()).size();
    }

    // ------------------------------------------------------------------ conversion with the calendar library

    public static Entry<String> toEntry(AgendaEvent event) {
        Entry<String> entry = new Entry<>(event.title());
        entry.setInterval(event.start(), event.end(), ZoneId.systemDefault());
        entry.setFullDay(event.fullDay());
        entry.setLocation(event.location());
        if (event.recurrence() != null) {
            entry.setRecurrenceRule(event.recurrence());
        }
        return entry;
    }

    /** @param entry an entry that belongs to a calendar (its name gives {@link AgendaEvent#calendar()}) */
    public static AgendaEvent fromEntry(Entry<?> entry) {
        return new AgendaEvent(entry.getTitle() == null ? "" : entry.getTitle(), entry.getCalendar().getName(),
                entry.getStartAsLocalDateTime(), entry.getEndAsLocalDateTime(), entry.isFullDay(), entry.getLocation(),
                entry.getRecurrenceRule());
    }
}
