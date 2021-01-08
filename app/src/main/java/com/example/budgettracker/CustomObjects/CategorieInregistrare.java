package com.example.budgettracker.CustomObjects;

public class CategorieInregistrare {

    public enum Categorie {
        ALIMENTE, CUMPARATURI, LOCUINTA, TRANSPORT, VEHICUL, DIVERTISMENT, SALARIU, CHIRII, PROIECTE, DIVIDENDE
    }

    private Categorie categorie;
    private int idImagine;

    public CategorieInregistrare(Categorie categorie, int idImagine) {
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
