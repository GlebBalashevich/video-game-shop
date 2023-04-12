package com.ekids.shop.repository;

import java.util.List;

import com.ekids.shop.model.Game;
import com.ekids.shop.processor.GameFileProcessor;

public class GameRepository {

    private final GameFileProcessor gameFileProcessor;

    private List<Game> games;

    public GameRepository() {
        this.gameFileProcessor = new GameFileProcessor();
        loadData();
    }

    public List<Game> findGames() {
        return games;
    }

    public boolean addGame(Game game) {
        return games.add(game);
    }

    public void deleteGame(Game game) {
        games.remove(game);
    }

    public void deleteById(String id){
        for(Game game : games){
            if (game.getId().equals(id)){
                games.remove(game);
                break;
            }
        }
    }

    private void loadData() {
        if (games == null) {
            games = gameFileProcessor.readGames();
        }
    }

    public void storeData() {
        gameFileProcessor.saveGames(games);
    }

}
