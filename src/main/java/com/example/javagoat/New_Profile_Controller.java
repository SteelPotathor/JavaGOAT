package com.example.javagoat;

import com.example.javagoat.back.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.util.Objects;

public class New_Profile_Controller {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent parent;
    //ALL THE CHOICEBOXES
    @FXML
    private ChoiceBox<String> choicebox_ethnicity;
    @FXML
    private ChoiceBox<String> Smoker_choicebox;
    @FXML
    private ChoiceBox<String> alcohol_choicebox;
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
    private ChoiceBox<String> sex_choicebox;


    //ALL THE BUTTONS
    @FXML
    private Button button_create_profile;

    //ALL THE TEXTFIELDS
    public TextField textfield_first_name;
    public TextField textfield_last_name;
    public TextField textfield_age;
    public TextField textfield_size;
    public TextField textfield_qi;


    //ALL THE OBSERVABLELISTS
    @FXML
    ObservableList<String> element_sex = FXCollections.observableArrayList("MALE", "FEMALE");
    @FXML
    ObservableList<String> element_miscellanious = FXCollections.observableArrayList("BIKE", "MOVIES", "ANIME", "MANGA", "NETFLIX", "COOKING", "RUNNING", "DANCING", "YOGA", "TRAVELING", "MODE", "GYM", "BASKET", "TENNIS", "SOCCER", "MARTIAL_ARTS", "LITTERATURE", "DIVING", "WRITING", "PROGRAMMATION", "PHOTOGRAPHY", "GARDENING", "HISTORY", "GEOGRAPHY", "VACATIONS", "MUSIC", "PAINTING", "PORN", "SLEEP", "ANIMALS", "SPIRITUALITY", "GRINDING", "PLAYING_WITH_KIDS", "CHESS", "COSPLAY", "FURRIES", "PSYCHOLOGY", "PHILOSOPHY", "SCIENCES", "THEATHER", "VLOGGING", "CLIMBING", "NUDISM", "DECORATIONS");
    @FXML
    ObservableList<String> element_video_games = FXCollections.observableArrayList("LEAGUE_OF_LEGENDS", "VALORANT", "MINECRAFT", "TERRARIA", "CALL_OF_DUTY", "DESTINY_2", "DIABLO_3", "WORLD_OF_WARCRAFT", "HEARTHSTONE", "BATTLEFIELD", "APEX_LEGENDS", "DOTA_2", "CS_GO", "SUPER_SMASH_BROS", "FORTNITE", "JACKBOX_PARTY", "DIVINE_KNOCKOUT", "AMOGUS", "STREET_FIGHTER", "ARK", "CRAB_GAME", "BRAWLHALLA", "MONSTER_HUNTER", "DRAGON_QUEST", "GTA", "GENSHIN_IMPACT"
            , "SEA_OF_THIEVES", "DEAD_BY_DAYLIGHT", "THE_DOORS", "FALL_GUYS", "ROCKET_LEAGUE", "WARFRAME", "PUBG", "VALHEIM", "RUST");
    @FXML
    ObservableList<String> element_hair_length = FXCollections.observableArrayList("LONG", "HALF_LONG", "SHORT");
    @FXML
    ObservableList<String> element_hair_type = FXCollections.observableArrayList("STRAIGHT", "WAVY", "CURLY");
    @FXML
    ObservableList<String> element_hair_color = FXCollections.observableArrayList("BLONDE", "RED", "BRUNETTE", "BLACK");
    @FXML
    ObservableList<String> element_religion = FXCollections.observableArrayList("MUSLIM", "CHRISTIAN", "JEWISH", "BUDDHIST", "HINDUIST", "OTHER", "ATHEIST");
    @FXML
    ObservableList<String> element_bodybuild = FXCollections.observableArrayList("SKINNY", "MEDIUM", "OVERWEIGHT");
    @FXML
    ObservableList<String> element_feed = FXCollections.observableArrayList("VEGAN", "VEGETARIAN", "OMNIVORE", "FLEXIVORE");
    @FXML
    ObservableList<String> element_athlete = FXCollections.observableArrayList("SPORTY", "SEDENTARY", "LAZY");
    @FXML
    ObservableList<String> element_alcohol = FXCollections.observableArrayList("NEVER", "OCCASIONNALY", "REGULARLY", "DAILY");
    @FXML
    ObservableList<String> element_smoker = FXCollections.observableArrayList("NEVER", "OCCASIONNALY", "REGULARLY", "DAILY");
    @FXML
    ObservableList<String> element_ethnicity = FXCollections.observableArrayList("WHITE", "BLACK", "ASIAN", "LATINO");

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
        sex_choicebox.setValue("MALE/FEMALE");
        sex_choicebox.setItems(element_sex);

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


    @FXML
    void validate_profil() {
        if (Objects.equals(choicebox_ethnicity.getValue(), "Select")
                || Objects.equals(Smoker_choicebox.getValue(), "Select")
                || Objects.equals(alcohol_choicebox.getValue(), "Select")
                || Objects.equals(Athlete_choicebox.getValue(), "Select")
                || Objects.equals(feed_choicebox.getValue(), "Select")
                || Objects.equals(bodybuild_choicebox.getValue(), "Select")
                || Objects.equals(religion_choicebox.getValue(), "Select")
                || Objects.equals(color_of_hair_choicebox.getValue(), "Select")
                || Objects.equals(hair_type_choicebox.getValue(), "Select")
                || Objects.equals(hair_length_choicebox.getValue(), "Select")
                || Objects.equals(sex_choicebox.getValue(), "MALE/FEMALE")
                || Objects.equals(video_games_checkcombobox.getCheckModel().getCheckedItems().size(), 0)
                || Objects.equals(miscellanious_checkcombobox.getCheckModel().getCheckedItems().size(), 0)
                || Objects.equals(textfield_first_name.getText().replace(" ", ""), "")
                || Objects.equals(textfield_last_name.getText().replace(" ", ""), "")
                || Objects.equals(textfield_age.getText().replace(" ", ""), "")
                || Objects.equals(textfield_size.getText().replace(" ", ""), "")
                || Objects.equals(textfield_qi.getText().replace(" ", ""), "")
        ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Profile not created");
            alert.setContentText("Your profile has not been created, please fill all the fields");
            alert.showAndWait();
        } else {
            Identity identity = new Identity(Integer.parseInt(textfield_age.getText()), Biology.sex.valueOf(sex_choicebox.getValue()), Biology.ethnicity.valueOf(choicebox_ethnicity.getValue()), Integer.parseInt(textfield_qi.getText()), textfield_last_name.getText(), textfield_first_name.getText());
            PhysicalAttributes physicalAttributes = new PhysicalAttributes(Integer.parseInt(textfield_size.getText()), PhysicalAttributes.hairColor.valueOf(color_of_hair_choicebox.getValue()), PhysicalAttributes.hairType.valueOf(hair_type_choicebox.getValue()), PhysicalAttributes.hairLength.valueOf(hair_length_choicebox.getValue()));
            LifeStyle lifeStyle = new LifeStyle(LifeStyle.smoker.valueOf(Smoker_choicebox.getValue()), LifeStyle.athlete.valueOf(Athlete_choicebox.getValue()), LifeStyle.feed.valueOf(feed_choicebox.getValue()), LifeStyle.bodyBuild.valueOf(bodybuild_choicebox.getValue()), LifeStyle.religion.valueOf(religion_choicebox.getValue()), LifeStyle.alcohol.valueOf(alcohol_choicebox.getValue()));
            Preferences preferences = new Preferences();
            preferences.setRandomPreferences();
            Passion passion = new Passion();
            ObservableList<String> video_games_checked = video_games_checkcombobox.getCheckModel().getCheckedItems();
            ObservableList<String> miscellaneous_checked = miscellanious_checkcombobox.getCheckModel().getCheckedItems();
            for (String video_games : video_games_checked)
                passion.passionVG.add(Passion.video_games.valueOf(video_games));
            for (String miscellaneous : miscellaneous_checked)
                passion.passionM.add(Passion.miscellaneous.valueOf(miscellaneous));

            ImageView imageView = new ImageView(new Image("file:src/main/java/com/example/javagoat/back/images/kumalala.jpg"));
            Profile profile = new Profile(identity, physicalAttributes, lifeStyle, preferences, passion, imageView);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Profile created");
            alert.setContentText("Your profile has been created");
            alert.showAndWait();

            ModelMatch modelMatch = new ModelMatch();
            System.out.println(profile);

            modelMatch.addProfile(profile);

            System.out.println(modelMatch.modelP.profileHashMap.size());
        }

    }

    @FXML
    void write_string_only_firstname(KeyEvent event) {
        var key = event.getCode();
        if (!key.isLetterKey()) {
            textfield_first_name.setText("");
        }
    }

    @FXML
    void write_string_only_lastname(KeyEvent event) {
        var key = event.getCode();
        if (!key.isLetterKey()) {
            textfield_last_name.setText("");
        }
    }

    @FXML
    void write_number_only_age(KeyEvent event) {
        var key = event.getCode();
        if (!key.isDigitKey()) {
            textfield_age.setText("");
        }
    }

    @FXML
    void write_number_only_size(KeyEvent event) {
        var key = event.getCode();
        if (!key.isDigitKey()) {
            textfield_size.setText("");
        }
    }

    @FXML
    void write_number_only_qi(KeyEvent event) {
        var key = event.getCode();
        if (!key.isDigitKey()) {
            textfield_qi.setText("");
        }
    }


}