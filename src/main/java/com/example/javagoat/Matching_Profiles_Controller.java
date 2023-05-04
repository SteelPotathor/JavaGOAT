package com.example.javagoat;

import com.example.javagoat.back.ModelMatch;
import com.example.javagoat.back.Profile;
import javafx.fxml.FXML;
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
    void Initialize() {

    }

    public void set_match(Profile profile) {

        HashMap<Profile, Integer> result_matching = modelMatch.getKNN(profile.getIdentity().getNoId(), 5);
        List<Circle> circleList = Arrays.asList(circleProfilePictureNE, circleProfilePictureSE, circleProfilePictureS, circleProfilePictureSW, circleProfilePictureNW);
        try {
            circleProfilePictureCenter.setFill(new ImagePattern(new Image(profile.getImageView().getImage().getUrl())));
            int i = 0;
            for (Profile p : result_matching.keySet()) {
                circleList.get(i).setFill(new ImagePattern(new Image(p.getImageView().getImage().getUrl())));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
