package com.example.javagoat.back;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class ModelHistoMatch implements Serializable {

    public HashMap<TupleHistoHashMap, Date> stockHisto = new HashMap<>();

    public ModelNotification modelNotification = new ModelNotification();

    public ModelHistoMatch() {
    }


    // Method to add a match at the current date
    public void addMatch(Profile p1, Profile p2) {
        // Put the priority at the lowest for the two profiles
        p1.setPriority(3);
        p2.setPriority(3);
        HashMap<TupleHistoHashMap, Date> matchP1 = p1.getModelHisto().getStockHisto();
        TupleHistoHashMap tupleHistoHashMap1 = new TupleHistoHashMap(p2.getIdentity().getNoId());
        matchP1.put(tupleHistoHashMap1, new Date());
        HashMap<TupleHistoHashMap, Date> matchP2 = p2.getModelHisto().getStockHisto();
        TupleHistoHashMap tupleHistoHashMap2 = new TupleHistoHashMap(p1.getIdentity().getNoId());
        matchP2.put(tupleHistoHashMap2, new Date());
        modelNotification.addNotification(new Date(), "Match between " + p1.getIdentity().getFirstname() + " " + p1.getIdentity().getLastname() + " and " + p2.getIdentity().getFirstname() + " " + p2.getIdentity().getLastname());
        matchCount++;
    }

    // Method to add a match at a specific date (used for test)
    public void addMatch(Profile p1, Profile p2, Date date) {
        // Put the priority at the lowest for the two profiles
        p1.setPriority(3);
        p2.setPriority(3);
        HashMap<TupleHistoHashMap, Date> matchP1 = p1.getModelHisto().getStockHisto();
        TupleHistoHashMap tupleHistoHashMap1 = new TupleHistoHashMap(p2.getIdentity().getNoId());
        matchP1.put(tupleHistoHashMap1, date);
        HashMap<TupleHistoHashMap, Date> matchP2 = p2.getModelHisto().getStockHisto();
        TupleHistoHashMap tupleHistoHashMap2 = new TupleHistoHashMap(p1.getIdentity().getNoId());
        matchP2.put(tupleHistoHashMap2, date);
        matchCount++;
    }


    // Method to remove a match
    public void removeMatch(Profile p1, Profile p2) {
        HashMap<TupleHistoHashMap, Date> matchP1 = p1.getModelHisto().getStockHisto();
        TupleHistoHashMap tupleHistoHashMap1 = new TupleHistoHashMap(p2.getIdentity().getNoId());
        matchP1.remove(tupleHistoHashMap1);
        HashMap<TupleHistoHashMap, Date> matchP2 = p2.getModelHisto().getStockHisto();
        TupleHistoHashMap tupleHistoHashMap2 = new TupleHistoHashMap(p1.getIdentity().getNoId());
        matchP2.remove(tupleHistoHashMap2);
    }

    public HashMap<TupleHistoHashMap, Date> getStockHisto() {
        return stockHisto;
    }

    public void setStockHisto(HashMap<TupleHistoHashMap, Date> stockHisto) {
        this.stockHisto = stockHisto;
    }

    public ModelNotification getModelNotification() {
        return modelNotification;
    }

    public void setModelNotification(ModelNotification modelNotification) {
        this.modelNotification = modelNotification;
    }

    @Override
    public String toString() {
        String sb = "ModelHistoMatch{" + "stockHisto=" + stockHisto +
                '}';
        return sb;
    }


}
