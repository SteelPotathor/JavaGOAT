package com.example.javagoat.back;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PhysicalAttributes implements Serializable, Cloneable {

    private static final Random random = new Random();
    public int size;
    public hairColor PAhairColor;
    public hairType PAhairType;
    public hairLength PAhairLength;

    // Customised PhysicalAttributes -> Add Profile
    public PhysicalAttributes(int size, hairColor hColor, hairType hType, hairLength hLength) {
        this.size = size;
        this.PAhairColor = hColor;
        this.PAhairType = hType;
        this.PAhairLength = hLength;
    }

    // Random PhysicalAttributes
    public PhysicalAttributes() {
    }

    public void setRandomPhysicalAttributes() {
        setSize(random.nextInt(30) + 150);
        setHairColor(hairColor.randomHColor());
        setHairType(hairType.randomHType());
        setHairLength(hairLength.randomHLength());
    }

    @Override
    public PhysicalAttributes clone() throws CloneNotSupportedException {
        return (PhysicalAttributes) super.clone();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public hairColor getHairColor() {
        return PAhairColor;
    }

    public void setHairColor(hairColor hairColor) {
        this.PAhairColor = hairColor;
    }

    public hairType getHairType() {
        return PAhairType;
    }

    public void setHairType(hairType hairType) {
        this.PAhairType = hairType;
    }

    public hairLength getHairLength() {
        return PAhairLength;
    }

    public void setHairLength(hairLength hairLength) {
        this.PAhairLength = hairLength;
    }

    public hairColor getPAhairColor() {
        return PAhairColor;
    }

    public void setPAhairColor(hairColor PAhairColor) {
        this.PAhairColor = PAhairColor;
    }

    public hairType getPAhairType() {
        return PAhairType;
    }

    public void setPAhairType(hairType PAhairType) {
        this.PAhairType = PAhairType;
    }

    public hairLength getPAhairLength() {
        return PAhairLength;
    }

    public void setPAhairLength(hairLength PAhairLength) {
        this.PAhairLength = PAhairLength;
    }

    @Override
    public String toString() {
        String sb = "PhysicalAttributes{" + "size=" + size +
                ", PAhairColor=" + PAhairColor +
                ", PAhairType=" + PAhairType +
                ", PAhairLength=" + PAhairLength +
                '}';
        return sb;
    }

    public enum hairColor {
        BLONDE,
        RED,
        BRUNETTE,
        BLACK;

        private static final List<hairColor> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static hairColor randomHColor() {
            return VALUES.get(random.nextInt(SIZE));
        }

    }

    public enum hairType {
        STRAIGHT,
        WAVY,
        CURLY;

        private static final List<hairType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static hairType randomHType() {
            return VALUES.get(random.nextInt(SIZE));
        }

    }

    public enum hairLength {
        LONG,
        HALF_LONG,
        SHORT;

        private static final List<hairLength> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static hairLength randomHLength() {
            return VALUES.get(random.nextInt(SIZE));
        }

    }
}
