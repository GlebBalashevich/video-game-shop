package com.ekids.shop.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.ekids.shop.controller.impl.GameAddController;
import com.ekids.shop.controller.impl.GameEditController;
import com.ekids.shop.controller.impl.GameShowController;
import com.ekids.shop.controller.impl.PopupMessageController;
import com.ekids.shop.controller.impl.ShopController;
import com.ekids.shop.dto.GameDto;
import com.ekids.shop.exception.GameException;
import com.ekids.shop.model.Genre;
import com.ekids.shop.service.GameService;
import com.ekids.shop.util.Message;
import com.ekids.shop.util.ComponentPath;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameControllerMediator {

    private final GameAddController gameAddController;

    private final GameEditController gameEditController;

    private final GameShowController gameShowController;

    private final ShopController shopController;

    private final GameService gameService;

    private final PopupMessageController popupMessageController;

    private final Image icon;

    public GameControllerMediator(GameService gameService) {
        this.gameService = gameService;
        this.gameAddController = new GameAddController(this);
        this.gameEditController = new GameEditController(this);
        this.gameShowController = new GameShowController(this);
        this.shopController = new ShopController(this);
        this.popupMessageController = new PopupMessageController(this);
        this.icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ComponentPath.ICON_PATH)));
    }

    public void loadMainScene(Stage primaryStage) {
        Scene scene = loadScene(ComponentPath.SHOP_TAB_PATH, shopController);
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showGames(String gameName, Genre genre, String minPrice, String maxPrice) {
        try {
            List<GameDto> games = gameService.findGames(gameName, genre, minPrice, maxPrice);
            shopController.showGames(games);
        } catch (GameException e) {
            publishMessage(e.getMessage());
        }
    }

    public void initAddGame() {
        Scene scene = loadScene(ComponentPath.SAVE_GAME_TAB_PATH, gameAddController);
        showScene(scene);
    }

    public void addGame(GameDto gameDto) {
        try {
            if (gameService.saveGame(gameDto)) {
                gameAddController.closeWindow();
                publishMessage(Message.GAME_ADDED);
            }
        } catch (GameException e) {
            publishMessage(e.getMessage());
        }
    }

    public void initEditGame(GameDto gameDto) {
        Scene scene = loadScene(ComponentPath.SAVE_GAME_TAB_PATH, gameEditController);
        if (gameDto != null) {
            gameEditController.initEditData(gameDto);
            showScene(scene);
        } else {
            publishMessage(Message.SELECT_GAME);
        }
    }

    public void editGame(GameDto gameDto) {
        try {
            if (gameService.updateGame(gameDto)) {
                gameEditController.closeWindow();
                shopController.updateGames(gameDto);
                publishMessage(Message.GAME_EDITED);
            }
        } catch (GameException e) {
            publishMessage(e.getMessage());
        }
    }

    public void deleteGame(GameDto gameDto) {
        if (gameDto != null) {
            gameService.deleteGame(gameDto);
            shopController.removeGame(gameDto);
            publishMessage(Message.GAME_DELETED);
        } else {
            publishMessage(Message.SELECT_GAME);
        }
    }

    public void showGameDetails(GameDto gameDto) {
        Scene scene = loadScene(ComponentPath.SHOW_GAME_TAB_PATH, gameShowController);
        gameShowController.initShowData(gameDto);
        showScene(scene);
    }

    public void okAcknowledge() {
        popupMessageController.closeWindow();
    }

    private void publishMessage(String message) {
        Scene scene = loadScene(ComponentPath.POPUP_MESSAGE_TAB_PATH, popupMessageController);
        popupMessageController.initMessageData(message);
        showScene(scene);
    }

    private Scene loadScene(String path, Controller controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            loader.setController(controller);
            return new Scene(loader.load());
        } catch (IOException e) {
            System.out.println("error occurred during controller class loading");
            throw new RuntimeException("Controller couldn't be loaded");
        }
    }

    private void showScene(Scene scene) {
        Stage stage = new Stage();
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

}
