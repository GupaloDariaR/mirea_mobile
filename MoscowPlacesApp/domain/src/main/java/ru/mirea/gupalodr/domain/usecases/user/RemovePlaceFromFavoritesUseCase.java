package ru.mirea.gupalodr.domain.usecases.user;

import ru.mirea.gupalodr.domain.repository.UserRepository;

public class RemovePlaceFromFavoritesUseCase {
    private UserRepository userRepository;

    public RemovePlaceFromFavoritesUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(int id) {
        return userRepository.removePlaceFromFavorites(id);
    }
}
