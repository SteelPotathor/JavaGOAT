package com.example.javagoat.Model;

import java.util.List;
import java.util.Random;

public class Biology implements Cloneable {


    public int age;
    public sex Bsex;
    public ethnicity Bethnicity;
    public int qi;

    public enum sex {
        MALE, FEMALE;
        public static final Random random = new Random();
        private static final List<sex> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();

        public static sex randomSex() {
            return VALUES.get(random.nextInt(SIZE));
        }
    }

    public enum ethnicity {
        WHITE, BLACK, ASIAN, LATINO;
        public static final Random random = new Random();
        private static final List<ethnicity> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();

        public static ethnicity randomEthnicity() {
            return VALUES.get(random.nextInt(SIZE));
        }
    }

    public Biology() {

    }

    // Customised Biology -> Add Profile
    public Biology(int age, sex Bsex, ethnicity Bethnicity, int qi) {
        this.age = age;
        this.Bsex = Bsex;
        this.Bethnicity = Bethnicity;
        this.qi = qi;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public sex getBsex() {
        return Bsex;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Biology{");
        sb.append("age=").append(age);
        sb.append(", Bsex=").append(Bsex);
        sb.append(", Bethnicity=").append(Bethnicity);
        sb.append(", qi=").append(qi);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}