package ru.mirea.gupalodr.lesson9.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import ru.mirea.gupalodr.lesson9.domain.models.Movie;
import ru.mirea.gupalodr.lesson9.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    Context context;

    public MovieRepositoryImpl(Context context){
        this.context = context;
    }

    @Override
    public boolean saveMovie(Movie movie){
        SharedPreferences sharedPreferences = context.getSharedPreferences("movie", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("movie", movie.getName());
        editor.apply();
        return true;
    }
    @Override
    public Movie getMovie(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("movie", Context.MODE_PRIVATE);
        return new Movie(1, sharedPreferences.getString("movie", "Game of throne"));
    }
}
