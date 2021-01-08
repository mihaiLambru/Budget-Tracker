package com.example.budgettracker.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "Users")
public class User implements Serializable {
    @PrimaryKey
    private int idUser;
    private String nume;
    private String email;

    public User(int idUser, String nume, String email) {
        this.idUser = idUser;
        this.nume = nume;
        this.email = email;
    }

    public int getIdUser() {
        return idUser;
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
