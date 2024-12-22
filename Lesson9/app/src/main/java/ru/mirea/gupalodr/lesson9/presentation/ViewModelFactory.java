package ru.mirea.gupalodr.lesson9.presentation;

import androidx.annotation.NonNull;
import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.gupalodr.data.repository.MovieRepositoryImpl;
import ru.mirea.gupalodr.data.storage.MovieStorage;
import ru.mirea.gupalodr.data.storage.sharedprefs.SharedPrefMovieStorage;
import ru.mirea.gupalodr.domain.repository.MovieRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public ViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        MovieStorage sharedPrefMovieStorage = new SharedPrefMovieStorage(context);
        MovieRepository movieRepository = new MovieRepositoryImpl(sharedPrefMovieStorage);
        return (T) new MainViewModel(movieRepository);
    }

}
