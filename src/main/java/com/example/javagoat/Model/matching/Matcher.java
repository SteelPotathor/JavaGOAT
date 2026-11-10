package com.example.javagoat.Model.matching;

import com.example.javagoat.Model.LifeStyle;
import com.example.javagoat.Model.Preferences;
import com.example.javagoat.Model.Profile;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/**
 * Computes how well two profiles fit each other. See matching.properties for the formula.
 * <p>
 * Everything is computed on demand: with a few hundred profiles a full ranking takes a few milliseconds,
 * so there is no precomputed distance table to keep in sync.
 */
public final class Matcher {

    /** A suggestion for a selected person. Satisfactions are in [0, 1], compatibility in [0, 100]. */
    public record Suggestion(Profile candidate, double compatibility, double selectedSatisfaction,
                             double candidateSatisfaction, int commonPassions) {
    }

    private static final ToIntFunction<LifeStyle.feed> FEED_RANK = feed -> switch (feed) {
        case VEGAN -> 0;
        case VEGETARIAN -> 1;
        case FLEXIVORE -> 2;
        case OMNIVORE -> 3;
    };

    private final MatchingSettings settings;

    public Matcher(MatchingSettings settings) {
        this.settings = Objects.requireNonNull(settings);
    }

    public MatchingSettings settings() {
        return settings;
    }

    /**
     * Satisfaction of 'from' with 'to' according to the preferences of 'from', in [0, 1],
     * or empty if 'to' is eliminated (sex not accepted or a REQUIRED criterion not fully met).
     */
    public OptionalDouble satisfaction(Profile from, Profile to) {
        Preferences p = from.getPreferences();
        if (!p.getSexes().contains(to.getIdentity().getBsex())) {
            return OptionalDouble.empty();
        }
        Score score = new Score();
        double tol;

        tol = settings.tolerance("age");
        if (!score.add("age", p.getAge().importance(), p.getAge().satisfaction(to.getIdentity().getAge(), tol))) return OptionalDouble.empty();
        tol = settings.tolerance("size");
        if (!score.add("size", p.getSize().importance(), p.getSize().satisfaction(to.getPhysicalAttributes().getSize(), tol))) return OptionalDouble.empty();
        tol = settings.tolerance("iq");
        if (!score.add("iq", p.getIq().importance(), p.getIq().satisfaction(to.getIdentity().getQi(), tol))) return OptionalDouble.empty();

        double step = settings.stepPenalty();
        boolean kept = score.add("hairColor", p.getHairColor().importance(), p.getHairColor().satisfaction(to.getPhysicalAttributes().getHairColor()))
                && score.add("hairType", p.getHairType().importance(), p.getHairType().satisfaction(to.getPhysicalAttributes().getHairType(), Enum::ordinal, step))
                && score.add("hairLength", p.getHairLength().importance(), p.getHairLength().satisfaction(to.getPhysicalAttributes().getHairLength(), Enum::ordinal, step))
                && score.add("ethnicity", p.getEthnicity().importance(), p.getEthnicity().satisfaction(to.getIdentity().getBethnicity()))
                && score.add("bodyBuild", p.getBodyBuild().importance(), p.getBodyBuild().satisfaction(to.getLifeStyle().getLSbodyBuild(), Enum::ordinal, step))
                && score.add("smoker", p.getSmoker().importance(), p.getSmoker().satisfaction(to.getLifeStyle().getLSsmoker(), Enum::ordinal, step))
                && score.add("alcohol", p.getAlcohol().importance(), p.getAlcohol().satisfaction(to.getLifeStyle().getLSalcohol(), Enum::ordinal, step))
                && score.add("athlete", p.getAthlete().importance(), p.getAthlete().satisfaction(to.getLifeStyle().getLSathlete(), Enum::ordinal, step))
                && score.add("feed", p.getFeed().importance(), p.getFeed().satisfaction(to.getLifeStyle().getLSfeed(), FEED_RANK, step))
                && score.add("religion", p.getReligion().importance(), p.getReligion().satisfaction(to.getLifeStyle().getLSreligion()));
        if (!kept) {
            return OptionalDouble.empty();
        }
        score.addWeighted(settings.weight("passions"), passionSimilarity(from, to));
        return OptionalDouble.of(score.value());
    }

    /** Mutual compatibility in [0, 100], or empty if one of the two eliminates the other. */
    public Optional<Suggestion> evaluate(Profile selected, Profile candidate) {
        OptionalDouble forSelected = satisfaction(selected, candidate);
        if (forSelected.isEmpty()) {
            return Optional.empty();
        }
        OptionalDouble forCandidate = satisfaction(candidate, selected);
        if (forCandidate.isEmpty()) {
            return Optional.empty();
        }
        double compatibility = 100 * Math.sqrt(forSelected.getAsDouble() * forCandidate.getAsDouble());
        return Optional.of(new Suggestion(candidate, compatibility, forSelected.getAsDouble(),
                forCandidate.getAsDouble(), commonPassions(selected, candidate)));
    }

    /**
     * The 'howMany' most compatible candidates for 'selected', best first (ties broken by id).
     *
     * @param excluded candidates to skip (e.g. people already matched with 'selected')
     */
    public List<Suggestion> suggestions(Profile selected, Collection<Profile> candidates, int howMany, Predicate<Profile> excluded) {
        return candidates.stream()
                .filter(c -> c != selected && c.getIdentity().getNoId() != selected.getIdentity().getNoId())
                .filter(excluded.negate())
                .map(c -> evaluate(selected, c))
                .flatMap(Optional::stream)
                .sorted(Comparator.comparingDouble(Suggestion::compatibility).reversed()
                        .thenComparingInt(s -> s.candidate().getIdentity().getNoId()))
                .limit(howMany)
                .toList();
    }

    public static int commonPassions(Profile a, Profile b) {
        int common = 0;
        for (var game : a.getPassion().getPassionVG()) {
            if (b.getPassion().getPassionVG().contains(game)) common++;
        }
        for (var passion : a.getPassion().getPassionM()) {
            if (b.getPassion().getPassionM().contains(passion)) common++;
        }
        return common;
    }

    /** common / sqrt(countA x countB): 1 when both have exactly the same passions, 0 when none is shared. */
    static double passionSimilarity(Profile a, Profile b) {
        int countA = a.getPassion().getPassionVG().size() + a.getPassion().getPassionM().size();
        int countB = b.getPassion().getPassionVG().size() + b.getPassion().getPassionM().size();
        if (countA == 0 || countB == 0) {
            return 0;
        }
        return commonPassions(a, b) / Math.sqrt((double) countA * countB);
    }

    /** Weighted average of the satisfactions; remembers whether a REQUIRED criterion failed. */
    private final class Score {
        private double sum;
        private double totalWeight;

        /** @return false if the candidate is eliminated */
        boolean add(String criterion, Importance importance, double satisfaction) {
            if (importance == Importance.REQUIRED && satisfaction < 1.0) {
                return false;
            }
            addWeighted(settings.weight(criterion) * settings.importanceWeight(importance), satisfaction);
            return true;
        }

        void addWeighted(double weight, double satisfaction) {
            sum += weight * satisfaction;
            totalWeight += weight;
        }

        double value() {
            return totalWeight == 0 ? 1.0 : sum / totalWeight;
        }
    }
}
