package com.example.javagoat;

import animatefx.animation.FadeInDownBig;
import animatefx.animation.FadeInLeftBig;
import animatefx.animation.FadeInRightBig;
import animatefx.animation.FadeInUpBig;
import com.example.javagoat.Model.*;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class New_Profile_Controller {

//ALL THE TEXTFIELDS
    public TextField textfield_first_name;
    public TextField textfield_last_name;
    public TextField textfield_age;
    public TextField textfield_size;
    public TextField textfield_qi;
    @FXML
    public Pane topRectangle;
    @FXML
    public Pane bottomRectangle;
    ModelMatch modelMatch = new ModelMatch();
    File file;

    //CIRCLE FOR ONGLETS
    //ALL THE OBSERVABLELISTS
    @FXML
    ObservableList<String> element_sex = FormInput.names(Biology.sex.class);
    @FXML
    ObservableList<String> element_miscellanious = FormInput.names(Passion.miscellaneous.class);
    @FXML
    ObservableList<String> element_video_games = FormInput.names(Passion.video_games.class);
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
    private PreferencesForm preferencesForm;
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
    private Pane leftRectangle;
    @FXML
    private Pane rightRectangle;
    private boolean personnal_preferences = true;

    @FXML
    void initialize() {
        earlyAnimations();
        setTextFieldsLimitations();
        initAllPersonnalInfo();
        preferencesForm = new PreferencesForm(grid_life_style_preferences, grid_physical_information_preferences,
                grid_passions_preferences, this::refreshCircles);
        preferencesForm.show(new Preferences());
        initImage();
        initCircleListeners();
        refreshCircles();
        profile_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
    }

    // The status dots follow every change of the form (they used to be updated on some clicks only)
    private void initCircleListeners() {
        for (TextField field : List.of(textfield_first_name, textfield_last_name, textfield_age, textfield_size, textfield_qi)) {
            field.textProperty().addListener((obs, old, value) -> refreshCircles());
        }
        for (ChoiceBox<String> box : List.of(sex_choicebox, Smoker_choicebox, alcohol_choicebox, Athlete_choicebox,
                feed_choicebox, bodybuild_choicebox, religion_choicebox, color_of_hair_choicebox, hair_type_choicebox,
                hair_length_choicebox, choicebox_ethnicity)) {
            box.valueProperty().addListener((obs, old, value) -> refreshCircles());
        }
        video_games_checkcombobox.getCheckModel().getCheckedItems().addListener((InvalidationListener) obs -> refreshCircles());
        miscellanious_checkcombobox.getCheckModel().getCheckedItems().addListener((InvalidationListener) obs -> refreshCircles());
    }

    private void earlyAnimations() {
        new FadeInLeftBig(leftRectangle).play();
        new FadeInRightBig(bottomRectangle).play();
        new FadeInDownBig(topRectangle).play();
        new FadeInUpBig(button_create_profile).play();
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

    private void initImage() {
        Image image = new Image(getClass().getResource("GlobalImages/DefaultImage.png").toExternalForm(), false);
        circle_profile_picture.setFill(new ImagePattern(image));
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
        Edit_Profile_Controller.initInfo(feed_choicebox, element_feed, bodybuild_choicebox, element_bodybuild, religion_choicebox, element_religion, color_of_hair_choicebox, element_hair_color, hair_type_choicebox, element_hair_type, hair_length_choicebox, element_hair_length);
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
        ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Profile not created");
            alert.setContentText("Your profile has not been created, please fill all the fields");
            alert.showAndWait();
        } else {
            List<String> problems = FormInput.checkPersonNumbers(textfield_age.getText(), textfield_size.getText(), textfield_qi.getText());
            problems.addAll(preferencesForm.problems());
            if (!problems.isEmpty()) {
                FormInput.showProblems("Profile not created", problems);
                return;
            }
            Identity identity = new Identity(Integer.parseInt(textfield_age.getText()), Biology.sex.valueOf(sex_choicebox.getValue()), Biology.ethnicity.valueOf(choicebox_ethnicity.getValue()), Integer.parseInt(textfield_qi.getText()), textfield_last_name.getText(), textfield_first_name.getText(), ModelMatch.nextId());
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

            // The picture is optional: without one, the profile uses the default picture
            String image = file != null ? file.toURI().toString() : ImageLoader.DEFAULT_PROFILE_IMAGE;
            Profile profile = new Profile(identity, physicalAttributes, lifeStyle, preferences, passion, image);
            profile.setCreated(LocalDate.now());
            modelMatch.addProfile(profile);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Profile created");
            alert.setContentText("Your profile has been created");
            alert.showAndWait();
            resetForm();
        }
    }

    // Reloads an empty form: clicking "create" twice used to create the same person twice
    private void resetForm() {
        try {
            Parent fresh = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewProfile/new_profile.fxml")));
            button_create_profile.getScene().setRoot(fresh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            refreshCircles();


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
            refreshCircles();
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

    // ------------------------------------------------------------------ status dots

    private static boolean chosen(ChoiceBox<String> box) {
        return box.getValue() != null && box.getItems().contains(box.getValue()); // "Select" is not an item
    }

    private static boolean filled(TextField field) {
        return field.getText() != null && !field.getText().isBlank();
    }

    /** Name, age, size, IQ (within the accepted bounds) and sex: the left part of the form. */
    boolean identityComplete() {
        return filled(textfield_first_name) && filled(textfield_last_name) && chosen(sex_choicebox)
                && FormInput.checkPersonNumbers(textfield_age.getText(), textfield_size.getText(), textfield_qi.getText()).isEmpty();
    }

    boolean lifestyleComplete() {
        return chosen(Smoker_choicebox) && chosen(Athlete_choicebox) && chosen(feed_choicebox) && chosen(alcohol_choicebox);
    }

    boolean physicalComplete() {
        return chosen(bodybuild_choicebox) && chosen(hair_type_choicebox) && chosen(choicebox_ethnicity)
                && chosen(hair_length_choicebox) && chosen(color_of_hair_choicebox);
    }

    boolean otherComplete() {
        return chosen(religion_choicebox) && !video_games_checkcombobox.getCheckModel().getCheckedItems().isEmpty()
                && !miscellanious_checkcombobox.getCheckModel().getCheckedItems().isEmpty();
    }

    boolean personalComplete() {
        return identityComplete() && lifestyleComplete() && physicalComplete() && otherComplete();
    }

    /**
     * "Personal" and "Preferences" dots: is each tab complete. The lifestyle / physical / other dots are shared by
     * the two tabs and show the tab currently displayed (they used to keep the colours of the other tab).
     */
    void refreshCircles() {
        if (preferencesForm == null) {
            return; // called by a listener during initialize()
        }
        setGreen(circlePersonal, personalComplete());
        setGreen(circle_preferences, preferencesForm.problems().isEmpty());
        if (personnal_preferences) {
            setGreen(circle_life_style, lifestyleComplete());
            setGreen(circle_physical, physicalComplete());
            setGreen(circle_other, otherComplete());
        } else {
            setGreen(circle_life_style, preferencesForm.lifestyleValid());
            setGreen(circle_physical, preferencesForm.physicalValid());
            setGreen(circle_other, preferencesForm.otherValid());
        }
    }

    private static void setGreen(Circle circle, boolean ok) {
        circle.setFill(Paint.valueOf(ok ? "green" : "red"));
    }

    @FXML
    void change_scene_to_page_dashboard(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard/home.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void change_scene_to_new_profile(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewProfile/new_profile.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void change_scene_to_page_search(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Search/search.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void change_scene_to_page_calendar(MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Calendar/calendar.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void exit_script() {
        ApplicationStart.saveAndExit();
    }

}


