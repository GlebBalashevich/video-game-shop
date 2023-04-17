package com.ekids.shop.validator;

import com.ekids.shop.dto.GameDto;
import com.ekids.shop.exception.GameException;
import com.ekids.shop.util.Message;

public class GameValidator {

    private static final String NUMBER_REGEX = "\\-?\\d+";

    public void validateSearchParams(String minPriceValue, String maxPriceValue) throws GameException {
        Integer minPrice = validatePrice(minPriceValue);
        Integer maxPrice = validatePrice(maxPriceValue);
        if (minPrice != null && maxPrice != null) {
            if (minPrice > maxPrice) {
                throw new GameException(Message.VALIDATION_MAX_PRICE_LESS_THAN_MIN);
            }
        }
    }

    public void validateGame(GameDto gameDto) throws GameException {
        if (gameDto.getName() == null || gameDto.getName().isBlank()) {
            throw new GameException(Message.VALIDATION_EMPTY_NAME);
        }
        if (gameDto.getGenre() == null) {
            throw new GameException(Message.VALIDATION_EMPTY_GENRE);
        }
        if (gameDto.getPrice() == null || gameDto.getPrice().isBlank()) {
            throw new GameException(Message.VALIDATION_EMPTY_PRICE);
        }
        validatePrice(gameDto.getPrice());
    }

    private Integer validatePrice(String priceValue) throws GameException {
        Integer price = null;
        if (priceValue != null && !priceValue.isBlank()) {
            if (!priceValue.matches(NUMBER_REGEX)) {
                throw new GameException(Message.VALIDATION_PRICE_NOT_NUMBER);
            }
            price = Integer.parseInt(priceValue);
            if (price < 0) {
                throw new GameException(Message.VALIDATION_PRICE_LESS_THAN_ZERO);
            }
        }
        return price;
    }

}
