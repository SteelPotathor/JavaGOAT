package com.example.javagoat.Model.matching;

import java.util.Objects;

/** "Between min and max" preference (age, size, IQ), with its importance. */
public final class RangeCriterion {

    private final int min;
    private final int max;
    private final Importance importance;

    public RangeCriterion(int min, int max, Importance importance) {
        if (min > max) {
            throw new IllegalArgumentException("min (" + min + ") > max (" + max + ")");
        }
        this.min = min;
        this.max = max;
        this.importance = Objects.requireNonNull(importance, "importance");
    }

    /**
     * 1 inside [min, max]; outside, decreases linearly and reaches 0 at 'tolerance' units from the range.
     */
    public double satisfaction(int value, double tolerance) {
        int gap = value < min ? min - value : value > max ? value - max : 0;
        if (gap == 0) {
            return 1.0;
        }
        return Math.max(0.0, 1.0 - gap / tolerance);
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }

    public Importance importance() {
        return importance;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof RangeCriterion r && r.min == min && r.max == max && r.importance == importance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max, importance);
    }

    @Override
    public String toString() {
        return min + "-" + max + " (" + importance + ")";
    }
}
