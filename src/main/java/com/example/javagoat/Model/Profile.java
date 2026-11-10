package com.example.javagoat.Model;

import javafx.scene.image.ImageView;

import java.time.LocalDate;


public class Profile implements Comparable<Profile> {

    public Identity identity;
    public PhysicalAttributes physicalAttributes;
    public LifeStyle lifeStyle;
    public Preferences preferences;
    public Passion passion;

    public ModelHistoMatch modelHisto;

    public String imageView;
    public int priority = 1; // Base priority value (computed at start-up by CheckPriorityStart)
    // Feedback received from the people this profile was matched with (derived from their votes when loading)
    public double positif = 0;
    public double total = 0;
    // Day the profile was registered in the agency (null when unknown, e.g. generated profiles)
    public LocalDate created;


    public Profile(Identity identity, PhysicalAttributes physicalAttributes, LifeStyle lifeStyle, Preferences preferences, Passion passion, String imageView) {
        this.identity = identity;
        this.physicalAttributes = physicalAttributes;
        this.lifeStyle = lifeStyle;
        this.preferences = preferences;
        this.passion = passion;
        this.modelHisto = new ModelHistoMatch();
        this.imageView = imageView;
    }

    public ProfileTableView toProfileTableView() {
        ImageView img = new ImageView(ImageLoader.profileImage(this.imageView));
        img.setPreserveRatio(true);
        img.setFitHeight(40);
        img.setFitWidth(40);
        return new ProfileTableView(this.identity.noId, this.priority, img, this.identity.firstname, this.identity.lastname, this.identity.age, this.identity.Bsex.toString());
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
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

    public void setPositif(double positif) {
        this.positif = positif;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getRatio() {
        return this.total == 0 ? 0 : this.positif / this.total * 100;
    }


    @Override
    public int compareTo(Profile o) {
        return this.getPriority() - o.getPriority();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Profile{");
        sb.append("identity=").append(identity);
        sb.append(", physicalAttributes=").append(physicalAttributes);
        sb.append(", lifeStyle=").append(lifeStyle);
        sb.append(", preferences=").append(preferences);
        sb.append(", passion=").append(passion);
        sb.append(", modelHisto=").append(modelHisto);
        sb.append(", imageView='").append(imageView).append('\'');
        sb.append(", priority=").append(priority);
        sb.append(", positif=").append(positif);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}
