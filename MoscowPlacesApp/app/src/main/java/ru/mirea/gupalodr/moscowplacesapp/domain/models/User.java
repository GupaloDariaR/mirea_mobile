package ru.mirea.gupalodr.moscowplacesapp.domain.models;

import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Place> favoritePlaces;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password =  password;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String new_login) {
        this.login = new_login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String new_password) {
        this.password = new_password;
    }

    public List<Place> getFavoritePlaces() {
        return favoritePlaces;
    }

    public void addToFavoritePlaces(Place place) {
        this.favoritePlaces.add(place);
    }

    public void removeFromFavoritePlaces(int id) {
        this.favoritePlaces.removeIf(place -> (place.getId() == id));
    }

    @Override
    public String toString() {
        return this.getLogin();
    }

}
