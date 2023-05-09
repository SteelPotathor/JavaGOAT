package com.example.javagoat;

import animatefx.animation.FadeInDownBig;
import animatefx.animation.FadeInUpBig;
import animatefx.animation.RotateInDownLeft;
import com.example.javagoat.back.ModelMatch;
import com.example.javagoat.back.Profile;
import com.example.javagoat.back.TupleTreeSet;
import javafx.fxml.FXML;
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

    public HashMap<Profile, Integer> result_matching;

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
        result_matching = modelMatch.getKNN(profile.getIdentity().getNoId(), 5);
        profileList = new ArrayList<>(result_matching.keySet());
        List<Circle> circleList = Arrays.asList(circleProfilePictureNE, circleProfilePictureSE, circleProfilePictureS, circleProfilePictureSW, circleProfilePictureNW);
        List<Label> lastNameList = Arrays.asList(lastNameNE, lastNameSE, lastNameS, lastNameSW, lastNameNW);
        List<Label> firstNameList = Arrays.asList(firstNameNE, firstNameSE, firstNameS, firstNameSW, firstNameNW);
        List<Label> ageList = Arrays.asList(ageNE, ageSE, ageS, ageSW, ageNW);
        List<Label> passionList = Arrays.asList(passionNE, passionSE, passionS, passionSW, passionNW);
        List<Label> labelBlueList = Arrays.asList(labelBlueNE, labelBlueSE, labelBlueS, labelBlueSW, labelBlueNW);
        List<Label> labelRedList = Arrays.asList(labelRedNE, labelRedSE, labelRedS, labelRedSW, labelRedNW);
        try {
            circleProfilePictureCenter.setFill(new ImagePattern(new Image(profile.getImageView())));
            int i = 0;
            for (Profile p : result_matching.keySet()) {
                passionList.get(i).setText("Common passions : " + result_matching.get(p));
                ageList.get(i).setText("Age : " + p.getIdentity().getAge());
                firstNameList.get(i).setText(p.getIdentity().getFirstname());
                lastNameList.get(i).setText(p.getIdentity().getLastname());
                circleList.get(i).setFill(new ImagePattern(new Image(p.getImageView())));

                TreeSet<TupleTreeSet> treeSetBlue = modelMatch.getStockDistance().get(profile.getIdentity().getNoId());
                for (TupleTreeSet t : treeSetBlue) {
                    if (t.getId() == p.getIdentity().getNoId()) {
                        labelBlueList.get(i).setText(Math.round(100 - (100 * t.getDistance() / 1051.4669)) + "%");
                        break;
                    }
                }

                TreeSet<TupleTreeSet> treeSetRed = modelMatch.getStockDistance().get(p.getIdentity().getNoId());
                for (TupleTreeSet t : treeSetRed) {
                    if (t.getId() == profile.getIdentity().getNoId()) {
                        labelRedList.get(i).setText(Math.round(100 - (100 * t.getDistance() / 1051.4669)) + "%");
                        break;
                    }
                }

                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        animation();
    }

    public void make_match(MouseEvent mouseEvent) throws IOException {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmation Dialog");
        confirm.setHeaderText("Are you sure you want to add this match ?");
        confirm.setContentText("You can't undo this action");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() != ButtonType.OK) {
            return;
        }

        switch (mouseEvent.getPickResult().getIntersectedNode().getId()) {
            case "circleProfilePictureNE" -> {
                modelMatch.modelP.getProfileHashMap().get(profileSelected.getIdentity().getNoId()).modelHisto.addMatch(profileSelected, profileList.get(0));
            }
            case "circleProfilePictureSE" -> {
                modelMatch.modelP.getProfileHashMap().get(profileSelected.getIdentity().getNoId()).modelHisto.addMatch(profileSelected, profileList.get(1));
            }
            case "circleProfilePictureS" -> {
                modelMatch.modelP.getProfileHashMap().get(profileSelected.getIdentity().getNoId()).modelHisto.addMatch(profileSelected, profileList.get(2));
            }
            case "circleProfilePictureSW" -> {
                modelMatch.modelP.getProfileHashMap().get(profileSelected.getIdentity().getNoId()).modelHisto.addMatch(profileSelected, profileList.get(3));
            }
            case "circleProfilePictureNW" -> {
                modelMatch.modelP.getProfileHashMap().get(profileSelected.getIdentity().getNoId()).modelHisto.addMatch(profileSelected, profileList.get(4));
            }
        }

        Stage stage = (Stage) mouseEvent.getPickResult().getIntersectedNode().getScene().getWindow();
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
