package ru.mirea.gupalodr.moscowplacesapp.domain.usecases.user;

import ru.mirea.gupalodr.moscowplacesapp.domain.repository.PlaceRepository;
import ru.mirea.gupalodr.moscowplacesapp.domain.repository.UserRepository;

public class RemovePlaceFromFavoritesUseCase {
    private UserRepository userRepository;

    public RemovePlaceFromFavoritesUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(int id) {
        return userRepository.removePlaceFromFavorites(id);
    }
}
