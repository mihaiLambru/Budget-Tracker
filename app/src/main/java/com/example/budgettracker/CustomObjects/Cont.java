package com.example.budgettracker.CustomObjects;


import java.io.Serializable;

public class Cont implements Serializable {
    private String nume;
    private double suma;
    private String moneda;

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

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}
