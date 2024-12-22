package ru.mirea.gupalodr.moscowplacesapp.presentation.authorization;

import com.google.firebase.auth.FirebaseAuth;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.gupalodr.data.repository.UserRepositoryImpl;
import ru.mirea.gupalodr.data.storage.UserStorage;
import ru.mirea.gupalodr.data.storage.sharedprefs.SharedPrefUserStorage;
import ru.mirea.gupalodr.domain.repository.UserRepository;

public class AuthViewModelFactory implements ViewModelProvider.Factory {
    private Context context;
    private FirebaseAuth firebaseAuth;

    public AuthViewModelFactory(Context context, FirebaseAuth firebaseAuth) {
        this.context = context;
        this.firebaseAuth = firebaseAuth;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        UserStorage sharedPrefUserStorage = new SharedPrefUserStorage(context);
        UserRepository userRepository = new UserRepositoryImpl(sharedPrefUserStorage, firebaseAuth);
        return (T) new AuthViewModel(userRepository);
    }
}
