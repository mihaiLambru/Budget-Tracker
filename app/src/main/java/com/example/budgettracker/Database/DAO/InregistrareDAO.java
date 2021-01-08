package com.example.budgettracker.Database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgettracker.Database.Entities.Inregistrare;

@Dao
public interface InregistrareDAO {

    @Insert
    void insert(Inregistrare inregistrare);

    @Update
    void update(Inregistrare inregistrare);

    @Delete
    void delete(Inregistrare inregistrare);

    @Query("SELECT * FROM Inregistrari WHERE idInregistrare LIKE :idInregistrare")
    Inregistrare select(int idInregistrare);

    //de mai pus un select
}
