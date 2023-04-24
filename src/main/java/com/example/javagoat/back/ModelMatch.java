package com.example.javagoat.back;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

public class ModelMatch implements Serializable {

    public HashMap<Integer /*id*/, TreeSet<Tuple>> stockDistance;
    public ModelProfile modelP;
    public ModelHistoMatch modelHistoMatch;

    String DistancePath = "src\\main\\java\\com\\example\\javagoat\\back\\Distances.xml";
    String ProfilePath = "src\\main\\java\\com\\example\\javagoat\\back\\Profiles.xml";
    String HistoPath = "src\\main\\java\\com\\example\\javagoat\\back\\Histo.xml";


    public ModelMatch() {
        XMLDecoder decoder = null;
        try {
            FileInputStream fis = new FileInputStream(this.ProfilePath);
            BufferedInputStream ois = new BufferedInputStream(fis);
            decoder = new XMLDecoder(ois);

            this.modelP = new ModelProfile();
            this.modelP.profileHashMap = ((HashMap<Integer, Profile>) decoder.readObject());

            fis = new FileInputStream(this.DistancePath);
            ois = new BufferedInputStream(fis);
            decoder = new XMLDecoder(ois);
            this.stockDistance = (HashMap<Integer, TreeSet<Tuple>>) decoder.readObject();

            fis = new FileInputStream(this.HistoPath);
            ois = new BufferedInputStream(fis);
            decoder = new XMLDecoder(ois);

            this.modelHistoMatch = new ModelHistoMatch();
            this.modelHistoMatch.stockHisto = (HashMap<Integer, HashMap<Integer, String>>) decoder.readObject();

        } catch (Exception e) {
            this.modelP = new ModelProfile();
            this.stockDistance = new HashMap<>();
            this.modelHistoMatch = new ModelHistoMatch();
        } finally {
            if (decoder != null) decoder.close();
        }
    }

    public void addProfile(Profile p) {
        // Add in every TreeSets the new distance between the profile 'p' and every other profiles contained in this HashMap
        for (int idProfile : this.stockDistance.keySet()) {
            TreeSet<Tuple> treeSetProfile = this.stockDistance.get(idProfile);
            Profile profile = this.modelP.profileHashMap.get(idProfile);
            treeSetProfile.add(new Tuple(p.identity.getNoId(), profile, p));
        }

        // Create a TreeSet for the profile 'p'
        this.stockDistance.put(p.identity.getNoId(), new TreeSet<>());
        TreeSet<Tuple> treeSetP = this.stockDistance.get(p.identity.getNoId());

        // Add in the new TreeSet every distance with other profiles
        for (int idProfile : this.modelP.profileHashMap.keySet()) {
            Profile profile = this.modelP.profileHashMap.get(idProfile);
            treeSetP.add(new Tuple(idProfile, p, profile));
        }

        // Add the profile 'p' in the hashMap
        this.modelP.profileHashMap.put(p.identity.getNoId(), p);
    }

    public void editProfile(Profile p) {
        // Get the old profile before updating it
        Profile oldProfile = this.modelP.profileHashMap.get(p.getIdentity().getNoId());

        // Set in every TreeSets the new distance between the profile 'p' and every other profiles contained in this HashMap
        for (int idProfile : this.stockDistance.keySet()) {
            if (idProfile != p.getIdentity().getNoId()) {
                TreeSet<Tuple> treeSetProfile = this.stockDistance.get(idProfile);
                Profile profile = this.modelP.profileHashMap.get(idProfile);
                treeSetProfile.remove(new Tuple(p.identity.getNoId(), profile, oldProfile));
                treeSetProfile.add(new Tuple(p.identity.getNoId(), profile, p));
            }
        }

        // Clear the TreeSet for the profile 'p'
        this.stockDistance.get(p.identity.getNoId()).clear();
        TreeSet<Tuple> treeSetP = this.stockDistance.get(p.identity.getNoId());

        // Add in the new TreeSet every distance with other profiles
        for (int idProfile : this.modelP.profileHashMap.keySet()) {
            Profile profile = this.modelP.profileHashMap.get(idProfile);
            treeSetP.add(new Tuple(idProfile, p, profile));
        }

        this.modelP.profileHashMap.replace(p.getIdentity().getNoId(), p);
    }

    public ArrayList<Profile> getKNN(int noProfile, int howMany) {
        // Get the treemap of the profile 'noProfile'
        TreeSet<Tuple> treeSetD = this.stockDistance.get(noProfile);
        HashMap<Integer, String> hashMapH = this.modelHistoMatch.stockHisto.get(noProfile);

        // Set an iterator to get 'howMany' first elements in the treemap (and an Arraylist to stock the results)
        // Obtains the nearest profiles in the ArrayList
        int i = 0;
        ArrayList<Profile> KNNProfiles = new ArrayList<>();
        Iterator<Tuple> itr = treeSetD.iterator();

        while (i < howMany && itr.hasNext()) {
            Tuple t = itr.next();
            if (hashMapH == null || !hashMapH.containsKey(t.id)) {
                KNNProfiles.add(this.modelP.profileHashMap.get(t.id));
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

    public void saveHisto() {
        XMLEncoder encoder = null;
        try {
            FileOutputStream fos = new FileOutputStream(HistoPath);
            BufferedOutputStream oos = new BufferedOutputStream(fos);
            encoder = new XMLEncoder(oos);
            encoder.writeObject(this.getModelHistoMatch().getStockHisto());
            encoder.flush();

        } catch (final IOException e) {
            throw new RuntimeException();
        } finally {
            if (encoder != null) encoder.close();
        }

    }



    public HashMap<Integer, TreeSet<Tuple>> getStockDistance() {
        return stockDistance;
    }

    public void setStockDistance(HashMap<Integer, TreeSet<Tuple>> stockDistance) {
        this.stockDistance = stockDistance;
    }

    public ModelProfile getModelP() {
        return modelP;
    }

    public void setModelP(ModelProfile modelP) {
        this.modelP = modelP;
    }

    public ModelHistoMatch getModelHistoMatch() {
        return modelHistoMatch;
    }

    public void setModelHistoMatch(ModelHistoMatch modelHistoMatch) {
        this.modelHistoMatch = modelHistoMatch;
    }

}
