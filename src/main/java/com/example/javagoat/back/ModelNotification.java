package com.example.javagoat.back;

import org.controlsfx.control.Notifications;

import java.util.Comparator;
import java.util.Date;
import java.util.TreeMap;

public class ModelNotification {

    public static TreeMap<Date, String> stockNotification = new TreeMap<Date, String>(Comparator.reverseOrder());

    public ModelNotification() {
    }

    public void addNotification(Date date, String string) {
        stockNotification.put(date, string);
        System.out.println(stockNotification);
    }

    public static void main(String[] args) {
        ModelNotification modelNotification = new ModelNotification();
        modelNotification.addNotification(new Date(123,4,3), "3");
        modelNotification.addNotification(new Date(122,4,3), "2");
        modelNotification.addNotification(new Date(121, 4, 3), "1");
        modelNotification.addNotification(new Date(), "4");
        System.out.println(modelNotification.stockNotification);
    }
}
