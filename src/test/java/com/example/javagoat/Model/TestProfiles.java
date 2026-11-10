package com.example.javagoat.Model;

import com.example.javagoat.Model.matching.Importance;
import com.example.javagoat.Model.matching.RangeCriterion;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

/** Small factory for deterministic profiles used by the tests. */
public final class TestProfiles {

    private TestProfiles() {
    }

    public static Profile profile(int id, Biology.sex sex, int age, Biology.sex preferredSex, int minAge, int maxAge) {
        Identity identity = new Identity(age, sex, Biology.ethnicity.WHITE, 100, "Last" + id, "First" + id, id);
        PhysicalAttributes physical = new PhysicalAttributes(175, PhysicalAttributes.hairColor.BLACK,
                PhysicalAttributes.hairType.STRAIGHT, PhysicalAttributes.hairLength.SHORT);
        LifeStyle lifeStyle = new LifeStyle(LifeStyle.smoker.NEVER, LifeStyle.athlete.SPORTY, LifeStyle.feed.OMNIVORE,
                LifeStyle.bodyBuild.MEDIUM, LifeStyle.religion.ATHEIST, LifeStyle.alcohol.OCCASIONNALY);
        Preferences preferences = new Preferences();
        preferences.setSexes(EnumSet.of(preferredSex));
        preferences.setAge(new RangeCriterion(minAge, maxAge, Importance.IMPORTANT));
        Passion passion = new Passion(new HashSet<>(List.of(Passion.video_games.MINECRAFT)),
                new HashSet<>(List.of(Passion.miscellaneous.MOVIES)));
        return new Profile(identity, physical, lifeStyle, preferences, passion, ImageLoader.DEFAULT_PROFILE_IMAGE);
    }
}
