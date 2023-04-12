package com.example.javagoat.back;


public class Launcher {

    public ModelMatch modelM;

    public Launcher(ModelMatch modelM) {
        this.modelM = modelM;
    }


    public static void main(String[] args) {
        Launcher launcher = new Launcher(new ModelMatch());

        System.out.println(launcher.modelM.getModelP().getProfileHashMap().get(1).getPreferences());

    }

}
