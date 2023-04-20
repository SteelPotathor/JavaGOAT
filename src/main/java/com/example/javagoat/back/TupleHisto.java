package com.example.javagoat.back;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TupleHisto {

    public int id;
    public Date date;
    public SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");

    public TupleHisto(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public TupleHisto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "id=" + id + ", date=" + timeFormat.format(date);
    }
}
