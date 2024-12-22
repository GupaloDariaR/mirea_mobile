package ru.mirea.gupalodr.data.repository;

import android.annotation.SuppressLint;

import java.time.LocalDate;

import ru.mirea.gupalodr.data.storage.MovieStorage;
import ru.mirea.gupalodr.data.storage.models.Movie;
import ru.mirea.gupalodr.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    MovieStorage sharedPrefMovieStorage;
    public MovieRepositoryImpl(MovieStorage sharedPrefMovieStorage){
        this.sharedPrefMovieStorage = sharedPrefMovieStorage;
    }
    @SuppressLint("CommitPrefEdits")
    @Override
    public boolean saveMovie(ru.mirea.gupalodr.domain.models.Movie movie){
        sharedPrefMovieStorage.save(mapToStorage(movie));
        return true;
    }
    @Override
    public ru.mirea.gupalodr.domain.models.Movie getMovie(){
        Movie movie = sharedPrefMovieStorage.get();
        return mapToDomain(movie);
    }

    private Movie mapToStorage(ru.mirea.gupalodr.domain.models.Movie movie){
        String name = movie.getName();
        return new Movie(2, name, LocalDate.now().toString());
    }

    private ru.mirea.gupalodr.domain.models.Movie mapToDomain(Movie movie){
        String name = movie.getName();
        return new ru.mirea.gupalodr.domain.models.Movie(movie.getId(), movie.getName());
    }
}
