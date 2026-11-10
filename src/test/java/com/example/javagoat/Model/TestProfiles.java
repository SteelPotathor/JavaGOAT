package com.example.javagoat.Model;

import java.util.HashSet;
import java.util.List;

/** Small factory for deterministic profiles used by the tests. */
final class TestProfiles {

    private TestProfiles() {
    }

    static Profile profile(int id, Biology.sex sex, int age, Biology.sex preferredSex, int preferredAge) {
        Identity identity = new Identity(age, sex, Biology.ethnicity.WHITE, 100, "Last" + id, "First" + id, id);
        PhysicalAttributes physical = new PhysicalAttributes(175, PhysicalAttributes.hairColor.BLACK,
                PhysicalAttributes.hairType.STRAIGHT, PhysicalAttributes.hairLength.SHORT);
        LifeStyle lifeStyle = new LifeStyle(LifeStyle.smoker.NEVER, LifeStyle.athlete.SPORTY, LifeStyle.feed.OMNIVORE,
                LifeStyle.bodyBuild.MEDIUM, LifeStyle.religion.ATHEIST, LifeStyle.alcohol.OCCASIONNALY);
        Preferences preferences = new Preferences(
                new PhysicalAttributes(170, PhysicalAttributes.hairColor.BLACK, PhysicalAttributes.hairType.STRAIGHT,
                        PhysicalAttributes.hairLength.SHORT),
                new Biology(preferredAge, preferredSex, Biology.ethnicity.WHITE, 100),
                new LifeStyle(LifeStyle.smoker.NEVER, LifeStyle.athlete.SPORTY, LifeStyle.feed.OMNIVORE,
                        LifeStyle.bodyBuild.MEDIUM, LifeStyle.religion.ATHEIST, LifeStyle.alcohol.OCCASIONNALY));
        Passion passion = new Passion(new HashSet<>(List.of(Passion.video_games.MINECRAFT)),
                new HashSet<>(List.of(Passion.miscellaneous.MOVIES)));
        return new Profile(identity, physical, lifeStyle, preferences, passion, ImageLoader.DEFAULT_PROFILE_IMAGE);
    }
}
