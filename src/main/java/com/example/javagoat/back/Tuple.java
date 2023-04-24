package com.example.javagoat.back;

import java.io.Serializable;

public class Tuple implements Comparable<Tuple>, Serializable {

    public int id;
    public float distance;

    public Tuple(int id, Profile p1, Profile p2) {
        this.id = id;
        this.distance = p1.getDistance(p2);
    }

    public Tuple() {

    }


    @Override
    public String toString() {
        return "(id=" + id + ", distance=" + distance + ")";
    }

    // Useful for the TreeSet where this class will be used
    @Override
    public int compareTo(Tuple o) {
        return (int) (this.distance - o.distance);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
