package ru.mirea.gupalodr.data.storage.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.gupalodr.data.storage.UserStorage;
import ru.mirea.gupalodr.data.storage.models.Place;
import ru.mirea.gupalodr.data.storage.models.User;

public class SharedPrefUserStorage implements UserStorage {
    private static final List<User> USERS = new ArrayList<>();
    Context context;

    public SharedPrefUserStorage (Context context){
        this.context = context;
        USERS.add(new User(1, "daria", "daria_pass"));
    }

    @Override
    public boolean saveUser(User user) {
        if (USERS.contains(user)) {
            return false;
        } else {
            USERS.add(user);
            return true;
        }
    }

    public static List<User> getAllUsers() {
        return USERS;
    }

    @Override
    public User getUserById(int id) {
        for (User user : USERS) {
            if(user.getId() == id) return user;
        };
        return null;
    }

    @Override
    public boolean ChangeUserInfoById(int id, String login, String password) {
        for (User user: USERS) {
            if (user.getId() == id) {
                user.setLogin(login);
                user.setPassword(password);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean logIn(String login, String password) {
        for (User user : USERS) {
            if (user.getLogin() == login && user.getPassword() == password) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("user", user.getId());
                editor.apply();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addPlaceToFavorites(Place place) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        int userid = sharedPreferences.getInt("user", 1);
        getUserById(userid).addToFavoritePlaces(place);
        return true;
    }

    @Override
    public List<Place> getFavoritePlaces() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        int userid = sharedPreferences.getInt("user", 1);
        return getUserById(userid).getFavoritePlaces();
    }

    @Override
    public boolean removePlaceFromFavorites(int id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        int userid = sharedPreferences.getInt("user", 1);
        getUserById(userid).removeFromFavoritePlaces(id);
        return true;
    }
}
