package com.example.javagoat.Model;

import java.io.Serializable;

public class TupleTreeSet implements Comparable<TupleTreeSet>, Serializable {

    public int id;
    public float distance;

    public TupleTreeSet(int id, Profile p1, Profile p2) {
        this.id = id;
        this.distance = p1.getDistance(p2);
    }

    public TupleTreeSet() {
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


    // Useful for the TreeSet where this class will be used
    @Override
    public int compareTo(TupleTreeSet o) {
        return (int) (this.distance - o.distance);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TupleTreeSet{");
        sb.append("id=").append(id);
        sb.append(", distance=").append(distance);
        sb.append('}');
        return sb.toString();
    }
}
