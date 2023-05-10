package com.example.javagoat.back;

import java.io.Serializable;

public class Identity extends Biology implements Serializable, Cloneable {

    public String lastname;
    public String firstname;
    public int noId;
    public static int currentMembers = 0;


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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getNoId() {
        return noId;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setNoId(int noId) {
        this.noId = noId;
    }

    public static int getCurrentMembers() {
        return currentMembers;
    }

    public static void setCurrentMembers(int currentMembers) {
        Identity.currentMembers = currentMembers;
    }


    @Override
    public String toString() {
        String sb = "Identity{" + "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", noId=" + noId +
                super.toString() +
                '}';
        return sb;
    }

}