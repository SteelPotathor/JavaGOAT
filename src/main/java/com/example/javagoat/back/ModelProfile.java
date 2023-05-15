package com.example.javagoat.back;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class ModelProfile implements Serializable {

    public static HashMap<Integer /*id*/, Profile> profileHashMap = new HashMap<>(); // This DS must be accessible and be the same for everyone

    public ModelProfile() {
    }

    public PriorityQueue<Profile> toPriorityQueue() {
        return new PriorityQueue<>(profileHashMap.values());
    }

    public Set<String> getAllLastName() {
        return new HashSet<>(profileHashMap.values().stream()
                .map(profile -> profile.getIdentity().getLastname()).toList());
    }

    public Set<String> getAllFirstName() {
        return new HashSet<>(profileHashMap.values().stream()
                .map(profile -> profile.getIdentity().getFirstname()).toList());
    }


    // Check if this combination of String exists in the DS
    public static boolean correspondingName(String lastname, String firstname) {
        List<Profile> list = profileHashMap.values().stream().
                filter(profile -> profile.identity.lastname.toUpperCase().startsWith(lastname.toUpperCase())
                        && profile.identity.firstname.toUpperCase().startsWith(firstname.toUpperCase())).toList();
        return list.size() >= 1;
    }

    public Set<Profile> suggestion(String firstname, String lastname) {
        return profileHashMap.values().stream()
                .filter(profile -> profile.identity.firstname.toUpperCase().startsWith(firstname.toUpperCase())
                        && profile.identity.lastname.toUpperCase().startsWith(lastname.toUpperCase()))
                .collect(Collectors.toSet());
    }

    public Set<Profile> searchProfile(String firstname, String lastname, int minSize, int maxSize, int minAge, int maxAge, List<String> hairType, List<String> hairColor, List<String> ethnicity, List<String> bodybuild, List<String> sex) {
        Set<Profile> set = profileHashMap.values().stream()
                .filter(profile -> profile.identity.firstname.toUpperCase().startsWith(firstname.toUpperCase())
                        && profile.identity.lastname.toUpperCase().startsWith(lastname.toUpperCase())
                        && profile.physicalAttributes.size >= minSize
                        && profile.physicalAttributes.size <= maxSize
                        && profile.identity.age >= minAge
                        && profile.identity.age <= maxAge)
                .collect(Collectors.toSet());

        if (!hairType.isEmpty()) {
            set.retainAll(searchHairType(hairType));
        }

        if (!hairColor.isEmpty()) {
            set.retainAll(searchHairColor(hairColor));
        }

        if (!ethnicity.isEmpty()) {
            set.retainAll(searchEthnicity(ethnicity));
        }
        if (!bodybuild.isEmpty()) {
            set.retainAll(searchBodyBuild(bodybuild));
        }
        if (!sex.isEmpty()) {
            set.retainAll(searchSex(sex));
        }
        return set;
    }


    public Set<Profile> searchHairType(List<String> hairType) {
        List<String> hairTypeMAJ = hairType.stream().map(String::toUpperCase).toList();
        return profileHashMap.values().stream()
                .filter(profile -> hairTypeMAJ.contains(String.valueOf(profile.physicalAttributes.PAhairType).toUpperCase()))
                .collect(Collectors.toSet());
    }


    public Set<Profile> searchHairColor(List<String> hairColor) {
        List<String> hairColorMAJ = hairColor.stream().map(String::toUpperCase).toList();
        return profileHashMap.values().stream()
                .filter(profile -> hairColorMAJ.contains(String.valueOf(profile.physicalAttributes.PAhairColor).toUpperCase()))
                .collect(Collectors.toSet());
    }

    public Set<Profile> searchEthnicity(List<String> ethnicity) {
        List<String> ethnicityMAJ = ethnicity.stream().map(String::toUpperCase).toList();
        return profileHashMap.values().stream()
                .filter(profile -> ethnicityMAJ.contains(String.valueOf(profile.identity.getBethnicity()).toUpperCase()))
                .collect(Collectors.toSet());
    }

    public Set<Profile> searchBodyBuild(List<String> bodyBuild) {
        List<String> bodyBuildMAJ = bodyBuild.stream().map(String::toUpperCase).toList();
        return profileHashMap.values().stream()
                .filter(profile -> bodyBuildMAJ.contains(String.valueOf(profile.lifeStyle.getLSbodyBuild()).toUpperCase()))
                .collect(Collectors.toSet());
    }

    public Set<Profile> searchSex(List<String> sex) {
        List<String> sexMAJ = sex.stream().map(String::toUpperCase).collect(Collectors.toList());
        return profileHashMap.values().stream()
                .filter(profile -> sexMAJ.contains(String.valueOf(profile.identity.getBsex()).toUpperCase()))
                .collect(Collectors.toSet());
    }


    public HashMap<Integer, Profile> getProfileHashMap() {
        return profileHashMap;
    }

    public void setProfileHashMap(HashMap<Integer, Profile> profileHashMap) {
        this.profileHashMap = profileHashMap;
    }

    @Override
    public String toString() {
        String s = "";
        for (Integer i : profileHashMap.keySet()) {
            s += i + ": " + profileHashMap.get(i) + "\n";
        }
        return s;
    }

}
