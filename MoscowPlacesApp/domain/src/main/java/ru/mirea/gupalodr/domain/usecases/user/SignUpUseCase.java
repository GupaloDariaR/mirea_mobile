package ru.mirea.gupalodr.domain.usecases.user;

import ru.mirea.gupalodr.domain.models.User;
import ru.mirea.gupalodr.domain.repository.UserRepository;

public class SignUpUseCase {
    private UserRepository userRepository;

    public SignUpUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(String login, String password) {
        return userRepository.signUp(login, password);
    }
}
