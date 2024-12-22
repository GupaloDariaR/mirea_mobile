package ru.mirea.gupalodr.domain.usecases.user;

import ru.mirea.gupalodr.domain.repository.UserRepository;
import ru.mirea.gupalodr.domain.models.Place;

public class AddPlaceToFavoritesUseCase {
    private UserRepository userRepository;

    public AddPlaceToFavoritesUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(Place place) {
        return userRepository.addPlaceToFavorites(place);
    }
}
