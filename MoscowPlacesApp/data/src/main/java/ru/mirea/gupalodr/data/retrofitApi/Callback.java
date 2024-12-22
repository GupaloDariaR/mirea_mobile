package ru.mirea.gupalodr.data.retrofitApi;

public interface Callback<T> {
    public void onSuccess(T result);
    public void onFailure(Exception e);
}
