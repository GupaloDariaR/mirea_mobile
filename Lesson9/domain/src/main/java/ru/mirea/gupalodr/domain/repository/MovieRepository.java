package ru.mirea.gupalodr.domain.repository;

import ru.mirea.gupalodr.domain.models.Movie;

public interface MovieRepository {
    public boolean saveMovie(Movie movie);
    public Movie getMovie();
}
