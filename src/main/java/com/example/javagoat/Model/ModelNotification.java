package com.example.javagoat.Model;

import java.util.Comparator;
import java.util.Date;
import java.util.TreeMap;

public class ModelNotification {


    public static TreeMap<Date, String> stockNotification = new TreeMap<Date, String>(Comparator.reverseOrder());

    public ModelNotification() {
    }

    public void addNotification(Date date, String string) {
        // Dates are the keys: two notifications in the same millisecond used to overwrite each other
        Date key = date;
        while (stockNotification.containsKey(key)) {
            key = new Date(key.getTime() + 1);
        }
        stockNotification.put(key, string);
    }

    public static TreeMap<Date, String> getStockNotification() {
        return stockNotification;
    }

    public static void setStockNotification(TreeMap<Date, String> stockNotification) {
        ModelNotification.stockNotification = stockNotification;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Date date : stockNotification.keySet()) {
            sb.append(date).append(" : ").append(stockNotification.get(date));
        }
        return sb.toString();
    }
}
