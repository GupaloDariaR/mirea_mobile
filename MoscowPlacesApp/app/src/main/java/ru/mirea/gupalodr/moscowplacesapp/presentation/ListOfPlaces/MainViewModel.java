package ru.mirea.gupalodr.moscowplacesapp.presentation.ListOfPlaces;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.gupalodr.data.retrofitApi.Callback;
import ru.mirea.gupalodr.data.retrofitApi.RetrofitPlaceApi;
import ru.mirea.gupalodr.domain.models.Place;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Place>> items = new MutableLiveData<>();

    public MainViewModel (Context context) {
        RetrofitPlaceApi api = new RetrofitPlaceApi();
        api.getPlaces(new Callback<List<Place>>() {
            @Override
            public void onSuccess(List<Place> result) {
                items.setValue(result);
                Log.d("KKK","MainViewModel -> api.getPlaces success");
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("KKK", e.getMessage());
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<Place>> getItems() {
        return items;
    }

    // Метод для добавления нового элемента в список
    public void addItem(Place item) {
        List<Place> currentList = items.getValue();
        if (currentList != null) {
            currentList.add(item);
            items.setValue(currentList); // Обновляем LiveData
        }
    }
}
