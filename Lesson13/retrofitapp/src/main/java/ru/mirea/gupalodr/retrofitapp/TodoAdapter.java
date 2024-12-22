package ru.mirea.gupalodr.retrofitapp;

import static java.lang.System.load;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Todo> todos;

    public TodoAdapter(Context context, List<Todo>	todoList){
        this.layoutInflater	= LayoutInflater.from(context);
        this.todos = todoList;
    }

    @NonNull
    @Override
    public	TodoViewHolder	onCreateViewHolder(@NonNull ViewGroup parent, int viewType)	{
        View view =	layoutInflater.inflate(R.layout.item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position)	{
        Todo todo =	todos.get(position);
        holder.textViewTitle.setText(todo.getTitle());
        holder.checkBoxCompleted.setChecked(todo.getCompleted());
        Picasso.get()
                .load("https://goodhands.vet/upload/resize_cache/ram.watermark/d4c/2e4/a32/728/8f2072e27c2d6eae2d7f85142d303b9c.webp")
                .resize(100,100)
                .centerCrop()
                .error(R.drawable.error_image)
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);
    }
    @Override
    public int getItemCount()	{
        return todos.size();
    }
}
