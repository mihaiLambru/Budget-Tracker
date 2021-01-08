package com.example.budgettracker.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.budgettracker.Database.DAO.InregistrareDAO;
import com.example.budgettracker.Database.Entities.Cont;
import com.example.budgettracker.Database.Entities.Inregistrare;
import com.example.budgettracker.Database.Entities.User;
import com.example.budgettracker.Database.DAO.ContDAO;
import com.example.budgettracker.Database.DAO.UserDAO;

@Database(entities = {User.class, Cont.class, Inregistrare.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class LocalDatabase extends RoomDatabase {
    private static LocalDatabase instance;

    public abstract UserDAO getUserDAO();

    public abstract ContDAO getContDAO();

    public abstract InregistrareDAO getInregistrareDAO();

    public static synchronized LocalDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, "LocalDatabase.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
