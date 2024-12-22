package ru.mirea.gupalodr.lesson9.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ru.mirea.gupalodr.lesson9.R;
import ru.mirea.gupalodr.data.repository.MovieRepositoryImpl;
import ru.mirea.gupalodr.data.storage.MovieStorage;
import ru.mirea.gupalodr.data.storage.sharedprefs.SharedPrefMovieStorage;
import ru.mirea.gupalodr.domain.models.Movie;
import ru.mirea.gupalodr.domain.repository.MovieRepository;
import ru.mirea.gupalodr.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.gupalodr.domain.usecases.SaveFilmToFavoriteUseCase;

public class MainActivity extends AppCompatActivity {

    private MainViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(MainActivity.class.getSimpleName().toString(), "MainActivity created");
        vm = new ViewModelProvider(this, new ViewModelFactory(this)).get(MainViewModel.class);

        EditText text = findViewById(R.id.editTextMovie);
        TextView textView = findViewById(R.id.textViewMovie);

        vm.getFavoriteMovie().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });

        findViewById(R.id.buttonSaveMovie).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                vm.setText(new Movie(2, text.getText().toString()));
             }
        });

        findViewById(R.id.buttonGetMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.getText();
            }
        });
    }
}