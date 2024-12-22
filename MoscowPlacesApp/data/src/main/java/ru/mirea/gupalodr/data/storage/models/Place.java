package ru.mirea.gupalodr.data.storage.models;

import java.util.List;

public class Place {
    private int id;
    private String title;
    private String description;

    private String img;
    private List<Review> reviews;

    public Place (int id, String title, String description, String img) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.img = img;
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

    public String getImg() {
        return img;
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
