package ru.mirea.gupalodr.moscowplacesapp.presentation.ListOfPlaces;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.gupalodr.domain.models.Place;
import ru.mirea.gupalodr.moscowplacesapp.R;

public class PlaceRecyclerViewAdapter extends RecyclerView.Adapter<PlaceViewHolder> {
    private List<Place> itemList = new ArrayList<>();

    public void setItems(List<Place> items) {
        this.itemList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recyclerViewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_item_view, parent, false);
        return new PlaceViewHolder(recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Place place = itemList.get(position);
        holder.textViewPlaceTitle().setText(place.getTitle());
        holder.textViewPlaceDescription().setText(place.getDescription());
        Picasso.get()
                .load(place.getImg())
                .resize(100,100)
                .centerCrop()
                .error(R.drawable.error_image)
                .placeholder(R.drawable.placeholder)
                .into(holder.getImgView());
        Log.d("KKK","PlaceRecyclerViewAdapter -> вывод данных их api");
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
