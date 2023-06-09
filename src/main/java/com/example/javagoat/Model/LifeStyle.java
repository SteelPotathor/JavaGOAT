package com.example.javagoat.Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LifeStyle implements Serializable, Cloneable {

    private static final Random random = new Random();
    public smoker LSsmoker;
    public athlete LSathlete;
    public feed LSfeed;
    public bodyBuild LSbodyBuild;
    public religion LSreligion;
    public alcohol LSalcohol;

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

    public enum alcohol {
        NEVER,
        OCCASIONNALY,
        REGULARLY,
        DAILY;

        private static final List<alcohol> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static alcohol randomLSalcohol() {
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
        VEGAN,
        VEGETARIAN,
        OMNIVORE,
        FLEXIVORE;

        private static final List<feed> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static feed randomLSfeed() {
            return VALUES.get(random.nextInt(SIZE));
        }

    }

    public enum bodyBuild {
        SKINNY,
        MEDIUM,
        OVERWEIGHT;

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


    // Customised LifeStyle -> Add Profile
    public LifeStyle(smoker LSsmoker, athlete LSathlete, feed LSfeed, bodyBuild LSbodyBuild, religion LSreligion, alcohol LSalcohol) {
        this.LSsmoker = LSsmoker;
        this.LSathlete = LSathlete;
        this.LSfeed = LSfeed;
        this.LSbodyBuild = LSbodyBuild;
        this.LSreligion = LSreligion;
        this.LSalcohol = LSalcohol;
    }

    // Random LifeStyle
    public LifeStyle() {
    }

    public void setRandomLifeStyle() {
        setLSsmoker(smoker.randomLSsmoker());
        setLSathlete(athlete.randomLSathlete());
        setLSfeed(feed.randomLSfeed());
        setLSbodyBuild(bodyBuild.randomLSbodyBuild());
        setLSreligion(religion.randomLSreligion());
        setLSalcohol(alcohol.randomLSalcohol());
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

    public religion getLSreligion() {
        return LSreligion;
    }

    public void setLSreligion(religion LSreligion) {
        this.LSreligion = LSreligion;
    }

    public alcohol getLSalcohol() {
        return LSalcohol;
    }

    public void setLSalcohol(alcohol LSalcohol) {
        this.LSalcohol = LSalcohol;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LifeStyle{");
        sb.append("LSsmoker=").append(LSsmoker);
        sb.append(", LSathlete=").append(LSathlete);
        sb.append(", LSfeed=").append(LSfeed);
        sb.append(", LSbodyBuild=").append(LSbodyBuild);
        sb.append(", LSreligion=").append(LSreligion);
        sb.append(", LSalcohol=").append(LSalcohol);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public LifeStyle clone() throws CloneNotSupportedException {
        return (LifeStyle) super.clone();
    }
}
