package com.example.javagoat.back.test;

public class Tuple implements Comparable {

    public int id;
    public int distance;

    public Tuple(int id, ProfileTest p1, ProfileTest p2) {
        this.id = id;
        this.distance = p1.calculDistance(p2);
    }


    @Override
    public String toString() {
        return "id=" + id +
                ", distance='" + distance + '\'' +
                '}';
    }

    // necessaire pour faire en sorte que le treeset soit ok
    @Override
    public int compareTo(Object o) {
        Tuple tuple = (Tuple) o;
        return this.distance - tuple.distance;
    }

}
