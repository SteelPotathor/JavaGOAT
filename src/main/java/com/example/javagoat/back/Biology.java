package com.example.javagoat.back;

import com.example.javagoat.back.names.NameGenerator;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Biology implements Serializable {

    public int age;
    public sex Bsex;
    public ethnicity Bethnicity;
    public int qi;

    public static final Random random = new Random();

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
    public Biology(int age, sex Bsex, ethnicity Bethnicity, int qi) {
        this.age = age;
        this.Bsex = Bsex;
        this.Bethnicity = Bethnicity;
        this.qi = qi;
    }

    // Random Biology
    public Biology() {}

    public void setRandomBiology() {
        setAge(random.nextInt(40) + 20);
        setBsex(sex.randomSex());
        setBethnicity(ethnicity.randomEthnicity());
        setQi(random.nextInt());
    }

    public String[] getRandomName() {
        return new NameGenerator(this).getRandomName();
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

    public int getQi() {
        return qi;
    }

    public void setQi(int qi) {
        this.qi = qi;
    }

    public String toString() {
        return "Biology : {" + getBsex() + ", " + getAge() + ", " + getBethnicity() + "}";
    }

}