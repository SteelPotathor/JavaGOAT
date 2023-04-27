package com.example.javagoat.back;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class ModelHistoMatch implements Serializable {

    public HashMap<Integer, String> stockHisto;
    public SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ModelHistoMatch() {
        this.stockHisto = new HashMap<>();
    }


    private void addMatch(Profile p1, Profile p2) {
        HashMap<Integer, String> matchP1 = p1.getModelHisto().getStockHisto();
        matchP1.put(p2.getIdentity().getNoId(), timeFormat.format(new Date()));
        HashMap<Integer, String> matchP2 = p2.getModelHisto().getStockHisto();
        matchP2.put(p1.getIdentity().getNoId(), timeFormat.format(new Date()));
    }

    public HashMap<Integer, String> getStockHisto() {
        return stockHisto;
    }

    public void setStockHisto(HashMap<Integer, String> stockHisto) {
        this.stockHisto = stockHisto;
    }


}
