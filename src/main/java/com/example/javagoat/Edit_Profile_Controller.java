package com.example.javagoat;

import com.example.javagoat.back.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Edit_Profile_Controller {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent parent;
    //ALL THE PANES
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

    //Image to put in circle
    @FXML
    private Circle circle_profile_picture;
    //ALL THE CHOICEBOXES PERSONNAL INFORMATION
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

    //ALL THE CHOICEBOXES PREFERENCES INFORMATION

    @FXML
    private ChoiceBox<String> choicebox_ethnicity_preferences;
    @FXML
    private ChoiceBox<String> Smoker_choicebox_preferences;
    @FXML
    private ChoiceBox<String> alcohol_choicebox_preferences;
    @FXML
    private ChoiceBox<String> Athlete_choicebox_preferences;
    @FXML
    private ChoiceBox<String> feed_choicebox_preferences;
    @FXML
    private ChoiceBox<String> bodybuild_choicebox_preferences;
    @FXML
    private ChoiceBox<String> religion_choicebox_preferences;
    @FXML
    private ChoiceBox<String> color_of_hair_choicebox_preferences;
    @FXML
    private ChoiceBox<String> hair_type_choicebox_preferences;
    @FXML
    private ChoiceBox<String> hair_length_choicebox_preferences;
    @FXML
    private CheckComboBox<String> video_games_checkcombobox_preferences;
    @FXML
    private CheckComboBox<String> miscellanious_checkcombobox_preferences;
    @FXML
    private ChoiceBox<String> sex_choicebox_preferences;

    //ALL BUTTONS PREFERENCES INFORMATION
    public TextField textfield_age_preferences;
    public TextField textfield_size_preferences;
    //ALL THE BUTTONS
    @FXML
    private Button button_create_profile;
    @FXML
    private Button button_personnal_information;
    @FXML
    Button button_historic_of_matches;
    @FXML
    private Button button_preferences_information;
    @FXML
    private Button button_physical_information;
    @FXML
    private Button button_lifestyle_information;
    @FXML
    private Button button_passions_information;

    @FXML
    private TableView<Profile> tableview_profile;
    //ALL THE TEXTFIELDS
    public TextField textfield_first_name;
    public TextField textfield_last_name;
    public TextField textfield_age;
    public TextField textfield_size;
    public TextField textfield_qi;

    //ALL GRIDS Personnal Information
    @FXML
    private Pane grid_life_style;
    @FXML
    private Pane grid_physical_information;
    @FXML
    private Pane grid_passions;

    //ALL GRIDS Preferences Information
    @FXML
    private Pane grid_life_style_preferences;
    @FXML
    private Pane grid_physical_information_preferences;
    @FXML
    private Pane grid_passions_preferences;

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
        // Setup data for personnal info
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
        sex_choicebox.setValue("MALE/FEMALE");
        sex_choicebox.setItems(element_sex);
        video_games_checkcombobox.getItems().addAll(element_video_games);
        miscellanious_checkcombobox.getItems().addAll(element_miscellanious);


        //Adding image to circle
        Image image = new Image(getClass().getResource("profile_image.jpeg").toExternalForm(), false);
        circle_profile_picture.setFill(new ImagePattern(image));

        //Adding data to preferences

        choicebox_ethnicity_preferences.setItems(element_ethnicity);
        choicebox_ethnicity_preferences.setValue("Select");
        Smoker_choicebox_preferences.setValue("Select");
        Smoker_choicebox_preferences.setItems(element_smoker);
        alcohol_choicebox_preferences.setValue("Select");
        alcohol_choicebox_preferences.setItems(element_alcohol);
        Athlete_choicebox_preferences.setValue("Select");
        Athlete_choicebox_preferences.setItems(element_athlete);
        feed_choicebox_preferences.setValue("Select");
        feed_choicebox_preferences.setItems(element_feed);
        bodybuild_choicebox_preferences.setValue("Select");
        bodybuild_choicebox_preferences.setItems(element_bodybuild);
        religion_choicebox_preferences.setValue("Select");
        religion_choicebox_preferences.setItems(element_religion);
        color_of_hair_choicebox_preferences.setValue("Select");
        color_of_hair_choicebox_preferences.setItems(element_hair_color);
        hair_type_choicebox_preferences.setValue("Select");
        hair_type_choicebox_preferences.setItems(element_hair_type);
        hair_length_choicebox_preferences.setValue("Select");
        hair_length_choicebox_preferences.setItems(element_hair_length);
        sex_choicebox_preferences.setValue("MALE/FEMALE");
        sex_choicebox_preferences.setItems(element_sex);

        //mouse over

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
                || Objects.equals(Smoker_choicebox_preferences.getValue(), "Select")
                || Objects.equals(alcohol_choicebox_preferences.getValue(), "Select")
                || Objects.equals(Athlete_choicebox_preferences.getValue(), "Select")
                || Objects.equals(feed_choicebox_preferences.getValue(), "Select")
                || Objects.equals(bodybuild_choicebox_preferences.getValue(), "Select")
                || Objects.equals(religion_choicebox_preferences.getValue(), "Select")
                || Objects.equals(color_of_hair_choicebox_preferences.getValue(), "Select")
                || Objects.equals(hair_type_choicebox_preferences.getValue(), "Select")
                || Objects.equals(hair_length_choicebox_preferences.getValue(), "Select")
                || Objects.equals(sex_choicebox_preferences.getValue(), "MALE/FEMALE")
                || Objects.equals(choicebox_ethnicity_preferences.getValue(), "Select")
                || Objects.equals(textfield_age_preferences.getText().replace(" ", ""), "")
                || Objects.equals(textfield_size_preferences.getText().replace(" ", ""), "")
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
            Preferences preferences = new Preferences(new PhysicalAttributes(Integer.parseInt(textfield_size.getText()), PhysicalAttributes.hairColor.valueOf(color_of_hair_choicebox_preferences.getValue()), PhysicalAttributes.hairType.valueOf(hair_type_choicebox_preferences.getValue()), PhysicalAttributes.hairLength.valueOf(hair_length_choicebox_preferences.getValue())), new Biology(Integer.parseInt(textfield_age_preferences.getText()), Biology.sex.valueOf(sex_choicebox_preferences.getValue()), Biology.ethnicity.valueOf(choicebox_ethnicity_preferences.getValue()), Integer.parseInt(textfield_qi.getText())), new LifeStyle(LifeStyle.smoker.valueOf(Smoker_choicebox_preferences.getValue()), LifeStyle.athlete.valueOf(Athlete_choicebox_preferences.getValue()), LifeStyle.feed.valueOf(feed_choicebox_preferences.getValue()), LifeStyle.bodyBuild.valueOf(bodybuild_choicebox_preferences.getValue()), LifeStyle.religion.valueOf(religion_choicebox_preferences.getValue()), LifeStyle.alcohol.valueOf(alcohol_choicebox_preferences.getValue())));
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

            System.out.println(ModelProfile.profileHashMap.size());
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

    private boolean personnal_preferences = true;

    @FXML
    void change_button(MouseEvent event) {
        if (event.getSource() == button_preferences_information) {
            button_preferences_information.setStyle("-fx-background-color:  rgba(255,255,255,0.7); -fx-background-radius: 50; -fx-border-radius: 50");
            button_historic_of_matches.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50");

            button_personnal_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            button_lifestyle_information.setStyle("-fx-background-color:  rgba(255,255,255,0.7); -fx-background-radius: 50; -fx-border-radius: 50");


            button_physical_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            button_passions_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");

            grid_life_style_preferences.setVisible(true);
            grid_physical_information_preferences.setVisible(false);
            grid_passions_preferences.setVisible(false);


            grid_passions.setVisible(false);
            grid_physical_information.setVisible(false);
            grid_life_style.setVisible(false);
            personnal_preferences = false;
            tableview_profile.setVisible(false);


        } else if (event.getSource() == button_personnal_information) {
            button_personnal_information.setStyle("-fx-background-color:  rgba(255,255,255,0.7); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            button_preferences_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50");
            button_lifestyle_information.setStyle("-fx-background-color:  rgba(255,255,255,0.7); -fx-background-radius: 50; -fx-border-radius: 50");
            button_historic_of_matches.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50");


            button_physical_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            button_passions_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");


            grid_life_style_preferences.setVisible(false);
            grid_physical_information_preferences.setVisible(false);
            grid_passions_preferences.setVisible(false);

            grid_passions.setVisible(false);
            grid_physical_information.setVisible(false);
            grid_life_style.setVisible(true);
            personnal_preferences = true;
            tableview_profile.setVisible(false);
        } else {
            button_historic_of_matches.setStyle("-fx-background-color:  rgba(255,255,255,0.7); -fx-background-radius: 50; -fx-border-radius: 50");
            button_personnal_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            button_preferences_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50");

            button_physical_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            button_passions_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            button_lifestyle_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50");

            grid_life_style_preferences.setVisible(false);
            grid_physical_information_preferences.setVisible(false);
            grid_passions_preferences.setVisible(false);
            tableview_profile.setVisible(true);
        }
    }

    @FXML
    void change_style(MouseEvent event) {
        if (event.getSource() == button_lifestyle_information) {
            button_lifestyle_information.setStyle("-fx-background-color:  rgba(255,255,255,0.7); -fx-background-radius: 50; -fx-border-radius: 50");
            button_physical_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            button_passions_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            if (this.personnal_preferences) {
                grid_life_style.setVisible(true);
                grid_physical_information.setVisible(false);
                grid_passions.setVisible(false);
            } else {
                grid_life_style_preferences.setVisible(true);
                grid_physical_information_preferences.setVisible(false);
                grid_passions_preferences.setVisible(false);
            }


        } else if (event.getSource() == button_physical_information) {
            button_physical_information.setStyle("-fx-background-color:  rgba(255,255,255,0.7); -fx-background-radius: 50; -fx-border-radius: 50");
            button_lifestyle_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            button_passions_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            if (this.personnal_preferences) {
                grid_life_style.setVisible(false);
                grid_physical_information.setVisible(true);
                grid_passions.setVisible(false);
            } else {
                grid_life_style_preferences.setVisible(false);
                grid_physical_information_preferences.setVisible(true);
                grid_passions_preferences.setVisible(false);
            }
        } else {
            button_passions_information.setStyle("-fx-background-color:  rgba(255,255,255,0.7); -fx-background-radius: 50; -fx-border-radius: 50");
            button_physical_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            button_lifestyle_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            if (this.personnal_preferences) {
                grid_life_style.setVisible(false);
                grid_physical_information.setVisible(false);
                grid_passions.setVisible(true);
            } else {
                grid_life_style_preferences.setVisible(false);
                grid_physical_information_preferences.setVisible(false);
                grid_passions_preferences.setVisible(true);
            }

        }
    }

    @FXML
    void add_profile_mouse_over(MouseEvent event) {
        button_create_profile.setStyle("-fx-background-color:  rgba(255,255,255,0.7); -fx-background-radius: 50; -fx-border-radius: 50");
    }

    @FXML
    void add_profile_mouse_exit(MouseEvent event) {
        button_create_profile.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50");
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
    void import_new_image(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        //type  of file jpg png
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            circle_profile_picture.setFill(new ImagePattern(new Image(file.toURI().toString())));
            System.out.println(file.getAbsolutePath());
        }
    }

    @FXML
    public void set_profile(Profile profile) {
        textfield_first_name.setText(profile.getIdentity().firstname);
        textfield_last_name.setText(profile.getIdentity().lastname);
        textfield_age.setText(String.valueOf(profile.getIdentity().age));
        sex_choicebox.setValue(profile.getIdentity().getBsex().toString());
        textfield_size.setText(String.valueOf(profile.getPhysicalAttributes().getSize()));
        textfield_qi.setText(String.valueOf(profile.getIdentity().qi));
        circle_profile_picture.setFill(new ImagePattern(new Image(profile.getImageView().getImage().getUrl())));
        Smoker_choicebox.setValue(profile.getLifeStyle().getLSsmoker().toString());
        choicebox_ethnicity.setValue(profile.getIdentity().getBethnicity().toString());
        alcohol_choicebox.setValue(profile.getLifeStyle().getLSalcohol().toString());
        Athlete_choicebox.setValue(profile.getLifeStyle().getLSathlete().toString());
        feed_choicebox.setValue(profile.getLifeStyle().getLSfeed().toString());
        bodybuild_choicebox.setValue(profile.getLifeStyle().getLSbodyBuild().toString());
        hair_type_choicebox.setValue(profile.getPhysicalAttributes().getHairType().toString());
        color_of_hair_choicebox.setValue(profile.getPhysicalAttributes().getHairColor().toString());
        hair_length_choicebox.setValue(profile.getPhysicalAttributes().getHairLength().toString());
        religion_choicebox.setValue(profile.getLifeStyle().getLSreligion().toString());


        Smoker_choicebox_preferences.setValue(profile.getPreferences().getLifestyle().getLSsmoker().toString());
        choicebox_ethnicity_preferences.setValue(profile.getPreferences().getBiology().getBethnicity().toString());
        alcohol_choicebox_preferences.setValue(profile.getPreferences().getLifestyle().getLSalcohol().toString());
        Athlete_choicebox_preferences.setValue(profile.getPreferences().getLifestyle().getLSathlete().toString());
        feed_choicebox_preferences.setValue(profile.getPreferences().getLifestyle().getLSfeed().toString());
        bodybuild_choicebox_preferences.setValue(profile.getPreferences().getLifestyle().getLSbodyBuild().toString());
        hair_type_choicebox_preferences.setValue(profile.getPreferences().getPhysicalAttributes().getHairType().toString());
        color_of_hair_choicebox_preferences.setValue(profile.getPreferences().getPhysicalAttributes().getHairColor().toString());
        hair_length_choicebox_preferences.setValue(profile.getPreferences().getPhysicalAttributes().getHairLength().toString());
        religion_choicebox_preferences.setValue(profile.getPreferences().getLifestyle().getLSreligion().toString());
        sex_choicebox_preferences.setValue(profile.getPreferences().getBiology().getBsex().toString());
        // convert the arraylist in indexedCheckModel

        ArrayList<String> video_games_list = new ArrayList<>();
        profile.getPassion().passionVG.forEach(passion -> video_games_list.add(passion.toString()));


        for (String value : video_games_list) {

            element_video_games.indexOf(value);
            video_games_checkcombobox.getCheckModel().checkIndices(element_video_games.indexOf(value));

        }

        ArrayList<String> miscellanous_list = new ArrayList<>();

        profile.getPassion().passionM.forEach(passion -> miscellanous_list.add(passion.toString()));
        System.out.println(miscellanous_list);
        for (String s : miscellanous_list) {

            element_miscellanious.indexOf(s);
            miscellanious_checkcombobox.getCheckModel().checkIndices(element_miscellanious.indexOf(s));

        }


    }
}
