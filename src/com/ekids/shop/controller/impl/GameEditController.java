package com.ekids.shop.controller.impl;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.ekids.shop.controller.Controller;
import com.ekids.shop.controller.GameControllerMediator;
import com.ekids.shop.dto.GameDto;
import com.ekids.shop.model.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GameEditController implements Initializable, Controller {

    private final GameControllerMediator gameControllerMediator;

    @FXML
    private TextField nameTxtF;

    @FXML
    private TextArea descriptionTxtA;

    @FXML
    private TextField priceTxtF;

    @FXML
    private ChoiceBox<Genre> genresDropDwn;

    @FXML
    private Button saveGameBtn;

    private GameDto gameDto;

    public GameEditController(GameControllerMediator gameControllerMediator) {
        this.gameControllerMediator = gameControllerMediator;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Genre> dropDownItems = FXCollections.observableList(Arrays.asList(Genre.values()));
        genresDropDwn.setItems(dropDownItems);

    }

    @FXML
    private void saveGameAct(ActionEvent actionEvent) {
        String name = nameTxtF.getText();
        String description = descriptionTxtA.getText();
        String price = priceTxtF.getText();
        Genre genre = genresDropDwn.getValue();
        GameDto editingGameDto = new GameDto(gameDto.getId(), name, description, genre, price);
        gameControllerMediator.editGame(editingGameDto);
    }

    public void closeWindow(){
        Stage stage = (Stage) saveGameBtn.getScene().getWindow();
        stage.close();
    }

    public void initEditData(GameDto editingGameDto) {
        this.gameDto = editingGameDto;
        nameTxtF.setText(editingGameDto.getName());
        descriptionTxtA.setText(editingGameDto.getDescription());
        priceTxtF.setText(String.valueOf(editingGameDto.getPrice()));
        genresDropDwn.setValue(editingGameDto.getGenre());
    }

}
