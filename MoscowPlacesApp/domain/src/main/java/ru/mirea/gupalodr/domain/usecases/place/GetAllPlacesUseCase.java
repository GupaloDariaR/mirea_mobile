package ru.mirea.gupalodr.domain.usecases.place;

import java.util.List;

import ru.mirea.gupalodr.domain.repository.PlaceRepository;
import ru.mirea.gupalodr.domain.models.Place;

public class GetAllPlacesUseCase {
    private PlaceRepository placeRepository;

    public GetAllPlacesUseCase(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> execute() {
        return placeRepository.getAllPlaces();
    }
}
