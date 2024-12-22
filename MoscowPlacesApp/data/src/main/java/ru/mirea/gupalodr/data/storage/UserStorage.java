package ru.mirea.gupalodr.data.storage;

import java.util.List;

import ru.mirea.gupalodr.data.storage.models.Place;
import ru.mirea.gupalodr.data.storage.models.User;

public interface UserStorage {
    public boolean saveUser(User user);
    public User getUserById(int id);
    public boolean changeUserInfoById(int id, String login, String password);
    public boolean addPlaceToFavorites(Place place);
    public List<Place> getFavoritePlaces();
    public boolean removePlaceFromFavorites(int id);

}