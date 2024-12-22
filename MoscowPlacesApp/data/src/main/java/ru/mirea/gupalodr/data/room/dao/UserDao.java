package ru.mirea.gupalodr.data.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import ru.mirea.gupalodr.data.room.models.UserDB;

@Dao
public interface UserDao {
    @Insert
    void addUser(UserDB user);

    @Query("select * from users where login=:login")
    UserDB getUser(String login);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateUser(UserDB user);
}
