package com.example.javagoat.back;

import java.util.HashMap;

public class Profile {

    public Identity identity;
    public PhysicalAttributes physicalAttributes;
    public LifeStyle lifeStyle;
    public Preferences preferences;
    public Passion passion;

    public Vecteur vectProfile;
    public Vecteur vectPreferences;

    public HashMap<String, Integer> attributesWeights = new HashMap<>() {{
        put("Size", 10);
        put("Age", 10);
        put("HairColor", 10);
        put("HairLenght", 10);
        put("HairType", 10);
        put("Smoker", 10);
        put("Athlete", 10);
        put("Feed", 10);
        put("BodyBuild", 10);
        put("Ethnicity", 10);
        put("Sex", 1000);
        put("Religion", 10);

    }};

    // Customised Profile
    public Profile(Identity identity, PhysicalAttributes physicalAttributes, LifeStyle lifeStyle, Preferences preferences, Passion passion) {
        this.identity = identity;
        this.physicalAttributes = physicalAttributes;
        this.lifeStyle = lifeStyle;
        this.preferences = preferences;
        this.passion = passion;
        // DONT REPEAT YOURSELF (DRY)
        if (this.identity.getBsex() == Biology.sex.MALE) {
            this.physicalAttributes.setSize(this.physicalAttributes.getSize() + 15);
        }

        this.vectProfile = new Vecteur(this);
        this.vectPreferences = new Vecteur(this.getPreferences());
    }

    // Random Profile
    public Profile() {
        this.identity = new Identity();
        this.physicalAttributes = new PhysicalAttributes();
        this.lifeStyle = new LifeStyle();
        this.preferences = new Preferences();
        this.passion = new Passion();
        // DONT REPEAT YOURSELF (DRY)
        if (this.identity.getBsex() == Biology.sex.MALE) {
            this.physicalAttributes.setSize(this.physicalAttributes.getSize() + 15);
        }

        this.vectProfile = new Vecteur(this);
        this.vectPreferences = new Vecteur(this.getPreferences());
    }

    public float distance(Profile profile) {
        float distance = Math.abs(profile.vectProfile.getxSize() - this.vectPreferences.getxSize())
                + Math.abs(profile.vectProfile.getxAge() - this.vectPreferences.getxAge())
                + Math.abs(profile.vectProfile.getxHairColor() - this.vectPreferences.getxHairColor())
                + Math.abs(profile.vectProfile.getxHairLenght() - this.vectPreferences.getxHairLenght())
                + Math.abs(profile.vectProfile.getxHairType() - this.vectPreferences.getxHairType())
                + Math.abs(profile.vectProfile.getxSmoker() - this.vectPreferences.getxSmoker())
                + Math.abs(profile.vectProfile.getxAthlete() - this.vectPreferences.getxAthlete())
                + Math.abs(profile.vectProfile.getxFeed() - this.vectPreferences.getxFeed())
                + Math.abs(profile.vectProfile.getxBodyBuild() - this.vectPreferences.getxBodyBuild())
                + Math.abs(profile.vectProfile.getxEthnicity() - this.vectPreferences.getxEthnicity())
                + Math.abs(profile.vectProfile.getxSex() - this.vectPreferences.getxSex());

        if (profile.getLifeStyle().getLSreligion() != this.getPreferences().getLifestyle().getLSreligion()) {
            distance ++;
        }

        return distance;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public PhysicalAttributes getPhysicalAttributes() {
        return physicalAttributes;
    }

    public void setPhysicalAttributes(PhysicalAttributes physicalAttributes) {
        this.physicalAttributes = physicalAttributes;
    }

    public LifeStyle getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(LifeStyle lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public Passion getPassion() {
        return passion;
    }

    public void setPassion(Passion passion) {
        this.passion = passion;
    }

    public Vecteur getVectProfile() {
        return vectProfile;
    }

    public void setVectProfile(Vecteur vectProfile) {
        this.vectProfile = vectProfile;
    }

    public Vecteur getVectPreferences() {
        return vectPreferences;
    }

    public void setVectPreferences(Vecteur vectPreferences) {
        this.vectPreferences = vectPreferences;
    }

    public String toString() {
        return "Size : " + this.getPhysicalAttributes().getSize() +
                ", hColor : " + this.getPhysicalAttributes().getHairColor() +
                ", hLenght : " + this.getPhysicalAttributes().getHairLength() +
                ", hType : " + this.getPhysicalAttributes().getHairType();
    }
}
