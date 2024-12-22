package ru.mirea.gupalodr.data.room;

public interface DBCallback<T> {
    void onSuccess(T result);
    void onError(Exception e);
}
