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

    void add() {

    }


    @FXML
    void exit_script() {
        System.exit(0);
    }


}
