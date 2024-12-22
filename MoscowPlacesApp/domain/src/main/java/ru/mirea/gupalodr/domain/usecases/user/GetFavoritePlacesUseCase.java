package ru.mirea.gupalodr.domain.usecases.user;

import java.util.List;

import ru.mirea.gupalodr.domain.repository.UserRepository;
import ru.mirea.gupalodr.domain.models.Place;

public class GetFavoritePlacesUseCase {
    private UserRepository userRepository;

    public GetFavoritePlacesUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Place> execute() {
        return userRepository.getFavoritePlaces();
    }
}
