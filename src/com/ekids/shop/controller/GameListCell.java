package com.ekids.shop.controller;

import com.ekids.shop.dto.GameDto;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class GameListCell implements Callback<ListView<GameDto>, ListCell<GameDto>> {

    @Override public ListCell<GameDto> call(ListView<GameDto> listView) {
        return new ListCell<>() {
            @Override
            public void updateItem(GameDto item, boolean empty) {
                super.updateItem(item, empty);
                setText(null);
                if (!empty && item != null && item.getName() != null && item.getPrice() != null) {
                    setText(String.format("%s - %s$", item.getName(), item.getPrice()));
                }
            }
        };
    }
}
