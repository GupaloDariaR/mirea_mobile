package ru.mirea.gupalodr.data.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.mirea.gupalodr.data.room.models.PlaceDB;

@Dao
public interface PlaceDao {
    @Insert
    void addPlace(PlaceDB place);

    @Query("select * from places where id==:id")
    PlaceDB getPlaceById(int id);

    @Query("select * from places")
    List<PlaceDB> getAllPlaces();

    @Query("delete from places where id==:id")
    void deletePlace(int id);
}
