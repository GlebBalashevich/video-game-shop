package com.ekids.shop;

import com.ekids.shop.controller.GameControllerMediator;
import com.ekids.shop.mapper.GameMapper;
import com.ekids.shop.repository.GameRepository;
import com.ekids.shop.service.GameService;
import com.ekids.shop.validator.GameValidator;
import javafx.application.Application;
import javafx.stage.Stage;

public class ShopApplication extends Application {

    private GameRepository gameRepository;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        gameRepository = new GameRepository();
        GameMapper gameMapper = new GameMapper();
        GameValidator gameValidator = new GameValidator();
        GameService gameService = new GameService(gameRepository, gameMapper, gameValidator);
        GameControllerMediator gameControllerMediator = new GameControllerMediator(gameService);
        gameControllerMediator.loadMainScene(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        gameRepository.storeData();
        super.stop();
    }
}
