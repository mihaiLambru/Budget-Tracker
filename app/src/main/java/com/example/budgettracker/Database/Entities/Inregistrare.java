package com.example.budgettracker.Database.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Inregistrari", foreignKeys = @ForeignKey(entity = Cont.class,
        parentColumns = "idCont",
        childColumns = "idCont",
        onDelete = ForeignKey.CASCADE))
public class Inregistrare {

    public enum CategorieCheltuiala {
        ALIMENTE, CUMPARATURI, LOCUINTA, TRANSPORT, VEHICUL, DIVERTISMENT, NULL
    }

    public enum CategorieVenit {
        SALARIU, CHIRII, PROIECTE, DIVIDENDE, NULL
    }

    public enum TipInregistrare {
        VENIT, CHELTUIALA
    }

    @PrimaryKey(autoGenerate = true)
    private int idInregistrare;
    private float suma;
    private int idCont;

    private CategorieCheltuiala categorieCheltuiala;
    private CategorieVenit categorieVenit;
    private TipInregistrare tipInregistrare;

    private static int id = 0;

    public Inregistrare(float suma, int idCont, CategorieCheltuiala categorieCheltuiala, TipInregistrare tipInregistrare) {
        this.suma = suma;
        this.idCont = idCont;
        this.categorieCheltuiala = categorieCheltuiala;
        this.categorieVenit = null;
        this.tipInregistrare = tipInregistrare;

    }

    @Ignore
    public Inregistrare(float suma, int idCont, CategorieVenit categorieVenit, TipInregistrare tipInregistrare) {
        this.suma = suma;
        this.idCont = idCont;
        this.categorieVenit = categorieVenit;
        this.categorieCheltuiala = null;
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

    public float getSuma() {
        return suma;
    }

    public void setSuma(float suma) {
        this.suma = suma;
    }

    public CategorieVenit getCategorieVenit() {
        return categorieVenit;
    }

    public void setCategorieVenit(CategorieVenit categorieVenit) {
        this.categorieVenit = categorieVenit;
    }
}
