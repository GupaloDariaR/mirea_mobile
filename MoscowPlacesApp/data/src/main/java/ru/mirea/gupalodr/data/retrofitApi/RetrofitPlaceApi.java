package ru.mirea.gupalodr.data.retrofitApi;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mirea.gupalodr.domain.models.Place;

public class RetrofitPlaceApi {
    public static final String BASE_URL = "https://lv01j.wiremockapi.cloud/";
    private static Retrofit retrofit;
    private static ApiService apiService;

    public RetrofitPlaceApi() {
        Log.d("KKK","RetrofitPlaceApi -> create retrofit and apiService");
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void getPlaces(ru.mirea.gupalodr.data.retrofitApi.Callback<List<Place>> callback) {
        Call<List<Place>> call = apiService.getPlaces();
        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("KKK","RetrofitPlaceApi -> get places from api success");
                    List<Place> places = response.body();
                    callback.onSuccess(places);
                } else {
                    callback.onFailure(new Exception("Ошибка при получении данных из api"));
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable throwable) {
                callback.onFailure((new Exception(throwable.getMessage())));
                Log.d("KKK",throwable.getMessage());
            }
        });
    }
}
