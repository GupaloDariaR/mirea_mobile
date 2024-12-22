package ru.mirea.gupalodr.domain.usecases.place;

import ru.mirea.gupalodr.domain.repository.PlaceRepository;

public class AddReviewForPlaceUseCase {
    private PlaceRepository placeRepository;

    public AddReviewForPlaceUseCase(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public boolean execute(int id, String text) {
        return placeRepository.addReviewForPlace(id, text);
    }
}
