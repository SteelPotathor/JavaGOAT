package com.example.javagoat;

import animatefx.animation.FadeInRightBig;
import animatefx.animation.FadeInUpBig;
import animatefx.animation.FadeOutRightBig;
import animatefx.animation.RotateInDownLeft;
import com.example.javagoat.back.ModelMatch;
import com.example.javagoat.back.Profile;
import com.example.javagoat.back.ProfileTableView;
import javafx.animation.PauseTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.example.javagoat.back.ModelProfile.correspondingName;
import static com.example.javagoat.back.ModelProfile.profileHashMap;

public class Search_Controller {

    ModelMatch modelMatch = new ModelMatch();

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent parent;
    @FXML
    private Pane dashboard_pane;
    @FXML
    private Pane profile_pane;
    @FXML
    private Pane search_pane;
    @FXML
    private Pane calendar_pane;
    @FXML
    private Pane advanced_research_panel;
    @FXML
    private Pane advanced_research_panel1;
    @FXML
    private AutoCompletionBinding<String> last_name_search_bar;
    @FXML
    private AutoCompletionBinding<String> first_name_search_bar;
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
    private TextField first_name_text_field;
    @FXML
    private TextField last_name_text_field;


    @FXML
    ObservableList<String> sexe = FXCollections.observableArrayList("Male", "Female");
    @FXML
    ObservableList<String> ethnicity = FXCollections.observableArrayList("WHITE", "BLACK", "ASIAN", "LATINO");
    @FXML
    ObservableList<String> color_of_hair = FXCollections.observableArrayList("BLONDE", "RED", "BRUNETTE", "BLACK");
    @FXML
    ObservableList<String> type_of_hair = FXCollections.observableArrayList("STRAIGHT", "WAVY", "CURLY");
    @FXML
    ObservableList<String> weight = FXCollections.observableArrayList("SKINNY", "MEDIUM", "OVERWEIGHT");

    @FXML
    public CheckComboBox<String> sexe_choice_box;
    @FXML
    public CheckComboBox<String> color_of_hair_choice_box;
    @FXML
    public CheckComboBox<String> type_of_hair_choice_box;
    @FXML
    public CheckComboBox<String> weight_choice_box;

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
        early_animations();
        initAllCheckComboBox();
        setAgeTextField(20, 59);
        setHeightTextField(100, 200);
        setTextFieldsLimitations();
        setTextFieldsAutoCompletion();
        initTableView();
        firstFillTableView();
        search_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
    }

    public void updateAfterEditOrMatch() {
        update();
        setTextFieldsAutoCompletion();
        search_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
    }

    private void early_animations() {
        new RotateInDownLeft(first_name_text_field).play();
        new RotateInDownLeft(last_name_text_field).play();
        new RotateInDownLeft(icon_to_show_or_hide_advanced_research_panel).play();
        new FadeInUpBig(tableView).play();
    }


    private void firstFillTableView() {
        tableView.getItems().clear();
        ObservableList<ProfileTableView> profiles = tableView.getItems();
        try {
            for (int i = 1; i < 150; i++) {
                Profile profile = profileHashMap.get(i);
                // The object in the tableview must match the columns attributes
                ProfileTableView profileTableView = profile.toProfileTableView();
                Pane modify = (Pane) profileTableView.actions.getChildren().get(1);
                Pane match = (Pane) profileTableView.actions.getChildren().get(3);
                modify.setStyle("-fx-cursor: HAND");
                modify.setOnMouseClicked(this::edit);
                match.setStyle("-fx-cursor: HAND");
                match.setOnMouseClicked(this::match);
                profiles.add(profileTableView);
            }
            tableView.setItems(profiles);
        } catch (NullPointerException nullPointerException) {
            tableView.setPlaceholder(new Label("No results"));
            System.out.println("NullPointerException");
        }
    }


    private void setTextFieldsAutoCompletion() {
        Set<String> lastnameList = modelMatch.modelP.getAllLastName();
        last_name_search_bar = TextFields.bindAutoCompletion(last_name_text_field, suggestionRequest -> matchingLastName(lastnameList, suggestionRequest.getUserText()));
        last_name_search_bar.setDelay(0);
        last_name_search_bar.setVisibleRowCount(5);

        Set<String> firstnameList = modelMatch.modelP.getAllFirstName();
        first_name_search_bar = TextFields.bindAutoCompletion(first_name_text_field, suggestionRequest -> matchingFirstName(firstnameList, suggestionRequest.getUserText()));
        first_name_search_bar.setDelay(0);
        first_name_search_bar.setVisibleRowCount(5);
    }

    private void initTableView() {
        avatar.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        action.setCellValueFactory(new PropertyValueFactory<>("actions"));
    }

    private void setAgeTextField(int min, int max) {
        age_min.setText(String.valueOf(min));
        age_max.setText(String.valueOf(max));
    }

    private void setHeightTextField(int min, int max) {
        height_min.setText(String.valueOf(min));
        height_max.setText(String.valueOf(max));
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
        last_name_text_field.setTextFormatter(formatterLetters);
        first_name_text_field.setTextFormatter(formatterLetters2);

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
        age_min.setTextFormatter(formatterNumbers);
        age_max.setTextFormatter(formatterNumbers2);
        height_min.setTextFormatter(formatterNumbers3);
        height_max.setTextFormatter(formatterNumbers4);
    }

    private void initAllCheckComboBox() {
        sexe_choice_box.getItems().addAll(sexe);
        sexe_choice_box.getCheckModel().getCheckedItems().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                update();
            }
        });
        ethnicity_choice_box.getItems().addAll(ethnicity);
        ethnicity_choice_box.getCheckModel().getCheckedItems().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                update();
            }
        });
        color_of_hair_choice_box.getItems().addAll(color_of_hair);
        color_of_hair_choice_box.getCheckModel().getCheckedItems().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                update();
            }
        });
        type_of_hair_choice_box.getItems().addAll(type_of_hair);
        type_of_hair_choice_box.getCheckModel().getCheckedItems().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                update();
            }
        });
        weight_choice_box.getItems().addAll(weight);
        weight_choice_box.getCheckModel().getCheckedItems().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                update();
            }
        });
    }

    private List<String> matchingLastName(Set<String> lastnameList, String userText) {
        List<String> matches = new ArrayList<>();
        for (String s : lastnameList) {
            if (s.toUpperCase().startsWith(userText.toUpperCase()) && correspondingName(s, first_name_text_field.getText())) {
                matches.add(s);
            }
        }
        return matches.stream().sorted().collect(Collectors.toList());
    }

    private List<String> matchingFirstName(Set<String> firstnameList, String userText) {
        List<String> matches = new ArrayList<>();
        for (String s : firstnameList) {
            if (s.toUpperCase().startsWith(userText.toUpperCase()) && correspondingName(last_name_text_field.getText(), s)) {
                matches.add(s);
            }
        }
        return matches.stream().sorted().collect(Collectors.toList());
    }


    public void getSearch(KeyEvent keyEvent) {
        update();
    }

    void update() {
        String lastname = last_name_text_field.getText();
        String firstname = first_name_text_field.getText();

        int min_size;
        int max_size;
        int min_age;
        int max_age;
        if (height_min.getText().equals("")) {
            min_size = 0;
        } else {
            min_size = Integer.parseInt(height_min.getText());
        }
        if (height_max.getText().equals("")) {
            max_size = 200;
        } else {
            max_size = Integer.parseInt(height_max.getText());
        }
        if (age_min.getText().equals("")) {
            min_age = 0;
        } else {
            min_age = Integer.parseInt(age_min.getText());
        }
        if (age_max.getText().equals("")) {
            max_age = 100;
        } else {
            max_age = Integer.parseInt(age_max.getText());
        }

        // Ajust the search with corresponding options
        List<String> hairColor = color_of_hair_choice_box.getCheckModel().getCheckedItems();
        List<String> hairType = type_of_hair_choice_box.getCheckModel().getCheckedItems();
        List<String> ethnicity = ethnicity_choice_box.getCheckModel().getCheckedItems();
        List<String> bodybuild = weight_choice_box.getCheckModel().getCheckedItems();
        List<String> sex = sexe_choice_box.getCheckModel().getCheckedItems();
        Set<Profile> set = modelMatch.modelP.searchProfile(firstname, lastname, min_size, max_size, min_age, max_age, hairType, hairColor, ethnicity, bodybuild, sex);

        // Clear the tableView
        tableView.getItems().clear();
        // Fill the tableView
        ObservableList<ProfileTableView> profiles = tableView.getItems();
        for (Profile profile : set) {
            ProfileTableView profileTableView = profile.toProfileTableView();
            Pane modify = (Pane) profileTableView.actions.getChildren().get(1);
            Pane match = (Pane) profileTableView.actions.getChildren().get(3);
            modify.setOnMouseClicked(this::edit);
            modify.setStyle("-fx-cursor: hand");
            match.setOnMouseClicked(this::match);
            match.setStyle("-fx-cursor: hand");
            profiles.add(profileTableView);
        }
        if (profiles.isEmpty()) {
            tableView.setPlaceholder(new Label("No profile corresponding to your search!"));
        }
        // After each search, reset the sort view
        tableView.getSortOrder().clear();
    }

    @FXML
    public void match(MouseEvent mouseEvent) {
        int i = 0;
        ProfileTableView profileTableView = tableView.getItems().get(i);
        while (i < profileHashMap.size() && !(profileTableView.actions.getChildren().get(3).equals(mouseEvent.getSource()))) {
            i++;
            profileTableView = tableView.getItems().get(i);
        }
        Profile profile = profileTableView.toProfile();
        try {
            change_scene_to_page_matching(profile);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void edit(MouseEvent mouseEvent) {
        int i = 0;
        ProfileTableView profileTableView = tableView.getItems().get(i);
        while (i < profileHashMap.size() && !(profileTableView.actions.getChildren().get(1).equals(mouseEvent.getSource()))) {
            i++;
            profileTableView = tableView.getItems().get(i);
        }
        Profile profile = profileTableView.toProfile();
        try {
            change_scene_to_page_edit(profile);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    @FXML
    void show_advanced_research(MouseEvent event) {
        advanced_research_panel_is_open = !advanced_research_panel_is_open;
        if (advanced_research_panel_is_open) {
            icon_to_show_or_hide_advanced_research_panel.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("minus.png"))));
            animateAdvancedOptionsOpening();

        } else {
            icon_to_show_or_hide_advanced_research_panel.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("plus.png"))));
            animateAdvancedOptionsClosing();

        }
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(0.65));
        PauseTransition visiblePause1 = new PauseTransition(Duration.seconds(0.65));
        if (!advanced_research_panel_is_open) {
            visiblePause.setOnFinished(e -> advanced_research_panel.setVisible(advanced_research_panel_is_open));
            visiblePause1.setOnFinished(e -> advanced_research_panel1.setVisible(advanced_research_panel_is_open));
            visiblePause.play();
            visiblePause1.play();
        } else {
            advanced_research_panel.setVisible(advanced_research_panel_is_open);
            advanced_research_panel1.setVisible(advanced_research_panel_is_open);
        }
    }

    private void animateAdvancedOptionsClosing() {
        new FadeOutRightBig(advanced_research_panel1).play();
        new FadeOutRightBig(advanced_research_panel).play();
    }

    private void animateAdvancedOptionsOpening() {
        new FadeInRightBig(advanced_research_panel1).play();
        new FadeInRightBig(advanced_research_panel).play();
    }

    @FXML
    void change_background_color(MouseEvent event) throws InterruptedException {
        if (event.getSource() == dashboard_pane) {
            dashboard_pane.setStyle("-fx-background-color: rgba(255, 255,255, 0.3)");
        } else if (event.getSource() == profile_pane) {
            profile_pane.setStyle("-fx-background-color: rgba(255, 255,255, 0.3)");
        } else if (event.getSource() == calendar_pane) {
            calendar_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        }
    }

    public void unselectNavigation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == dashboard_pane) {
            dashboard_pane.setStyle("-fx-background-color: rgba(255, 255,255, 0)");
        } else if (mouseEvent.getSource() == calendar_pane) {
            calendar_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0)");
        } else if (mouseEvent.getSource() == profile_pane) {
            profile_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0)");
        }
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
    void change_scene_to_page_edit(Profile profile) throws IOException {
        // open new window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_profile.fxml"));
        Parent root = loader.load();
        // load the controller
        Edit_Profile_Controller edit_profile_controller = loader.getController();
        edit_profile_controller.set_profile(profile);
        edit_profile_controller.setSearch_controller(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void change_scene_to_page_matching(Profile profile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("matching_profiles.fxml"));
        Parent root = loader.load();

        Matching_Profiles_Controller matching_profiles_controller = loader.getController();
        matching_profiles_controller.set_match(profile);
        matching_profiles_controller.setSearch_controller(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void exit_script() {
        modelMatch.saveProfiles();
        modelMatch.saveDistances();
        System.exit(0);
    }

}

