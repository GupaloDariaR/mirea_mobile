package ru.mirea.gupalodr.domain.usecases.user;

import ru.mirea.gupalodr.domain.repository.UserRepository;

public class LogInUseCase {
    private UserRepository userRepository;

    public LogInUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(String login, String password) {
        return userRepository.signIn(login, password);
    }
}
