package com.example.javagoat.back;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ModelHistoMatch {

    public HashMap<Integer /*id*/, ArrayList<TupleHisto>> stockHisto;

    // FOR JAWAD
    public ModelHistoMatch() {
    }

    public void addMatch(int idP1, int idP2) {
        if (this.stockHisto.get(idP1) == null) {
            ArrayList<TupleHisto> arrayList = new ArrayList<TupleHisto>();
            TupleHisto tupleHisto = new TupleHisto(idP2, new Date());
            arrayList.add(tupleHisto);
            this.stockHisto.put(idP1, arrayList);
        } else {
            ArrayList<TupleHisto> arrayList = this.stockHisto.get(idP1);
            TupleHisto tupleHisto = new TupleHisto(idP2, new Date());
            arrayList.add(tupleHisto);
            this.stockHisto.put(idP1, arrayList);
        }

        if (this.stockHisto.get(idP2) == null) {
            ArrayList<TupleHisto> arrayList = new ArrayList<TupleHisto>();
            TupleHisto tupleHisto = new TupleHisto(idP1, new Date());
            arrayList.add(tupleHisto);
            this.stockHisto.put(idP2, arrayList);
        } else {
            ArrayList<TupleHisto> arrayList = this.stockHisto.get(idP1);
            TupleHisto tupleHisto = new TupleHisto(idP1, new Date());
            arrayList.add(tupleHisto);
            this.stockHisto.put(idP2, arrayList);
        }

    }

}
