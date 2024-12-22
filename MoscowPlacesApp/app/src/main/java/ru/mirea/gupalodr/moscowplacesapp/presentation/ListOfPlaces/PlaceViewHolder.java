package ru.mirea.gupalodr.moscowplacesapp.presentation.ListOfPlaces;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.mirea.gupalodr.moscowplacesapp.R;

public class PlaceViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewPlaceTitle;
    private TextView textViewPlaceDescription;
    private ImageView imageView;

    public PlaceViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textViewPlaceTitle = itemView.findViewById(R.id.textViewPlaceTitle);
        this.textViewPlaceDescription = itemView.findViewById(R.id.textViewPlaceDescription);
        this.imageView = itemView.findViewById(R.id.imageView);
    }

    public ImageView getImgView() {
        return imageView;
    }

    public TextView textViewPlaceTitle() {
        return textViewPlaceTitle;
    }

    public TextView textViewPlaceDescription() {
        return textViewPlaceDescription;
    }
}
