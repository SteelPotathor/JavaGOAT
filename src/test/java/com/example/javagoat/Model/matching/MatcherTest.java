package com.example.javagoat.Model.matching;

import com.example.javagoat.Model.LifeStyle;
import com.example.javagoat.Model.Profile;
import com.example.javagoat.Model.TupleHistoHashMap;
import com.example.javagoat.Model.storage.ProfileStore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The scenarios written in data/showcase-profiles.json (see the "note" of each profile)
 * must behave as documented with the default settings.
 */
class MatcherTest {

    static Map<Integer, Profile> showcase;
    static final Matcher MATCHER = new Matcher(MatchingSettings.defaults());

    static final int CAMILLE = 1, THOMAS = 2, HUGO = 3, JULIEN = 4, ANTOINE = 5, INES = 6, SARAH = 7,
            KARIM = 8, LEA = 9, MAXIME = 10, MARGAUX = 11, BERNARD = 12;

    @BeforeAll
    static void loadShowcase() {
        showcase = ProfileStore.read(Path.of("data", "showcase-profiles.json"));
    }

    private static List<Matcher.Suggestion> suggestionsFor(int id) {
        Profile selected = showcase.get(id);
        return MATCHER.suggestions(selected, showcase.values(), 5,
                c -> selected.getModelHisto().getStockHisto().containsKey(new TupleHistoHashMap(c.getIdentity().getNoId())));
    }

    private static List<Integer> idsSuggestedFor(int id) {
        return suggestionsFor(id).stream().map(s -> s.candidate().getIdentity().getNoId()).toList();
    }

    @Test
    void thomasIsCamillesBestMatchAndTheFeelingIsMutual() {
        Matcher.Suggestion first = suggestionsFor(CAMILLE).get(0);
        assertEquals(THOMAS, first.candidate().getIdentity().getNoId());
        assertTrue(first.compatibility() > 90, "got " + first.compatibility());
        assertEquals(CAMILLE, idsSuggestedFor(THOMAS).get(0));
    }

    @Test
    void requiredCriteriaEliminateCandidates() {
        Profile camille = showcase.get(CAMILLE);
        assertTrue(MATCHER.evaluate(camille, showcase.get(HUGO)).isEmpty(), "Hugo smokes daily, Camille requires a non-smoker");
        assertTrue(MATCHER.evaluate(camille, showcase.get(JULIEN)).isEmpty(), "Julien is 44, Camille requires 27-35");
        assertFalse(idsSuggestedFor(CAMILLE).contains(HUGO));
        assertFalse(idsSuggestedFor(HUGO).contains(CAMILLE), "elimination works in both directions");
    }

    @Test
    void satisfactionIsNotSymmetric() {
        Matcher.Suggestion antoine = MATCHER.evaluate(showcase.get(CAMILLE), showcase.get(ANTOINE)).orElseThrow();
        assertTrue(antoine.selectedSatisfaction() > 0.8, "Antoine fits Camille: " + antoine.selectedSatisfaction());
        assertTrue(antoine.candidateSatisfaction() < 0.5, "Camille does not fit Antoine: " + antoine.candidateSatisfaction());
        double reverse = MATCHER.evaluate(showcase.get(ANTOINE), showcase.get(CAMILLE)).orElseThrow().compatibility();
        assertEquals(antoine.compatibility(), reverse, 1e-9, "the mutual compatibility is symmetric");
    }

    @Test
    void sexPreferencesAreMutual() {
        assertEquals(List.of(SARAH), idsSuggestedFor(INES));
        assertTrue(MATCHER.evaluate(showcase.get(KARIM), showcase.get(THOMAS)).isEmpty(), "Thomas only looks for women");
        assertTrue(MATCHER.evaluate(showcase.get(KARIM), showcase.get(LEA)).isPresent(), "Karim accepts women, Léa accepts men");
        assertTrue(MATCHER.evaluate(showcase.get(KARIM), showcase.get(INES)).isEmpty(), "Inès only looks for women");
    }

    @Test
    void commonPassionsMakeTheDifference() {
        Matcher.Suggestion first = suggestionsFor(LEA).get(0);
        assertEquals(MAXIME, first.candidate().getIdentity().getNoId());
        assertEquals(6, first.commonPassions());
    }

    @Test
    void alreadyMatchedPeopleAreNotSuggestedAgain() {
        assertFalse(idsSuggestedFor(MARGAUX).contains(BERNARD));
        assertEquals(1.0, showcase.get(BERNARD).getTotal());
        assertEquals(100.0, showcase.get(BERNARD).getRatio());
    }

    @Test
    void rangeSatisfactionDecreasesOutsideTheRange() {
        RangeCriterion range = new RangeCriterion(25, 30, Importance.IMPORTANT);
        assertEquals(1.0, range.satisfaction(27, 5));
        assertEquals(0.6, range.satisfaction(32, 5), 1e-9);
        assertEquals(0.0, range.satisfaction(40, 5));
    }

    @Test
    void orderedChoicesGivePartialSatisfaction() {
        ChoiceCriterion<LifeStyle.smoker> nonSmoker = new ChoiceCriterion<>(LifeStyle.smoker.class,
                EnumSet.of(LifeStyle.smoker.NEVER), Importance.IMPORTANT);
        assertEquals(1.0, nonSmoker.satisfaction(LifeStyle.smoker.NEVER, Enum::ordinal, 0.5));
        assertEquals(0.5, nonSmoker.satisfaction(LifeStyle.smoker.OCCASIONNALY, Enum::ordinal, 0.5));
        assertEquals(0.0, nonSmoker.satisfaction(LifeStyle.smoker.DAILY, Enum::ordinal, 0.5));
    }

    @Test
    void anImportantChoiceNeedsAcceptedValues() {
        assertThrows(IllegalArgumentException.class,
                () -> new ChoiceCriterion<>(LifeStyle.smoker.class, EnumSet.noneOf(LifeStyle.smoker.class), Importance.IMPORTANT));
    }
}
