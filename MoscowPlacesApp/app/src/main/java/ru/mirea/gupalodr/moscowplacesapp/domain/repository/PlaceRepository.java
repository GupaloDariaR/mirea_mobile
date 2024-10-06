package ru.mirea.gupalodr.moscowplacesapp.domain.repository;

import java.util.List;

import ru.mirea.gupalodr.moscowplacesapp.domain.models.Place;
import ru.mirea.gupalodr.moscowplacesapp.domain.models.Review;

public interface PlaceRepository {
    public List<Place> getAllPlaces();

    public Place getPlaceById(int id);
    public boolean addReviewForPlace(int id, String text);
}
