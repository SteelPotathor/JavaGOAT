package com.example.javagoat.back;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ModelProfile {

    public static HashMap<Integer /*id*/, Profile> profileHashMap = new HashMap<>(); // This DS must be accessible and be the same for everyone

    public ModelProfile() {
    }

    public Set<String> getAllLastName() {
        Set<String> set = new HashSet<>();
        set.addAll(this.profileHashMap.values().stream()
                .map(profile -> profile.getIdentity().getLastname()).toList());
        return set;
    }

    public Set<String> getAllFirstName() {
        Set<String> set = new HashSet<>();
        set.addAll(this.profileHashMap.values().stream()
                .map(profile -> profile.getIdentity().getFirstname()).toList());
        return set;
    }


    public static boolean correspondingName(String lastname, String firstname) {
        List<Profile> list = profileHashMap.values().stream().
                filter(profile -> profile.identity.lastname.toUpperCase().startsWith(lastname.toUpperCase())
                        && profile.identity.firstname.toUpperCase().startsWith(firstname.toUpperCase())).toList();
        return list.size() >= 1;
    }

    public Set<Profile> suggestion(String firstname, String lastname) {
        Set<Profile> set = this.profileHashMap.values().stream()
                .filter(profile -> profile.identity.firstname.toUpperCase().startsWith(firstname.toUpperCase())
                        && profile.identity.lastname.toUpperCase().startsWith(lastname.toUpperCase()))
                .collect(Collectors.toSet());
        return set;
    }

    public Set<Profile> searchProfile(String firstname, String lastname, int minSize, int maxSize, int minAge, int maxAge, PhysicalAttributes.hairType hairType, PhysicalAttributes.hairColor hairColor, Biology.ethnicity ethnicity, LifeStyle.bodyBuild bodybuild) {
        Set<Profile> set = this.profileHashMap.values().stream()
                .filter(profile -> profile.identity.firstname.toUpperCase().startsWith(firstname.toUpperCase())
                        && profile.identity.lastname.toUpperCase().startsWith(lastname.toUpperCase())
                        && profile.physicalAttributes.size >= minSize
                        && profile.physicalAttributes.size <= maxSize
                        && profile.identity.age >= minAge
                        && profile.identity.age <= maxAge)
                .collect(Collectors.toSet());
        if (hairType != null) {
            set.retainAll(searchHairType(hairType));
        }
        if (hairColor != null) {
            set.retainAll(searchHairColor(hairColor));
        }
        if (ethnicity != null) {
            set.retainAll(searchEthnicity(ethnicity));
        }
        if (bodybuild != null) {
            set.retainAll(searchBodyBuild(bodybuild));
        }
        return set;
    }

    public Set<Profile> searchHairType(PhysicalAttributes.hairType hairType) {
        return this.profileHashMap.values().stream()
                .filter(profile -> profile.physicalAttributes.PAhairType.equals(hairType))
                .collect(Collectors.toSet());
    }

    public Set<Profile> searchHairColor(PhysicalAttributes.hairColor hairColor) {
        return this.profileHashMap.values().stream()
                .filter(profile -> profile.physicalAttributes.PAhairColor.equals(hairColor))
                .collect(Collectors.toSet());
    }

    public Set<Profile> searchEthnicity(Biology.ethnicity ethnicity) {
        return this.profileHashMap.values().stream()
                .filter(profile -> profile.identity.getBethnicity().equals(ethnicity))
                .collect(Collectors.toSet());
    }

    public Set<Profile> searchBodyBuild(LifeStyle.bodyBuild bodyBuild) {
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

    public static void main(String[] args) {
        ModelMatch modelMatch = new ModelMatch();
        Profile p = new Profile();
        p.setRandomProfileExceptName("Alma", "Katherine");
        modelMatch.addProfile(p);
        System.out.println(correspondingName("Alm", "Katherine"));
    }

}
