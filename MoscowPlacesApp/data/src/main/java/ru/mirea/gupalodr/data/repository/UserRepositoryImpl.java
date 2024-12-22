package ru.mirea.gupalodr.data.repository;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import ru.mirea.gupalodr.data.storage.UserStorage;
import ru.mirea.gupalodr.data.storage.models.Place;
import ru.mirea.gupalodr.data.storage.models.User;
import ru.mirea.gupalodr.data.storage.sharedprefs.SharedPrefUserStorage;
import ru.mirea.gupalodr.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    UserStorage sharedPrefUserStorage;
    private FirebaseAuth firebaseAuth;
    String TAG = "FirebaseAuth";

    public UserRepositoryImpl(UserStorage sharedPrefUserStorage, FirebaseAuth firebaseAuth){
        this.sharedPrefUserStorage = sharedPrefUserStorage;
        this.firebaseAuth = firebaseAuth;
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
    public ru.mirea.gupalodr.domain.models.User getUserById(int id) {
        User user = sharedPrefUserStorage.getUserById(id);
        return mapToDomainUser(user);
    }

    @Override
    public boolean changeUserInfoById(int id, String login, String password) {
        return sharedPrefUserStorage.changeUserInfoById(id, login, password);
    }

    @Override
    public boolean signIn(String login, String password) {
        AtomicBoolean isSuccess = new AtomicBoolean(true);
        firebaseAuth.signInWithEmailAndPassword(login, password)
                .addOnCompleteListener(task -> {
                   if (task.isSuccessful()) {
                       Log.d(TAG, "SignIn success");
                   } else {
                       isSuccess.set(false);
                       Log.d(TAG, "SignIn fail");
                   }
                });
        return isSuccess.get();
    }

    @Override
    public boolean signUp(String login, String password) {
        AtomicBoolean isSuccess = new AtomicBoolean(true);
        firebaseAuth.createUserWithEmailAndPassword(login, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "SignUp success");
                    } else {
                        isSuccess.set(false);
                        Log.d(TAG, "SignUp fail");
                    }
                });
        List<ru.mirea.gupalodr.domain.models.User> allUsers = getAllUsers();
        sharedPrefUserStorage.saveUser(new User(allUsers.get(allUsers.size() - 1).getId()+1, login, password));
        return isSuccess.get();
    }

    public void signOut(){
        firebaseAuth.signOut();
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
        String img = place.getImg();
        return new Place(id, title, description, img);
    }

    private ru.mirea.gupalodr.domain.models.Place mapToDomainPlace(Place place){
        int id = place.getId();
        String title = place.getTitle();
        String description = place.getDescription();
        String img = place.getImg();
        return new ru.mirea.gupalodr.domain.models.Place(id, title, description, img);
    }
}
