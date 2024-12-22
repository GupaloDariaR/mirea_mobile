package ru.mirea.gupalodr.domain.usecases.user;

import ru.mirea.gupalodr.domain.models.User;
import ru.mirea.gupalodr.domain.repository.UserRepository;

public class GetUserInfoByIdUseCase {
    private UserRepository userRepository;

    public GetUserInfoByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(int id) {
        return userRepository.getUserById(id);
    }
}
