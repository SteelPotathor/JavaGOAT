package com.example.javagoat.back;

public class Preferences {

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
        this.physicalAttributes = new PhysicalAttributes();
        this.biology = new Biology();
        this.lifestyle = new LifeStyle();

        if (this.biology.getBsex() == Biology.sex.MALE) {
            this.physicalAttributes.setSize(this.physicalAttributes.getSize() + 15);
        }
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

    public String toString() {
        return "Preference :  [" + getBiology() + " | " + getPhysicalAttributes() + " | " + getLifestyle() + "]";
    }

}
