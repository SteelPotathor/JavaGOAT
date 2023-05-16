package com.example.javagoat;

import animatefx.animation.FadeInDown;
import animatefx.animation.FadeInRightBig;
import animatefx.animation.FadeInUpBig;
import animatefx.animation.Swing;
import com.example.javagoat.back.ModelMatch;
import com.example.javagoat.back.ModelNotification;
import com.example.javagoat.back.Profile;
import com.example.javagoat.back.ProfileTableView;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.javagoat.back.ModelHistoMatch.getMatchCount;

public class Dashboard_Controller {

    private final int total = 60;
    @FXML
    public TableView<ProfileTableView> tableView;
    @FXML
    public TableColumn<ProfileTableView, Circle> priority;
    @FXML
    public TableColumn<ProfileTableView, String> image;
    @FXML
    public TableColumn<ProfileTableView, String> firstname;
    @FXML
    public TableColumn<ProfileTableView, String> lastname;
    @FXML
    public TableColumn<ProfileTableView, Integer> age;
    @FXML
    public TableColumn<ProfileTableView, String> gender;
    @FXML
    public TableColumn<ProfileTableView, String> actions;
    @FXML
    public ListView<String> list_view_notification;
    @FXML
    public Pane first_stat_box;
    @FXML
    public Pane second_stat_box;
    @FXML
    public Pane third_stat_box;
    @FXML
    public Pane fourth_stat_box;
    @FXML
    public TitledPane notificationsBox;
    ModelMatch modelMatch = new ModelMatch();
    ModelNotification modelNotification = new ModelNotification();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent parent;
    @FXML
    private Pane coucoumike;
    @FXML
    private Pane dashboard_pane;
    @FXML
    private Pane profile_pane;
    @FXML
    private Pane search_pane;
    @FXML
    private Pane calendar_pane;
    @FXML
    private Label label_today_matches;
    @FXML
    private Label label_total_profiles;
    @FXML
    private Label label_today_new_profiles;
    @FXML
    private Label label_today_events;

    @FXML
    void initialize() {
        earlyAnimation(0.1);
        initTableView();
        fillTableView();
        fillNotifications();
        initStats();

        dashboard_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
    }

    public void updateAfterEditOrMatch() {
        fillNotifications();
        fillTableView();
        initStats();
    }

    private void initStats() {
        label_total_profiles.setText(String.valueOf(modelMatch.getModelP().getProfileHashMap().size()));
        label_today_matches.setText(String.valueOf(getMatchCount()));
        label_today_new_profiles.setText(String.valueOf(ModelMatch.getCreateCounter()));
    }

    public void fillTableView() {
        tableView.getItems().clear();
        ObservableList<ProfileTableView> profiles = tableView.getItems();
        PriorityQueue<Profile> priorityQueue = modelMatch.modelP.toPriorityQueue();

        // Putting some profiles in the tableView
        int cpt = 0;
        while (!priorityQueue.isEmpty() && cpt < total) {
            Profile profile = priorityQueue.poll();
            ProfileTableView profileTableView = profile.toProfileTableView();
            Pane modify = (Pane) profileTableView.actions.getChildren().get(1);
            Pane match = (Pane) profileTableView.actions.getChildren().get(3);
            modify.setStyle("-fx-cursor: HAND");
            modify.setOnMouseClicked(this::edit);
            match.setStyle("-fx-cursor: HAND");
            match.setOnMouseClicked(this::match);
            profiles.add(profileTableView);
            cpt++;
        }
        tableView.setItems(profiles);
    }

    private void initTableView() {
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        image.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        actions.setCellValueFactory(new PropertyValueFactory<>("actions"));
    }


    public void earlyAnimation(double delay) {
        new FadeInDown(first_stat_box).play();

        second_stat_box.setVisible(false);
        PauseTransition visiblePauseSecondVisible = new PauseTransition(Duration.seconds(delay));
        visiblePauseSecondVisible.setOnFinished(e -> second_stat_box.setVisible(true));
        visiblePauseSecondVisible.play();
        PauseTransition visiblePauseSecondAnimation = new PauseTransition(Duration.seconds(delay - 0.005));
        visiblePauseSecondAnimation.setOnFinished(e -> new FadeInDown(second_stat_box).play());
        visiblePauseSecondAnimation.play();

        third_stat_box.setVisible(false);
        PauseTransition visiblePauseThirdVisible = new PauseTransition(Duration.seconds(delay * 2));
        visiblePauseThirdVisible.setOnFinished(e -> third_stat_box.setVisible(true));
        visiblePauseThirdVisible.play();
        PauseTransition visiblePauseThirdAnimation = new PauseTransition(Duration.seconds((delay - 0.005) * 2));
        visiblePauseThirdAnimation.setOnFinished(e -> new FadeInDown(third_stat_box).play());
        visiblePauseThirdAnimation.play();

        fourth_stat_box.setVisible(false);
        PauseTransition visiblePauseFourthVisible = new PauseTransition(Duration.seconds(delay * 3));
        visiblePauseFourthVisible.setOnFinished(e -> fourth_stat_box.setVisible(true));
        visiblePauseFourthVisible.play();
        PauseTransition visiblePauseFourthAnimation = new PauseTransition(Duration.seconds((delay - 0.005) * 3));
        visiblePauseFourthAnimation.setOnFinished(e -> new FadeInDown(fourth_stat_box).play());
        visiblePauseFourthAnimation.play();

        new FadeInUpBig(tableView).play();

        new Swing(label_total_profiles).play();
        PauseTransition secondAnimationText = new PauseTransition(Duration.seconds(delay - 0.005));
        secondAnimationText.setOnFinished(e -> new Swing(label_today_matches).play());
        secondAnimationText.play();
        PauseTransition thirdAnimationText = new PauseTransition(Duration.seconds((delay - 0.005) * 2));
        thirdAnimationText.setOnFinished(e -> new Swing(label_today_new_profiles).play());
        thirdAnimationText.play();
        PauseTransition fourthAnimationText = new PauseTransition(Duration.seconds((delay - 0.005) * 3));
        fourthAnimationText.setOnFinished(e -> new Swing(label_today_events).play());
        fourthAnimationText.play();

        new FadeInRightBig(notificationsBox).play();
        coucoumike.setVisible(false);
        PauseTransition imagemike = new PauseTransition(Duration.seconds(4));
        imagemike.setOnFinished(e -> coucoumike.setVisible(true));
        imagemike.play();


    }

    @FXML
    public void match(MouseEvent mouseEvent) {
        int i = 0;
        ProfileTableView profileTableView = tableView.getItems().get(i);
        while (i < total && !(profileTableView.actions.getChildren().get(3).equals(mouseEvent.getSource()))) {
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
        while (i < total && !(profileTableView.actions.getChildren().get(1).equals(mouseEvent.getSource()))) {
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


    void fillNotifications() {
        list_view_notification.getItems().clear();
        int i = 0;
        Iterator<Map.Entry<Date, String>> iterator = ModelNotification.stockNotification.entrySet().iterator();
        while (i < 30 && iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            list_view_notification.getItems().add(simpleDateFormat.format(entry.getKey()) + " : " + entry.getValue().toString());
            i++;
        }
    }


    @FXML
    void change_background_color(MouseEvent event) throws InterruptedException {
        if (event.getSource() == profile_pane) {
            profile_pane.setStyle("-fx-background-color: rgba(255, 255,255, 0.3)");
        } else if (event.getSource() == search_pane) {
            search_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        } else if (event.getSource() == calendar_pane) {
            calendar_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0.3)");
        }
    }

    public void unselectNavigation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == calendar_pane) {
            calendar_pane.setStyle("-fx-background-color: rgba(255, 255,255, 0)");
        } else if (mouseEvent.getSource() == search_pane) {
            search_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0)");
        } else if (mouseEvent.getSource() == profile_pane) {
            profile_pane.setStyle("-fx-background-color:  rgba(255, 255,255, 0)");
        }
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
    void change_scene_to_page_edit(Profile event) throws IOException {
        // open new window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProfile/edit_profile.fxml"));
        Parent root = loader.load();
        // load the controller
        Edit_Profile_Controller edit_profile_controller = loader.getController();
        edit_profile_controller.set_profile(event);
        edit_profile_controller.setDashboard_controller(this);
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void change_scene_to_page_matching(Profile profile) throws IOException {
        // open new window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Match/matching_profiles.fxml"));
        Parent root = loader.load();

        Matching_Profiles_Controller matching_profiles_controller = loader.getController();
        matching_profiles_controller.set_match(profile);
        matching_profiles_controller.setDashboard_controller(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
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
        modelMatch.saveProfiles();
        modelMatch.saveDistances();
        System.exit(0);
    }
}
