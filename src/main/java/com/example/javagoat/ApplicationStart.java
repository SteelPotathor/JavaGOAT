package com.example.javagoat;

import com.example.javagoat.Model.CheckPriorityStart;
import com.example.javagoat.Model.ModelMatch;
import com.example.javagoat.Model.Profile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ApplicationStart extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        ModelMatch modelMatch = new ModelMatch();

        for (int i = 0; i < 500; i++) {
            Profile p = new Profile();
            p.setRandomProfile();
            modelMatch.addProfile(p);
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard/home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Application");
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error : the application can't be launched");
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        CheckPriorityStart checkPriorityStart = new CheckPriorityStart();
        checkPriorityStart.initPriority();
        launch();
    }
}
