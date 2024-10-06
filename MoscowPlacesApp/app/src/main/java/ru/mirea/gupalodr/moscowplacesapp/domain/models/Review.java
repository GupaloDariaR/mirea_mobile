package ru.mirea.gupalodr.moscowplacesapp.domain.models;

public class Review {
    private int id;
    private String text;
    private User user;

    public Review(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return this.getText();
    }
}
