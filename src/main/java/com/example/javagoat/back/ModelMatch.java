package com.example.javagoat.back;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

public class ModelMatch implements Serializable {

    public static HashMap<Integer /*id*/, TreeSet<TupleTreeSet>> stockDistance;
    public ModelProfile modelP;
    public ModelNotification modelN = new ModelNotification();

    public String DistancePath = "src\\main\\java\\com\\example\\javagoat\\back\\Distances.xml";
    public String ProfilePath = "src\\main\\java\\com\\example\\javagoat\\back\\Profiles.xml";

    public static int createCounter = 0;


    public ModelMatch() {
        XMLDecoder decoder = null;
        try {
            modelP = new ModelProfile();
            // check if the hashmap is not null because of static (we don't want to reset it)
            if (modelP.getProfileHashMap() == null) {
                FileInputStream fileInputStream = new FileInputStream(this.ProfilePath);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                decoder = new XMLDecoder(bufferedInputStream);
                modelP.profileHashMap = ((HashMap<Integer, Profile>) decoder.readObject());
            }

            // check if the DS is not null because of static (we don't want to reset it)
            if (stockDistance == null) {
                FileInputStream fileInputStream = new FileInputStream(this.DistancePath);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                decoder = new XMLDecoder(bufferedInputStream);
                stockDistance = (HashMap<Integer, TreeSet<TupleTreeSet>>) decoder.readObject();
            }

        } catch (Exception e) {
            modelP = new ModelProfile();
            stockDistance = new HashMap<>();
        } finally {
            if (decoder != null) decoder.close();
        }
    }

    public void addProfile(Profile p) {
        // Add in every TreeSets the new distance between the profile 'p' and every other profiles contained in this HashMap
        for (int idProfile : stockDistance.keySet()) {
            TreeSet<TupleTreeSet> treeSetProfile = stockDistance.get(idProfile);
            Profile profile = this.modelP.profileHashMap.get(idProfile);
            treeSetProfile.add(new TupleTreeSet(p.identity.getNoId(), profile, p));
        }

        // Create a TreeSet for the profile 'p'
        stockDistance.put(p.identity.getNoId(), new TreeSet<>());
        TreeSet<TupleTreeSet> treeSetP = stockDistance.get(p.identity.getNoId());

        // Add in the new TreeSet every distance with other profiles
        for (int idProfile : this.modelP.profileHashMap.keySet()) {
            Profile profile = this.modelP.profileHashMap.get(idProfile);
            treeSetP.add(new TupleTreeSet(idProfile, p, profile));
        }

        // Add the profile 'p' in the hashMap
        this.modelP.profileHashMap.put(p.identity.getNoId(), p);
        modelN.addNotification(new Date(), "Profile created : " + p.identity.getLastname() + " " + p.identity.getFirstname());
        createCounter++;
    }

    public void editProfile(Profile p) {
        // Get the old profile before updating it
        Profile oldProfile = modelP.profileHashMap.get(p.getIdentity().getNoId());

        // Set in every TreeSets the new distance between the profile 'p' and every other profiles contained in this HashMap
        for (int idProfile : stockDistance.keySet()) {
            if (idProfile != p.getIdentity().getNoId()) {
                TreeSet<TupleTreeSet> treeSetProfile = stockDistance.get(idProfile);
                Profile profile = modelP.profileHashMap.get(idProfile);
                treeSetProfile.remove(new TupleTreeSet(p.identity.getNoId(), profile, oldProfile));
                treeSetProfile.add(new TupleTreeSet(p.identity.getNoId(), profile, p));
            }
        }

        // Clear the TreeSet for the profile 'p'
        stockDistance.get(p.identity.getNoId()).clear();
        TreeSet<TupleTreeSet> treeSetP = stockDistance.get(p.identity.getNoId());

        // Add in the new TreeSet every distance with other profiles
        for (int idProfile : modelP.profileHashMap.keySet()) {
            Profile profile = modelP.profileHashMap.get(idProfile);
            treeSetP.add(new TupleTreeSet(idProfile, p, profile));
        }

        modelP.profileHashMap.replace(p.getIdentity().getNoId(), p);
        modelN.addNotification(new Date(), "Profile edited : " + p.identity.getLastname() + " " + p.identity.getFirstname());

    }

    public HashMap<Profile, Integer> getKNN(int noProfile, int howMany) {
        // Get the treemap of the profile 'noProfile'
        TreeSet<TupleTreeSet> treeSetD = stockDistance.get(noProfile);
        HashMap<Integer, Date> hashMapH = modelP.profileHashMap.get(noProfile).getModelHisto().getStockHisto();

        // Set an iterator to get 'howMany' first elements in the treemap (and an Arraylist to stock the results)
        // Obtains the nearest profiles in the ArrayList
        int i = 0;
        HashMap<Profile, Integer> KNNProfiles = new HashMap<>();
        Iterator<TupleTreeSet> itr = treeSetD.iterator();

        while (i < howMany && itr.hasNext()) {
            TupleTreeSet t = itr.next();
            if (hashMapH == null || !hashMapH.containsKey(t.id)) {
                HashSet<Passion.miscellaneous> PMprofile = modelP.profileHashMap.get(noProfile).getPassion().getPassionM();
                HashSet<Passion.video_games> PVGprofile = modelP.profileHashMap.get(noProfile).getPassion().getPassionVG();
                int counter = modelP.profileHashMap.get(t.id).getPassion().getPassionM().stream().filter(PMprofile::contains).toArray().length;
                counter += modelP.profileHashMap.get(t.id).getPassion().getPassionVG().stream().filter(PVGprofile::contains).toArray().length;
                KNNProfiles.put(modelP.profileHashMap.get(t.id), counter);
                i++;
            }

        }

        return KNNProfiles;
    }

    public void saveProfiles() {
        XMLEncoder encoder = null;
        try {
            FileOutputStream fos = new FileOutputStream(ProfilePath);
            BufferedOutputStream oos = new BufferedOutputStream(fos);
            encoder = new XMLEncoder(oos);
            encoder.writeObject(this.getModelP().getProfileHashMap());
            encoder.flush();

        } catch (final IOException e) {
            throw new RuntimeException();
        } finally {
            if (encoder != null) encoder.close();
        }

    }

    public void saveDistances() {
        XMLEncoder encoder = null;
        try {
            FileOutputStream fos = new FileOutputStream(DistancePath);
            BufferedOutputStream oos = new BufferedOutputStream(fos);
            encoder = new XMLEncoder(oos);
            encoder.writeObject(this.getStockDistance());
            encoder.flush();

        } catch (final IOException e) {
            throw new RuntimeException();
        } finally {
            if (encoder != null) encoder.close();
        }

    }

    public HashMap<Integer, TreeSet<TupleTreeSet>> getStockDistance() {
        return stockDistance;
    }

    public void setStockDistance(HashMap<Integer, TreeSet<TupleTreeSet>> stockD) {
        stockDistance = stockD;
    }

    public ModelProfile getModelP() {
        return modelP;
    }

    public void setModelP(ModelProfile modelProfile) {
        modelP = modelProfile;
    }

    public static int getCreateCounter() {
        return createCounter;
    }

    public static void setCreateCounter(int createCounter) {
        ModelMatch.createCounter = createCounter;
    }
}
