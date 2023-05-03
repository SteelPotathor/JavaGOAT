package com.example.javagoat;

import com.example.javagoat.back.Profile;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.FileInputStream;

public class Matching_Profiles_Controller {

    @FXML
    private Circle circle_profile_picture11;
    @FXML
    private Circle circle_profile_picture12;
    @FXML
    private Circle circle_profile_picture111;
    @FXML
    private Circle circle_profile_picture1111;
    @FXML
    private Circle circle_profile_picture;
    @FXML
    private Circle circle_profile_picture1;

    @FXML
    void Initialize() {
        circle_profile_picture11.setFill(new ImagePattern(new Image(new File("src\\main\\java\\com\\example\\javagoat\\back\\images\\kumalala.jpg").toString())));
    }

}
