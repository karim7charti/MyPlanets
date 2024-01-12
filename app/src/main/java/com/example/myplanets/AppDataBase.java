package com.example.myplanets;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Planet.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase INSTANCE;

    public abstract PlanetDao planetDao();

    public static AppDataBase getAppDataBase(Context context){
        if (INSTANCE == null){
            synchronized (AppDataBase.class){
                if (INSTANCE == null) INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "planets_db").build();
            }
        }
        return INSTANCE;
    }

}
