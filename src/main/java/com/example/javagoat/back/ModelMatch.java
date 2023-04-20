package com.example.javagoat.back;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

public class ModelMatch implements Serializable {

    public HashMap<Integer /*id*/, TreeSet<Tuple>> stockDistance;
    public ModelProfile modelP;

    String DistancePath = "src\\main\\java\\com\\example\\javagoat\\back\\Distances.xml";
    String ProfilePath = "src\\main\\java\\com\\example\\javagoat\\back\\Profiles.xml";


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

        } catch (Exception e) {
            this.modelP = new ModelProfile();
            this.stockDistance = new HashMap<>();
        } finally {
            if (decoder != null) decoder.close();
        }
    }

    public void addProfile(Profile p) {
        // Add in every TreeSets the new distance between the profile 'p' and every other profiles contained in this TreeMap
        for (int idProfile : this.stockDistance.keySet()) {
            TreeSet<Tuple> treeSetProfile = this.stockDistance.get(idProfile);
            Profile profile = this.modelP.profileHashMap.get(idProfile);
            treeSetProfile.add(new Tuple(p.identity.getNoId(), profile, p));
        }

        // Create a TreeMap for the profile 'p'
        this.stockDistance.put(p.identity.getNoId(), new TreeSet<>());
        TreeSet<Tuple> treeSetP = this.stockDistance.get(p.identity.getNoId());

        // Add in the new TreeMap every distance with other profiles
        for (int idProfile : this.modelP.profileHashMap.keySet()) {
            Profile profile = this.modelP.profileHashMap.get(idProfile);
            treeSetP.add(new Tuple(idProfile, p, profile));
        }

        // Add the profile 'p' in the hashMap
        this.modelP.profileHashMap.put(p.identity.getNoId(), p);
    }

    public ArrayList<Profile> getKNN(int noProfile, int howMany) {
        // Get the treemap of the profile 'noProfile'
        TreeSet<Tuple> treeSetD = this.stockDistance.get(noProfile);

        // Set an iterator to get 'howMany' first elements in the treemap (and an Arraylist to stock the results)
        // Obtains the nearest profiles in the ArrayList
        int i = 0;
        ArrayList<Profile> KNNProfiles = new ArrayList<>();
        Iterator<Tuple> itr = treeSetD.iterator();

        while (i < 5 && itr.hasNext()) {
            KNNProfiles.add(this.modelP.profileHashMap.get(itr.next().id));
            i++;
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


}
