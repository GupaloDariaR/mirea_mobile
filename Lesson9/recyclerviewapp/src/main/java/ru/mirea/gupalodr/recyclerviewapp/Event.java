package ru.mirea.gupalodr.recyclerviewapp;

public class Event {
    // Модель исторического события с кратким описанием и изображением.
    private String title;
    private String description;
    private String img;

    public Event(String title, String description, String img) {
        this.title = title;
        this.description = description;
        this.img= img;
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
    @Override
    public String toString() {
        return this.title+" ("+ this.description+")";
    }
}
