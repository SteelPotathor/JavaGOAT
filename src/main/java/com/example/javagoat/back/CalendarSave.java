package com.example.javagoat.back;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class CalendarSave {

    public static CalendarView calendarView;
    public final String CalendarPath = "src\\main\\java\\com\\example\\javagoat\\back\\Calendar.dat";

    public CalendarSave(CalendarView calendar) {
        this.calendarView = calendar;
        System.out.println("nouveau calendrier : " + this.calendarView);
        try {
            FileInputStream fileInputStream = new FileInputStream(this.CalendarPath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ObservableList observableList = this.calendarView.getCalendars();
            ArrayList<Map<Date, Entry>> list = (ArrayList<Map<Date, Entry>>) objectInputStream.readObject();
            for (int i = 0; i < observableList.size(); i++) {
                Calendar c = (Calendar) observableList.get(i);
                Iterator<Map.Entry<Date, Entry>> iterator = list.get(i).entrySet().iterator();
                while (iterator.hasNext()) {
                    c.addEntries(iterator.next().getValue());
                }
            }
        } catch (Exception e) {
        }
    }

    public void saveEntries() {
        try {
            FileOutputStream fos = new FileOutputStream(this.CalendarPath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            System.out.println("objet manipulé : " + this.calendarView);
            System.out.println("sous calendars : " + this.calendarView.getCalendars());

            ArrayList<Map<Date, Entry>> list = new ArrayList<>();
            for (Calendar c : this.calendarView.getCalendars()) {
                Map<Date, Entry> map = c.findEntries(LocalDate.of(1900, 1, 1), LocalDate.of(2500, 1, 1), ZoneId.of("Europe/Paris"));
                list.add(map);
            }

            oos.writeObject(list);
            System.out.println("save : " +list);
            oos.close();
            fos.close();

        } catch (final IOException e) {
            throw new RuntimeException("Error while saving calendars");
        }
    }

}
