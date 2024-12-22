package ru.mirea.gupalodr.domain.usecases;

import ru.mirea.gupalodr.domain.models.Movie;
import ru.mirea.gupalodr.domain.repository.MovieRepository;

public class SaveFilmToFavoriteUseCase {
    private MovieRepository movieRepository;
    public SaveFilmToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean execute(Movie movie){
        return movieRepository.saveMovie(movie);
    }
}
