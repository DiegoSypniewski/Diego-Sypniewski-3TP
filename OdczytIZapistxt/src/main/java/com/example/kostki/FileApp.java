package com.example.kostki;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileApp extends Application {

    public static List<String> readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllLines(path);
    }

    public static void writeFile(String filePath, List<String> lines) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, lines);
    }

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        textArea.setPrefHeight(400);

        Button loadButton = new Button("Załaduj plik");
        Button saveButton = new Button("Zapisz plik");

        loadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Wybierz plik do załadowania");
            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null) {
                try {
                    List<String> content = readFile(file.getAbsolutePath());
                    textArea.setText(String.join("\n", content));
                } catch (IOException ex) {
                    showAlert("Błąd odczytu", "Nie udało się odczytać pliku: " + ex.getMessage());
                }
            }
        });

        saveButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Wybierz miejsce zapisu");
            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                try {
                    writeFile(file.getAbsolutePath(), List.of(textArea.getText().split("\n")));
                    showAlert("Sukces", "Plik zapisano pomyślnie.");
                } catch (IOException ex) {
                    showAlert("Błąd zapisu", "Nie udało się zapisać pliku: " + ex.getMessage());
                }
            }
        });

        VBox root = new VBox(10, textArea, loadButton, saveButton);
        Scene scene = new Scene(root, 600, 500);

        primaryStage.setTitle("Odczyt i zapis pliku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
