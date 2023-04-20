package com.example.javagoat.back;

public class Tuple implements Comparable {

    public int id;
    public float distance;

    public Tuple(int id, Profile p1, Profile p2) {
        this.id = id;
        this.distance = p1.getDistance(p2);
    }


    @Override
    public String toString() {
        return "{id=" + id +
                ", distance='" + distance + '\'' +
                '}';
    }

    // necessaire pour faire en sorte que le treeset soit ok
    @Override
    public int compareTo(Object o) {
        Tuple tuple = (Tuple) o;
        return (int) (this.distance - tuple.distance);
    }

}
