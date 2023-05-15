package com.example.javagoat.back;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class ModelHistoMatch implements Serializable {

    public static int matchCount = 0;
    public HashMap<Integer, Date> stockHisto = new HashMap<>();
    public ModelNotification modelNotification = new ModelNotification();

    public ModelHistoMatch() {
    }

    public static int getMatchCount() {
        return matchCount;
    }

    public static void setMatchCount(int matchCount) {
        ModelHistoMatch.matchCount = matchCount;
    }

    public void addMatch(Profile p1, Profile p2) {
        // Put the priority at the lowest for the two profiles
        p1.setPriority(3);
        p2.setPriority(3);
        HashMap<Integer, Date> matchP1 = p1.getModelHisto().getStockHisto();
        matchP1.put(p2.getIdentity().getNoId(), new Date());
        HashMap<Integer, Date> matchP2 = p2.getModelHisto().getStockHisto();
        matchP2.put(p1.getIdentity().getNoId(), new Date());
        modelNotification.addNotification(new Date(), "Match between " + p1.getIdentity().getFirstname() + " " + p1.getIdentity().getLastname() + " and " + p2.getIdentity().getFirstname() + " " + p2.getIdentity().getLastname());
        matchCount++;
    }

    public HashMap<Integer, Date> getStockHisto() {
        return stockHisto;
    }

    public void setStockHisto(HashMap<Integer, Date> stockHisto) {
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
