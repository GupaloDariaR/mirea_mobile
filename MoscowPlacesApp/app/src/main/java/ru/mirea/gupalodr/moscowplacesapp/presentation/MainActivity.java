package ru.mirea.gupalodr.moscowplacesapp.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import ru.mirea.gupalodr.moscowplacesapp.R;
import ru.mirea.gupalodr.moscowplacesapp.data.repository.PlaceRepositoryImpl;
import ru.mirea.gupalodr.moscowplacesapp.data.repository.UserRepositoryImpl;
import ru.mirea.gupalodr.moscowplacesapp.domain.models.Place;
import ru.mirea.gupalodr.moscowplacesapp.domain.models.User;
import ru.mirea.gupalodr.moscowplacesapp.domain.repository.PlaceRepository;
import ru.mirea.gupalodr.moscowplacesapp.domain.repository.UserRepository;
import ru.mirea.gupalodr.moscowplacesapp.domain.usecases.place.GetAllPlacesUseCase;
import ru.mirea.gupalodr.moscowplacesapp.domain.usecases.user.SignUpUseCase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Вывод всех мест
        TextView textAllPlaces = findViewById(R.id.textViewAllPlaces);
        PlaceRepository placeRepository = new PlaceRepositoryImpl(getApplicationContext());

        findViewById(R.id.buttonGetAllPlaces).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Place> places = new GetAllPlacesUseCase(placeRepository).execute();
                String result = "";
                for (Place place : places) {
                    result += String.format("%s\n", place.toString());
                }
                textAllPlaces.setText(result);
            }
        });

//      Вывод всех пользователей
        TextView textAllUsers = findViewById(R.id.textViewAllUsers);
        UserRepositoryImpl userRepository = new UserRepositoryImpl(getApplicationContext());

        findViewById(R.id.buttonGetAllUsers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = userRepository.getAllUsers();
                String result = "";
                for (User user : users) {
                    result += String.format("%d - %s - %s\n", user.getId(), user.getLogin(), user.getPassword());
                }
                textAllUsers.setText(result);
            }
        });

//        Регистрация
        EditText editTextLogin = findViewById(R.id.editTextLogin);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        TextView signUpResult = findViewById(R.id.textViewSignUpResult);

        findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = new SignUpUseCase(userRepository).execute(new User(userRepository.getAllUsers().size()+1, editTextLogin.getText().toString(), editTextPassword.getText().toString()));
                signUpResult.setText(String.format("Результат регистрации: %s", result));
                editTextLogin.setText("");
                editTextPassword.setText("");
            }
        });
    }
}