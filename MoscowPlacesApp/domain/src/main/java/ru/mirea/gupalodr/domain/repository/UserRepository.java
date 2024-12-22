package ru.mirea.gupalodr.domain.repository;

import java.util.List;

import ru.mirea.gupalodr.domain.models.Place;
import ru.mirea.gupalodr.domain.models.User;

public interface UserRepository {
    public boolean saveUser(User user);
    public User getUserById(int id);
    public boolean ChangeUserInfoById(int id, String login, String password);
    public boolean logIn(String login, String password);
    public boolean addPlaceToFavorites(Place place);
    public List<Place> getFavoritePlaces();
    public boolean removePlaceFromFavorites(int id);
}
