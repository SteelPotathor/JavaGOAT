package com.example.javagoat.Model;

import java.io.Serializable;

public class Preferences implements Serializable, Cloneable {

    public PhysicalAttributes physicalAttributes;
    public Biology biology;
    public LifeStyle lifestyle;

    // Customised Preferences
    public Preferences(PhysicalAttributes physicalAttributes, Biology biology, LifeStyle lifestyle) {
        this.physicalAttributes = physicalAttributes;
        this.biology = biology;
        this.lifestyle = lifestyle;

        if (this.biology.getBsex() == Biology.sex.MALE) {
            this.physicalAttributes.setSize(this.physicalAttributes.getSize() + 15);
        }
    }

    // Random Preferences
    public Preferences() {
    }

    public void setRandomPreferences() {
        this.physicalAttributes = new PhysicalAttributes();
        this.physicalAttributes.setRandomPhysicalAttributes();
        this.biology = new Biology();
        this.biology.setRandomBiology();
        this.lifestyle = new LifeStyle();
        this.lifestyle.setRandomLifeStyle();
        if (this.biology.getBsex() == Biology.sex.MALE) {
            this.physicalAttributes.setSize(this.physicalAttributes.getSize() + 15);
        }
    }

    @Override
    public Preferences clone() throws CloneNotSupportedException {
        Preferences clone = (Preferences) super.clone();
        clone.setPhysicalAttributes(clone.physicalAttributes.clone());
        clone.setBiology((Biology) clone.biology.clone());
        clone.setLifestyle(clone.lifestyle.clone());

        return clone;
    }

    public PhysicalAttributes getPhysicalAttributes() {
        return physicalAttributes;
    }

    public void setPhysicalAttributes(PhysicalAttributes physicalAttributes) {
        this.physicalAttributes = physicalAttributes;
    }

    public Biology getBiology() {
        return biology;
    }

    public void setBiology(Biology biology) {
        this.biology = biology;
    }

    public LifeStyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(LifeStyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Preferences{");
        sb.append("physicalAttributes=").append(physicalAttributes);
        sb.append(", biology=").append(biology);
        sb.append(", lifestyle=").append(lifestyle);
        sb.append('}');
        return sb.toString();
    }
}
