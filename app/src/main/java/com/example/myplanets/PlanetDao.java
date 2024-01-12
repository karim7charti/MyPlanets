package com.example.myplanets;
import android.database.Cursor;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PlanetDao {

    @Insert
    void insertPlanet(Planet planet);

    @Update
    void updatePlanet(Planet planet);

    @Delete
    void deletePlanet(Planet planet);

    @Query("SELECT * FROM planet")
    public Cursor getAllPlanets();

    @Query("SELECT * FROM planet WHERE _id LIKE :id")
    public Planet getPlanetById(int id);

    @Query("SELECT * FROM planet WHERE name LIKE :planetName")
    public Planet getPlanetByName(String planetName);

}
