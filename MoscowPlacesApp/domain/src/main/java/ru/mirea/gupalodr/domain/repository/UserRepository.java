package ru.mirea.gupalodr.domain.repository;

import java.util.List;

import ru.mirea.gupalodr.domain.models.Place;
import ru.mirea.gupalodr.domain.models.User;

public interface UserRepository {
    public List<User> getAllUsers();
//    public boolean saveUser(User user);
    public User getUserById(int id);
    public boolean changeUserInfoById(int id, String login, String password);
    public boolean signIn(String login, String password);
    public boolean signUp(String login, String password);
    public void signOut();
    public boolean addPlaceToFavorites(Place place);
    public List<Place> getFavoritePlaces();
    public boolean removePlaceFromFavorites(int id);
}
