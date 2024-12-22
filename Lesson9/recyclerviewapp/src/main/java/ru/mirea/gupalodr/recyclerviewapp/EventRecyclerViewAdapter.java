package ru.mirea.gupalodr.recyclerviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private List<Event> events;
    private Context context;
    public EventRecyclerViewAdapter(List<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate view from recyclerview_item_layout.xml
        context = parent.getContext();
        View recyclerViewItem = LayoutInflater.from(context).inflate(R.layout.event_item_view, parent, false);
        return new EventViewHolder(recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        // Set country in countries via position
        Event event = this.events.get(position);
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(event.getImg(),"drawable", pkgName);
        // Bind data to viewholder
        holder.getimageView().setImageResource(resID);
        holder.getTextViewEventTitle().setText(event.getTitle());
        holder.getTextViewEventDescription().setText(event.getDescription());
    }

    @Override
    public int getItemCount() {
        return this.events.size();
    }
}
