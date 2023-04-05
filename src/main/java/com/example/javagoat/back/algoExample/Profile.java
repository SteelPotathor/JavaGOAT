package com.example.javagoat.back.algoExample;

public class Profile implements Comparable<Profile> {
    public int noid;
    public String nom;
    public Vecteur v;

    public Profile(int noid, String nom, Vecteur vecteur) {
        this.noid = noid;
        this.nom = nom;
        this.v = vecteur;
    }

    //distance between 2 profiles
    public int distance(Profile profile) {
        return Math.abs(profile.v.x - this.v.x);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "noid=" + noid +
                ", nom='" + nom + '\'' +
                '}';
    }

    // Necessary for the treemap
    @Override
    public int compareTo(Profile o) {
        return this.noid - o.noid;
    }
}
