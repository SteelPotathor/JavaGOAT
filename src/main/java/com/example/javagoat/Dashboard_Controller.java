package com.example.javagoat;

import com.example.javagoat.back.ModelProfile;
import com.example.javagoat.back.Profile;
import com.example.javagoat.back.ProfileTableView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Dashboard_Controller {


    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent parent;

    @FXML
    private TableView<ProfileTableView> tableView;
    @FXML
    private TableColumn<ProfileTableView, Integer> priority;
    @FXML
    private TableColumn<ProfileTableView, String> image;
    @FXML
    private TableColumn<ProfileTableView, String> firstname;
    @FXML
    private TableColumn<ProfileTableView, String> lastname;
    @FXML
    private TableColumn<ProfileTableView, Integer> age;
    @FXML
    private TableColumn<ProfileTableView, String> gender;
    @FXML
    private TableColumn<ProfileTableView, String> actions;
    @FXML
    private VBox notif;
    @FXML
    private Pane dashboard_pane;
    @FXML
    private Pane profile_pane;
    @FXML
    private Pane search_pane;
    @FXML
    private Pane calendar_pane;
    @FXML
    private Pane events_pane;

    @FXML
    void change_scene_to_page_dashboard(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void change_scene_to_new_profile(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("new_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void change_scene_to_page_search(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("search.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void change_scene_to_page_calendar(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("calendar.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void change_scene_to_page_events(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("events.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        image.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        actions.setCellValueFactory(new PropertyValueFactory<>("actions"));
        ObservableList<ProfileTableView> profiles = tableView.getItems();
        ModelProfile modelProfile = new ModelProfile();
        System.out.println(modelProfile.getProfileHashMap());

        for (int i = 1; i < 21; i++) {
            Profile profile = modelProfile.getProfileHashMap().get(i);
            // The object in the tableview must match the columns attributes
            ProfileTableView profileTableView = profile.toProfileTableView();
            profiles.add(profileTableView);
        }
        tableView.setItems(profiles);
    }

    @FXML
    void change_background_color(MouseEvent event) throws InterruptedException {
        dashboard_pane.setStyle("-fx-background-color:  linear-gradient(from 0.0% 100.0% to 100.0% 100.0%, #197ac2 0.0%, #197ac2 0.6711%, #6925ba 100.0%)");
        profile_pane.setStyle("-fx-background-color:  linear-gradient(from 0.0% 100.0% to 100.0% 100.0%, #197ac2 0.0%, #197ac2 0.6711%, #6925ba 100.0%)");
        search_pane.setStyle("-fx-background-color:  linear-gradient(from 0.0% 100.0% to 100.0% 100.0%, #197ac2 0.0%, #197ac2 0.6711%, #6925ba 100.0%)");
        calendar_pane.setStyle("-fx-background-color:  linear-gradient(from 0.0% 100.0% to 100.0% 100.0%, #197ac2 0.0%, #197ac2 0.6711%, #6925ba 100.0%)");
        events_pane.setStyle("-fx-background-color:  linear-gradient(from 0.0% 100.0% to 100.0% 100.0%, #197ac2 0.0%, #197ac2 0.6711%, #6925ba 100.0%)");
        if (event.getSource() == dashboard_pane) {

            dashboard_pane.setStyle("-fx-background-color: rgba(255, 255,255, 0.3)");
        }
        else if (event.getSource() == profile_pane) {
            profile_pane.setStyle("-fx-background-color: rgba(255, 255,255, 0.3)");
        }
        else if (event.getSource() == search_pane) {
            search_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        }
        else if (event.getSource() == calendar_pane) {
            calendar_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        }
        else if (event.getSource() == events_pane) {
            events_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        }
        //modify the color of the panel from event
    }

    @FXML
    void exit_script() {
        System.exit(0);
    }


}
