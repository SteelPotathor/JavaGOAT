package com.example.javagoat.back;

import com.example.javagoat.back.names.NameGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Biology implements Serializable, Cloneable {

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

    public int gauss_law_simulation_classic() {
        Random random = new Random();
        double randomNumber = random.nextFloat(0, 1);
        double randomNumber2 = random.nextFloat(0, 1);

        return (int) (Math.sqrt(-2 * Math.log(randomNumber)) * Math.cos(2 * Math.PI * randomNumber2));
    }

    public int gauss_law_simulation(int average, int standard_deviation) {
        int number = gauss_law_simulation_classic();
        return average + standard_deviation * number;
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