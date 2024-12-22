package ru.mirea.gupalodr.data.room;


import ru.mirea.gupalodr.data.room.models.UserDB;

public interface UserDBStorage {
    void addUser(UserDB user);
    void getUser(String login, DBCallback<UserDB> callback);
}
