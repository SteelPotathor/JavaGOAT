package com.example.javagoat.back;

import java.io.File;
import java.io.Serializable;


public class Profile implements Serializable {

    public Identity identity;
    public PhysicalAttributes physicalAttributes;
    public LifeStyle lifeStyle;
    public Preferences preferences;
    public Passion passion;

    public Vecteur vectProfile;
    public Vecteur vectPreferences;

    public ModelHistoMatch modelHisto;

    File image = new File("src\\main\\java\\com\\example\\javagoat\\back\\images\\DefaultImage.png");


    // Customised Profile -> Add Profile
    public Profile(Identity identity, PhysicalAttributes physicalAttributes, LifeStyle lifeStyle, Preferences preferences, Passion passion, File file) {
        this.identity = identity;
        this.physicalAttributes = physicalAttributes;
        this.lifeStyle = lifeStyle;
        this.preferences = preferences;
        this.passion = passion;
        // DONT REPEAT YOURSELF (DRY)
        if (this.identity.getBsex() == Biology.sex.MALE) {
            this.physicalAttributes.setSize(this.physicalAttributes.getSize() + 15);
        }

        this.vectProfile = new Vecteur();
        this.vectPreferences = new Vecteur();
        this.vectProfile.setProfileVecteur(this);
        this.vectPreferences.setPrefVecteur(this.getPreferences());
        this.modelHisto = new ModelHistoMatch();
        this.image = file;
    }

    // Random Profile
    public Profile() {
    }

    public void setRandomProfile() {
        this.physicalAttributes = new PhysicalAttributes();
        this.physicalAttributes.setRandomPhysicalAttributes();

        this.identity = new Identity();
        this.identity.setRandomIdentity();

        this.lifeStyle = new LifeStyle();
        this.lifeStyle.setRandomLifeStyle();

        this.preferences = new Preferences();
        this.preferences.setRandomPreferences();

        if (this.identity.getBsex() == Biology.sex.MALE) {
            this.physicalAttributes.setSize(this.physicalAttributes.getSize() + 15);
        }

        this.passion = new Passion();
        this.passion.setRandomPassion();

        this.vectProfile = new Vecteur();
        this.vectPreferences = new Vecteur();
        this.vectProfile.setProfileVecteur(this);
        this.vectPreferences.setPrefVecteur(this.getPreferences());

        this.modelHisto = new ModelHistoMatch();
    }

    public float getDistance(Profile profile) {
        return new DistanceCalculator(this).getDistance(profile);
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

    public Passion getPassion() {
        return passion;
    }

    public void setPassion(Passion passion) {
        this.passion = passion;
    }

    public ModelHistoMatch getModelHisto() {
        return modelHisto;
    }

    public void setModelHisto(ModelHistoMatch modelHisto) {
        this.modelHisto = modelHisto;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String toString() {
        return " (" + this.getIdentity() + " | " + this.getPhysicalAttributes() + " | " + this.getLifeStyle() + ") ";
    }
}
