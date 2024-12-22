package ru.mirea.gupalodr.domain.repository;

import java.util.List;

import ru.mirea.gupalodr.domain.models.Place;

public interface PlaceRepository {
    public List<Place> getAllPlaces();

    public Place getPlaceById(int id);
    public boolean addReviewForPlace(int id, String text);
}
