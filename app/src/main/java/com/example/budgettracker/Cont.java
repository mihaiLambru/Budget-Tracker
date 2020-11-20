package com.example.budgettracker;

import android.content.Context;

public class Cont {
    private String nume;
    private double suma;
    private String moneda;
   // private int imagine;

    public Cont(String nume, double suma, String moneda) {
        this.nume = nume;
        this.suma = suma;
        this.moneda = moneda;
    }

    public String getNume() {
        return nume;
    }

    public double getSuma() {
        return suma;
    }

    public String getMoneda() {
        return moneda;
    }

    /*public int getImagine() {
        return imagine;
    }*/

}
