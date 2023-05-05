package com.example.javagoat;

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

        ModelMatch modelM = new ModelMatch();

        for (int i = 0; i < 500; i++) { // 4000 profiles crash the view (because search has no limits)
            Profile profile = new Profile();
            profile.setRandomProfile();
            modelM.addProfile(profile);
        }

        ModelMatch.createCounter = 0;
        modelM.modelP.getProfileHashMap().get(1).modelHisto.addMatch(modelM.modelP.getProfileHashMap().get(1), modelM.modelP.getProfileHashMap().get(2));

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
            //add css
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Application");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error : the application can't be launched");
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        //ModelProfile modelProfile = new ModelProfile(); Useful to create this object?
        //We can create another class to handle this algorithm
        Date actualDate = new Date();
        for (Integer key : ModelProfile.profileHashMap.keySet()) {
            Profile profile = ModelProfile.profileHashMap.get(key);
            Date aux = new Date(1900, 1, 1);
            for (Integer id : profile.modelHisto.stockHisto.keySet()) {
                Date date = profile.modelHisto.stockHisto.get(id);
                // Determine the nearest date
                if (date.after(aux)) {
                    aux = date;
                }
            }
            long time = aux.getTime();
            long timeInterval = (time - actualDate.getTime()) * (-1); // The difference between time and the actual date is negative so *-1 to turns it positive
            timeInterval /= 36000000; // Convert the timeInterval which is in ms to hour
            if (timeInterval <= 24 * 7) { // less than a week
                profile.setPriority(3);
            } else if (timeInterval <= 24 * 14) { // between a week and 2 weeks
                profile.setPriority(2);
            } else { // more than two weeks
                profile.setPriority(1);
            }
        }
        launch();
    }
}