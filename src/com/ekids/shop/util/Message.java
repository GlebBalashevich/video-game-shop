package com.ekids.shop.util;

public class Message {

    private Message() {
    }

    //Validation messages
    public static final String VALIDATION_MAX_PRICE_LESS_THAN_MIN = "Max price couldn't be less than min price";

    public static final String VALIDATION_EMPTY_NAME = "Name field cannot be empty";

    public static final String VALIDATION_EMPTY_GENRE = "Genre field cannot be empty";

    public static final String VALIDATION_EMPTY_PRICE = "Price field cannot be empty";

    public static final String VALIDATION_PRICE_NOT_NUMBER = "Price value is not a number";

    public static final String VALIDATION_PRICE_LESS_THAN_ZERO = "Price value is less than zero";

    //Info messages
    public static final String GAME_ADDED = "New Game was successfully added";

    public static final String GAME_EDITED = "Game was successfully edited";

    public static final String GAME_DELETED = "Game was successfully deleted";

    //Warn messages
    public static final String SELECT_GAME = "Select game from list";

}
