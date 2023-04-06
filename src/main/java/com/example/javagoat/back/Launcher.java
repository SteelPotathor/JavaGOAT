package com.example.javagoat.back;


import java.io.IOException;

public class Launcher {

    public ModelMatch modelM;

    public Launcher(ModelMatch modelM) {
        this.modelM = modelM;
    }


    public static void main(String[] args) throws IOException {
        Launcher launcher = new Launcher(new ModelMatch());

        System.out.println(launcher.modelM.getModelP().getProfileHashMap().get(10).getPreferences());
        System.out.println(launcher.modelM.getKNN(10, 5));

    }

}