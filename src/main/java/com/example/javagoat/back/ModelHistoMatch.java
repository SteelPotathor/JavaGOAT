package com.example.javagoat.back;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ModelHistoMatch implements Serializable {

    public HashMap<Integer, Date> stockHisto;
    public SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");
    public ModelNotification modelNotification = new ModelNotification();

    public static int matchCount = 0;

    public ModelHistoMatch(HashMap<Integer, Date> stockHisto) {
        this.stockHisto = stockHisto;
    }

    public ModelHistoMatch() {
    }

    public void saveHisto() {
        String ProfilePath = "src\\main\\java\\com\\example\\javagoat\\back\\Profiles.xml";
        XMLEncoder encoder = null;
        try {
            FileOutputStream fos = new FileOutputStream(ProfilePath);
            BufferedOutputStream oos = new BufferedOutputStream(fos);
            encoder = new XMLEncoder(oos);
            encoder.writeObject(this);
            encoder.flush();
        } catch (final IOException e) {
            throw new RuntimeException();
        } finally {
            if (encoder != null) encoder.close();
        }
    }
/*
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
    }*/

    public HashMap<Integer, Date> getStockHisto() {
        return stockHisto;
    }

    public void setStockHisto(HashMap<Integer, Date> stockHisto) {
        this.stockHisto = stockHisto;
    }

    public SimpleDateFormat getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(SimpleDateFormat timeFormat) {
        this.timeFormat = timeFormat;
    }

    public ModelNotification getModelNotification() {
        return modelNotification;
    }

    public void setModelNotification(ModelNotification modelNotification) {
        this.modelNotification = modelNotification;
    }

    public static int getMatchCount() {
        return matchCount;
    }

    public static void setMatchCount(int matchCount) {
        ModelHistoMatch.matchCount = matchCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ModelHistoMatch{");
        sb.append("stockHisto=").append(stockHisto);
        sb.append(", timeFormat=").append(timeFormat);
        sb.append('}');
        return sb.toString();
    }


}
