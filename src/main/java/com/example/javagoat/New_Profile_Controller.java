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
    ObservableList<String> element_miscellanious = FXCollections.observableArrayList("BIKE","MOVIES","ANIME","MANGA","NETFLIX","COOKING","RUNNING","DANCING","YOGA","TRAVELING","MODE","GYM","BASKET","TENNIS","SOCCER","MARTIAL_ARTS","LITTERATURE","DIVING","WRITING","PROGRAMMATION","PHOTOGRAPHY","GARDENING","HISTORY","GEOGRAPHY","VACATIONS","MUSIC","PAINTING","PORN","SLEEP","ANIMALS","SPIRITUALITY","GRINDING","PLAYING_WITH_KIDS","CHESS","COSPLAY","FURRIES","PSYCHOLOGY","PHILOSOPHY","SCIENCES","THEATHER","VLOGGING","CLIMBING","NUDISM","DECORATIONS");
    @FXML
    ObservableList<String> element_video_games = FXCollections.observableArrayList("LEAGUE_OF_LEGENDS","VALORANT","MINECRAFT","TERRARIA","CALL_OF_DUTY","DESTINY_2","DIABLO_3","WORLD_OF_WARCRAFT","HEARTHSTONE","BATTLEFIELD","APEX_LEGENDS","DOTA_2","CS_GO","SUPER_SMASH_BROS","FORTNITE","JACKBOX_PARTY","DIVINE_KNOCKOUT","AMOGUS","STREET_FIGHTER","ARK","CRAB_GAME","BRAWLHALLA","MONSTER_HUNTER","DRAGON_QUEST","GTA","GENSHIN_IMPACT"
            ,"SEA_OF_THIEVES","DEAD_BY_DAYLIGHT","THE_DOORS","FALL_GUYS","ROCKET_LEAGUE","WARFRAME","PUBG","VALHEIM","RUST");


    @FXML
    ObservableList<String> element_hair_length = FXCollections.observableArrayList("LONG","HALF_LONG","SHORT");

    @FXML
    ObservableList<String> element_hair_type = FXCollections.observableArrayList("STRAIGHT","WAVY","CURLY");

    @FXML
    ObservableList<String> element_hair_color = FXCollections.observableArrayList("BLONDE","RED","BRUNETTE","BLACK");

    @FXML
    ObservableList<String> element_religion = FXCollections.observableArrayList("MUSLIM","CHRISTIAN","JEWISH","BUDDHIST","HINDUIST","OTHER","ATHEIST");
    @FXML
    ObservableList<String> element_bodybuild = FXCollections.observableArrayList("SKINNY","MEDIUM","OVERWEIGHT");
    @FXML
    ObservableList<String> element_feed = FXCollections.observableArrayList("VEGAN","VEGETARIAN","OMNIVORE","FLEXIVORE");

    @FXML
    ObservableList<String> element_athlete = FXCollections.observableArrayList("SPORTY","SEDENTARY","LAZY");
    @FXML
    ObservableList<String> element_alcohol = FXCollections.observableArrayList("NEVER","OCCASIONNALY","REGULARLY","DAILY");

    @FXML
    ObservableList<String> element_smoker = FXCollections.observableArrayList("NEVER","OCCASIONNALY","REGULARLY","DAILY");

    @FXML
    ObservableList<String> element_ethnicity = FXCollections.observableArrayList("WHITE","BLACK","ASIAN","LATINO");

    @FXML
    void initialize() {
        choicebox_ethnicity.setItems(element_ethnicity);
        choicebox_ethnicity.setValue("Select");
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