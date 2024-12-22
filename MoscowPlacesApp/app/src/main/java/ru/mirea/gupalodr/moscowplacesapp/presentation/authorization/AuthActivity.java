package ru.mirea.gupalodr.moscowplacesapp.presentation.authorization;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.gupalodr.moscowplacesapp.R;
import ru.mirea.gupalodr.moscowplacesapp.presentation.ListOfPlaces.MainActivity;

public class AuthActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        authViewModel = new ViewModelProvider(this, new AuthViewModelFactory(getApplicationContext(), FirebaseAuth.getInstance())).get(AuthViewModel.class);

        EditText login = findViewById(R.id.editTextLogin);
        EditText password = findViewById(R.id.editTextPassword);
        RadioButton radioButtonSignIn = findViewById(R.id.radioButtonSignIn);
        RadioButton radioButtonSignUp = findViewById(R.id.radioButtonSignUp);

        if (radioButtonSignIn.isChecked()) {
            findViewById(R.id.buttonSignUp).setVisibility(View.GONE);
            findViewById(R.id.buttonSignIn).setVisibility(View.VISIBLE);
            Log.d("KKK","AuthActivity -> radioButtonSignIn is Checked");
        }

        if (radioButtonSignUp.isChecked()) {
            findViewById(R.id.buttonSignIn).setVisibility(View.GONE);
            findViewById(R.id.buttonSignUp).setVisibility(View.VISIBLE);
            Log.d("KKK","AuthActivity -> radioButtonSignUp is Checked");
        }

        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = authViewModel.signIn(login.getText().toString(), password.getText().toString());
                String message = "Логин или пароль введены не верно. Попробуйте еще раз.";
                checkAuthResult(result, message);
                Log.d("KKK","AuthActivity -> click to buttonSignIn");
            }
        });

        findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = authViewModel.signUp(login.getText().toString(), password.getText().toString());
                String message = "Такой пользователь уже существует или данные введены не корректно.";
                checkAuthResult(result, message);
                Log.d("KKK","AuthActivity -> click to buttonSignUp");
            }
        });

    }

    private void checkAuthResult (Boolean result, String message) {
        if (result) {
            Log.d("KKK","AuthActivity -> login success - go to main activity");
            Intent intent = new Intent(AuthActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(AuthActivity.this, message, Toast.LENGTH_SHORT).show();
            Log.d("KKK",message);
        }
    }
}
