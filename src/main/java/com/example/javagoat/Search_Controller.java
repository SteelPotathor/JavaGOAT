package com.example.javagoat;

import com.example.javagoat.back.ModelProfile;
import com.example.javagoat.back.Profile;
import com.example.javagoat.back.ProfileTableView;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.util.Objects;
import java.util.Observable;

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
    private Pane advanced_research_panel1;
    @FXML
    private ImageView icon_to_show_or_hide_advanced_research_panel;
    @FXML
    private TextField age_min;
    @FXML
    private TextField age_max;
    @FXML
    private TextField height_min;
    @FXML
    private TextField height_max;


    @FXML
    ObservableList<String> sexe = FXCollections.observableArrayList("Male", "Female");
    @FXML
    ObservableList<String> ethnicity = FXCollections.observableArrayList("WHITE","BLACK","ASIAN","LATINO");
    @FXML
    ObservableList<String> color_of_hair = FXCollections.observableArrayList("BLONDE","RED","BRUNETTE","BLACK");
    @FXML
    ObservableList<String> type_of_hair = FXCollections.observableArrayList("STRAIGHT","WAVY","CURLY");

    @FXML
    ObservableList<String> weight = FXCollections.observableArrayList("SKINNY","MEDIUM","OVERWEIGHT");

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
    private TableView<ProfileTableView> tableView;
    @FXML
    private TableColumn<ProfileTableView, Integer> avatar;
    @FXML
    private TableColumn<ProfileTableView, String> firstname;
    @FXML
    private TableColumn<ProfileTableView, String> lastname;
    @FXML
    private TableColumn<ProfileTableView, Integer> age;
    @FXML
    private TableColumn<ProfileTableView, String> gender;
    @FXML
    private TableColumn<ProfileTableView, HBox> action;


    @FXML
    void initialize() {
        //add element in choice box
        age_min.setOnKeyReleased(this::change);
        sexe_choice_box.getItems().addAll(sexe);
        sexe_choice_box.setOnMouseEntered(this::t);
        sexe_choice_box.getItems().addListener((ListChangeListener<String>) change -> System.out.println(change));
        ethnicity_choice_box.getItems().addAll(ethnicity);
        color_of_hair_choice_box.getItems().addAll(color_of_hair);
        type_of_hair_choice_box.getItems().addAll(type_of_hair);
        weight_choice_box.getItems().addAll(weight);

        avatar.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        action.setCellValueFactory(new PropertyValueFactory<>("actions"));
        ObservableList<ProfileTableView> profiles = tableView.getItems();

        ModelProfile modelProfile = new ModelProfile();
        System.out.println(modelProfile.getProfileHashMap());

        for (int i = 1; i < 501; i++) {
            Profile profile = modelProfile.getProfileHashMap().get(i);
            // The object in the tableview must match the columns attributes
            ProfileTableView profileTableView = profile.toProfileTableView();
            profiles.add(profileTableView);
        }
    }

    private void t(MouseEvent mouseEvent) {
        System.out.println("oui");
    }

    private void change(KeyEvent keyEvent) {
        System.out.println(keyEvent);
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
        advanced_research_panel1.setVisible(advanced_research_panel_is_open);


    }
    @FXML
    void write_string_only_age_min(KeyEvent event) {
        var key = event.getCode();
        if (!key.isDigitKey()) {
            age_min.setText("");
        }
    }
    @FXML
    void write_string_only_age_max(KeyEvent event) {
        var key = event.getCode();
        if (!key.isDigitKey()) {
            age_max.setText("");
        }
    }

    @FXML
    void write_string_only_height_min(KeyEvent event) {
        var key = event.getCode();
        if (!key.isDigitKey()) {
            height_min.setText("");
        }
    }
    @FXML
    void write_string_only_height_max(KeyEvent event) {
        var key = event.getCode();
        if (!key.isDigitKey()) {
            height_max.setText("");
        }
    }

    @FXML
    void exit_script() {
        System.exit(0);
    }


}