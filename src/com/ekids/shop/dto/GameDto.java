package com.ekids.shop.dto;

import com.ekids.shop.model.Genre;

public class GameDto {

    private final String id;

    private final String name;

    private final String description;

    private final Genre genre;

    private final String price;

    public GameDto(String id, String name, String description, Genre genre, String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.price = price;
    }

    public GameDto(String name, String description, Genre genre, String price) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getPrice() {
        return price;
    }

}
