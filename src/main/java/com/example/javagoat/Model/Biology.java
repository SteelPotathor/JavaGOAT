package com.example.javagoat.Model;

import com.example.javagoat.Model.names.NameGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Biology implements Serializable, Cloneable {

    // Frozen value: keeps the existing .dat files readable after code changes.
    private static final long serialVersionUID = 3335013214069143045L;

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

    public void setRandomBiology() {
        Random random = new Random();
        setAge(random.nextInt(40) + 20);
        setBsex(sex.randomSex());
        setBethnicity(ethnicity.randomEthnicity());
        setQi(gauss_law_simulation(100, 15));
    }

    // Standard normal value (Box-Muller). It used to be cast to int, so it was almost always -1, 0 or 1
    // and every generated IQ was 85, 100 or 115. 1 - nextDouble() is in ]0, 1], so log() never receives 0.
    public double gauss_law_simulation_classic() {
        Random random = new Random();
        double randomNumber = 1.0 - random.nextDouble();
        double randomNumber2 = random.nextDouble();

        return Math.sqrt(-2 * Math.log(randomNumber)) * Math.cos(2 * Math.PI * randomNumber2);
    }

    public int gauss_law_simulation(int average, int standard_deviation) {
        double number = gauss_law_simulation_classic();
        return (int) Math.round(average + standard_deviation * number);
    }

    public String[] getRandomName() {
        return new NameGenerator(this).getRandomName();
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