package ru.mirea.gupalodr.domain.usecases;

import ru.mirea.gupalodr.domain.models.Movie;
import ru.mirea.gupalodr.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private MovieRepository movieRepository;
    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public Movie execute(){
        return movieRepository.getMovie();
    }
}
