package com.example.javagoat.back;


import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;


// Class exclusively used for test
public class Launcher {

    public ModelMatch modelM;

    public Launcher(ModelMatch modelM) {
        this.modelM = modelM;
    }


    public static void main(String[] args) {
        ModelNotification modelNotification = new ModelNotification();
        modelNotification.addNotification(new Date(), "Bonjour");
        Launcher launcher = new Launcher(new ModelMatch());


        ModelMatch modelMatch = new ModelMatch();
        // create red
        for (int i = 1; i < 250; i++) {
            Profile profile = modelMatch.getModelP().getProfileHashMap().get(i);
            profile.modelHisto.addMatch(modelMatch.getModelP().getProfileHashMap().get(i), modelMatch.getModelP().getProfileHashMap().get(i + 1));
        }
        // create orange
        for (int i = 250; i < 270; i++) {
            Profile profile = modelMatch.getModelP().getProfileHashMap().get(i);
            profile.modelHisto.addMatch(modelMatch.getModelP().getProfileHashMap().get(i), modelMatch.getModelP().getProfileHashMap().get(i + 1), new Date(123, 5, 1));
        }
        HashMap<Integer, TreeSet<TupleTreeSet>> map = launcher.modelM.getStockDistance();

        float min = 1000000000000.0F;
        float max = 0.0F;
        float sum = 0.0F;

        for (Integer i : map.keySet()) {
            for (TupleTreeSet t : map.get(i)) {
                if (min > t.getDistance()) {
                    min = t.getDistance();
                }
                if (max < t.getDistance()) {
                    max = t.getDistance();
                }
                sum += t.getDistance();
            }
        }

        System.out.println((min + max) / 2);
        System.out.println(max);


    }

}
