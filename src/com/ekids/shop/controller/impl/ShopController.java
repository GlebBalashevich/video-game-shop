package com.ekids.shop.controller.impl;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.ekids.shop.controller.Controller;
import com.ekids.shop.controller.GameControllerMediator;
import com.ekids.shop.controller.GameListCell;
import com.ekids.shop.dto.GameDto;
import com.ekids.shop.model.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ShopController implements Initializable, Controller {

    private final GameControllerMediator gameControllerMediator;

    @FXML
    private TextField gameNameTxtF;

    @FXML
    private ChoiceBox<Genre> genresDropDwn;

    @FXML
    private TextField minPriceTxtF;

    @FXML
    private TextField maxPriceTxtF;

    @FXML
    private ListView<GameDto> gamesListView;

    public ShopController(GameControllerMediator gameControllerMediator) {
        this.gameControllerMediator = gameControllerMediator;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Genre> dropDownItems = FXCollections.observableArrayList();
        dropDownItems.add(null);
        dropDownItems.addAll(Genre.values());
        genresDropDwn.setItems(dropDownItems);
        gamesListView.setCellFactory(new GameListCell());
    }

    public void showGames(List<GameDto> games) {
        ObservableList<GameDto> gamesList = FXCollections.observableList(games);
        gamesListView.setItems(gamesList);
        gamesListView.refresh();
    }

    public void updateGames(GameDto gameDto) {
        removeItemById(gameDto.getId());
        gamesListView.getItems().add(gameDto);
    }

    public void removeGame(GameDto gameDto) {
        gamesListView.getItems().remove(gameDto);
    }

    @FXML
    private void showGamesAct(ActionEvent actionEvent) {
        String gameName = gameNameTxtF.getText();
        Genre genre = genresDropDwn.getValue();
        String minPrice = minPriceTxtF.getText();
        String maxPrice = maxPriceTxtF.getText();
        gameControllerMediator.showGames(gameName, genre, minPrice, maxPrice);
    }

    @FXML
    private void addGameAct(ActionEvent actionEvent) {
        gameControllerMediator.initAddGame();
    }

    @FXML
    private void editGameAct(ActionEvent actionEvent) {
        GameDto gameDto = gamesListView.getSelectionModel().getSelectedItem();
        gameControllerMediator.initEditGame(gameDto);
    }

    @FXML
    private void deleteGameAct(ActionEvent actionEvent) {
        GameDto gameDto = gamesListView.getSelectionModel().getSelectedItem();
        gameControllerMediator.deleteGame(gameDto);
    }

    @FXML
    private void listItemClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            GameDto gameDto = gamesListView.getSelectionModel().getSelectedItem();
            gameControllerMediator.showGameDetails(gameDto);
        }
    }

    private void removeItemById(String id) {
        GameDto deletingGame = null;
        for (GameDto game : gamesListView.getItems()) {
            if (game.getId().equals(id)) {
                deletingGame = game;
                break;
            }
        }
        gamesListView.getItems().remove(deletingGame);
    }

}
