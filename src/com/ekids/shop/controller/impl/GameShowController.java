package com.ekids.shop.controller.impl;

import com.ekids.shop.controller.Controller;
import com.ekids.shop.controller.GameControllerMediator;
import com.ekids.shop.dto.GameDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameShowController implements Controller {

    private final GameControllerMediator gameControllerMediator;

    @FXML
    private Label gameNameLbl;

    @FXML
    private Label gameDescriptionLbl;

    @FXML
    private Label gamePriceLbl;

    @FXML
    private Label gameGenreLbl;

    public GameShowController(GameControllerMediator gameControllerMediator) {
        this.gameControllerMediator = gameControllerMediator;
    }

    public void initShowData(GameDto gameDto) {
        gameNameLbl.setText(gameDto.getName());
        gameDescriptionLbl.setText(gameDto.getDescription());
        gamePriceLbl.setText(String.valueOf(gameDto.getPrice()));
        gameGenreLbl.setText(gameDto.getGenre().name());
    }
}
