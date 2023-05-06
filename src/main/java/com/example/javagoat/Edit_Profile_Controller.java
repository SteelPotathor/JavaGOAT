package com.example.javagoat;

import animatefx.animation.FadeInDownBig;
import animatefx.animation.FadeInLeftBig;
import animatefx.animation.FadeInRightBig;
import animatefx.animation.FadeInUpBig;
import com.example.javagoat.back.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import static com.example.javagoat.back.ModelProfile.profileHashMap;

public class Edit_Profile_Controller {

    ModelNotification modelNotification = new ModelNotification();
    ModelMatch modelMatch = new ModelMatch();
    int idProfile;

    //Image to put in circle
    @FXML
    private Circle circle_profile_picture;
    @FXML
    private Pane newImageSelector;

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
    private Button button_historic_of_matches;
    @FXML
    private Button button_preferences_information;
    @FXML
    private Button button_physical_information;
    @FXML
    private Button button_lifestyle_information;
    @FXML
    private Button button_passions_information;

    @FXML
    private TableView<ProfileTableViewHistoric> tableview_profile;
    //ALL THE TEXTFIELDS
    public TextField textfield_first_name;
    public TextField textfield_last_name;
    public TextField textfield_age;
    public TextField textfield_size;
    public TextField textfield_qi;
    public Label feedbackText;

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
    private TableColumn<ProfileTableViewHistoric, Integer> avatar;
    @FXML
    private TableColumn<ProfileTableViewHistoric, String> firstname;
    @FXML
    private TableColumn<ProfileTableViewHistoric, String> lastname;
    @FXML
    private TableColumn<ProfileTableViewHistoric, Integer> age;
    @FXML
    private TableColumn<ProfileTableViewHistoric, String> gender;
    @FXML
    private TableColumn<ProfileTableViewHistoric, HBox> feedback;

    @FXML
    public Pane leftRectangle;
    @FXML
    public Pane rightRectangle;
    @FXML
    public Pane bottomRectangle;
    @FXML
    public Pane topRectangle;

    private Dashboard_Controller dashboard_controller;
    private Search_Controller search_controller;

    public void setDashboard_controller(Dashboard_Controller dashboard_controller) {
        this.dashboard_controller = dashboard_controller;
    }

    public void setSearch_controller(Search_Controller search_controller) {
        this.search_controller = search_controller;
    }

    @FXML
    void initialize() {
        earlyAnimations();
        setTextFieldsLimitations();
        initAllPersonnalInfo();
        initAllPreferencesInfo();
        initImage();
        initTableView();
    }

    private void positif(MouseEvent mouseEvent) {
        try {
            int i = 0;
            ProfileTableViewHistoric profileTableViewHistoric = tableview_profile.getItems().get(i);
            while (i < tableview_profile.getItems().size() && !(profileTableViewHistoric.actions.getChildren().get(1).equals(mouseEvent.getSource()))) {
                i++;
                profileTableViewHistoric = tableview_profile.getItems().get(i);
            }
            if (!profileTableViewHistoric.voted) {
                desactivateThumbsNeg(profileTableViewHistoric);
                profileTableViewHistoric.toProfile().positif++;
                profileTableViewHistoric.toProfile().total++;
                modelNotification.addNotification(new Date(), profileTableViewHistoric.lastname + " " + profileTableViewHistoric.firstname + " received a positive feedback from " + profileHashMap.get(idProfile).getIdentity().getLastname() + " " + profileHashMap.get(idProfile).getIdentity().getFirstname());
                notificationDashboard();
                updateSearch();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private void negatif(MouseEvent mouseEvent) {
        try {
            int i = 0;
            ProfileTableViewHistoric profileTableViewHistoric = tableview_profile.getItems().get(i);
            while (i < tableview_profile.getItems().size() && !(profileTableViewHistoric.actions.getChildren().get(3).equals(mouseEvent.getSource()))) {
                i++;
                profileTableViewHistoric = tableview_profile.getItems().get(i);
            }
            if (!profileTableViewHistoric.voted) {
                desactivateThumbsPos(profileTableViewHistoric);
                profileTableViewHistoric.toProfile().total++;
                modelNotification.addNotification(new Date(), profileTableViewHistoric.lastname + " " + profileTableViewHistoric.firstname + " received a negative feedback from " + profileHashMap.get(idProfile).getIdentity().getLastname() + " " + profileHashMap.get(idProfile).getIdentity().getFirstname());

                notificationDashboard();
                updateSearch();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void notificationDashboard() throws IOException {
        if (dashboard_controller != null) {
            dashboard_controller.initializeWithoutAnimations();
        }
    }

    private void updateSearch() throws IOException {
        if (search_controller != null) {
            search_controller.initializeWithoutAnimations();
        }
    }

    public void desactivateThumbsNeg(ProfileTableViewHistoric profileTableViewHistoric) {
        changeCursor(profileTableViewHistoric);
        Pane thumbsDown = (Pane) profileTableViewHistoric.actions.getChildren().get(3);
        ImageView imageDown = (ImageView) thumbsDown.getChildren().get(0);
        imageDown.setVisible(false);
        profileTableViewHistoric.voted = true;
    }

    public void desactivateThumbsPos(ProfileTableViewHistoric profileTableViewHistoric) {
        changeCursor(profileTableViewHistoric);
        Pane thumbsUp = (Pane) profileTableViewHistoric.actions.getChildren().get(1);
        ImageView imageUp = (ImageView) thumbsUp.getChildren().get(0);
        imageUp.setVisible(false);
        profileTableViewHistoric.voted = true;
    }

    public void changeCursor(ProfileTableViewHistoric profileTableViewHistoric) {
        Pane thumbsUp = (Pane) profileTableViewHistoric.actions.getChildren().get(1);
        thumbsUp.setStyle("-fx-cursor: NONE");
        Pane thumbsDown = (Pane) profileTableViewHistoric.actions.getChildren().get(3);
        thumbsDown.setStyle("-fx-cursor: NONE");
    }

    private void initTableView() {
        avatar.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        feedback.setCellValueFactory(new PropertyValueFactory<>("actions"));
    }

    private void earlyAnimations() {
        new FadeInLeftBig(leftRectangle).play();
        new FadeInRightBig(bottomRectangle).play();
        new FadeInDownBig(topRectangle).play();
        new FadeInUpBig(button_create_profile).play();
    }

    private void initImage() {
        Image image = new Image(getClass().getResource("profile_image.jpeg").toExternalForm(), false);
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


    @FXML
    void validate_profil() throws IOException {
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
            alert.setHeaderText("Profile not edited");
            alert.setContentText("Your profile has not been edited, please fill all the fields");
            alert.showAndWait();
        } else {
            // DO NOT CREATE NEW IDENTITY OBJECT
            Identity identity = modelMatch.modelP.getProfileHashMap().get(idProfile).getIdentity();
            identity.setLastname(textfield_last_name.getText());
            identity.setFirstname(textfield_first_name.getText());
            identity.setAge(Integer.parseInt(textfield_age.getText()));
            identity.setBsex(Biology.sex.valueOf(sex_choicebox.getValue()));
            identity.setBethnicity(Biology.ethnicity.valueOf(choicebox_ethnicity.getValue()));
            identity.setQi(Integer.parseInt(textfield_qi.getText()));
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
            Image image = imagePattern.getImage();
            ImageView imageView = new ImageView(image);

            Profile profile = modelMatch.modelP.getProfileHashMap().get(idProfile);

            profile.setPhysicalAttributes(physicalAttributes);
            profile.setLifeStyle(lifeStyle);
            profile.setPreferences(preferences);
            profile.setPassion(passion);
            profile.setImageView(imageView);

            modelMatch.editProfile(profile);
            Stage stage = (Stage) button_create_profile.getScene().getWindow();
            stage.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Profile edited");
            alert.setContentText("Your profile has been successfully edited");
            alert.showAndWait();

            modelNotification.addNotification(new Date(), "Profile of " + profile.getIdentity().getLastname() + " " + profile.getIdentity().getFirstname() + " has been successfully edited");
            notificationDashboard();
            updateSearch();
        }
    }

    @FXML
    void import_new_image(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        //type of file jpg png
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            circle_profile_picture.setFill(new ImagePattern(new Image(file.toURI().toString())));
        }

    }

    @FXML
    public void set_profile(Profile profile) {
        idProfile = profile.getIdentity().getNoId();
        textfield_first_name.setText(profile.getIdentity().firstname);
        textfield_last_name.setText(profile.getIdentity().lastname);
        textfield_age.setText(String.valueOf(profile.getIdentity().age));
        sex_choicebox.setValue(profile.getIdentity().getBsex().toString());
        textfield_size.setText(String.valueOf(profile.getPhysicalAttributes().getSize()));
        textfield_qi.setText(String.valueOf(profile.getIdentity().qi));
        circle_profile_picture.setFill(new ImagePattern(new Image(profile.getImageView().getImage().getUrl())));
        if (profile.getTotal() <= 1) {
            feedbackText.setText("Feedback : " + profile.getRatio() + " %    " + profile.getTotal() + " vote");
        } else {
            feedbackText.setText("Feedback : " + profile.getRatio() + " %    " + profile.getTotal() + " votes");
        }
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
        textfield_age_preferences.setText(String.valueOf(profile.getPreferences().getBiology().getAge()));
        textfield_size_preferences.setText(String.valueOf(profile.getPreferences().getPhysicalAttributes().getSize()));
        // convert the arraylist in indexedCheckModel

        ArrayList<String> video_games_list = new ArrayList<>();
        profile.getPassion().passionVG.forEach(passion -> video_games_list.add(passion.toString()));

        for (String value : video_games_list) {
            element_video_games.indexOf(value);
            video_games_checkcombobox.getCheckModel().checkIndices(element_video_games.indexOf(value));
        }

        ArrayList<String> miscellanous_list = new ArrayList<>();
        profile.getPassion().passionM.forEach(passion -> miscellanous_list.add(passion.toString()));

        for (String s : miscellanous_list) {
            element_miscellanious.indexOf(s);
            miscellanious_checkcombobox.getCheckModel().checkIndices(element_miscellanious.indexOf(s));
        }

        ObservableList<ProfileTableViewHistoric> profiles = tableview_profile.getItems();
        HashMap<Integer, Date> hashMap = profile.modelHisto.stockHisto;
        for (Integer key : hashMap.keySet()) {
            Profile profileHash = modelMatch.modelP.getProfileHashMap().get(key);
            ProfileTableViewHistoric profileTableViewHistoric = profileHash.toProfileTableViewHistoric();
            Pane thumbsUp = (Pane) profileTableViewHistoric.actions.getChildren().get(1);
            thumbsUp.setStyle("-fx-cursor: HAND");
            thumbsUp.setOnMouseClicked(this::positif);
            Pane thumbsDown = (Pane) profileTableViewHistoric.actions.getChildren().get(3);
            thumbsDown.setStyle("-fx-cursor: HAND");
            thumbsDown.setOnMouseClicked(this::negatif);
            profiles.add(profileTableViewHistoric);
        }
        if (profiles.isEmpty()) {
            tableview_profile.setPlaceholder(new Label("No matches!"));
        }
        tableview_profile.setItems(profiles);

    }

    private boolean personnal_preferences = true;

    @FXML
    void change_button(MouseEvent event) {
        if (event.getSource() == button_preferences_information) {
            button_passions_information.setVisible(true);
            button_lifestyle_information.setVisible(true);
            button_physical_information.setVisible(true);
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
            button_passions_information.setVisible(true);
            button_lifestyle_information.setVisible(true);
            button_physical_information.setVisible(true);
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
            button_passions_information.setVisible(false);
            button_lifestyle_information.setVisible(false);
            button_physical_information.setVisible(false);
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

    public void newImageCursor(MouseEvent mouseEvent) {
        newImageSelector.setCursor(Cursor.HAND);
    }

    @FXML
    void exit_script() {
        //exit the window
    }
}
