package ru.mirea.gupalodr.moscowplacesapp.domain.usecases.place;

import ru.mirea.gupalodr.moscowplacesapp.domain.models.Place;
import ru.mirea.gupalodr.moscowplacesapp.domain.models.Review;
import ru.mirea.gupalodr.moscowplacesapp.domain.repository.PlaceRepository;

public class AddReviewForPlaceUseCase {
    private PlaceRepository placeRepository;

    public AddReviewForPlaceUseCase(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public boolean execute(int id, String text) {
        return placeRepository.addReviewForPlace(id, text);
    }
}
