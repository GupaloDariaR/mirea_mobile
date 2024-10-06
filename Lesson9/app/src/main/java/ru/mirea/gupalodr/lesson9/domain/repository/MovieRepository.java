package ru.mirea.gupalodr.lesson9.domain.repository;

import ru.mirea.gupalodr.lesson9.domain.models.Movie;

public interface MovieRepository {
    public boolean saveMovie(Movie movie);
    public Movie getMovie();
}
