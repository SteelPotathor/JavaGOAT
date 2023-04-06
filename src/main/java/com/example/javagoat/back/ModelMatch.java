package com.example.javagoat.back;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ModelProfile {

    public HashMap<Integer /*id*/, TreeMap<Float /*distance*/, Integer /*id*/>> stockDistance;

    public HashMap<Integer /*id*/, Profile> profileHashMap;

    String testPath = "https://github.com/SteelPotathor/JavaGOAT/blob/0423a094b021e13e186644eb7925ea7490c2d266/src/main/java/com/example/javagoat/back/Biology.java";
    String path = "src/main/java/com/example/javagoat/back/Biology.java";

    public ModelProfile() {
        XMLDecoder decoder = null;
        try {
            FileInputStream fis = new FileInputStream(this.path);
            BufferedInputStream ois = new BufferedInputStream(fis);
            decoder = new XMLDecoder(ois);

            this.profileHashMap = (HashMap<Integer, Profile>) decoder.readObject();
            this.stockDistance = (HashMap<Integer, TreeMap<Float, Integer>>) decoder.readObject();

        } catch (Exception e) {
            this.profileHashMap = new HashMap<>();
            this.stockDistance = new HashMap<>();
        } finally {
            if (decoder != null) decoder.close();
        }
    }

    public void addProfile(Profile p) {
        // Add in every TreeMaps the new distance between the profile 'p' and every other profiles contained in this TreeMap
        for (int idProfile : stockDistance.keySet()) {
            TreeMap<Float, Integer> treeMapProfile = stockDistance.get(idProfile);
            Profile profile = profileHashMap.get(idProfile);
            float distanceP = profile.getDistance(p);
            treeMapProfile.put(distanceP, p.identity.getNoId());
        }

        // Create a TreeMap for the profile 'p'
        stockDistance.put(p.identity.getNoId(), new TreeMap<>());
        TreeMap<Float, Integer> treeMapP = stockDistance.get(p.identity.getNoId());

        // Add in the new TreeMap every distance with other profiles
        for (int idProfile : profileHashMap.keySet()) {
            Profile profile = profileHashMap.get(idProfile);
            float distanceP = profile.getDistance(p);
            treeMapP.put(distanceP, p.identity.getNoId());
        }

        // Add the profile 'p' in the hashMap
        this.profileHashMap.put(p.identity.getNoId(), p);
    }

    public ArrayList<Profile> getKNN(int noProfile, int howMany) {
        // Get the treemap of the profile 'noProfile'
        TreeMap<Float, Integer> treeMapD = this.stockDistance.get(noProfile);

        // Set an iterator to get 'howMany' first elements in the treemap (and an Arraylist to stock the results)
        int n = 0;
        ArrayList<Profile> KNNProfiles = new ArrayList<>();
        Set<Map.Entry<Float, Integer>> entries = treeMapD.entrySet();
        Iterator<Map.Entry<Float, Integer>> iterator = entries.iterator();

        // Obtains the nearest profiles in the ArrayList
        while (n < howMany && iterator.hasNext()) {
            int noId = iterator.next().getValue();
            KNNProfiles.add(this.profileHashMap.get(noId));
            n++;
        }

        return KNNProfiles;
    }

    public HashMap<Integer, TreeMap<Float, Integer>> getStockD() {
        return stockDistance;
    }

    public void setStockD(HashMap<Integer, TreeMap<Float, Integer>> stockD) {
        this.stockDistance = stockD;
    }

    public HashMap<Integer, Profile> getProfileHashMap() {
        return profileHashMap;
    }

    public void setProfileHashMap(HashMap<Integer, Profile> profileHashMap) {
        this.profileHashMap = profileHashMap;
    }

}
