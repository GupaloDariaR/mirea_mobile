package ru.mirea.gupalodr.moscowplacesapp.domain.usecases.user;

import ru.mirea.gupalodr.moscowplacesapp.domain.models.User;
import ru.mirea.gupalodr.moscowplacesapp.domain.repository.UserRepository;

public class SignUpUseCase {
    private UserRepository userRepository;

    public SignUpUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(User user) {
        return userRepository.saveUser(user);
    }
}
