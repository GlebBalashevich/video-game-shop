package com.ekids.shop.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ekids.shop.dto.GameDto;
import com.ekids.shop.model.Game;

public class GameMapper {

    public List<GameDto> toGamesDtos(List<Game> games) {
        List<GameDto> gameDtos = new ArrayList<>();
        for (Game game : games) {
            GameDto gameDto = new GameDto(game.getId(), game.getName(), game.getDescription(), game.getGenre(),
                    String.valueOf(game.getPrice()));
            gameDtos.add(gameDto);
        }
        return gameDtos;
    }

    public Game toGame(GameDto gameDto, String id) {
        return new Game(id, gameDto.getName(), gameDto.getDescription(), gameDto.getGenre(),
                Integer.parseInt(gameDto.getPrice()));
    }

    public Game toGame(GameDto gameDto) {
        return new Game(gameDto.getId(), gameDto.getName(), gameDto.getDescription(), gameDto.getGenre(),
                Integer.parseInt(gameDto.getPrice()));
    }

}
