package com.example.javagoat.back;

import java.io.Serializable;

public class Identity extends Biology implements Serializable, Cloneable {

    // currentMembers was used for test
    public static int currentMembers = 0;
    public String lastname;
    public String firstname;
    public int noId;


    // Customised Identity
    public Identity(int age, sex sex, ethnicity ethnicity, int qi, String lastname, String firstname, int noid) {
        super(age, sex, ethnicity, qi);
        this.lastname = lastname;
        this.firstname = firstname;
        this.noId = noid;
    }

    // Random Identity
    public Identity() {
    }

    public void setRandomIdentity() {
        this.setRandomBiology();
        this.setLastname(getRandomName()[1]);
        this.setFirstname(getRandomName()[0]);
        currentMembers++;
        this.setNoId(currentMembers);
    }

    public static int getCurrentMembers() {
        return currentMembers;
    }

    public static void setCurrentMembers(int currentMembers) {
        Identity.currentMembers = currentMembers;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getNoId() {
        return noId;
    }

    public void setNoId(int noId) {
        this.noId = noId;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Identity{");
        sb.append("lastname='").append(lastname).append('\'');
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", noId=").append(noId);
        sb.append(", age=").append(age);
        sb.append(", sex=").append(Bsex);
        sb.append('}');
        return sb.toString();
    }
}