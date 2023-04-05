package com.example.javagoat.back.algoExample;

import java.util.HashMap;
import java.util.TreeMap;

public class Main {
    
    // Possibilité d'utiliser un HashMap à la place du 1er TreeMap (j'utilise içi un TreeMap pour mieux visualiser avec un print)
    public HashMap<Integer /*id*/, TreeMap<Integer /*distance*/, Integer /*id*/>> stockD = new HashMap<>();

    // Stocke tous les profils à partir de leurs id
    public HashMap<Integer /*id*/, Profile> profileHashMap = new HashMap<>();

    public void ajoutProfil(Profile p) {
        // on add dans tous les TreeMap la nouvelle distance entre le profil p et les points contenus dans la TreeMap
        for (int i: stockD.keySet()) {
            TreeMap<Integer, Integer> tm = stockD.get(i);
            Profile profile = profileHashMap.get(i);
            int d = profile.distance(p);
            tm.put(d, p.noid);
        }

        //on add dans le nouveau TreeMap (celui du profil p) toutes les distances avec les autres profils
        stockD.put(p.noid, new TreeMap<>());
        TreeMap<Integer, Integer> t = stockD.get(p.noid);
        for (int d: profileHashMap.keySet()) {
            Profile pro = profileHashMap.get(d);
            int dis = pro.distance(p);
            t.put(dis, p.noid);
        }

        // on ajoute dans la SD qui stocke les profils le nouveau profil
        this.profileHashMap.put(p.noid, p);
    }

    public static void main(String[] args) {

        Main main = new Main();

        Profile p1 = new Profile(1, "Jawad", new Vecteur(0));
        Profile p2 = new Profile(2, "Oussama", new Vecteur(3));
        Profile p3 = new Profile(3, "Timothée", new Vecteur(5));
        main.ajoutProfil(p1);
        main.ajoutProfil(p2);
        main.ajoutProfil(p3);

        System.out.println(main.stockD.toString());
    }
}
