package com.example.javagoat.back;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class ModelProfile {

    public static HashMap<Integer /*id*/, Profile> profileHashMap = new HashMap<>();

    public ModelProfile() {
    }

    public Set searchProfile(String firstname, String lastname, int minSize, int maxSize, int minAge, int maxAge, PhysicalAttributes.hairType hairType, PhysicalAttributes.hairColor hairColor, Biology.ethnicity ethnicity, LifeStyle.bodyBuild bodybuild) {
        Set<Profile> set = this.profileHashMap.values().stream()
                .filter(profile -> profile.identity.firstname.toUpperCase().startsWith(firstname.toUpperCase())
                        && profile.identity.lastname.toUpperCase().startsWith(lastname.toUpperCase())
                        && profile.physicalAttributes.size >= minSize
                        && profile.physicalAttributes.size <= maxSize
                        && profile.identity.age >= minAge
                        && profile.identity.age <= maxAge)
                .collect(Collectors.toSet());
        set.retainAll(searchHairType(hairType));
        set.retainAll(searchHairColor(hairColor));
        set.retainAll(searchEthnicity(ethnicity));
        set.retainAll(searchBodyBuild(bodybuild));
        return set;
    }

    public Set searchHairType(PhysicalAttributes.hairType hairType) {
        return this.profileHashMap.values().stream()
                .filter(profile -> profile.physicalAttributes.PAhairType.equals(hairType))
                .collect(Collectors.toSet());
    }

    public Set searchHairColor(PhysicalAttributes.hairColor hairColor) {
        return this.profileHashMap.values().stream()
                .filter(profile -> profile.physicalAttributes.PAhairColor.equals(hairColor))
                .collect(Collectors.toSet());
    }

    public Set searchEthnicity(Biology.ethnicity ethnicity) {
        return this.profileHashMap.values().stream()
                .filter(profile -> profile.identity.getBethnicity().equals(ethnicity))
                .collect(Collectors.toSet());
    }

    public Set searchBodyBuild(LifeStyle.bodyBuild bodyBuild) {
        return this.profileHashMap.values().stream()
                .filter(profile -> profile.lifeStyle.getLSbodyBuild().equals(bodyBuild))
                .collect(Collectors.toSet());
    }


    public HashMap<Integer, Profile> getProfileHashMap() {
        return profileHashMap;
    }

    public void setProfileHashMap(HashMap<Integer, Profile> profileHashMap) {
        this.profileHashMap = profileHashMap;
    }

}
