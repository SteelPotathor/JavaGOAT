package com.example.javagoat;

import animatefx.animation.FadeInDownBig;
import animatefx.animation.FadeInLeftBig;
import animatefx.animation.FadeInRightBig;
import animatefx.animation.FadeInUpBig;
import com.example.javagoat.Model.*;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import static com.example.javagoat.Model.ModelProfile.profileHashMap;

public class Edit_Profile_Controller {

    //ALL THE TEXTFIELDS
    public TextField textfield_first_name;
    public TextField textfield_last_name;
    public TextField textfield_age;
    public TextField textfield_size;
    public TextField textfield_qi;
    public Label feedbackText;
    @FXML
    public Pane leftRectangle;
    @FXML
    public Pane rightRectangle;
    @FXML
    public Pane bottomRectangle;
    @FXML
    public Pane topRectangle;
    ModelNotification modelNotification = new ModelNotification();
    ModelMatch modelMatch = new ModelMatch();
    int idProfile;
    private ArrayList<TupleHistoHashMap> list;

    //ALL THE OBSERVABLELISTS
    @FXML
    ObservableList<String> element_sex = FormInput.names(Biology.sex.class);
    @FXML
    ObservableList<String> element_miscellanious = FormInput.names(Passion.miscellaneous.class);
    @FXML
    ObservableList<String> element_video_games = FormInput.names(Passion.video_games.class);

    //ALL THE CHOICEBOXES PREFERENCES INFORMATION
    @FXML
    ObservableList<String> element_hair_length = FormInput.names(PhysicalAttributes.hairLength.class);
    @FXML
    ObservableList<String> element_hair_type = FormInput.names(PhysicalAttributes.hairType.class);
    @FXML
    ObservableList<String> element_hair_color = FormInput.names(PhysicalAttributes.hairColor.class);
    @FXML
    ObservableList<String> element_religion = FormInput.names(LifeStyle.religion.class);
    @FXML
    ObservableList<String> element_bodybuild = FormInput.names(LifeStyle.bodyBuild.class);
    @FXML
    ObservableList<String> element_feed = FormInput.names(LifeStyle.feed.class);
    @FXML
    ObservableList<String> element_athlete = FormInput.names(LifeStyle.athlete.class);
    @FXML
    ObservableList<String> element_alcohol = FormInput.names(LifeStyle.alcohol.class);
    @FXML
    ObservableList<String> element_smoker = FormInput.names(LifeStyle.smoker.class);
    @FXML
    ObservableList<String> element_ethnicity = FormInput.names(Biology.ethnicity.class);

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

    private Dashboard_Controller dashboard_controller;
    private Search_Controller search_controller;
    private boolean personnal_preferences = true;
    private PreferencesForm preferencesForm;

    public void setDashboard_controller(Dashboard_Controller dashboard_controller) {
        this.dashboard_controller = dashboard_controller;
    }

    public void setSearch_controller(Search_Controller search_controller) {
        this.search_controller = search_controller;
    }

    // Reference of the picture saved with the profile (the URL of the displayed image is not reliable)
    private String selectedImage = ImageLoader.DEFAULT_PROFILE_IMAGE;

    @FXML
    void initialize() {
        earlyAnimations();
        setTextFieldsLimitations();
        initAllPersonnalInfo();
        preferencesForm = new PreferencesForm(grid_life_style_preferences, grid_physical_information_preferences,
                grid_passions_preferences, () -> {
        });
        initImage();
        initTableView();
    }

    private void positive(MouseEvent mouseEvent) {
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

    private void negative(MouseEvent mouseEvent) {
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
            dashboard_controller.updateAfterEditOrMatch();
        }
    }

    private void updateSearch() throws IOException {
        if (search_controller != null) {
            search_controller.updateAfterEditOrMatch();
        }
    }

    public void desactivateThumbsNeg(ProfileTableViewHistoric profileTableViewHistoric) {
        int i = 0;
        while (i < list.size() && list.get(i).getId() != profileTableViewHistoric.getId()) {
            i++;
        }
        TupleHistoHashMap tuple = list.get(i);
        tuple.setVoted(true);
        tuple.setVote(1);

        changeCursor(profileTableViewHistoric);
        Pane thumbsDown = (Pane) profileTableViewHistoric.actions.getChildren().get(3);
        ImageView imageDown = (ImageView) thumbsDown.getChildren().get(0);
        imageDown.setVisible(false);
    }

    public void desactivateThumbsPos(ProfileTableViewHistoric profileTableViewHistoric) {
        int i = 0;
        while (i < list.size() && list.get(i).getId() != profileTableViewHistoric.getId()) {
            i++;
        }
        TupleHistoHashMap tuple = list.get(i);
        tuple.setVoted(true);
        tuple.setVote(2);
        changeCursor(profileTableViewHistoric);
        Pane thumbsUp = (Pane) profileTableViewHistoric.actions.getChildren().get(1);
        ImageView imageUp = (ImageView) thumbsUp.getChildren().get(0);
        imageUp.setVisible(false);
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
        Image image = new Image(Objects.requireNonNull(getClass().getResource("GlobalImages/DefaultImage.png")).toExternalForm(), false);
        circle_profile_picture.setFill(new ImagePattern(image));
    }

    static void initInfo(ChoiceBox<String> smoker_choicebox_preferences, ObservableList<String> element_smoker, ChoiceBox<String> alcohol_choicebox_preferences, ObservableList<String> element_alcohol, ChoiceBox<String> athlete_choicebox_preferences, ObservableList<String> element_athlete, ChoiceBox<String> feed_choicebox_preferences, ObservableList<String> element_feed, ChoiceBox<String> bodybuild_choicebox_preferences, ObservableList<String> element_bodybuild, ChoiceBox<String> religion_choicebox_preferences, ObservableList<String> element_religion) {
        smoker_choicebox_preferences.setValue("Select");
        smoker_choicebox_preferences.setItems(element_smoker);
        alcohol_choicebox_preferences.setValue("Select");
        alcohol_choicebox_preferences.setItems(element_alcohol);
        athlete_choicebox_preferences.setValue("Select");
        athlete_choicebox_preferences.setItems(element_athlete);
        feed_choicebox_preferences.setValue("Select");
        feed_choicebox_preferences.setItems(element_feed);
        bodybuild_choicebox_preferences.setValue("Select");
        bodybuild_choicebox_preferences.setItems(element_bodybuild);
        religion_choicebox_preferences.setValue("Select");
        religion_choicebox_preferences.setItems(element_religion);
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
        initInfo(feed_choicebox, element_feed, bodybuild_choicebox, element_bodybuild, religion_choicebox, element_religion, color_of_hair_choicebox, element_hair_color, hair_type_choicebox, element_hair_type, hair_length_choicebox, element_hair_length);
        sex_choicebox.setValue("MALE/FEMALE");
        sex_choicebox.setItems(element_sex);
        video_games_checkcombobox.getItems().addAll(element_video_games);
        miscellanious_checkcombobox.getItems().addAll(element_miscellanious);
    }

    private void setTextFieldsLimitations() {
        // Textfields for search bar accept only alphabetical characters
        Pattern patternLetters = Pattern.compile(FormInput.NAME_PATTERN);
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
        Pattern patternNumbers = Pattern.compile(FormInput.NUMBER_PATTERN);
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
        textfield_age.setTextFormatter(formatterNumbers);
        textfield_size.setTextFormatter(formatterNumbers2);
        textfield_qi.setTextFormatter(formatterNumbers3);
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
        ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Profile not edited");
            alert.setContentText("Your profile has not been edited, please fill all the fields");
            alert.showAndWait();
        } else {
            List<String> problems = FormInput.checkPersonNumbers(textfield_age.getText(), textfield_size.getText(), textfield_qi.getText());
            problems.addAll(preferencesForm.problems());
            if (!problems.isEmpty()) {
                FormInput.showProblems("Profile not edited", problems);
                return;
            }
            Identity identity = new Identity(Integer.parseInt(textfield_age.getText()), Biology.sex.valueOf(sex_choicebox.getValue()), Biology.ethnicity.valueOf(choicebox_ethnicity.getValue()), Integer.parseInt(textfield_qi.getText()), textfield_last_name.getText(), textfield_first_name.getText(), idProfile);
            PhysicalAttributes physicalAttributes = new PhysicalAttributes(Integer.parseInt(textfield_size.getText()), PhysicalAttributes.hairColor.valueOf(color_of_hair_choicebox.getValue()), PhysicalAttributes.hairType.valueOf(hair_type_choicebox.getValue()), PhysicalAttributes.hairLength.valueOf(hair_length_choicebox.getValue()));
            LifeStyle lifeStyle = new LifeStyle(LifeStyle.smoker.valueOf(Smoker_choicebox.getValue()), LifeStyle.athlete.valueOf(Athlete_choicebox.getValue()), LifeStyle.feed.valueOf(feed_choicebox.getValue()), LifeStyle.bodyBuild.valueOf(bodybuild_choicebox.getValue()), LifeStyle.religion.valueOf(religion_choicebox.getValue()), LifeStyle.alcohol.valueOf(alcohol_choicebox.getValue()));
            Preferences preferences = preferencesForm.read();
            Passion passion = new Passion();
            ObservableList<String> video_games_checked = video_games_checkcombobox.getCheckModel().getCheckedItems();
            ObservableList<String> miscellaneous_checked = miscellanious_checkcombobox.getCheckModel().getCheckedItems();
            for (String video_games : video_games_checked)
                passion.passionVG.add(Passion.video_games.valueOf(video_games));
            for (String miscellaneous : miscellaneous_checked)
                passion.passionM.add(Passion.miscellaneous.valueOf(miscellaneous));

            Profile profile = modelMatch.modelP.getProfileHashMap().get(idProfile);

            profile.setIdentity(identity);
            profile.setPhysicalAttributes(physicalAttributes);
            profile.setLifeStyle(lifeStyle);
            profile.setPreferences(preferences);
            profile.setPassion(passion);
            profile.setImageView(selectedImage);

            // Recomputes the vectors and the distances (they used to keep the values of the old profile)
            modelMatch.editProfile(profile);
            Stage stage = (Stage) button_create_profile.getScene().getWindow();
            stage.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Profile edited");
            alert.setContentText("Your profile has been successfully edited");
            alert.showAndWait();

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
            selectedImage = file.toURI().toString();
            circle_profile_picture.setFill(new ImagePattern(ImageLoader.profileImage(selectedImage)));
        }

    }

    @FXML
    public void set_profile(Profile profile) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        idProfile = profile.getIdentity().getNoId();
        textfield_first_name.setText(profile.getIdentity().firstname);
        textfield_last_name.setText(profile.getIdentity().lastname);
        textfield_age.setText(String.valueOf(profile.getIdentity().age));
        sex_choicebox.setValue(profile.getIdentity().getBsex().toString());
        textfield_size.setText(String.valueOf(profile.getPhysicalAttributes().getSize()));
        textfield_qi.setText(String.valueOf(profile.getIdentity().qi));
        selectedImage = profile.getImageView();
        circle_profile_picture.setFill(new ImagePattern(ImageLoader.profileImage(selectedImage)));
        if (profile.getTotal() <= 1) {
            feedbackText.setText("Feedback : " + decimalFormat.format(profile.getRatio()) + " %    " + profile.getTotal() + " vote");
        } else {
            feedbackText.setText("Feedback : " + decimalFormat.format(profile.getRatio()) + " %    " + profile.getTotal() + " votes");
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

        preferencesForm.show(profile.getPreferences());
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
        HashMap<TupleHistoHashMap, Date> hashMap = profile.getModelHisto().getStockHisto();
        list = new ArrayList<>();

        for (TupleHistoHashMap key : hashMap.keySet()) {
            ProfileTableViewHistoric profileTableViewHistoric = key.toProfileTableViewHistoric();

            Pane thumbsUp = (Pane) profileTableViewHistoric.actions.getChildren().get(1);


            Pane thumbsDown = (Pane) profileTableViewHistoric.actions.getChildren().get(3);


            if (!profileTableViewHistoric.isVoted()) {

                thumbsUp.setStyle("-fx-cursor: HAND");
                thumbsUp.setOnMouseClicked(this::positive);

                thumbsDown.setStyle("-fx-cursor: HAND");
                thumbsDown.setOnMouseClicked(this::negative);

            }

            if (profileTableViewHistoric.isVoted() && profileTableViewHistoric.getVote() == 2) {
                thumbsUp.setVisible(false);
            }

            if (profileTableViewHistoric.isVoted() && profileTableViewHistoric.getVote() == 1) {
                thumbsDown.setVisible(false);
            }

            list.add(key);
            profiles.add(profileTableViewHistoric);
        }
        if (profiles.isEmpty()) {
            tableview_profile.setPlaceholder(new Label("No matches!"));
        }
        tableview_profile.setItems(profiles);

    }

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

}
