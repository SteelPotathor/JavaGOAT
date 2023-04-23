package com.example.javagoat.back;

import java.util.HashMap;

public class DistanceCalculator {

    public Profile profile;

    public HashMap<String, Integer> attributesWeights = new HashMap<>() {{
        put("Size", 6);
        put("Age", 20);
        put("HairColor", 5);
        put("HairLength", 5);
        put("HairType", 5);
        put("Smoker", 10);
        put("Athlete", 7);
        put("Feed", 7);
        put("BodyBuild", 10);
        put("Ethnicity", 8);
        put("Sex", 1000);
        put("Religion", 1);
        put("Alcohol", 10);
        put("IQ", 10);
    }};

    public DistanceCalculator(Profile profile) {
        this.profile = profile;
    }

    public float getDistance(Profile profile) {
        float IQdistance = Math.abs(profile.vectProfile.getxIQ() - this.profile.vectPreferences.getxIQ());

        float distance = Math.abs(profile.vectProfile.getxSize() - this.profile.vectPreferences.getxSize()) * attributesWeights.get("Size")
                + Math.abs(profile.vectProfile.getxAge() - this.profile.vectPreferences.getxAge()) * attributesWeights.get("Age")
                + Math.abs(profile.vectProfile.getxHairColor() - this.profile.vectPreferences.getxHairColor()) * attributesWeights.get("HairColor")
                + Math.abs(profile.vectProfile.getxHairLenght() - this.profile.vectPreferences.getxHairLenght()) * attributesWeights.get("HairLength")
                + Math.abs(profile.vectProfile.getxHairType() - this.profile.vectPreferences.getxHairType()) * attributesWeights.get("HairType")
                + Math.abs(profile.vectProfile.getxSmoker() - this.profile.vectPreferences.getxSmoker()) * attributesWeights.get("Smoker")
                + Math.abs(profile.vectProfile.getxAthlete() - this.profile.vectPreferences.getxAthlete()) * attributesWeights.get("Athlete")
                + Math.abs(profile.vectProfile.getxFeed() - this.profile.vectPreferences.getxFeed()) * attributesWeights.get("Feed")
                + Math.abs(profile.vectProfile.getxBodyBuild() - this.profile.vectPreferences.getxBodyBuild()) * attributesWeights.get("BodyBuild")
                + Math.abs(profile.vectProfile.getxEthnicity() - this.profile.vectPreferences.getxEthnicity()) * attributesWeights.get("Ethnicity")
                + Math.abs(profile.vectProfile.getxSex() - this.profile.vectPreferences.getxSex()) * attributesWeights.get("Sex")
                + Math.abs(profile.vectProfile.getxAlcohol() - this.profile.vectPreferences.getxAlcohol()) * attributesWeights.get("Alcohol");

        if (profile.getLifeStyle().getLSreligion() != this.profile.getPreferences().getLifestyle().getLSreligion()) {
            distance += attributesWeights.get("Religion");
        }

        if (IQdistance > 30) {
            distance += IQdistance * attributesWeights.get("IQ");
        }

        return distance;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public HashMap<String, Integer> getAttributesWeights() {
        return attributesWeights;
    }

    public void setAttributesWeights(HashMap<String, Integer> attributesWeights) {
        this.attributesWeights = attributesWeights;
    }


}
