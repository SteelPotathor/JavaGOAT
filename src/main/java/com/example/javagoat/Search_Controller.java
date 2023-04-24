package com.example.javagoat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.util.Objects;

public class Search_Controller {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent parent;



    @FXML
    private Pane advanced_research_panel;
    @FXML
    private ImageView icon_to_show_or_hide_advanced_research_panel;


    @FXML
    ObservableList<String> sexe = FXCollections.observableArrayList("Male", "Female");
    @FXML
    ObservableList<String> ethnicity = FXCollections.observableArrayList("White", "Black", "Asian", "Indian", "Arab");
    @FXML
    ObservableList<String> color_of_hair = FXCollections.observableArrayList("Blond", "Brown", "Black", "Red", "White", "Gray");
    @FXML
    ObservableList<String> type_of_hair = FXCollections.observableArrayList("Straight", "Wavy", "Curly");

    @FXML
    ObservableList<String> weight = FXCollections.observableArrayList("Skinny", "Medium", "Overweight");

    @FXML
    public CheckComboBox<String> sexe_choice_box ;
    @FXML
    public CheckComboBox<String> color_of_hair_choice_box ;
    @FXML
    public CheckComboBox<String> type_of_hair_choice_box ;
    @FXML
    public CheckComboBox<String> weight_choice_box ;

    @FXML
    public CheckComboBox<String> ethnicity_choice_box;

    @FXML
    private boolean advanced_research_panel_is_open = false;


    @FXML
    void initialize() {
        //add element in choice box
        sexe_choice_box.getItems().addAll(sexe);
        ethnicity_choice_box.getItems().addAll(ethnicity);
        color_of_hair_choice_box.getItems().addAll(color_of_hair);
        type_of_hair_choice_box.getItems().addAll(type_of_hair);
        weight_choice_box.getItems().addAll(weight);




    }

    @FXML
    void change_scene_to_page_dashboard(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void change_scene_to_new_profile(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("new_profile.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void change_scene_to_page_search(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("search.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void change_scene_to_page_calendar(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("calendar.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void change_scene_to_page_events(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("events.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void show_advanced_research(MouseEvent event) throws IOException {
        advanced_research_panel_is_open = !advanced_research_panel_is_open;
        if (advanced_research_panel_is_open) {
            icon_to_show_or_hide_advanced_research_panel.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("minus.png"))));


        } else {
            icon_to_show_or_hide_advanced_research_panel.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("plus.png"))));
            //add the evenement when clickec on the icon

        }

        advanced_research_panel.setVisible(advanced_research_panel_is_open);


    }



    @FXML
    void exit_script() {
        System.exit(0);
    }


}