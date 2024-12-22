package ru.mirea.gupalodr.data.room.storage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.mirea.gupalodr.data.room.DBCallback;
import ru.mirea.gupalodr.data.room.PlaceDB;
import ru.mirea.gupalodr.data.room.PlaceDBStorage;
import ru.mirea.gupalodr.domain.models.Place;


public class PlaceDBStorageImpl implements PlaceDBStorage {
    private PlaceDB placeDB;
    private Context context;
    private final ExecutorService databaseExecutor = Executors.newSingleThreadExecutor();
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    public void PlaceDBStorageImpl(Context context) {
        this.context = context;
        createDB();
    }

    private void createDB() {
        RoomDatabase.Callback callback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        placeDB = Room.databaseBuilder(context, PlaceDB.class, "places")
                .addCallback(callback)
                .build();
    }


    @Override
    public void addPlace(ru.mirea.gupalodr.data.room.models.PlaceDB place) {
        databaseExecutor.execute(() -> placeDB.getPlaceDao().addPlace(place));
    }

    @Override
    public void deletePlace(int id) {
        databaseExecutor.execute(() -> placeDB.getPlaceDao().deletePlace(id));
    }

    @Override
    public void getPlaceById(int id, DBCallback<ru.mirea.gupalodr.data.room.models.PlaceDB> callback) {
        databaseExecutor.execute(() -> {
            ru.mirea.gupalodr.data.room.models.PlaceDB place = placeDB.getPlaceDao().getPlaceById(id);
            if (place != null) {
                mainThreadHandler.post(() -> callback.onSuccess(place));
            } else {
                mainThreadHandler.post(() -> callback.onError(new Exception("Место не найдено")));
            }
        });
    }

    @Override
    public void getAllPlaces(DBCallback<List<ru.mirea.gupalodr.data.room.models.PlaceDB>> callback) {
        List<ru.mirea.gupalodr.data.room.models.PlaceDB> places = placeDB.getPlaceDao().getAllPlaces();
        mainThreadHandler.post(() -> callback.onSuccess(places));
    }
}
