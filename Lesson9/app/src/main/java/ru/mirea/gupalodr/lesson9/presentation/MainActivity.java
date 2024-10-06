package ru.mirea.gupalodr.lesson9.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ru.mirea.gupalodr.lesson9.R;
import ru.mirea.gupalodr.lesson9.data.repository.MovieRepositoryImpl;
import ru.mirea.gupalodr.lesson9.domain.models.Movie;
import ru.mirea.gupalodr.lesson9.domain.repository.MovieRepository;
import ru.mirea.gupalodr.lesson9.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.gupalodr.lesson9.domain.usecases.SaveFilmToFavoriteUseCase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText text = findViewById(R.id.editTextMovie);
        TextView textView = findViewById(R.id.textViewMovie);

        MovieRepository movieRepository = new MovieRepositoryImpl(getApplicationContext());

        findViewById(R.id.buttonSaveMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean result = new SaveFilmToFavoriteUseCase(movieRepository).execute(new Movie(2, text.getText().toString()));
                textView.setText(String.format("Save result %s", result));
            }
        });

        findViewById(R.id.buttonGetMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
                textView.setText(String.format("Save result %s", movie.getName()));
            }
        });
    }
}