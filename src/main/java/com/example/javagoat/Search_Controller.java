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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Search_Controller {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent parent;
    @FXML
    private ChoiceBox<String> age_choice_box;
    @FXML
    public ChoiceBox<String> sexe_choice_box;
    @FXML
    public ChoiceBox<String> ethnicity_choice_box;
    @FXML
    public ChoiceBox<String> size_choice_box;

    @FXML
    private Pane advanced_research_panel;
    @FXML

    ObservableList<String> size = FXCollections.observableArrayList("1m60", "1m61", "1m62", "1m63", "1m64", "1m65", "1m66", "1m67", "1m68", "1m69", "1m70", "1m71", "1m72", "1m73", "1m74", "1m75", "1m76", "1m77", "1m78", "1m79", "1m80", "1m81", "1m82", "1m83", "1m84", "1m85", "1m86", "1m87", "1m88", "1m89", "1m90", "1m91", "1m92", "1m93", "1m94", "1m95", "1m96", "1m97", "1m98", "1m99", "2m00");
    @FXML
    ObservableList<String> age = FXCollections.observableArrayList("18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100");
    @FXML
    ObservableList<String> sexe = FXCollections.observableArrayList("Male", "Female");
    @FXML
    ObservableList<String> ethnicity = FXCollections.observableArrayList("White", "Black", "Asian", "Indian", "Arab");

    @FXML
    private boolean advanced_research_panel_is_open = false;


    @FXML
    void initialize() {
        age_choice_box.setValue("Age");
        age_choice_box.setItems(age);
        sexe_choice_box.setValue("Sexe");
        sexe_choice_box.setItems(sexe);
        ethnicity_choice_box.setValue("Ethnicity");
        ethnicity_choice_box.setItems(ethnicity);
        size_choice_box.setValue("Size");
        size_choice_box.setItems(size);


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
    void change_scene_to_page_historic_match(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("historic_match.fxml"));
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
    void show_advanced_research(MouseEvent event) throws IOException {
        advanced_research_panel_is_open = !advanced_research_panel_is_open;

        advanced_research_panel.setVisible(advanced_research_panel_is_open);


    }



    @FXML
    void exit_script() {
        System.exit(0);
    }


}