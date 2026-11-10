package com.example.javagoat.Model.matching;

/**
 * How much a criterion matters to the person who set it.
 * <p>
 * The weight given to each level is read from matching.properties ({@code importance.*} keys).
 * {@link #REQUIRED} is also eliminatory: a candidate who does not fully satisfy it is never suggested.
 */
public enum Importance {
    INDIFFERENT("Indifferent"),
    NICE_TO_HAVE("Nice to have"),
    IMPORTANT("Important"),
    REQUIRED("Required");

    private final String label;

    Importance(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
