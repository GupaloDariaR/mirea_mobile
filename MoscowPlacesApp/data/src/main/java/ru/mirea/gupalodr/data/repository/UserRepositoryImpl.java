package ru.mirea.gupalodr.data.repository;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.gupalodr.data.storage.UserStorage;
import ru.mirea.gupalodr.data.storage.models.Place;
import ru.mirea.gupalodr.data.storage.models.User;
import ru.mirea.gupalodr.data.storage.sharedprefs.SharedPrefUserStorage;
import ru.mirea.gupalodr.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    UserStorage sharedPrefUserStorage;

    public UserRepositoryImpl(UserStorage sharedPrefUserStorage){
        this.sharedPrefUserStorage = sharedPrefUserStorage;
    }

    @Override
    public List<ru.mirea.gupalodr.domain.models.User> getAllUsers() {
        List<User> users = SharedPrefUserStorage.getAllUsers();
        List<ru.mirea.gupalodr.domain.models.User> allUsers = new ArrayList<>();
        for (User user : users) {
            allUsers.add(mapToDomainUser(user));
        }
        return allUsers;
    }

    @Override
    public boolean saveUser(ru.mirea.gupalodr.domain.models.User user) {
        return sharedPrefUserStorage.saveUser(mapToStorageUser(user));
    }

    @Override
    public ru.mirea.gupalodr.domain.models.User getUserById(int id) {
        User user = sharedPrefUserStorage.getUserById(id);
        return mapToDomainUser(user);
    }

    @Override
    public boolean ChangeUserInfoById(int id, String login, String password) {
        return sharedPrefUserStorage.ChangeUserInfoById(id, login, password);
    }

    @Override
    public boolean logIn(String login, String password) {
        return sharedPrefUserStorage.logIn(login, password);
    }

    @Override
    public boolean addPlaceToFavorites(ru.mirea.gupalodr.domain.models.Place place) {
        return sharedPrefUserStorage.addPlaceToFavorites(mapToStoragePlace(place));
    }

    @Override
    public List<ru.mirea.gupalodr.domain.models.Place> getFavoritePlaces() {
        List<Place> favoritePlaces = sharedPrefUserStorage.getFavoritePlaces();
        List<ru.mirea.gupalodr.domain.models.Place> favs = new ArrayList<>();
        for (Place place : favoritePlaces) {
            favs.add(mapToDomainPlace(place));
        }
        return favs;
    }

    @Override
    public boolean removePlaceFromFavorites(int id) {
        return sharedPrefUserStorage.removePlaceFromFavorites(id);
    }

    private User mapToStorageUser(ru.mirea.gupalodr.domain.models.User user){
        int id = user.getId();
        String login = user.getLogin();
        String password = user.getPassword();
        return new User(id, login, password);
    }

    private ru.mirea.gupalodr.domain.models.User mapToDomainUser(User user){
        int id = user.getId();
        String login = user.getLogin();
        String password = user.getPassword();
        return new ru.mirea.gupalodr.domain.models.User(id, login, password);
    }

    private Place mapToStoragePlace(ru.mirea.gupalodr.domain.models.Place place){
        int id = place.getId();
        String title = place.getTitle();
        String description = place.getDescription();
        return new Place(id, title, description);
    }

    private ru.mirea.gupalodr.domain.models.Place mapToDomainPlace(Place place){
        int id = place.getId();
        String title = place.getTitle();
        String description = place.getDescription();
        return new ru.mirea.gupalodr.domain.models.Place(id, title, description);
    }
}
