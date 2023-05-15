package com.example.javagoat.back;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.HashMap;


public class Profile implements Serializable, Comparable<Profile>, Cloneable {

    public Identity identity;
    public PhysicalAttributes physicalAttributes;
    public LifeStyle lifeStyle;
    public Preferences preferences;
    public Passion passion;

    public Vecteur vectProfile;
    public Vecteur vectPreferences;

    public ModelHistoMatch modelHisto;

    public String imageView;
    public int priority = 1; // Base priority value
    public double positif = 0;
    public double total = 0;


    // Customised Profile -> Add Profile
    public Profile(Identity identity, PhysicalAttributes physicalAttributes, LifeStyle lifeStyle, Preferences preferences, Passion passion, String imageView) {
        this.identity = identity;
        this.physicalAttributes = physicalAttributes;
        this.lifeStyle = lifeStyle;
        this.preferences = preferences;
        this.passion = passion;

        if (this.identity.getBsex() == Biology.sex.MALE) {
            this.physicalAttributes.setSize(this.physicalAttributes.getSize() + 15);
        }

        this.vectProfile = new Vecteur();
        this.vectPreferences = new Vecteur();
        this.vectProfile.setProfileVecteur(this);
        this.vectPreferences.setPrefVecteur(this.getPreferences());
        this.modelHisto = new ModelHistoMatch();
        this.imageView = imageView;
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

        this.imageView = "file:src/main/resources/com/example/javagoat/DefaultImage.png";
    }

    // Method used for multiple tests
    public void setRandomProfileExceptName(String lastname, String firstname) {
        this.physicalAttributes = new PhysicalAttributes();
        this.physicalAttributes.setRandomPhysicalAttributes();

        this.identity = new Identity();
        this.identity.setRandomIdentity();
        this.identity.firstname = firstname;
        this.identity.lastname = lastname;

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

        this.imageView = "file:src/main/resources/com/example/javagoat/DefaultImage.png";
    }

    public ProfileTableView toProfileTableView() {
        ImageView img = new ImageView(new Image(this.imageView));
        img.setPreserveRatio(true);
        img.setFitHeight(40);
        img.setFitWidth(40);
        return new ProfileTableView(this.identity.noId, this.priority, img, this.identity.firstname, this.identity.lastname, this.identity.age, this.identity.Bsex.toString());
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

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public double getPositif() {
        return positif;
    }

    public void setPositif(int positif) {
        this.positif = positif;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getRatio() {
        return this.total == 0 ? 0 : this.positif / this.total * 100;
    }

    public void setPositif(double positif) {
        this.positif = positif;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Profile{");
        sb.append("identity=").append(identity);
        sb.append(", physicalAttributes=").append(physicalAttributes);
        sb.append(", lifeStyle=").append(lifeStyle);
        sb.append(", preferences=").append(preferences);
        sb.append(", passion=").append(passion);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Profile o) {
        return this.getPriority() - o.getPriority();
    }

    @Override
    public Profile clone() throws CloneNotSupportedException {
        Profile clone = (Profile) super.clone();

        clone.setIdentity((Identity) clone.getIdentity().clone());
        clone.setPhysicalAttributes(clone.getPhysicalAttributes().clone());
        clone.setLifeStyle(clone.getLifeStyle().clone());
        clone.setPreferences(clone.getPreferences().clone());
        clone.setPassion(clone.getPassion().clone());
        clone.setVectProfile(clone.getVectProfile().clone());
        clone.setVectPreferences(clone.getVectPreferences().clone());

        return clone;
    }
}
