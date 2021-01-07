package com.example.budgettracker.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgettracker.CustomObjects.Cont;

@Dao
public interface ContDAO {
    @Insert
    void insert(Cont cont);

    @Update
    void update(Cont cont);

    @Delete
    void delete(Cont cont);

    @Query("SELECT * FROM Conturi WHERE id LIKE :id")
    Cont select(int id);

    //mai trebuie un query

}
