package ru.mirea.gupalodr.moscowplacesapp.domain.usecases.user;

import ru.mirea.gupalodr.moscowplacesapp.domain.models.Place;
import ru.mirea.gupalodr.moscowplacesapp.domain.models.Review;
import ru.mirea.gupalodr.moscowplacesapp.domain.repository.PlaceRepository;
import ru.mirea.gupalodr.moscowplacesapp.domain.repository.UserRepository;

public class AddPlaceToFavoritesUseCase {
    private UserRepository userRepository;

    public AddPlaceToFavoritesUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(Place place) {
        return userRepository.addPlaceToFavorites(place);
    }
}
