package ru.mirea.gupalodr.data.storage.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.gupalodr.data.storage.PlaceStorage;
import ru.mirea.gupalodr.data.storage.models.Place;
import ru.mirea.gupalodr.data.storage.models.Review;
import ru.mirea.gupalodr.data.storage.models.User;

public class SharedPrefPlaceStorage implements PlaceStorage {
    private final List<Place> PLACES = new ArrayList<>();
    Context context;

    public SharedPrefPlaceStorage(Context context){
        this.context = context;
        PLACES.add(new Place(1, "Парк Горького", "Парк Горького имеет огромную площадь. Здесь много зелени, ухоженных аллей и живописных уголков. В парке есть несколько прудов с утками и лебедями, а также пруд, где можно покататься на водных велосипедах и катамаранах.", ""));
        PLACES.add(new Place(2, "Парк Зарядье", "В парке представлена необычная архитектура, высокотехнологичные аттракционы, современный научно-просветительский центр для детей и музейно-выставочное пространство.",""));
        PLACES.add(new Place(3, "Третьяковская галлерея", "ретьяковская галерея — это один из крупнейших художественных музеев мира, сокровищница русского искусства. Здесь собрана уникальная коллекция произведений русских художников, начиная с XI века и до наших дней.",""));

    }

    @Override
    public List<Place> getAllPlaces() {
        return PLACES;
    }

    @Override
    public Place getPlaceById(int id) {
        for (Place place : PLACES) {
            if (place.getId() == id) {
                return place;
            }
        };
        return null;
    }

    @Override
    public boolean addReviewForPlace(int id, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        int userid = sharedPreferences.getInt("user", 1);

        for (User user : SharedPrefUserStorage.getAllUsers()) {
            if (user.getId() == userid) {
                getPlaceById(id).addReview(new Review(text, user));
                return true;
            }
        }
        return false;
    }
}
