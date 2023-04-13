package com.example.javagoat.back;


public class Launcher {

    public ModelMatch modelM;

    public Launcher(ModelMatch modelM) {
        this.modelM = modelM;
    }


    public static void main(String[] args) {
        Launcher launcher = new Launcher(new ModelMatch());

        for (int i = 0; i < 5000; i++) {
            Profile p = new Profile();
            p.setRandomProfile();
            launcher.modelM.addProfile(p);
        }

        System.out.println(launcher.modelM.getModelP().getProfileHashMap().get(1).getPreferences());
        System.out.println(launcher.modelM.getKNN(1,10));

    }

}
