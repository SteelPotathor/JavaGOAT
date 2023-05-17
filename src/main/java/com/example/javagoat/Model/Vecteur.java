package com.example.javagoat.Model;

import java.io.Serializable;

public class Vecteur implements Serializable, Cloneable {

    public float xSize;
    public float xAge;
    public float xHairColor;
    public float xHairLenght;
    public float xHairType;
    public float xSmoker;
    public float xAthlete;
    public float xFeed;
    public float xBodyBuild;
    public float xEthnicity;
    public float xSex;
    public float xAlcohol;
    public float xIQ;


    public Vecteur() {
    }

    public void setProfileVecteur(Profile profile) {
        this.xSize = (float) profile.getPhysicalAttributes().getSize() / (float) 195;
        this.xAge = (float) profile.getIdentity().getAge() / (float) 100;
        this.xHairColor = getHairColorCoord(profile.getPhysicalAttributes().getHairColor());
        this.xHairLenght = getHairLenghtCoord(profile.getPhysicalAttributes().getHairLength());
        this.xHairType = getHairTypeCoord(profile.getPhysicalAttributes().getHairType());
        this.xSmoker = getSmokerCoord(profile.getLifeStyle().getLSsmoker());
        this.xAthlete = getAthleteCoord(profile.getLifeStyle().getLSathlete());
        this.xFeed = getFeedCoord(profile.getLifeStyle().getLSfeed());
        this.xBodyBuild = getBodyBuildCoord(profile.getLifeStyle().getLSbodyBuild());
        this.xEthnicity = getEthnicityCoord(profile.getIdentity().getBethnicity());
        this.xSex = getSexCoord(profile.getIdentity().getBsex());
        this.xAlcohol = getAlcoholCoord(profile.getLifeStyle().getLSalcohol());
        this.xIQ = (float) profile.getIdentity().getQi() / (float) 150;
    }

    public void setPrefVecteur(Preferences preferences) {
        this.xSize = (float) preferences.getPhysicalAttributes().getSize() / (float) 195;
        this.xAge = (float) preferences.getBiology().getAge() / (float) 100;
        this.xHairColor = getHairColorCoord(preferences.getPhysicalAttributes().getHairColor());
        this.xHairLenght = getHairLenghtCoord(preferences.getPhysicalAttributes().getHairLength());
        this.xHairType = getHairTypeCoord(preferences.getPhysicalAttributes().getHairType());
        this.xSmoker = getSmokerCoord(preferences.getLifestyle().getLSsmoker());
        this.xAthlete = getAthleteCoord(preferences.getLifestyle().getLSathlete());
        this.xFeed = getFeedCoord(preferences.getLifestyle().getLSfeed());
        this.xBodyBuild = getBodyBuildCoord(preferences.getLifestyle().getLSbodyBuild());
        this.xEthnicity = getEthnicityCoord(preferences.getBiology().getBethnicity());
        this.xSex = getSexCoord(preferences.getBiology().getBsex());
        this.xAlcohol = getAlcoholCoord(preferences.getLifestyle().getLSalcohol());
        this.xIQ = (float) preferences.getBiology().getQi() / (float) 150;
    }

    public float getHairColorCoord(PhysicalAttributes.hairColor hairColor) {
        switch (hairColor) {
            case BLONDE -> {
                return (float) 0.25;
            }
            case RED -> {
                return (float) 0.5;
            }
            case BRUNETTE -> {
                return (float) 0.75;
            }
            case BLACK -> {
                return (float) 1;
            }
            default -> {
                return (float) 0;
            }
        }
    }

    public float getHairTypeCoord(PhysicalAttributes.hairType hairType) {
        switch (hairType) {
            case STRAIGHT -> {
                return (float) 0.33;
            }
            case WAVY -> {
                return (float) 0.66;
            }
            case CURLY -> {
                return (float) 1;
            }
            default -> {
                return (float) 0;
            }
        }
    }

    public float getHairLenghtCoord(PhysicalAttributes.hairLength hairLength) {
        switch (hairLength) {
            case LONG -> {
                return (float) 0.33;
            }
            case HALF_LONG -> {
                return (float) 0.66;
            }
            case SHORT -> {
                return (float) 1;
            }
            default -> {
                return (float) 0;
            }
        }
    }

    public float getSmokerCoord(LifeStyle.smoker smoker) {
        switch (smoker) {
            case NEVER -> {
                return (float) 0.25;
            }
            case OCCASIONNALY -> {
                return (float) 0.5;
            }
            case REGULARLY -> {
                return (float) 0.75;
            }
            case DAILY -> {
                return (float) 1;
            }
            default -> {
                return (float) 0;
            }
        }
    }

    public float getAthleteCoord(LifeStyle.athlete athlete) {
        switch (athlete) {
            case SPORTY -> {
                return (float) 0.33;
            }
            case SEDENTARY -> {
                return (float) 0.66;
            }
            case LAZY -> {
                return (float) 1;
            }
            default -> {
                return (float) 0;
            }
        }
    }

    public float getFeedCoord(LifeStyle.feed feed) {
        switch (feed) {
            case VEGAN -> {
                return (float) 0.25;
            }
            case VEGETARIAN -> {
                return (float) 0.5;
            }
            case OMNIVORE -> {
                return (float) 0.75;
            }
            case FLEXIVORE -> {
                return (float) 1;
            }
            default -> {
                return (float) 0;
            }
        }
    }

    public float getBodyBuildCoord(LifeStyle.bodyBuild bodyBuild) {
        switch (bodyBuild) {
            case SKINNY -> {
                return (float) 0.33;
            }
            case MEDIUM -> {
                return (float) 0.66;
            }
            case OVERWEIGHT -> {
                return (float) 1;
            }
            default -> {
                return (float) 0;
            }
        }
    }

    public float getEthnicityCoord(Biology.ethnicity ethnicity) {
        switch (ethnicity) {
            case WHITE -> {
                return (float) 0.25;
            }
            case ASIAN -> {
                return (float) 0.5;
            }
            case LATINO -> {
                return (float) 0.75;
            }
            case BLACK -> {
                return (float) 1;
            }
            default -> {
                return (float) 0;
            }
        }
    }

    public float getSexCoord(Biology.sex sex) {
        if (sex == Biology.sex.MALE) {
            return (float) 0;
        } else {
            return (float) 1;
        }
    }

    public float getAlcoholCoord(LifeStyle.alcohol alcohol) {
        switch (alcohol) {
            case NEVER -> {
                return (float) 0.25;
            }
            case OCCASIONNALY -> {
                return (float) 0.5;
            }
            case REGULARLY -> {
                return (float) 0.75;
            }
            case DAILY -> {
                return (float) 1;
            }
            default -> {
                return (float) 0;
            }
        }
    }

    @Override
    public Vecteur clone() throws CloneNotSupportedException {
        return (Vecteur) super.clone();
    }


    public float getxSize() {
        return xSize;
    }

    public void setxSize(float xSize) {
        this.xSize = xSize;
    }

    public float getxAge() {
        return xAge;
    }

    public void setxAge(float xAge) {
        this.xAge = xAge;
    }

    public float getxHairColor() {
        return xHairColor;
    }

    public void setxHairColor(float xHairColor) {
        this.xHairColor = xHairColor;
    }

    public float getxHairLenght() {
        return xHairLenght;
    }

    public void setxHairLenght(float xHairLenght) {
        this.xHairLenght = xHairLenght;
    }

    public float getxHairType() {
        return xHairType;
    }

    public void setxHairType(float xHairType) {
        this.xHairType = xHairType;
    }

    public float getxSmoker() {
        return xSmoker;
    }

    public void setxSmoker(float xSmoker) {
        this.xSmoker = xSmoker;
    }

    public float getxAthlete() {
        return xAthlete;
    }

    public void setxAthlete(float xAthlete) {
        this.xAthlete = xAthlete;
    }

    public float getxFeed() {
        return xFeed;
    }

    public void setxFeed(float xFeed) {
        this.xFeed = xFeed;
    }

    public float getxBodyBuild() {
        return xBodyBuild;
    }

    public void setxBodyBuild(float xBodyBuild) {
        this.xBodyBuild = xBodyBuild;
    }

    public float getxEthnicity() {
        return xEthnicity;
    }

    public void setxEthnicity(float xEthnicity) {
        this.xEthnicity = xEthnicity;
    }

    public float getxSex() {
        return xSex;
    }

    public void setxSex(float xSex) {
        this.xSex = xSex;
    }

    public float getxAlcohol() {
        return xAlcohol;
    }

    public void setxAlcohol(float xAlcohol) {
        this.xAlcohol = xAlcohol;
    }

    public float getxIQ() {
        return xIQ;
    }

    public void setxIQ(float xIQ) {
        this.xIQ = xIQ;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vecteur{");
        sb.append("xSize=").append(xSize);
        sb.append(", xAge=").append(xAge);
        sb.append(", xHairColor=").append(xHairColor);
        sb.append(", xHairLenght=").append(xHairLenght);
        sb.append(", xHairType=").append(xHairType);
        sb.append(", xSmoker=").append(xSmoker);
        sb.append(", xAthlete=").append(xAthlete);
        sb.append(", xFeed=").append(xFeed);
        sb.append(", xBodyBuild=").append(xBodyBuild);
        sb.append(", xEthnicity=").append(xEthnicity);
        sb.append(", xSex=").append(xSex);
        sb.append(", xAlcohol=").append(xAlcohol);
        sb.append(", xIQ=").append(xIQ);
        sb.append('}');
        return sb.toString();
    }
}
