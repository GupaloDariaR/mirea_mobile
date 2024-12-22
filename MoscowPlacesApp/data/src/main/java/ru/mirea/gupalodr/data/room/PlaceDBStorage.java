package ru.mirea.gupalodr.data.room;

import java.util.List;

import ru.mirea.gupalodr.data.room.models.PlaceDB;

public interface PlaceDBStorage {
    void addPlace(PlaceDB place);
    void deletePlace(int id);
    void getPlaceById(int id, DBCallback<PlaceDB> callback);
    void getAllPlaces(DBCallback<List<PlaceDB>> callback);
}
