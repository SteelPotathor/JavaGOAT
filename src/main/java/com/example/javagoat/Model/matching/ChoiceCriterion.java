package com.example.javagoat.Model.matching;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.ToIntFunction;

/**
 * "One of these values" preference (hair colour, religion, smoker...), with its importance.
 * An empty set of accepted values is only allowed when the criterion is {@link Importance#INDIFFERENT}.
 */
public final class ChoiceCriterion<E extends Enum<E>> {

    private final EnumSet<E> accepted;
    private final Importance importance;

    public ChoiceCriterion(Class<E> type, Collection<E> accepted, Importance importance) {
        this.accepted = accepted.isEmpty() ? EnumSet.noneOf(type) : EnumSet.copyOf(accepted);
        this.importance = Objects.requireNonNull(importance, "importance");
        if (this.accepted.isEmpty() && importance != Importance.INDIFFERENT) {
            throw new IllegalArgumentException("At least one accepted value is needed when the importance is " + importance);
        }
    }

    public static <E extends Enum<E>> ChoiceCriterion<E> indifferent(Class<E> type) {
        return new ChoiceCriterion<>(type, EnumSet.noneOf(type), Importance.INDIFFERENT);
    }

    /** Nominal values (e.g. hair colour): accepted or not. */
    public double satisfaction(E value) {
        return accepted.isEmpty() || accepted.contains(value) ? 1.0 : 0.0;
    }

    /**
     * Ordered values (e.g. smoker NEVER < OCCASIONALLY < ...): a value close to an accepted one still
     * gets part of the satisfaction, minus 'stepPenalty' for each step away from the nearest accepted value.
     */
    public double satisfaction(E value, ToIntFunction<E> rank, double stepPenalty) {
        if (accepted.isEmpty() || accepted.contains(value)) {
            return 1.0;
        }
        int steps = accepted.stream().mapToInt(a -> Math.abs(rank.applyAsInt(a) - rank.applyAsInt(value))).min().orElseThrow();
        return Math.max(0.0, 1.0 - steps * stepPenalty);
    }

    public Set<E> accepted() {
        return accepted.clone();
    }

    public Importance importance() {
        return importance;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ChoiceCriterion<?> c && c.accepted.equals(accepted) && c.importance == importance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accepted, importance);
    }

    @Override
    public String toString() {
        return accepted + " (" + importance + ")";
    }
}
