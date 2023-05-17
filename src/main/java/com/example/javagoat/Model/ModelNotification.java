package com.example.javagoat.Model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeMap;

public class ModelNotification implements Serializable {

    public static TreeMap<Date, String> stockNotification = new TreeMap<Date, String>(Comparator.reverseOrder());

    public ModelNotification() {
    }

    public void addNotification(Date date, String string) {
        stockNotification.put(date, string);
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
