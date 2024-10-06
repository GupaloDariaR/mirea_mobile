package ru.mirea.gupalodr.moscowplacesapp.domain.usecases.user;

import ru.mirea.gupalodr.moscowplacesapp.domain.models.User;
import ru.mirea.gupalodr.moscowplacesapp.domain.repository.UserRepository;

public class LogInUseCase {
    private UserRepository userRepository;

    public LogInUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(String login, String password) {
        return userRepository.logIn(login, password);
    }
}
