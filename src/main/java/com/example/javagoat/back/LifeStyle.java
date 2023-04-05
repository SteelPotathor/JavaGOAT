package com.example.javagoat.back;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LifeStyle {

    public smoker LSsmoker;
    public athlete LSathlete;
    public feed LSfeed;
    public bodyBuild LSbodyBuild;
    public religion LSreligion;

    private static final Random random = new Random();


    public enum smoker {
        NEVER,
        OCCASIONNALY,
        REGULARLY,
        DAILY;

        private static final List<smoker> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static smoker randomLSsmoker() {
            return VALUES.get(random.nextInt(SIZE));
        }

    }

    public enum athlete {
        SPORTY,
        SEDENTARY,
        LAZY;

        private static final List<athlete> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static athlete randomLSathlete() {
            return VALUES.get(random.nextInt(SIZE));
        }

    }

    public enum feed {
        VEGETARIAN,
        OMNIVORE,
        VEGAN,
        FLEXIVORE;

        private static final List<feed> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static feed randomLSfeed() {
            return VALUES.get(random.nextInt(SIZE));
        }

    }

    public enum bodyBuild {
        SKINNY,
        OVERWEIGHT,
        MEDIUM;

        private static final List<bodyBuild> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static bodyBuild randomLSbodyBuild() {
            return VALUES.get(random.nextInt(SIZE));
        }

    }

    public enum religion {
        MUSLIM,
        CHRISTIAN,
        JEWISH,
        BUDDHIST,
        HINDUIST,
        OTHER,
        ATHEIST;

        private static final List<religion> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static religion randomLSreligion() {
            return VALUES.get(random.nextInt(SIZE));
        }
    }

    // Customised LifeStyle
    public LifeStyle(smoker LSsmoker, athlete LSathlete, feed LSfeed, bodyBuild LSbodyBuild, religion LSreligion) {
        this.LSsmoker = LSsmoker;
        this.LSathlete = LSathlete;
        this.LSfeed = LSfeed;
        this.LSbodyBuild = LSbodyBuild;
        this.LSreligion = LSreligion;
    }

    // Random LifeStyle
    public LifeStyle() {
        this.LSsmoker = smoker.randomLSsmoker();
        this.LSathlete = athlete.randomLSathlete();
        this.LSfeed = feed.randomLSfeed();
        this.LSbodyBuild = bodyBuild.randomLSbodyBuild();
        this.LSreligion = religion.randomLSreligion();
    }

    public smoker getLSsmoker() {
        return LSsmoker;
    }

    public void setLSsmoker(smoker LSsmoker) {
        this.LSsmoker = LSsmoker;
    }

    public athlete getLSathlete() {
        return LSathlete;
    }

    public void setLSathlete(athlete LSathlete) {
        this.LSathlete = LSathlete;
    }

    public feed getLSfeed() {
        return LSfeed;
    }

    public void setLSfeed(feed LSfeed) {
        this.LSfeed = LSfeed;
    }

    public bodyBuild getLSbodyBuild() {
        return LSbodyBuild;
    }

    public void setLSbodyBuild(bodyBuild LSbodyBuild) {
        this.LSbodyBuild = LSbodyBuild;
    }

    public String toString() {
        return "LifeStyle : {" + getLSsmoker() + ", " + getLSathlete() + ", " + getLSfeed() + ", " + getLSbodyBuild() + "}";
    }

}
