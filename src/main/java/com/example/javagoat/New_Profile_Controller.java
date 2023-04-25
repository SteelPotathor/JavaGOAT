package com.example.javagoat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;

public class New_Profile_Controller {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent parent;
    @FXML
    private ChoiceBox<String> choicebox_ethnicity;
    @FXML
    private ChoiceBox<String> Smoker_choicebox;
    @FXML
    private ChoiceBox<String>  alcohol_choicebox;
    @FXML
    private ChoiceBox<String> Athlete_choicebox;
    @FXML
    private ChoiceBox<String> feed_choicebox;
    @FXML
    private ChoiceBox<String> bodybuild_choicebox;
    @FXML
    private ChoiceBox<String> religion_choicebox;
    @FXML
    private ChoiceBox<String> color_of_hair_choicebox;

    @FXML
    private ChoiceBox<String> hair_type_choicebox;
    @FXML
    private ChoiceBox<String> hair_length_choicebox;
    @FXML
    private CheckComboBox<String> video_games_checkcombobox;
    @FXML
    private CheckComboBox<String> miscellanious_checkcombobox;
    @FXML
    ObservableList<String> element_miscellanious = FXCollections.observableArrayList("Gamer", "Musician", "Artist", "Writer", "Other");
    @FXML
    ObservableList<String> element_video_games = FXCollections.observableArrayList("Action", "Adventure", "Arcade", "Board", "Card", "Casino", "Educational", "Family", "Music", "Puzzle", "Racing", "Role-playing", "Shooter", "Simulation", "Sports", "Strategy", "Trivia", "Other");


    @FXML
    ObservableList<String> element_hair_length = FXCollections.observableArrayList("Short", "Medium", "Long", "Other");

    @FXML
    ObservableList<String> element_hair_type = FXCollections.observableArrayList("Straight", "Wavy", "Curly", "Afro", "Other");

    @FXML
    ObservableList<String> element_hair_color = FXCollections.observableArrayList("Black", "Brown", "Blonde", "Red", "Gray", "White", "Other");

    @FXML
    ObservableList<String> element_religion = FXCollections.observableArrayList("Christian", "Muslim", "Hindu", "Buddhist", "Jewish", "Other");
    @FXML
    ObservableList<String> element_bodybuild = FXCollections.observableArrayList("Yes", "No");
    @FXML
    ObservableList<String> element_feed = FXCollections.observableArrayList("Yes", "No");

    @FXML
    ObservableList<String> element_athlete = FXCollections.observableArrayList("Yes", "No");
    @FXML
    ObservableList<String> element_alcohol = FXCollections.observableArrayList("Yes", "No");

    @FXML
    ObservableList<String> element_smoker = FXCollections.observableArrayList("Yes", "No");

    @FXML
    ObservableList<String> element_ethnicity = FXCollections.observableArrayList("White", "Black", "Asian", "Hispanic", "Other");

    @FXML
    void initialize() {
        choicebox_ethnicity.setValue("Select");
        choicebox_ethnicity.setItems(element_ethnicity);
        Smoker_choicebox.setValue("Select");
        Smoker_choicebox.setItems(element_smoker);
        alcohol_choicebox.setValue("Select");
        alcohol_choicebox.setItems(element_alcohol);
        Athlete_choicebox.setValue("Select");
        Athlete_choicebox.setItems(element_athlete);
        feed_choicebox.setValue("Select");
        feed_choicebox.setItems(element_feed);
        bodybuild_choicebox.setValue("Select");
        bodybuild_choicebox.setItems(element_bodybuild);
        religion_choicebox.setValue("Select");
        religion_choicebox.setItems(element_religion);
        color_of_hair_choicebox.setValue("Select");
        color_of_hair_choicebox.setItems(element_hair_color);
        hair_type_choicebox.setValue("Select");
        hair_type_choicebox.setItems(element_hair_type);
        hair_length_choicebox.setValue("Select");
        hair_length_choicebox.setItems(element_hair_length);
        video_games_checkcombobox.getItems().addAll(element_video_games);
        miscellanious_checkcombobox.getItems().addAll(element_miscellanious);





    }
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
    void exit_script() {
        System.exit(0);
    }


}