package com.example.budgettracker.Database.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Inregistrari", foreignKeys = @ForeignKey(entity = Cont.class,
        parentColumns = "idCont",
        childColumns = "idCont",
        onDelete = ForeignKey.CASCADE))
public class Inregistrare {

    public enum CategorieCheltuiala {
        ALIMENTE, CUMPARATURI, LOCUINTA, TRANSPORT, VEHICUL, DIVERTISMENT
    }

    public enum CategorieVenit {
        SALARIU, CHIRII, PROIECTE, DIVIDENDE
    }

    public enum TipInregistrare {
        VENIT, CHELTUIALA
    }

    @PrimaryKey(autoGenerate = true)
    private int idInregistrare;
    private double suma;
    private int idCont;

    private CategorieCheltuiala categorieCheltuiala;
    private TipInregistrare tipInregistrare;

    public Inregistrare(int idInregistrare, double suma, int idCont, CategorieCheltuiala categorieCheltuiala, TipInregistrare tipInregistrare) {
        this.idInregistrare = idInregistrare;
        this.suma = suma;
        this.idCont = idCont;
        this.categorieCheltuiala = categorieCheltuiala;
        this.tipInregistrare = tipInregistrare;
    }

    public int getIdCont() {
        return idCont;
    }

    public void setIdCont(int idCont) {
        this.idCont = idCont;
    }

    public CategorieCheltuiala getCategorieCheltuiala() {
        return categorieCheltuiala;
    }

    public void setCategorieCheltuiala(CategorieCheltuiala categorieCheltuiala) {
        this.categorieCheltuiala = categorieCheltuiala;
    }

    public TipInregistrare getTipInregistrare() {
        return tipInregistrare;
    }

    public void setTipInregistrare(TipInregistrare tipInregistrare) {
        this.tipInregistrare = tipInregistrare;
    }

    public int getIdInregistrare() {
        return idInregistrare;
    }

    public void setIdInregistrare(int idInregistrare) {
        this.idInregistrare = idInregistrare;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }
}
