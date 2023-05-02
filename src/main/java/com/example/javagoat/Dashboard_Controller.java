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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    public Pane first_stat_box;
    @FXML
    public Pane second_stat_box;
    @FXML
    public Pane third_stat_box;
    @FXML
    public Pane fourth_stat_box;

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
    void change_scene_to_page_edit(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("edit_profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void change_scene_to_page_matching(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("matching_profiles.fxml"));
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
    void initialize() throws IOException {
        // test animations
        /*
        new FadeInLeft(dashboard_pane).play();
        new FadeInDown(first_stat_box).play();
        new FadeInDown(second_stat_box).play();
        new FadeInDown(third_stat_box).play();
        new FadeInDown(fourth_stat_box).play();
         */

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

        // Putting some profiles in the tableView
        for (int i = 1; i < 21; i++) {
            Profile profile = modelProfile.getProfileHashMap().get(i);
            // The object in the tableview must match the columns attributes
            ProfileTableView profileTableView = profile.toProfileTableView();
            Button modify = (Button) profileTableView.actions.getChildren().get(0);
            Button match = (Button) profileTableView.actions.getChildren().get(1);
            modify.setOnMouseClicked(this::edit);
            match.setOnMouseClicked(this::match);
            profiles.add(profileTableView);
        }
        tableView.setItems(profiles);
    }

    @FXML
    public void match(MouseEvent mouseEvent) {
        try {
            int i = 0;
            ProfileTableView profileTableView = tableView.getItems().get(i);
            while (i < 20 && !(profileTableView.actions.getChildren().get(1).equals(mouseEvent.getSource()))) {
                i++;
                profileTableView = tableView.getItems().get(i);
            }
            System.out.println(profileTableView);
            change_scene_to_page_matching(mouseEvent);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /*
            2e sol: selectionner la bonne ligne et cliquer sur un bouton (pb => n'importe quel bouton fonctionne)
            ProfileTableView profileTableView = tableView.getSelectionModel().getSelectedItem();
            System.out.println(profileTableView);         */

    @FXML
    public void edit(MouseEvent mouseEvent) {
        try {
            int i = 0;
            ProfileTableView profileTableView = tableView.getItems().get(i);
            while (i < 20 && !(profileTableView.actions.getChildren().get(0).equals(mouseEvent.getSource()))) {
                i++;
                profileTableView = tableView.getItems().get(i);
            }
            System.out.println(profileTableView);
            change_scene_to_page_edit(mouseEvent);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
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
        } else if (event.getSource() == profile_pane) {
            profile_pane.setStyle("-fx-background-color: rgba(255, 255,255, 0.3)");
        } else if (event.getSource() == search_pane) {
            search_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        } else if (event.getSource() == calendar_pane) {
            calendar_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        } else if (event.getSource() == events_pane) {
            events_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        }
        //modify the color of the panel from event
    }

    @FXML
    void exit_script() {
        System.exit(0);
    }


}
