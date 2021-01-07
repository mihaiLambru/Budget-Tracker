package com.example.budgettracker.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.budgettracker.CustomObjects.Cont;
import com.example.budgettracker.CustomObjects.User;

@Database(entities = {User.class, Cont.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {
    private static LocalDatabase instance;

    public abstract UserDAO getUserDAO();

    public abstract ContDAO getContDAO();

    static synchronized LocalDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, "LocalDatabase4.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
