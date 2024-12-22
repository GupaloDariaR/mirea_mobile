package ru.mirea.gupalodr.data.retrofitApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.mirea.gupalodr.domain.models.Place;

public interface ApiService {
    @GET("places")
    Call<List<Place>> getPlaces();
}
