package ru.mirea.gupalodr.moscowplacesapp.domain.usecases.place;

import ru.mirea.gupalodr.moscowplacesapp.domain.models.Place;
import ru.mirea.gupalodr.moscowplacesapp.domain.repository.PlaceRepository;

public class GetPlaceByIdUseCase {
    private PlaceRepository placeRepository;

    public GetPlaceByIdUseCase(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Place execute(int id) {
        return placeRepository.getPlaceById(id);
    }
}
