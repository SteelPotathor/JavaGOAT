package com.example.javagoat.back;


import java.util.Date;
import java.util.HashMap;

public class Launcher {

    public ModelMatch modelM;

    public Launcher(ModelMatch modelM) {
        this.modelM = modelM;
    }


    public static void main(String[] args) {
        ModelNotification modelNotification = new ModelNotification();
        modelNotification.addNotification(new Date(), "Bonjour");


        //launcher.modelM.saveProfiles();
        //launcher.modelM.saveDistances();
    }

}
