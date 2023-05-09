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
        /*
        ModelMatch modelMatch = new ModelMatch();
        //We can create another class to handle this algorithm
        Date actualDate = new Date();
        for (Integer key : modelMatch.modelP.profileHashMap.keySet()) {
            Profile profile = modelMatch.modelP.profileHashMap.get(key);
            Date aux = new Date(0, 1, 1); // Year => 1900, Month => 1, Day => 1
            for (Integer id : profile.modelHisto.stockHisto.keySet()) {
                System.out.println("histo boucle");
                System.out.println(profile);
                Date date = profile.modelHisto.stockHisto.get(id);
                // Determine the nearest date
                System.out.println(date);
                System.out.println(aux);
                if (date.after(aux)) {
                    aux = date;
                    System.out.println("new aux : " + aux);
                }
            }
            long time = aux.getTime();
            long timeInterval = (time - actualDate.getTime()) * -1; // The difference between time and the actual date is negative so *-1 to turns it positive
            timeInterval /= 36000000; // Convert the timeInterval which is in ms to hour
            System.out.println(profile);
            System.out.println("time interval : " + timeInterval);
            if (timeInterval <= 24 * 7) { // less than a week
                profile.setPriority(3);
            } else if (timeInterval <= 24 * 14) { // between a week and 2 weeks
                profile.setPriority(2);
            } else { // more than two weeks
                profile.setPriority(1);
            }
        }

         */
        CheckPriorityStart checkPriorityStart = new CheckPriorityStart();
        checkPriorityStart.initPriority();
        launch();
    }
}
