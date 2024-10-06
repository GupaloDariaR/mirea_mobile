package ru.mirea.gupalodr.moscowplacesapp.domain.usecases.place;

import java.util.List;

import ru.mirea.gupalodr.moscowplacesapp.domain.models.Place;
import ru.mirea.gupalodr.moscowplacesapp.domain.repository.PlaceRepository;

public class GetAllPlacesUseCase {
    private PlaceRepository placeRepository;

    public GetAllPlacesUseCase(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> execute() {
        return placeRepository.getAllPlaces();
    }
}
