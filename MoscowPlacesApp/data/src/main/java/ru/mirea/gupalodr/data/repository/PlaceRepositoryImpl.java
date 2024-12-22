package ru.mirea.gupalodr.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.gupalodr.data.storage.PlaceStorage;
import ru.mirea.gupalodr.data.storage.models.Review;
import ru.mirea.gupalodr.data.storage.models.User;
import ru.mirea.gupalodr.data.storage.models.Place;
import ru.mirea.gupalodr.domain.repository.PlaceRepository;

public class PlaceRepositoryImpl implements PlaceRepository {
    PlaceStorage sharedPrefPlaceStorage;

    public PlaceRepositoryImpl(PlaceStorage sharedPrefPlaceStorage){
        this.sharedPrefPlaceStorage = sharedPrefPlaceStorage;
    }

    @Override
    public List<ru.mirea.gupalodr.domain.models.Place> getAllPlaces() {
        List<Place> places = sharedPrefPlaceStorage.getAllPlaces();
        List<ru.mirea.gupalodr.domain.models.Place> allPlaces = new ArrayList<>();
        for (Place place : places) {
            allPlaces.add(mapToDomainPlace(place));
        }
        return allPlaces;
    }

    @Override
    public ru.mirea.gupalodr.domain.models.Place getPlaceById(int id) {
        return mapToDomainPlace(sharedPrefPlaceStorage.getPlaceById(id));
    }

    @Override
    public boolean addReviewForPlace(int id, String text) {
        return sharedPrefPlaceStorage.addReviewForPlace(id, text);
    }

    private ru.mirea.gupalodr.data.storage.models.Place mapToStoragePlace(ru.mirea.gupalodr.domain.models.Place place){
        int id = place.getId();
        String title = place.getTitle();
        String description = place.getDescription();
        String img = place.getImg();
        return new ru.mirea.gupalodr.data.storage.models.Place(id, title, description, img);
    }

    private ru.mirea.gupalodr.domain.models.Place mapToDomainPlace(ru.mirea.gupalodr.data.storage.models.Place place){
        int id = place.getId();
        String title = place.getTitle();
        String description = place.getDescription();
        String img = place.getImg();
        return new ru.mirea.gupalodr.domain.models.Place(id, title, description, img);
    }


}
