package com.example.javagoat;

import com.example.javagoat.Model.CheckPriorityStart;
import com.example.javagoat.Model.ModelMatch;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ApplicationStart extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Loaded here, before any controller exists, so that a broken data file stops the app
            // instead of silently starting with an empty agency (and overwriting the file on exit).
            ModelMatch.loadData();
            new CheckPriorityStart().initPriority();
        } catch (RuntimeException e) {
            e.printStackTrace();
            showError("The data could not be loaded", e.getMessage());
            Platform.exit();
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard/home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Application");
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            // Closing the main window used to quit without saving (only the "exit" button saved)
            stage.setOnCloseRequest(event -> {
                if (saveForExit()) {
                    Platform.exit();
                } else {
                    event.consume();
                }
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("The application can't be launched", e.toString());
            Platform.exit();
        }
    }

    /**
     * Saves the data before quitting.
     *
     * @return true if the application may quit (saved, or the user accepts to lose the changes)
     */
    static boolean saveForExit() {
        try {
            ModelMatch.saveData();
            return true;
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "The profiles could not be saved:\n" + e + "\n\nQuit anyway and lose the changes of this session?",
                    ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Save failed");
            return alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES;
        }
    }

    /** Used by the "exit" buttons of the navigation bar. */
    static void saveAndExit() {
        if (saveForExit()) {
            Platform.exit();
        }
    }

    private static void showError(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
