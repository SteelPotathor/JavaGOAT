package com.example.javagoat;

import animatefx.animation.FadeInDownBig;
import animatefx.animation.FadeInUpBig;
import animatefx.animation.RotateInDownLeft;
import com.example.javagoat.Model.ImageLoader;
import com.example.javagoat.Model.ModelMatch;
import com.example.javagoat.Model.Profile;
import com.example.javagoat.Model.matching.Matcher;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Matching_Profiles_Controller {


    public ModelMatch modelMatch = new ModelMatch();

    public ImageView arrowBlueSE;

    public ImageView arrowRedSE;

    public ImageView arrowBlueS;

    public ImageView arrowRedS;

    public ImageView arrowBlueSW;

    public ImageView arrowRedSW;

    public ImageView arrowRedNE;

    public ImageView arrowBlueNE;

    public ImageView arrowRedNW;

    public ImageView arrowBlueNW;

    public Profile profileSelected;

    public List<Matcher.Suggestion> suggestions;

    public List<Profile> profileList;

    @FXML
    private Circle circleProfilePictureSE;
    @FXML
    private Circle circleProfilePictureNW;
    @FXML
    private Circle circleProfilePictureSW;
    @FXML
    private Circle circleProfilePictureS;
    @FXML
    private Circle circleProfilePictureCenter;
    @FXML
    private Circle circleProfilePictureNE;

    @FXML
    private Label lastNameSE;
    @FXML
    private Label lastNameNW;
    @FXML
    private Label lastNameSW;
    @FXML
    private Label lastNameS;
    @FXML
    private Label lastNameNE;

    @FXML
    private Label firstNameSE;
    @FXML
    private Label firstNameNW;
    @FXML
    private Label firstNameSW;
    @FXML
    private Label firstNameS;
    @FXML
    private Label firstNameNE;

    @FXML
    private Label ageSE;
    @FXML
    private Label ageNW;
    @FXML
    private Label ageSW;
    @FXML
    private Label ageS;
    @FXML
    private Label ageNE;

    @FXML
    private Label passionSE;
    @FXML
    private Label passionNW;
    @FXML
    private Label passionSW;
    @FXML
    private Label passionS;
    @FXML
    private Label passionNE;

    @FXML
    private Label labelBlueSE;
    @FXML
    private Label labelRedSE;
    @FXML
    private Label labelBlueNW;
    @FXML
    private Label labelRedNW;
    @FXML
    private Label labelBlueSW;
    @FXML
    private Label labelRedSW;
    @FXML
    private Label labelBlueS;
    @FXML
    private Label labelRedS;
    @FXML
    private Label labelBlueNE;
    @FXML
    private Label labelRedNE;

    private Dashboard_Controller dashboard_controller;

    private Search_Controller search_controller;


    public void animation() {
        new FadeInDownBig(circleProfilePictureCenter).play();
        new FadeInDownBig(circleProfilePictureNW).play();
        new FadeInDownBig(circleProfilePictureNE).play();
        new FadeInUpBig(circleProfilePictureSW).play();
        new FadeInUpBig(circleProfilePictureSE).play();
        new FadeInUpBig(circleProfilePictureS).play();

        new RotateInDownLeft(arrowBlueSE).play();
        new RotateInDownLeft(arrowRedSE).play();
        new RotateInDownLeft(arrowBlueS).play();
        new RotateInDownLeft(arrowRedS).play();
        new RotateInDownLeft(arrowBlueSW).play();
        new RotateInDownLeft(arrowRedSW).play();
        new RotateInDownLeft(arrowBlueNE).play();
        new RotateInDownLeft(arrowRedNE).play();
        new RotateInDownLeft(arrowBlueNW).play();
        new RotateInDownLeft(arrowRedNW).play();

    }

    public void set_match(Profile profile) {
        profileSelected = profile;
        // Best mutual compatibility first (see matching.properties)
        suggestions = modelMatch.getSuggestions(profile.getIdentity().getNoId());
        profileList = suggestions.stream().map(Matcher.Suggestion::candidate).toList();

        List<Circle> circleList = Arrays.asList(circleProfilePictureNE, circleProfilePictureSE, circleProfilePictureS, circleProfilePictureSW, circleProfilePictureNW);
        List<Label> lastNameList = Arrays.asList(lastNameNE, lastNameSE, lastNameS, lastNameSW, lastNameNW);
        List<Label> firstNameList = Arrays.asList(firstNameNE, firstNameSE, firstNameS, firstNameSW, firstNameNW);
        List<Label> ageList = Arrays.asList(ageNE, ageSE, ageS, ageSW, ageNW);
        List<Label> passionList = Arrays.asList(passionNE, passionSE, passionS, passionSW, passionNW);
        List<Label> labelBlueList = Arrays.asList(labelBlueNE, labelBlueSE, labelBlueS, labelBlueSW, labelBlueNW);
        List<Label> labelRedList = Arrays.asList(labelRedNE, labelRedSE, labelRedS, labelRedSW, labelRedNW);

        circleProfilePictureCenter.setFill(new ImagePattern(ImageLoader.profileImage(profile.getImageView())));
        for (int i = 0; i < circleList.size(); i++) {
            if (i < profileList.size()) {
                Matcher.Suggestion suggestion = suggestions.get(i);
                Profile p = suggestion.candidate();
                passionList.get(i).setText(Math.round(suggestion.compatibility()) + "% match - " + suggestion.commonPassions() + " common passions");
                ageList.get(i).setText("Age : " + p.getIdentity().getAge());
                firstNameList.get(i).setText(p.getIdentity().getFirstname());
                lastNameList.get(i).setText(p.getIdentity().getLastname());
                circleList.get(i).setFill(new ImagePattern(ImageLoader.profileImage(p.getImageView())));

                // Blue: how well the candidate fits the selected person's preferences; red: the other way round
                labelBlueList.get(i).setText(percent(suggestion.selectedSatisfaction()));
                labelRedList.get(i).setText(percent(suggestion.candidateSatisfaction()));
            } else {
                // Fewer compatible profiles than places: the slot stays empty and cannot be clicked
                circleList.get(i).setDisable(true);
                circleList.get(i).setOpacity(0.3);
                firstNameList.get(i).setText(i == 0 ? "No compatible profile" : "");
                lastNameList.get(i).setText("");
                ageList.get(i).setText("");
                passionList.get(i).setText("");
                labelBlueList.get(i).setText("");
                labelRedList.get(i).setText("");
            }
        }
        animation();
    }

    private static String percent(double satisfaction) {
        return Math.round(100 * satisfaction) + "%";
    }

    public void make_match(MouseEvent mouseEvent) throws IOException {
        List<String> slots = List.of("circleProfilePictureNE", "circleProfilePictureSE", "circleProfilePictureS", "circleProfilePictureSW", "circleProfilePictureNW");
        int index = slots.indexOf(((Node) mouseEvent.getSource()).getId());
        if (index < 0 || index >= profileList.size()) {
            return; // empty slot
        }
        Profile chosen = profileList.get(index);

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmation Dialog");
        confirm.setHeaderText("Are you sure you want to add this match ?");
        confirm.setContentText("You can't undo this action");
        Optional<ButtonType> result = confirm.showAndWait();
        // Closing the dialog (empty result) used to be treated as "OK"
        if (result.isEmpty() || result.get() != ButtonType.OK) {
            return;
        }

        profileSelected.modelHisto.addMatch(profileSelected, chosen);

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Match added");
        alert.setContentText("The match has been successfully added");
        alert.showAndWait();

        notificationDashboard();
        updateSearch();
    }


    private void updateSearch() {
        if (search_controller != null) {
            search_controller.updateAfterEditOrMatch();
        }
    }

    private void notificationDashboard() {
        if (dashboard_controller != null) {
            dashboard_controller.updateAfterEditOrMatch();
        }
    }

    @FXML
    void SWCursor() {
        circleProfilePictureSW.setStyle("-fx-cursor: HAND");
    }

    @FXML
    void NECursor() {
        circleProfilePictureNE.setStyle("-fx-cursor: HAND");
    }

    @FXML
    void NWCursor() {
        circleProfilePictureNW.setStyle("-fx-cursor: HAND");
    }

    @FXML
    void SECursor() {
        circleProfilePictureSE.setStyle("-fx-cursor: HAND");
    }

    @FXML
    void SCursor() {
        circleProfilePictureS.setStyle("-fx-cursor: HAND");
    }

    public void setDashboard_controller(Dashboard_Controller dashboardController) {
        this.dashboard_controller = dashboardController;
    }

    public void setSearch_controller(Search_Controller searchController) {
        this.search_controller = searchController;
    }
}
