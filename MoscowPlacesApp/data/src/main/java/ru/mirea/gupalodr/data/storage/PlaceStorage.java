package ru.mirea.gupalodr.data.storage;

import java.util.List;

import ru.mirea.gupalodr.data.storage.models.Place;

public interface PlaceStorage {
    public List<Place> getAllPlaces();
    public Place getPlaceById(int id);
    public boolean addReviewForPlace(int id, String text);
}
