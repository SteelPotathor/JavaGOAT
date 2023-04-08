package com.example.javagoat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        // add css
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setTitle("Hello!");
        //remove stage button
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}