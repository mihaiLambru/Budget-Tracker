package com.example.budgettracker.Database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgettracker.Database.Entities.Cont;

import java.util.List;

@Dao
public interface ContDAO {
    @Insert
    void insert(Cont cont);

    @Update
    void update(Cont cont);

    @Delete
    void delete(Cont cont);

    @Query("SELECT * FROM Conturi WHERE idCont LIKE :id")
    Cont select(int id);

    @Query("SELECT * FROM Conturi WHERE idUser LIKE :id")
    List<Cont> selectList(int id);

}
