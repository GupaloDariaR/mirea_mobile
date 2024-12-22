package ru.mirea.gupalodr.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
// Cоздать список исторических событий с кратким описанием и изображением.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Event> events = getListData();
        RecyclerView recyclerView = this.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new EventRecyclerViewAdapter(events));

        // RecyclerView scroll vertical
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }
    private List<Event> getListData() {
        List<Event> list = new ArrayList<Event>();
        Event cosmos = new Event("Первый полет в космос",
                "12 апреля 1961 года на околоземную орбиту вышел Юрий Алексеевич Гагарин",
                "vostok_spacecraft");
        list.add(cosmos);
        return list;
    }

}