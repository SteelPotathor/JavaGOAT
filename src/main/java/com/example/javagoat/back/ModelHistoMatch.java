package com.example.javagoat.back;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class ModelHistoMatch implements Serializable {

    public HashMap<Integer, Date> stockHisto;
    public SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ModelHistoMatch() {
        this.stockHisto = new HashMap<>();
    }


    public void addMatch(Profile p1, Profile p2) {
        // Put the priority at the lowest for the two profiles
        p1.setPriority(3);
        p2.setPriority(3);
        HashMap<Integer, Date> matchP1 = p1.getModelHisto().getStockHisto();
        matchP1.put(p2.getIdentity().getNoId(), new Date());
        HashMap<Integer, Date> matchP2 = p2.getModelHisto().getStockHisto();
        matchP2.put(p1.getIdentity().getNoId(), new Date());
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ModelHistoMatch{");
        sb.append("stockHisto=").append(stockHisto);
        sb.append(", timeFormat=").append(timeFormat);
        sb.append('}');
        return sb.toString();
    }


}
