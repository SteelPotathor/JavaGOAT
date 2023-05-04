package com.example.javagoat;

import com.example.javagoat.back.ModelMatch;
import com.example.javagoat.back.Profile;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.*;

public class Matching_Profiles_Controller {

    public ModelMatch modelMatch = new ModelMatch();

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
    void Initialize() {

    }

    public void set_match(Profile profile) {

        HashMap<Profile, Integer> result_matching = modelMatch.getKNN(profile.getIdentity().getNoId(), 5);
        List<Circle> circleList = Arrays.asList(circleProfilePictureNE, circleProfilePictureSE, circleProfilePictureS, circleProfilePictureSW, circleProfilePictureNW);
        List<Label> lastNameList = Arrays.asList(lastNameNE, lastNameSE, lastNameS, lastNameSW, lastNameNW);
        List<Label> firstNameList = Arrays.asList(firstNameNE, firstNameSE, firstNameS, firstNameSW, firstNameNW);
        List<Label> ageList = Arrays.asList(ageNE, ageSE, ageS, ageSW, ageNW);
        try {
            circleProfilePictureCenter.setFill(new ImagePattern(new Image(profile.getImageView().getImage().getUrl())));
            int i = 0;
            for (Profile p : result_matching.keySet()) {
                ageList.get(i).setText("Age : " + p.getIdentity().getAge());
                firstNameList.get(i).setText(p.getIdentity().getFirstname());
                lastNameList.get(i).setText(p.getIdentity().getLastname());
                circleList.get(i).setFill(new ImagePattern(new Image(p.getImageView().getImage().getUrl())));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

}
