package com.example.javagoat.back;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class ModelProfile {

    public static HashMap<Integer /*id*/, Profile> profileHashMap = new HashMap<>(); // This DS must be accessible and be the same for everyone

    public ModelProfile() {
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
        if (hairColor!=null) {
            set.retainAll(searchHairColor(hairColor));
        }
        if (ethnicity!=null) {
            set.retainAll(searchEthnicity(ethnicity));
        }
        if (bodybuild!=null) {
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
        for (int i = 0; i < 500; i++) {
            Profile profile = new Profile();
            profile.setRandomProfile();
            modelMatch.addProfile(profile);
        }
        Profile p = new Profile(new Identity(15, Biology.sex.MALE, Biology.ethnicity.BLACK, 120, "Bob", "bILL"), new PhysicalAttributes(150, PhysicalAttributes.hairColor.RED, PhysicalAttributes.hairType.WAVY, PhysicalAttributes.hairLength.LONG), new LifeStyle(LifeStyle.smoker.NEVER, LifeStyle.athlete.SPORTY, LifeStyle.feed.VEGETARIAN, LifeStyle.bodyBuild.SKINNY, LifeStyle.religion.HINDUIST, LifeStyle.alcohol.REGULARLY), new Preferences(new PhysicalAttributes(150, PhysicalAttributes.hairColor.RED, PhysicalAttributes.hairType.WAVY, PhysicalAttributes.hairLength.LONG), new Biology(15, Biology.sex.MALE, Biology.ethnicity.BLACK, 120), new LifeStyle(LifeStyle.smoker.NEVER, LifeStyle.athlete.SPORTY, LifeStyle.feed.VEGETARIAN, LifeStyle.bodyBuild.SKINNY, LifeStyle.religion.HINDUIST, LifeStyle.alcohol.REGULARLY)), new Passion(), null);
        modelMatch.addProfile(p);
        System.out.println(profileHashMap);
        String lastName = "bob";
        String firstName = "";
        System.out.println(profileHashMap.get(501));
        Set<Profile> set = modelMatch.modelP.searchProfile(firstName, lastName, 10, 200, 0, 100, null, null, null, null);
        System.out.printf(set.toString());
    }

}
