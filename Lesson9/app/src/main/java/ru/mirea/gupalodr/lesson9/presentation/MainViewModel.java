package ru.mirea.gupalodr.lesson9.presentation;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.gupalodr.domain.models.Movie;
import ru.mirea.gupalodr.domain.repository.MovieRepository;
import ru.mirea.gupalodr.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.gupalodr.domain.usecases.SaveFilmToFavoriteUseCase;

public class MainViewModel extends ViewModel {
    private final MovieRepository movieRepository;
    private MutableLiveData<String> favoriteMovie = new MutableLiveData<>();

    public MainViewModel(MovieRepository movieRepository) {
        Log.d(MainViewModel.class.getSimpleName().toString(), "MainViewModel created");
        this.movieRepository = movieRepository;
    }

    @Override
    protected void onCleared() {
        Log.d(MainViewModel.class.getSimpleName().toString(), "MainViewModel cleared");
        super.onCleared();
    }

    public MutableLiveData<String> getFavoriteMovie() {
        return favoriteMovie;
    }

    public void setText(Movie movie) {
        boolean result = new SaveFilmToFavoriteUseCase(movieRepository).execute(movie);
        favoriteMovie.setValue(Boolean.toString(result));
    }

    public void getText() {
        Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
        favoriteMovie.setValue(String.format("My favorite movie is %s", movie.getName()));
    }
}
