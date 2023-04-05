package com.example.javagoat.back;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PhysicalAttributes {

    public int size;
    public hairColor PAhairColor;
    public hairType PAhairType;
    public hairLength PAhairLength;

    private static final Random random = new Random();

    public enum hairColor {
        BLONDE, RED, BRUNETTE, BLACK;

        private static final List<hairColor> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static hairColor randomHColor()  {
            return VALUES.get(random.nextInt(SIZE));
        }

    }

    public enum hairType {
        STRAIGHT, WAVY, CURLY ;

        private static final List<hairType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static hairType randomHType()  {
            return VALUES.get(random.nextInt(SIZE));
        }

    }

    public enum hairLength {
        LONG, HALF_LONG, SHORT;

        private static final List<hairLength> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static hairLength randomHLength()  {
            return VALUES.get(random.nextInt(SIZE));
        }

    }

    // Customised PhysicalAttributes
    public PhysicalAttributes(int size, hairColor hColor, hairType hType, hairLength hLength) {
        this.size = size;
        this.PAhairColor = hColor;
        this.PAhairType = hType;
        this.PAhairLength = hLength;
    }

    // Random PhysicalAttributes
    public PhysicalAttributes() {
        this.size = random.nextInt(30) + 150;
        this.PAhairColor = hairColor.randomHColor();
        this.PAhairType = hairType.randomHType();
        this.PAhairLength = hairLength.randomHLength();
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


    public String toString() {
        return "Physical Attributes : {" + getSize() + ", " + getHairColor() + ", " + getHairType() + ", " + getHairLength() + "}";
    }

}
