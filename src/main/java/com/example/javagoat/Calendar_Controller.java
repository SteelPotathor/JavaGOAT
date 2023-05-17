package com.example.javagoat;

import animatefx.animation.FadeInUpBig;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
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
import java.time.LocalDate;
import java.time.LocalTime;

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

    private Calendar work;
    private Calendar personal;

    @FXML
    public void initialize() {
        calendar_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        early_animations();
        setUpCalendarName();
        setUpCalendarEntry();
    }


    private void setUpCalendarEntry() {
        /* Did not find a way to serialize the date from calendar
           This method was only used to promote the app
         */
        CalendarSource calendarSource = calendar.getCalendarSources().get(0);
        work = calendarSource.getCalendars().get(0);
        personal = calendarSource.getCalendars().get(1);

        Entry<String> meetings16 = new Entry<>("BreakFast with Bob");
        meetings16.setInterval(LocalDate.of(2023, 5, 9));
        meetings16.changeStartDate(LocalDate.of(2023, 5, 9));
        meetings16.changeEndDate(LocalDate.of(2023, 5, 9));
        meetings16.changeStartTime(LocalTime.of(8,0));
        meetings16.changeEndTime(LocalTime.of(9,30));
        personal.addEntry(meetings16);

        Entry<String> meetings15 = new Entry<>("Algorithm course");
        meetings15.setInterval(LocalDate.of(2023, 5, 9));
        meetings15.changeStartDate(LocalDate.of(2023, 5, 9));
        meetings15.changeEndDate(LocalDate.of(2023, 5, 9));
        meetings15.changeStartTime(LocalTime.of(16,0));
        meetings15.changeEndTime(LocalTime.of(17,30));
        work.addEntry(meetings15);

        Entry<String> meetings = new Entry<>("Meeting with Mr. Panzoli");
        meetings.setInterval(LocalDate.of(2023, 5, 10));
        meetings.changeStartDate(LocalDate.of(2023, 5, 10));
        meetings.changeEndDate(LocalDate.of(2023, 5, 10));
        meetings.changeStartTime(LocalTime.of(8, 0));
        meetings.changeEndTime(LocalTime.of(12, 0));
        work.addEntry(meetings);

        Entry<String> meetings1 = new Entry<>("Meetings with Mr. Oxmaul");
        meetings1.setInterval(LocalDate.of(2023, 5, 11));
        meetings1.changeStartDate(LocalDate.of(2023, 5, 11));
        meetings1.changeEndDate(LocalDate.of(2023, 5, 11));
        meetings1.changeStartTime(LocalTime.of(14, 0));
        meetings1.changeEndTime(LocalTime.of(15, 0));
        work.addEntry(meetings1);

        Entry<String> meetings2 = new Entry<>("Meeting with colleagues");
        meetings2.setInterval(LocalDate.of(2023, 5, 12));
        meetings2.changeStartDate(LocalDate.of(2023, 5, 12));
        meetings2.changeEndDate(LocalDate.of(2023, 5, 12));
        meetings2.changeStartTime(LocalTime.of(9, 0));
        meetings2.changeEndTime(LocalTime.of(10, 0));
        work.addEntry(meetings2);

        Entry<String> meetings3 = new Entry<>("Meeting with Mr. Garcia-Gonzalez");
        meetings3.setInterval(LocalDate.of(2023, 5, 12));
        meetings3.changeStartDate(LocalDate.of(2023, 5, 12));
        meetings3.changeEndDate(LocalDate.of(2023, 5, 12));
        meetings3.changeStartTime(LocalTime.of(16, 0));
        meetings3.changeEndTime(LocalTime.of(17, 0));
        work.addEntry(meetings3);

        Entry<String> meetings4 = new Entry<>("Meeting with the chief");
        meetings4.setInterval(LocalDate.of(2023, 5, 15));
        meetings4.changeStartDate(LocalDate.of(2023, 5, 15));
        meetings4.changeEndDate(LocalDate.of(2023, 5, 15));
        meetings4.changeStartTime(LocalTime.of(10, 0));
        meetings4.changeEndTime(LocalTime.of(13, 0));
        work.addEntry(meetings4);

        Entry<String> meetings5 = new Entry<>("Training");
        meetings5.setInterval(LocalDate.of(2023, 5, 17));
        meetings5.changeStartDate(LocalDate.of(2023, 5, 17));
        meetings5.changeEndDate(LocalDate.of(2023, 5, 17));
        meetings5.changeStartTime(LocalTime.of(8, 30));
        meetings5.changeEndTime(LocalTime.of(16, 30));
        work.addEntry(meetings5);

        Entry<String> meetings6 = new Entry<>("Movie Marathon");
        meetings6.setInterval(LocalDate.of(2023, 5, 18));
        meetings6.changeStartDate(LocalDate.of(2023, 5, 18));
        meetings6.changeEndDate(LocalDate.of(2023, 5, 18));
        meetings6.changeStartTime(LocalTime.of(9, 30));
        meetings6.changeEndTime(LocalTime.of(15, 30));
        personal.addEntry(meetings6);

        Entry<String> meetings7 = new Entry<>("Training");
        meetings7.setInterval(LocalDate.of(2023, 5, 19));
        meetings7.changeStartDate(LocalDate.of(2023, 5, 19));
        meetings7.changeEndDate(LocalDate.of(2023, 5, 19));
        meetings7.changeStartTime(LocalTime.of(8, 30));
        meetings7.changeEndTime(LocalTime.of(12, 30));
        work.addEntry(meetings7);

        Entry<String> meetings8 = new Entry<>("Call with Miss. Greyrat");
        meetings8.setInterval(LocalDate.of(2023, 5, 22));
        meetings8.changeStartDate(LocalDate.of(2023, 5, 22));
        meetings8.changeEndDate(LocalDate.of(2023, 5, 22));
        meetings8.changeStartTime(LocalTime.of(9, 0));
        meetings8.changeEndTime(LocalTime.of(10, 0));
        work.addEntry(meetings8);

        Entry<String> meetings9 = new Entry<>("Meeting with colleagues");
        meetings9.setInterval(LocalDate.of(2023, 5, 23));
        meetings9.changeStartDate(LocalDate.of(2023, 5, 23));
        meetings9.changeEndDate(LocalDate.of(2023, 5, 23));
        meetings9.changeStartTime(LocalTime.of(9, 0));
        meetings9.changeEndTime(LocalTime.of(10, 0));
        work.addEntry(meetings9);

        Entry<String> meetings10 = new Entry<>("Meeting with Mr. Mike Razowski");
        meetings10.setInterval(LocalDate.of(2023, 5, 23));
        meetings10.changeStartDate(LocalDate.of(2023, 5, 23));
        meetings10.changeEndDate(LocalDate.of(2023, 5, 23));
        meetings10.changeStartTime(LocalTime.of(16, 0));
        meetings10.changeEndTime(LocalTime.of(16, 45));
        work.addEntry(meetings10);

        Entry<String> meetings11 = new Entry<>("Call with Miss. Weir");
        meetings11.setInterval(LocalDate.of(2023, 5, 10));
        meetings11.changeStartDate(LocalDate.of(2023, 5, 10));
        meetings11.changeEndDate(LocalDate.of(2023, 5, 10));
        meetings11.changeStartTime(LocalTime.of(13, 0));
        meetings11.changeEndTime(LocalTime.of(14, 30));
        work.addEntry(meetings11);

        Entry<String> meetings12 = new Entry<>("Rachel's birthday");
        meetings12.setInterval(LocalDate.of(2023, 5, 28));
        meetings12.changeStartDate(LocalDate.of(2023, 5, 28));
        meetings12.changeEndDate(LocalDate.of(2023, 5, 28));
        meetings12.changeStartTime(LocalTime.of(14, 0));
        meetings12.changeEndTime(LocalTime.of(22, 30));
        personal.addEntry(meetings12);

    }

    void setUpCalendarName() {
        CalendarSource calendarSource = calendar.getCalendarSources().get(0);
        Calendar work = calendarSource.getCalendars().get(0);
        Calendar personal = new Calendar("Personal");
        personal.setStyle(Calendar.Style.STYLE6);
        work.setName("Work");
        calendarSource.setName("My calendars");
        calendarSource.getCalendars().addAll(personal);
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
        modelMatch.saveProfiles();
        modelMatch.saveDistances();
        System.exit(0);
    }
}