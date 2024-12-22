package ru.mirea.gupalodr.recyclerviewapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView textViewEventTitle;
    private TextView textViewEventDescription;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.imageView);
        this.textViewEventTitle =
                itemView.findViewById(R.id.textViewEventTitle);
        this.textViewEventDescription = itemView.findViewById(R.id.textViewEventDescription);
    }
    public ImageView getimageView() {
        return imageView;
    }
    public TextView getTextViewEventTitle() {
        return textViewEventTitle;
    }
    public TextView getTextViewEventDescription() {
        return textViewEventDescription;
    }

}
