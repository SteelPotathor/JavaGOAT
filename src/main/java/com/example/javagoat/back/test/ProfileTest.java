package com.example.javagoat.back.test;

public class ProfileTest {

    int noId;
    int age;

    public ProfileTest(int noId, int age) {
        this.noId = noId;
        this.age = age;
    }

    public int calculDistance(ProfileTest profileTest) {
        return this.age - profileTest.age;
    }
}

