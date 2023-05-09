package com.example.javagoat;

import com.example.javagoat.back.CheckPriorityStart;
import com.example.javagoat.back.ModelMatch;
import com.example.javagoat.back.ModelProfile;
import com.example.javagoat.back.Profile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Date;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
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
