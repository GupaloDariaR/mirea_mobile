package ru.mirea.gupalodr.moscowplacesapp.domain.usecases.user;

import ru.mirea.gupalodr.moscowplacesapp.domain.repository.UserRepository;

public class ChangeUserInfoByIdUseCase {
    private UserRepository userRepository;

    public ChangeUserInfoByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(int id, String login, String password) {
        return userRepository.ChangeUserInfoById(id, login, password);
    }
}
