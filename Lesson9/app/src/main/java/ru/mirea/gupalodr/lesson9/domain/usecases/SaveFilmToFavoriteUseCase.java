package ru.mirea.gupalodr.lesson9.domain.usecases;

import ru.mirea.gupalodr.lesson9.domain.models.Movie;
import ru.mirea.gupalodr.lesson9.domain.repository.MovieRepository;

public class SaveFilmToFavoriteUseCase {
    private MovieRepository movieRepository;
    public SaveFilmToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean execute(Movie movie){
        return movieRepository.saveMovie(movie);
    }
}
