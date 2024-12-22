package ru.mirea.gupalodr.data.storage;

import ru.mirea.gupalodr.data.storage.models.Movie;

public interface MovieStorage {
    public Movie get();
    public boolean save(Movie movie);
}
