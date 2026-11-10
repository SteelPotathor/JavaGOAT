package com.example.javagoat.Model;

import java.io.Serializable;

public class TupleTreeSet implements Comparable<TupleTreeSet>, Serializable {

    // Frozen value: keeps the existing .dat files readable after code changes.
    private static final long serialVersionUID = -2791213364130578542L;

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


    // Useful for the TreeSet where this class will be used.
    // The old version returned (int) (distance difference): two profiles less than 1.0 apart were "equal",
    // so the TreeSet silently dropped most of them (about 42 entries kept out of 500).
    @Override
    public int compareTo(TupleTreeSet o) {
        int byDistance = Float.compare(this.distance, o.distance);
        return byDistance != 0 ? byDistance : Integer.compare(this.id, o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TupleTreeSet other)) return false;
        return id == other.id && Float.compare(distance, other.distance) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * id + Float.hashCode(distance);
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
