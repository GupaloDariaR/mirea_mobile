package ru.mirea.gupalodr.domain.usecases.place;

import ru.mirea.gupalodr.domain.models.Place;
import ru.mirea.gupalodr.domain.repository.PlaceRepository;

public class GetPlaceByIdUseCase {
    private PlaceRepository placeRepository;

    public GetPlaceByIdUseCase(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Place execute(int id) {
        return placeRepository.getPlaceById(id);
    }
}
