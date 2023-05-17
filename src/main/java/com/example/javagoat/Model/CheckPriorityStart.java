package com.example.javagoat.Model;

import java.util.Date;

import static com.example.javagoat.Model.ModelProfile.profileHashMap;

public class CheckPriorityStart {

    public void initPriority() {
        Date actualDate = new Date();
        for (Integer key : profileHashMap.keySet()) {
            Profile profile = profileHashMap.get(key);
            Date aux = new Date(0, 1, 1); // Year => 1900, Month => 1, Day => 1
            for (TupleHistoHashMap tupleHistoHashMap : profile.modelHisto.stockHisto.keySet()) {
                Date date = profile.modelHisto.stockHisto.get(tupleHistoHashMap);
                // Determine the nearest date
                if (date.after(aux)) {
                    aux = date;
                }
            }
            long time = aux.getTime();
            long timeInterval = (time - actualDate.getTime()) * -1; // The difference between time and the actual date is negative so *-1 to turns it positive
            timeInterval /= 36000000; // Convert the timeInterval which is in ms to hour
            if (timeInterval <= 24 * 7) { // less than a week
                profile.setPriority(3);
            } else if (timeInterval <= 24 * 14) { // between a week and 2 weeks
                profile.setPriority(2);
            } else { // more than two weeks
                profile.setPriority(1);
            }
        }

    }
}
