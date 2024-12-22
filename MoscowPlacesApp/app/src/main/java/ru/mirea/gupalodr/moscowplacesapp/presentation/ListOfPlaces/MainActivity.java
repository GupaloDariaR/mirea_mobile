package ru.mirea.gupalodr.moscowplacesapp.presentation.ListOfPlaces;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import ru.mirea.gupalodr.moscowplacesapp.R;
import ru.mirea.gupalodr.moscowplacesapp.presentation.authorization.AuthActivity;


public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PlaceRecyclerViewAdapter itemAdapter = new PlaceRecyclerViewAdapter();
        recyclerView.setAdapter(itemAdapter);

        mainViewModel = new ViewModelProvider(this, new MainViewModelFactory(getApplicationContext())).get(MainViewModel.class);
        // Добавляем наблюдатель для LiveData
        mainViewModel.getItems().observe(this, items -> {
            itemAdapter.setItems(items); // Обновляем данные в адаптере
        });

        findViewById(R.id.buttonGoToAuth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("KKK","MainActivity -> click to buttonGoToAuth");
                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                startActivity(intent);
            }
        });
    }
}