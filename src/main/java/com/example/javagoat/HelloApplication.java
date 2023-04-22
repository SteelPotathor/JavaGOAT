package com.example.javagoat;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            stage.setTitle("Application");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {

        }


    }

    public static void main(String[] args) {
        launch();
    }

}