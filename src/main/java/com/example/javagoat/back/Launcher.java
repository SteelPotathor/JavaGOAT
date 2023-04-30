package com.example.javagoat.back;


import java.util.HashMap;

public class Launcher {

    public ModelMatch modelM;

    public Launcher(ModelMatch modelM) {
        this.modelM = modelM;
    }


    public static void main(String[] args) {
        Launcher launcher = new Launcher(new ModelMatch());

        for (int i = 0; i < 500; i++) {
            Profile p = new Profile();
            p.setRandomProfile();
            launcher.modelM.addProfile(p);
        }

        System.out.println(launcher.modelM.getModelP().getProfileHashMap().get(5).getPassion());
        HashMap<Profile, Integer> res = (launcher.modelM.getKNN(5, 5));


        // Get key and value from HashMap
        for (Profile profile : res.keySet()) {
            System.out.println("Key: " + profile.getPassion() + " Value: " + res.get(profile));
        }


    }

}
