package com.example.javagoat.back;

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
        String s = "";
        for (Date date : stockNotification.keySet()) {
            s += date + " : " + stockNotification.get(date);
        }
        return s;
    }

    public static void main(String[] args) {
        ModelNotification modelNotification = new ModelNotification();
        modelNotification.addNotification(new Date(123, 4, 3), "3");
        modelNotification.addNotification(new Date(122, 4, 3), "2");
        modelNotification.addNotification(new Date(121, 4, 3), "1");
        modelNotification.addNotification(new Date(), "4");
    }
}
