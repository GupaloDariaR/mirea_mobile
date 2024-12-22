package ru.mirea.gupalodr.domain.models;

import java.util.List;

public class Place {
    private int id;
    private String title;
    private String description;
    private List<Review> reviews;

    public Place (int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    @Override
    public String toString(){
        return this.getTitle();
    }
}
