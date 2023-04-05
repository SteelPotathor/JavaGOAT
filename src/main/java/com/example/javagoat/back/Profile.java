package com.example.javagoat.back;

public class Profile {

    public Identity identity;
    public PhysicalAttributes physicalAttributes;
    public LifeStyle lifeStyle;
    public Preferences preferences;
    public Passion passion;


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


    public static void main(String[] args) {

        // Generation of 500 random profiles
        for (int i = 0; i < 10000; i++) {
            Profile p1 = new Profile();
            System.out.println(p1.getPhysicalAttributes());
            System.out.println(p1.getPreferences());
            System.out.println(p1.getPassion());
            System.out.println(p1.getLifeStyle());
            System.out.println(p1.getIdentity());
            System.out.println(" ");
        }

    }

}
