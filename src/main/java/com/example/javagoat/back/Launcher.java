package com.example.javagoat.back;


import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;

public class Launcher {

    public ModelMatch modelM;

    public Launcher(ModelMatch modelM) {
        this.modelM = modelM;
    }


    public static void main(String[] args) {
        ModelNotification modelNotification = new ModelNotification();
        modelNotification.addNotification(new Date(), "Bonjour");
        Launcher launcher = new Launcher(new ModelMatch());

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
