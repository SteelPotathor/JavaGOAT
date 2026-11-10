package com.example.javagoat;

import animatefx.animation.FadeInUpBig;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.example.javagoat.Model.AgendaEvent;
import com.example.javagoat.Model.ModelCalendar;
import com.example.javagoat.Model.ModelMatch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Calendar_Controller {

    ModelMatch modelMatch = new ModelMatch();

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent parent;
    @FXML
    private Pane dashboard_pane;
    @FXML
    private Pane profile_pane;
    @FXML
    private Pane search_pane;
    @FXML
    private Pane calendar_pane;

    @FXML
    private CalendarView calendar;

    @FXML
    public void initialize() {
        calendar_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        early_animations();
        setUpCalendars();
    }

    /** Entries of this page that are saved (the repetitions of a recurring entry are not: only their source). */
    private final Set<Entry<?>> entries = new LinkedHashSet<>();

    /**
     * Shows the saved events in the two calendars and keeps {@link ModelCalendar} up to date with every change
     * made in the page (creation, move, resize, edition, deletion). The events are written to data/calendar.json
     * with the profiles when the application is closed.
     */
    private void setUpCalendars() {
        CalendarSource calendarSource = calendar.getCalendarSources().get(0);
        Calendar<?> work = calendarSource.getCalendars().get(0); // the default calendar created by CalendarView
        work.setName(ModelCalendar.WORK);
        Calendar<?> personal = new Calendar<>(ModelCalendar.PERSONAL);
        personal.setStyle(Calendar.Style.STYLE6);
        calendarSource.setName("My calendars");
        calendarSource.getCalendars().add(personal);
        // Only these two calendars are saved: no "add calendar" button
        calendar.setShowAddCalendarButton(false);

        Map<String, Calendar<?>> byName = Map.of(ModelCalendar.WORK, work, ModelCalendar.PERSONAL, personal);
        for (AgendaEvent event : ModelCalendar.events()) {
            Entry<String> entry = ModelCalendar.toEntry(event);
            byName.get(event.calendar()).addEntry(entry);
            entries.add(entry);
        }
        for (Calendar<?> c : byName.values()) {
            c.addEventHandler(this::onCalendarChange);
        }
    }

    private void onCalendarChange(CalendarEvent event) {
        Entry<?> entry = event.getEntry();
        if (entry != null && !entry.isRecurrence()) {
            if (entry.getCalendar() == null) {
                entries.remove(entry); // deleted
            } else {
                entries.add(entry); // created, or moved to the other calendar
            }
        }
        ModelCalendar.setEvents(entries.stream()
                .filter(e -> e.getCalendar() != null)
                .map(ModelCalendar::fromEntry)
                .toList());
    }

    private void early_animations() {
        new FadeInUpBig(calendar).play();
    }

    @FXML
    void change_background_color(MouseEvent event) throws InterruptedException {
        if (event.getSource() == dashboard_pane) {
            dashboard_pane.setStyle("-fx-background-color: rgba(255, 255,255, 0.3)");
        } else if (event.getSource() == search_pane) {
            search_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        } else if (event.getSource() == profile_pane) {
            profile_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        }
    }

    @FXML
    void unselectNavigation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == dashboard_pane) {
            dashboard_pane.setStyle("-fx-background-color: rgba(255, 255,255, 0)");
        } else if (mouseEvent.getSource() == search_pane) {
            search_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0)");
        } else if (mouseEvent.getSource() == profile_pane) {
            profile_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0)");
        }
    }

    @FXML
    void change_scene_to_page_dashboard(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard/home.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void change_scene_to_new_profile(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewProfile/new_profile.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void change_scene_to_page_search(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Search/search.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void change_scene_to_page_calendar(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Calendar/calendar.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void exit_script() {
        ApplicationStart.saveAndExit();
    }
}