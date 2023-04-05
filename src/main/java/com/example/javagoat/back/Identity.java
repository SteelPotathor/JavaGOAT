package com.example.javagoat.back;


public class Identity extends Biology {

    public String lastname;
    public String firstname;
    public int noId;
    private static int currentMembers = 0;


    // Customised Identity
    public Identity(int age, sex sex, ethnicity ethnicity, String lastname, String firstname) {
        super(age, sex, ethnicity);
        this.lastname = lastname;
        this.firstname = firstname;
        currentMembers ++;
        this.noId = currentMembers;
    }

    // Random Identity
    public Identity() {
        super();
        this.lastname = "kebab";
        this.firstname = "italien";
        currentMembers ++;
        this.noId = currentMembers;
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

    public String toString() {
        return "Identity : {" + getNoId() + ", " + getFirstname() + ", " + getLastname() + ", " + getAge() + ", " + getBsex() + "}";
    }

}