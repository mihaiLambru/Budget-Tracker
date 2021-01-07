package com.example.budgettracker.CustomObjects;

public class Tranzactie {

    public enum Categorie {
        ALIMENTE, CUMPARATURI, LOCUINTA, TRANSPORT, VEHICUL, DIVERTISMENT
    }

    public enum TipTranzactie {
        VENIT, CHELTUIALA
    }

    private Categorie categorie;
    private TipTranzactie tipTranzactie;

    public Tranzactie(Categorie categorie, TipTranzactie tipTranzactie) {
        this.categorie = categorie;
        this.tipTranzactie = tipTranzactie;
    }

}
