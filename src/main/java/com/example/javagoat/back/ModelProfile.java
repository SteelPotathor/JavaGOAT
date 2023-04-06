package com.example.javagoat.back;

import java.util.HashMap;

public class ModelProfile {

    public HashMap<Integer /*id*/, Profile> profileHashMap;

    public ModelProfile() {
        this.profileHashMap = new HashMap<>();
    }

    public HashMap<Integer, Profile> getProfileHashMap() {
        return profileHashMap;
    }

    public void setProfileHashMap(HashMap<Integer, Profile> profileHashMap) {
        this.profileHashMap = profileHashMap;
    }

}
