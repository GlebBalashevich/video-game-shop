package com.ekids.shop.model;

import java.io.Serializable;
import java.util.Objects;

public class Game implements Serializable {

    private String id;

    private String name;

    private String description;

    private Genre genre;

    private int price;

    public Game(String id, String name, String description, Genre genre, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Game game = (Game) o;
        return price == game.price && Objects.equals(id, game.id) && Objects.equals(name,
                game.name) && Objects.equals(description, game.description) && genre == game.genre;
    }

    @Override public int hashCode() {
        return Objects.hash(id, name, description, genre, price);
    }

    @Override public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                ", price=" + price +
                '}';
    }
}
