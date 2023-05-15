package com.example.javagoat.back;

import animatefx.animation.SlideOutDown;

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
            // check if the DS is not null because of static (we don't want to reset it)
            if (modelP.getProfileHashMap().size() == 0) {
                FileInputStream fileInputStream = new FileInputStream(this.ProfilePath);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                decoder = new XMLDecoder(bufferedInputStream);
                ModelProfile.profileHashMap = ((HashMap<Integer, Profile>) decoder.readObject());
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
            Profile profile = ModelProfile.profileHashMap.get(idProfile);
            treeSetProfile.add(new TupleTreeSet(p.identity.getNoId(), profile, p));
        }

        // Create a TreeSet for the profile 'p'
        stockDistance.put(p.identity.getNoId(), new TreeSet<>());
        TreeSet<TupleTreeSet> treeSetP = stockDistance.get(p.identity.getNoId());

        // Add in the new TreeSet every distance with other profiles
        for (int idProfile : ModelProfile.profileHashMap.keySet()) {
            Profile profile = ModelProfile.profileHashMap.get(idProfile);
            treeSetP.add(new TupleTreeSet(idProfile, p, profile));
        }

        // Add the profile 'p' in the hashMap
        ModelProfile.profileHashMap.put(p.identity.getNoId(), p);
        modelN.addNotification(new Date(), "Profile created : " + p.identity.getLastname() + " " + p.identity.getFirstname());
        createCounter++;
    }

    public void removeProfile(Profile p) {
        // Remove the profile 'p' from the HashMap
        ModelProfile.profileHashMap.remove(p.identity.getNoId());

        // Remove the profile 'p' from every TreeSet
        for (int idProfile : stockDistance.keySet()) {
            TreeSet<TupleTreeSet> treeSetProfile = stockDistance.get(idProfile);
            treeSetProfile.remove(new TupleTreeSet(p.identity.getNoId(), this.modelP.getProfileHashMap().get(idProfile), p));
        }

        // Remove the TreeSet of the profile 'p'
        stockDistance.remove(p.identity.getNoId());
        createCounter--;
    }


    public void editProfile(Profile newProfile, Profile oldProfile) {
        // Set in every TreeSets the new distance between the profile 'newProfile' and every other profiles contained in this HashMap
        for (int idProfile : stockDistance.keySet()) {
            if (idProfile != newProfile.getIdentity().getNoId()) {
                TreeSet<TupleTreeSet> treeSetProfile = stockDistance.get(idProfile);
                Profile profile = ModelProfile.profileHashMap.get(idProfile);
                treeSetProfile.remove(new TupleTreeSet(newProfile.identity.getNoId(), profile, oldProfile));
                treeSetProfile.add(new TupleTreeSet(newProfile.identity.getNoId(), profile, newProfile));
            }
        }

        // Clear the TreeSet for the profile 'newProfile'
        stockDistance.get(newProfile.identity.getNoId()).clear();
        TreeSet<TupleTreeSet> treeSetP = stockDistance.get(newProfile.identity.getNoId());

        // Add in the new TreeSet every distance with other profiles
        for (int idProfile : ModelProfile.profileHashMap.keySet()) {
            Profile profile = ModelProfile.profileHashMap.get(idProfile);
            treeSetP.add(new TupleTreeSet(idProfile, newProfile, profile));
        }
        ModelProfile.profileHashMap.remove(oldProfile.getIdentity().getNoId());
        ModelProfile.profileHashMap.put(newProfile.getIdentity().getNoId(), newProfile);

        modelN.addNotification(new Date(), "Profile edited : " + newProfile.identity.getLastname() + " " + newProfile.identity.getFirstname());
    }

    public HashMap<Profile, Integer> getKNN(int noProfile, int howMany) {
        // Get the treemap of the profile 'noProfile'
        TreeSet<TupleTreeSet> treeSetD = stockDistance.get(noProfile);
        HashMap<TupleHistoHashMap, Date> hashMapH = modelP.profileHashMap.get(noProfile).getModelHisto().getStockHisto();

        // Set an iterator to get 'howMany' first elements in the treemap (and an Arraylist to stock the results)
        // Obtains the nearest profiles in the ArrayList
        int i = 0;
        HashMap<Profile, Integer> KNNProfiles = new HashMap<>();
        Iterator<TupleTreeSet> itr = treeSetD.iterator();

        while (i < howMany && itr.hasNext()) {
            TupleTreeSet tupleTreeSet = itr.next();
            if ((hashMapH == null || !hashMapH.containsKey(tupleTreeSet.getId()) && tupleTreeSet.getId() != noProfile && modelP.profileHashMap.get(tupleTreeSet.getId()).getPreferences().getBiology().getBsex() == modelP.getProfileHashMap().get(noProfile).getIdentity().getBsex())) {
                HashSet<Passion.miscellaneous> PMprofile = modelP.profileHashMap.get(noProfile).getPassion().getPassionM();
                HashSet<Passion.video_games> PVGprofile = modelP.profileHashMap.get(noProfile).getPassion().getPassionVG();
                int counter = modelP.profileHashMap.get(tupleTreeSet.id).getPassion().getPassionM().stream().filter(PMprofile::contains).toArray().length;
                counter += modelP.profileHashMap.get(tupleTreeSet.id).getPassion().getPassionVG().stream().filter(PVGprofile::contains).toArray().length;
                KNNProfiles.put(modelP.profileHashMap.get(tupleTreeSet.id), counter);
                System.out.println(tupleTreeSet.distance);
                i++;
            }

        }
        System.out.println(KNNProfiles);
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ModelMatch{");
        sb.append("modelP=").append(modelP);
        sb.append(", modelN=").append(modelN);
        sb.append(", DistancePath='").append(DistancePath).append('\'');
        sb.append(", ProfilePath='").append(ProfilePath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
