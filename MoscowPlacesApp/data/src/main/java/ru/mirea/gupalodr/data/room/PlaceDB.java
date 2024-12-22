package ru.mirea.gupalodr.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.gupalodr.data.room.dao.PlaceDao;

@Database(entities = {ru.mirea.gupalodr.data.room.models.PlaceDB.class}, version = 1)
public abstract class PlaceDB extends RoomDatabase{
    public abstract PlaceDao getPlaceDao();
}





