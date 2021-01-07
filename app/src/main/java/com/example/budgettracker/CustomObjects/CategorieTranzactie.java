package com.example.budgettracker.CustomObjects;

public class CategorieTranzactie {

    public enum Categorie {
        ALIMENTE, CUMPARATURI, LOCUINTA, TRANSPORT, VEHICUL, DIVERTISMENT
    }

    private Categorie categorie;
    private int idImagine;

    public CategorieTranzactie(Categorie categorie, int idImagine) {
        this.categorie = categorie;
        this.idImagine = idImagine;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public int getIdImagine() {
        return idImagine;
    }
}
