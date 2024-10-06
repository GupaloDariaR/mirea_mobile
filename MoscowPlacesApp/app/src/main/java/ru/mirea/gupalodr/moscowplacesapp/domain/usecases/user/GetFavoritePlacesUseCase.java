package ru.mirea.gupalodr.moscowplacesapp.domain.usecases.user;

import java.util.List;

import ru.mirea.gupalodr.moscowplacesapp.domain.models.Place;
import ru.mirea.gupalodr.moscowplacesapp.domain.repository.PlaceRepository;
import ru.mirea.gupalodr.moscowplacesapp.domain.repository.UserRepository;

public class GetFavoritePlacesUseCase {
    private UserRepository userRepository;

    public GetFavoritePlacesUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Place> execute() {
        return userRepository.getFavoritePlaces();
    }
}
