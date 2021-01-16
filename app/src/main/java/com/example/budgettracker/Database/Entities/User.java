package com.example.budgettracker.Database.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "Users")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int idUser;
    private String nume;
    private String email;

    private static int id = 0;

    public User(String nume, String email) {
        this.nume = nume;
        this.email = email;
    }

    public int getIdUser() {
        return idUser;
    }

    public static int getLastId() {
        return id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", nume='" + nume + '\'' +
                '}';
    }
}
