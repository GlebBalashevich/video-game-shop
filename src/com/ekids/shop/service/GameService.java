package com.ekids.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ekids.shop.dto.GameDto;
import com.ekids.shop.exception.GameException;
import com.ekids.shop.mapper.GameMapper;
import com.ekids.shop.model.Game;
import com.ekids.shop.model.Genre;
import com.ekids.shop.repository.GameRepository;
import com.ekids.shop.validator.GameValidator;

public class GameService {

    private final GameRepository gameRepository;

    private final GameMapper gameMapper;

    private final GameValidator gameValidator;

    public GameService(GameRepository gameRepository, GameMapper gameMapper, GameValidator gameValidator) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.gameValidator = gameValidator;
    }

    public List<GameDto> findGames(String gameName, Genre genre, String minPriceValue, String maxPriceValue)
            throws GameException {
        gameValidator.validateSearchParams(minPriceValue, maxPriceValue);
        List<Game> games = gameRepository.findGames();
        int minPrice = minPriceValue == null || minPriceValue.isBlank() ? 0 : Integer.parseInt(minPriceValue);
        int maxPrice =
                maxPriceValue == null || maxPriceValue.isBlank() ? Integer.MAX_VALUE : Integer.parseInt(maxPriceValue);
        List<Game> filteredGames = filterGames(games, gameName, genre, minPrice, maxPrice);
        return gameMapper.toGamesDtos(filteredGames);
    }

    public boolean saveGame(GameDto gameDto) throws GameException {
        gameValidator.validateGame(gameDto);
        Game game = gameMapper.toGame(gameDto, UUID.randomUUID().toString());
        return gameRepository.addGame(game);
    }

    public boolean updateGame(GameDto gameDto) throws GameException {
        gameValidator.validateGame(gameDto);
        Game updatingGame = gameMapper.toGame(gameDto);
        gameRepository.deleteById(gameDto.getId());
        return gameRepository.addGame(updatingGame);
    }

    public void deleteGame(GameDto gameDto) {
        gameRepository.deleteGame(gameMapper.toGame(gameDto));
    }

    private List<Game> filterGames(List<Game> games, String gameName, Genre genre, int minPrice, int maxPrice) {
        List<Game> filteredGames = new ArrayList<>();
        for (Game game : games) {
            boolean shouldAdd = true;
            if (game.getName() != null && !game.getName().isBlank()) {
                shouldAdd = game.getName().toLowerCase().contains(gameName.toLowerCase());
            }
            if (genre != null) {
                shouldAdd = game.getGenre() == genre;
            }
            if (minPrice > 0 && maxPrice > 0) {
                shouldAdd = game.getPrice() >= minPrice && game.getPrice() <= maxPrice;
            }
            if (shouldAdd) {
                filteredGames.add(game);
            }
        }
        return filteredGames;
    }

}
