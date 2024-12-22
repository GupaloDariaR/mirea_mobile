package ru.mirea.gupalodr.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.gupalodr.data.room.dao.UserDao;

@Database(entities = {ru.mirea.gupalodr.data.room.models.UserDB.class}, version = 1)
public abstract class UserDB extends RoomDatabase {
    public abstract UserDao getUserDao();
}
