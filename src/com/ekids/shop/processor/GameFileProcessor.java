package com.ekids.shop.processor;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.ekids.shop.model.Game;
import com.ekids.shop.util.ComponentPath;

public class GameFileProcessor {

    private static final String USER_HOME = "user.home";

    public List<Game> readGames() {
        List<Game> games = new ArrayList<>();
        try {
            Path filePath = buildPath();
            createFileIfNotExists(filePath);
            FileInputStream fileInputStream = new FileInputStream(filePath.toFile());
            games = readObjectsFromFile(fileInputStream);
        } catch (IOException | ClassNotFoundException | URISyntaxException e) {
            //stub
        }
        return games;
    }

    public void saveGames(List<Game> games) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(buildPath().toFile());
            writeObjectsToFile(fileOutputStream, games);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private List<Game> readObjectsFromFile(FileInputStream fileInputStream) throws IOException, ClassNotFoundException {
        List<Game> games = new ArrayList<>();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (true) {
                Object object = objectInputStream.readObject();
                if (object instanceof Game) {
                    games.add((Game) object);
                } else {
                    break;
                }
            }
        } catch (EOFException e) {
            //stub
        }
        return games;
    }

    private void writeObjectsToFile(FileOutputStream fileOutputStream, List<Game> games) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (Game game : games) {
            objectOutputStream.writeObject(game);
        }
        objectOutputStream.close();
    }

    private void createFileIfNotExists(Path filePath) throws IOException {
        if (!filePath.toFile().exists()) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        }
    }

    private Path buildPath() throws URISyntaxException {
        return Paths.get(System.getProperty(USER_HOME), ComponentPath.GAMES_PATH);
    }

}
