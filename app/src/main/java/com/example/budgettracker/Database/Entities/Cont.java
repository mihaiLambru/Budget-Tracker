package com.example.budgettracker.Database.Entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "Conturi", foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "idUser",
        childColumns = "idUser",
        onDelete = ForeignKey.CASCADE))
public class Cont implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int idCont;
    private String nume;
    private double suma;
    private String moneda;
    private int idUser;


    public Cont(String nume, double suma, String moneda, int idUser) {
        this.nume = nume;
        this.suma = suma;
        this.moneda = moneda;
        this.idUser = idUser;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCont() {
        return idCont;
    }

    public void setIdCont(int idCont) {
        this.idCont = idCont;
    }

}
