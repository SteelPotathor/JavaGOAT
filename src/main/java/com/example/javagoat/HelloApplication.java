package com.example.javagoat;

import com.example.javagoat.back.ModelMatch;
import com.example.javagoat.back.Profile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        ModelMatch modelM = new ModelMatch();


        for (int i = 0; i < 500; i++) {
            Profile profile = new Profile();
            profile.setRandomProfile();
            modelM.addProfile(profile);
        }
        System.out.println(modelM.modelP.getProfileHashMap());

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            stage.setTitle("Application");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error : the application can't be launched");
        }


    }


    public static void main(String[] args) {
        launch();
    }
}