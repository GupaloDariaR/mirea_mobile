package ru.mirea.gupalodr.moscowplacesapp.presentation.authorization;

import androidx.lifecycle.ViewModel;

import ru.mirea.gupalodr.domain.repository.UserRepository;
import ru.mirea.gupalodr.domain.usecases.user.LogInUseCase;
import ru.mirea.gupalodr.domain.usecases.user.SignUpUseCase;

public class AuthViewModel extends ViewModel {
    public UserRepository userRepository;
    public AuthViewModel (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public Boolean signIn (String login, String password) {
        return new LogInUseCase(userRepository).execute(login, password);
    }

    public Boolean signUp (String login, String password) {
        return new SignUpUseCase(userRepository).execute(login, password);
    }
}
