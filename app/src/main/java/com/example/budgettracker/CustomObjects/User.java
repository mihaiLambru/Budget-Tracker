package com.example.budgettracker.CustomObjects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "Users")
public class User implements Serializable {
    @PrimaryKey
    private int idUser;
    private String nume;
    //de pus si email, probabil de schimbat cheia primara

    public User(int idUser, String nume) {
        this.idUser = idUser;
        this.nume = nume;
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

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", nume='" + nume + '\'' +
                '}';
    }
}
