package com.ekids.shop.controller.impl;

import com.ekids.shop.controller.Controller;
import com.ekids.shop.controller.GameControllerMediator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopupMessageController implements Controller {

    private final GameControllerMediator gameControllerMediator;

    @FXML
    private Label msgLbl;

    public PopupMessageController(GameControllerMediator gameControllerMediator){
        this.gameControllerMediator = gameControllerMediator;
    }

    public void closeWindow() {
        Stage stage = (Stage) msgLbl.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void okAct(ActionEvent actionEvent) {
        gameControllerMediator.okAcknowledge();
    }

    public void initMessageData(String message) {
        msgLbl.setText(message);
    }

}
