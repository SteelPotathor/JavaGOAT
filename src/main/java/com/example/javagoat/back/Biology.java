package com.example.javagoat.back;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Biology {

    public int age;
    public sex Bsex;
    public ethnicity Bethnicity;

    private static final Random random = new Random();

    public enum sex {
        MALE, FEMALE;

        private static final List<sex> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static sex randomSex() {
            return VALUES.get(random.nextInt(SIZE));
        }

    }

    public enum ethnicity {
        WHITE, BLACK, ASIAN, LATINO;

        private static final List<ethnicity> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static ethnicity randomEthnicity() {
            return VALUES.get(random.nextInt(SIZE));
        }

    }

    // Customised Biology
    public Biology(int age, sex Bsex, ethnicity Bethnicity) {
        this.age = age;
        this.Bsex = Bsex;
        this.Bethnicity = Bethnicity;
    }

    // Random Biology
    public Biology() {
        this.age = random.nextInt(40) + 20;
        this.Bsex = sex.randomSex();
        this.Bethnicity = ethnicity.randomEthnicity();
    }

    public int getAge() {
        return age;
    }

    public sex getBsex() {
        return Bsex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBsex(sex bsex) {
        Bsex = bsex;
    }

    public ethnicity getBethnicity() {
        return Bethnicity;
    }

    public void setBethnicity(ethnicity bethnicity) {
        Bethnicity = bethnicity;
    }

    public String toString() {
        return "Biology : {" + getBsex() + ", " + getAge() + "}";
    }

}