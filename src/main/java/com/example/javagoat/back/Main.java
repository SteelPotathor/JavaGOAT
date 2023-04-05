package com.example.javagoat.back;

import java.util.HashMap;
import java.util.TreeMap;

public class Main {
    
    // Possibilité d'utiliser un HashMap à la place du 1er TreeMap (j'utilise içi un TreeMap pour mieux visualiser avec un print)
    public HashMap<Integer /*id*/, TreeMap<Float /*distance*/, Integer /*id*/>> stockD = new HashMap<>();

    // Stocke tous les profils à partir de leurs id
    public HashMap<Integer /*id*/, Profile> profileHashMap = new HashMap<>();

    public void ajoutProfil(Profile p) {
        // on add dans tous les TreeMap la nouvelle distance entre le profil p et les points contenus dans la TreeMap
        for (int i: stockD.keySet()) {
            TreeMap<Float, Integer> tm = stockD.get(i);
            Profile profile = profileHashMap.get(i);
            float d = profile.distance(p);
            tm.put(d, p.identity.getNoId());
        }

        //on add dans le nouveau TreeMap (celui du profil p) toutes les distances avec les autres profils
        stockD.put(p.identity.getNoId(), new TreeMap<>());
        TreeMap<Float, Integer> t = stockD.get(p.identity.getNoId());
        for (int d: profileHashMap.keySet()) {
            Profile pro = profileHashMap.get(d);
            float dis = pro.distance(p);
            t.put(dis, p.identity.getNoId());
        }

        // on ajoute dans la SD qui stocke les profils le nouveau profil
        this.profileHashMap.put(p.identity.getNoId(), p);
    }

    public static void main(String[] args) {

        Profile p1 = new Profile();
        Profile p2 = new Profile();

        System.out.println(p1.distance(p2));
    }
}
