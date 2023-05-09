package com.example.javagoat;

import animatefx.animation.FadeInDownBig;
import animatefx.animation.FadeInLeftBig;
import animatefx.animation.FadeInRightBig;
import animatefx.animation.FadeInUpBig;
import com.example.javagoat.back.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import static com.example.javagoat.back.ModelMatch.stockDistance;

public class New_Profile_Controller {

    ModelMatch modelMatch = new ModelMatch();
    File file;

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

    //Image to put in circle
    @FXML
    private Circle circle_profile_picture;
    @FXML
    private Pane newImageSelector;

    //CIRCLE FOR ONGLETS

    @FXML
    private Circle circlePersonal;
    @FXML
    private Circle circle_preferences;
    @FXML
    private Circle circle_life_style;
    @FXML
    private Circle circle_physical;
    @FXML
    private Circle circle_other;


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
    private ChoiceBox<String> sex_choicebox_preferences;

    //ALL BUTTONS PREFERENCES INFORMATION
    public TextField textfield_age_preferences;
    public TextField textfield_size_preferences;
    //ALL THE BUTTONS
    @FXML
    private Button button_create_profile;
    @FXML
    private Button buttonPersonalInformation;
    @FXML
    private Button button_preferences_information;
    @FXML
    private Button button_physical_information;
    @FXML
    private Button button_lifestyle_information;
    @FXML
    private Button button_passions_information;


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
    private Pane leftRectangle;
    @FXML
    private Pane rightRectangle;
    @FXML
    public Pane topRectangle;
    @FXML
    public Pane bottomRectangle;

    @FXML
    void initialize() {
        earlyAnimations();
        setTextFieldsLimitations();
        initAllPersonnalInfo();
        initAllPreferencesInfo();
        initImage();
        initListenerCheckComboBox();
        profile_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
    }

    private void initListenerCheckComboBox() {
        video_games_checkcombobox.getCheckModel().getCheckedItems().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                circlePersonalOther();
            }
        });
        miscellanious_checkcombobox.getCheckModel().getCheckedItems().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                circlePersonalOther();
            }
        });
    }


    private void earlyAnimations() {
        new FadeInLeftBig(leftRectangle).play();
        new FadeInRightBig(bottomRectangle).play();
        new FadeInDownBig(topRectangle).play();
        new FadeInUpBig(button_create_profile).play();
    }

    private void setTextFieldsLimitations() {
        // Textfields for search bar accept only alphabetical characters
        Pattern patternLetters = Pattern.compile("[a-zA-Z]*");
        UnaryOperator<TextFormatter.Change> filterLetters = change -> {
            if (patternLetters.matcher(change.getControlNewText()).matches()) {
                return change;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatterLetters = new TextFormatter<>(filterLetters);
        TextFormatter<String> formatterLetters2 = new TextFormatter<>(filterLetters);
        textfield_first_name.setTextFormatter(formatterLetters);
        textfield_last_name.setTextFormatter(formatterLetters2);

        // Textfields for age and height accept only numbers
        Pattern patternNumbers = Pattern.compile("[0-9]*");
        UnaryOperator<TextFormatter.Change> filterNumbers = change -> {
            if (patternNumbers.matcher(change.getControlNewText()).matches()) {
                return change;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatterNumbers = new TextFormatter<>(filterNumbers);
        TextFormatter<String> formatterNumbers2 = new TextFormatter<>(filterNumbers);
        TextFormatter<String> formatterNumbers3 = new TextFormatter<>(filterNumbers);
        TextFormatter<String> formatterNumbers4 = new TextFormatter<>(filterNumbers);
        TextFormatter<String> formatterNumbers5 = new TextFormatter<>(filterNumbers);
        textfield_age.setTextFormatter(formatterNumbers);
        textfield_size.setTextFormatter(formatterNumbers2);
        textfield_qi.setTextFormatter(formatterNumbers3);
        textfield_age_preferences.setTextFormatter(formatterNumbers4);
        textfield_size_preferences.setTextFormatter(formatterNumbers5);
    }

    private void initImage() {
        Image image = new Image(getClass().getResource("DefaultImage.png").toExternalForm(), false);
        circle_profile_picture.setFill(new ImagePattern(image));
    }

    private void initAllPreferencesInfo() {
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
    }

    private void initAllPersonnalInfo() {
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
                || Objects.equals(file, null)
        ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Profile not created");
            alert.setContentText("Your profile has not been created, please fill all the fields");
            alert.showAndWait();
        } else {
            Identity identity = new Identity(Integer.parseInt(textfield_age.getText()), Biology.sex.valueOf(sex_choicebox.getValue()), Biology.ethnicity.valueOf(choicebox_ethnicity.getValue()), Integer.parseInt(textfield_qi.getText()), textfield_last_name.getText(), textfield_first_name.getText(), modelMatch.modelP.getProfileHashMap().size()+1);
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

            ImagePattern imagePattern = (ImagePattern) circle_profile_picture.getFill();
            String image = imagePattern.getImage().getUrl();
            Profile profile = new Profile(identity, physicalAttributes, lifeStyle, preferences, passion, image);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Profile created");
            alert.setContentText("Your profile has been created");
            alert.showAndWait();

            System.out.println("new profile : " + profile);

            System.out.println("ancienne database : " +modelMatch.modelP.getProfileHashMap());
            modelMatch.addProfile(profile);
            System.out.println("nouvelle db : " +modelMatch.modelP.getProfileHashMap());
            //modelMatch.saveProfiles();
            System.out.println(profile);
        }
    }

    private boolean personnal_preferences = true;

    @FXML
    void change_button(MouseEvent event) {
        if (event.getSource() == button_preferences_information) {
            button_preferences_information.setStyle("-fx-background-color:  rgba(255,255,255,0.7); -fx-background-radius: 50; -fx-border-radius: 50");

            buttonPersonalInformation.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
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


        } else {
            buttonPersonalInformation.setStyle("-fx-background-color:  rgba(255,255,255,0.7); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            button_preferences_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50");
            button_lifestyle_information.setStyle("-fx-background-color:  rgba(255,255,255,0.7); -fx-background-radius: 50; -fx-border-radius: 50");

            button_physical_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");
            button_passions_information.setStyle("-fx-background-color:  rgba(255,255,255,0.3); -fx-background-radius: 50; -fx-border-radius: 50; -fx-underline: false");

            grid_life_style_preferences.setVisible(false);
            grid_physical_information_preferences.setVisible(false);
            grid_passions_preferences.setVisible(false);

            grid_passions.setVisible(false);
            grid_physical_information.setVisible(false);
            grid_life_style.setVisible(true);
            personnal_preferences = true;
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
    void change_background_color(MouseEvent event) throws InterruptedException {
        if (event.getSource() == dashboard_pane) {
            dashboard_pane.setStyle("-fx-background-color: rgba(255, 255,255, 0.3)");
        } else if (event.getSource() == search_pane) {
            search_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        } else if (event.getSource() == calendar_pane) {
            calendar_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        }
    }

    @FXML
    void unselectNavigation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == dashboard_pane) {
            dashboard_pane.setStyle("-fx-background-color: rgba(255, 255,255, 0)");
        } else if (mouseEvent.getSource() == search_pane) {
            search_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0)");
        } else if (mouseEvent.getSource() == calendar_pane) {
            calendar_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0)");
        }
    }

    @FXML
    void import_new_image(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        //type of file jpg png
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            ImagePattern imagePattern = new ImagePattern(new Image(file.toURI().toString()));
            circle_profile_picture.setFill(imagePattern);
        }
    }

    @FXML
    private void newImageCursor() {
        newImageSelector.setCursor(Cursor.HAND);
    }

    boolean personnalLifestyle = false;
    boolean personnalPhysical = false;
    boolean personnalOther = false;
    boolean preferencesLifestyle = false;
    boolean preferencesPhysical = false;
    boolean preferencesOther = false;

    public void checkPersonalLifeStyle(ActionEvent actionEvent) {
        circlePersonalLifestyle();
    }

    private void circlePersonalLifestyle() {
        if (Smoker_choicebox.getSelectionModel().getSelectedItem() != null && feed_choicebox.getSelectionModel().getSelectedItem() != null && Athlete_choicebox.getSelectionModel().getSelectedItem() != null && alcohol_choicebox.getSelectionModel().getSelectedItem() != null) {
            if (!Smoker_choicebox.getSelectionModel().getSelectedItem().equals("Select") && !feed_choicebox.getSelectionModel().getSelectedItem().equals("Select") && !Athlete_choicebox.getSelectionModel().getSelectedItem().equals("Select") && !alcohol_choicebox.getSelectionModel().getSelectedItem().equals("Select")) {
                circle_life_style.setFill(Paint.valueOf("green"));
                personnalLifestyle = true;
            } else {
                circle_life_style.setFill(Paint.valueOf("red"));
                personnalLifestyle = false;
            }
            personalColor();
        }
    }

    public void checkPersonalPhysical(ActionEvent actionEvent) {
        circlePersonalPhysical();
    }

    private void circlePersonalPhysical() {
        if (bodybuild_choicebox.getSelectionModel().getSelectedItem() != null && choicebox_ethnicity.getSelectionModel().getSelectedItem() != null && hair_type_choicebox.getSelectionModel().getSelectedItem() != null && hair_length_choicebox.getSelectionModel().getSelectedItem() != null && color_of_hair_choicebox.getSelectionModel().getSelectedItem() != null) {
            if (!bodybuild_choicebox.getSelectionModel().getSelectedItem().equals("Select") && !choicebox_ethnicity.getSelectionModel().getSelectedItem().equals("Select") && !hair_type_choicebox.getSelectionModel().getSelectedItem().equals("Select") && !hair_length_choicebox.getSelectionModel().getSelectedItem().equals("Select") && !color_of_hair_choicebox.getSelectionModel().getSelectedItem().equals("Select")) {
                circle_physical.setFill(Paint.valueOf("green"));
                personnalPhysical = true;
            } else {
                circle_physical.setFill(Paint.valueOf("red"));
                personnalPhysical = false;
            }
            personalColor();
        }
    }

    public void checkPersonalOther(ActionEvent mouseEvent) {
        circlePersonalOther();
    }

    private void circlePersonalOther() {
        if (religion_choicebox.getSelectionModel().getSelectedItem() != null) {
            if (video_games_checkcombobox.getCheckModel().getCheckedItems().get(0) != null && miscellanious_checkcombobox.getCheckModel().getCheckedItems().get(0) != null && !religion_choicebox.getSelectionModel().getSelectedItem().equals("Select")) {
                circle_other.setFill(Paint.valueOf("green"));
                personnalOther = true;
            } else {
                circle_other.setFill(Paint.valueOf("red"));
                personnalOther = false;
            }
            personalColor();
        }
    }

    void personalColor() {
        if (personnalLifestyle && personnalPhysical && personnalOther) {
            circlePersonal.setFill(Paint.valueOf("green"));
        } else {
            circlePersonal.setFill(Paint.valueOf("red"));
        }
    }

    public void changeCircleColorNewTab(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == button_preferences_information) {
            circlePreferencesLifeStyle();
            circlePreferencesPhysical();
            circlePreferencesOther();
        } else if (mouseEvent.getSource() == buttonPersonalInformation) {
            circlePersonalLifestyle();
            circlePersonalPhysical();
            circlePersonalOther();
        }
    }

    public void checkPreferencesLifestyle(ActionEvent actionEvent) {
        circlePreferencesLifeStyle();
    }

    private void circlePreferencesLifeStyle() {
        if (Smoker_choicebox_preferences.getSelectionModel().getSelectedItem() != null && feed_choicebox_preferences.getSelectionModel().getSelectedItem() != null && Athlete_choicebox_preferences.getSelectionModel().getSelectedItem() != null && alcohol_choicebox_preferences.getSelectionModel().getSelectedItem() != null) {
            if (!Smoker_choicebox_preferences.getSelectionModel().getSelectedItem().equals("Select") && !feed_choicebox_preferences.getSelectionModel().getSelectedItem().equals("Select") && !Athlete_choicebox_preferences.getSelectionModel().getSelectedItem().equals("Select") && !alcohol_choicebox_preferences.getSelectionModel().getSelectedItem().equals("Select")) {
                circle_life_style.setFill(Paint.valueOf("green"));
                preferencesLifestyle = true;
            } else {
                circle_life_style.setFill(Paint.valueOf("red"));
                preferencesLifestyle = false;
            }
            preferencesColor();
        }
    }

    public void checkPreferencesPhysical(ActionEvent actionEvent) {
        circlePreferencesPhysical();
    }

    private void circlePreferencesPhysical() {
        if (bodybuild_choicebox_preferences.getSelectionModel().getSelectedItem() != null && choicebox_ethnicity_preferences.getSelectionModel().getSelectedItem() != null && hair_type_choicebox_preferences.getSelectionModel().getSelectedItem() != null && hair_length_choicebox_preferences.getSelectionModel().getSelectedItem() != null && color_of_hair_choicebox_preferences.getSelectionModel().getSelectedItem() != null && sex_choicebox_preferences.getSelectionModel().getSelectedItem() != null) {
            if (!bodybuild_choicebox_preferences.getSelectionModel().getSelectedItem().equals("Select") && !choicebox_ethnicity_preferences.getSelectionModel().getSelectedItem().equals("Select") && !hair_type_choicebox_preferences.getSelectionModel().getSelectedItem().equals("Select") && !hair_length_choicebox_preferences.getSelectionModel().getSelectedItem().equals("Select") && !color_of_hair_choicebox_preferences.getSelectionModel().getSelectedItem().equals("Select") && !sex_choicebox_preferences.getSelectionModel().getSelectedItem().equals("MALE/FEMALE")) {
                circle_physical.setFill(Paint.valueOf("green"));
                preferencesPhysical = true;
            } else {
                circle_physical.setFill(Paint.valueOf("red"));
                preferencesPhysical = false;
            }
            preferencesColor();
        }
    }

    public void checkPreferencesOther(KeyEvent actionEvent) {
        circlePreferencesOther();
    }

    public void checkPreferencesReligion(ActionEvent actionEvent) {
        circlePreferencesOther();
    }

    private void circlePreferencesOther() {
        if (religion_choicebox_preferences.getSelectionModel().getSelectedItem() != null) {
            if (!textfield_age_preferences.getText().equals("") && !textfield_size_preferences.getText().equals("") && !religion_choicebox_preferences.getSelectionModel().getSelectedItem().equals("Select")) {
                circle_other.setFill(Paint.valueOf("green"));
                preferencesOther = true;
            } else {
                circle_other.setFill(Paint.valueOf("red"));
                preferencesOther = false;
            }
            preferencesColor();
        }
    }

    void preferencesColor() {
        if (preferencesLifestyle && preferencesPhysical && preferencesOther) {
            circle_preferences.setFill(Paint.valueOf("green"));
        } else {
            circle_preferences.setFill(Paint.valueOf("red"));
        }
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
    void exit_script() {
        modelMatch.saveProfiles();
        modelMatch.saveDistances();
        System.exit(0);
    }

}


