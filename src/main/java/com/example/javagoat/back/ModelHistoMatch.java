package com.example.javagoat.back;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class ModelHistoMatch implements Serializable {

    public HashMap<Integer, HashMap<Integer, String>> stockHisto;
    public SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ModelHistoMatch() {
        this.stockHisto = new HashMap<>();
    }

    public void addMatch(int idP1, int idP2) {

        addMatchBis(idP1, idP2);

        addMatchBis(idP2, idP1);

    }

    private void addMatchBis(int idP1, int idP2) {
        if (this.stockHisto.get(idP1) == null) {
            HashMap<Integer, String> match = new HashMap<>();
            match.put(idP2, timeFormat.format(new Date()));
            this.stockHisto.put(idP1, match);
        } else {
            HashMap<Integer, String> match = this.stockHisto.get(idP1);
            match.put(idP2, timeFormat.format(new Date()));
            this.stockHisto.put(idP1, match);
        }
    }

    public HashMap<Integer, HashMap<Integer, String>> getStockHisto() {
        return stockHisto;
    }

    public void setStockHisto(HashMap<Integer, HashMap<Integer, String>> stockHisto) {
        this.stockHisto = stockHisto;
    }


}
