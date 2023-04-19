package com.example.javagoat.back.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, ProfileTest> dico = new HashMap<Integer, ProfileTest>();
        // on remplace notre TreeMap par un TreeSet compose d'un id et trie en fonction de la distance
        TreeSet<Tuple> treeSet = new TreeSet<Tuple>();

        for (int i = 0; i < 10; i++) {
            treeSet.add(new Tuple(i, new ProfileTest(11, 11), new ProfileTest(i, i))); // on ajoute la distance entre p11 et pi
            dico.put(i, new ProfileTest(i, i)); // ici je remplie le repertoire dico id => profil
        }
        dico.put(11, new ProfileTest(11, 11));

        System.out.println(treeSet);

        //modif d un profil dans les treeset des autres profils
        treeSet.remove(new Tuple(3, dico.get(11), dico.get(3)));
        System.out.println(treeSet);
        treeSet.add(new Tuple(3, dico.get(11), dico.get(3)));
        System.out.println(treeSet);

        //iteration sur les 10 meilleurs profils
        int i = 0;
        Iterator<Tuple> itr = treeSet.iterator();
        System.out.println("Debut itr");
        while (i < 5 && itr.hasNext()) {
            System.out.println(itr.next());
            i++;
        }
        System.out.println("Fin itr");

        //pour modif le treeset entier d un profil, on le vide avec le .clear() et on le reremplit avec les bonnes distances
    }
}
