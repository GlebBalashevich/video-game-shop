package com.ekids.shop.processor;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.ekids.shop.model.Game;

public class GameFileProcessor {

    private static final String GAMES_PATH = "src/resources/games";

    public List<Game> readGames() {
        List<Game> games = new ArrayList<>();
        File file = new File(GAMES_PATH);
        try {
            file.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(file);
            games = readObjectsFromFile(fileInputStream);
            return games;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return games;
    }

    public void saveGames(List<Game> games) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(GAMES_PATH);
            writeObjectsToFile(fileOutputStream, games);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Game> readObjectsFromFile(FileInputStream fileInputStream) throws IOException, ClassNotFoundException {
        List<Game> games = new ArrayList<>();
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        while (true) {
            try {
                Object object = objectInputStream.readObject();
                if (object instanceof Game) {
                    games.add((Game) object);
                }
            } catch (EOFException e) {
                break;
            }
        }
        objectInputStream.close();
        return games;
    }

    private void writeObjectsToFile(FileOutputStream fileOutputStream, List<Game> games) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (Game game : games) {
            objectOutputStream.writeObject(game);
        }
        objectOutputStream.close();
    }

}
