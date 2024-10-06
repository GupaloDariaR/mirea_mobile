package ru.mirea.gupalodr.moscowplacesapp.domain.usecases.user;

import ru.mirea.gupalodr.moscowplacesapp.domain.models.User;
import ru.mirea.gupalodr.moscowplacesapp.domain.repository.UserRepository;

public class GetUserInfoByIdUseCase {
    private UserRepository userRepository;

    public GetUserInfoByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(int id) {
        return userRepository.getUserById(id);
    }
}
