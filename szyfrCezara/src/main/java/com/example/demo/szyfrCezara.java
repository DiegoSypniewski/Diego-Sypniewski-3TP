package com.example.demo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import java.io.*;

public class szyfrCezara extends Application {

    private TextField inputField = new TextField();
    private TextField keyField = new TextField();
    private TextArea outputArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Szyfr Cezara");

        keyField.setPromptText("Wprowadź klucz szyfrowania");

        Button encryptButton = new Button("Zaszyfruj");
        encryptButton.setOnAction(e -> encryptText());

        Button decryptButton = new Button("Deszyfruj");
        decryptButton.setOnAction(e -> decryptText());

        Button saveButton = new Button("Zapisz");
        saveButton.setOnAction(e -> saveToFile(primaryStage));

        Button openButton = new Button("Otwórz");
        openButton.setOnAction(e -> openFromFile(primaryStage));

        VBox layout = new VBox(10, inputField, keyField, encryptButton, decryptButton, saveButton, openButton, outputArea);
        Scene scene = new Scene(layout, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void encryptText() {
        String text = inputField.getText();
        int key = Integer.parseInt(keyField.getText());
        outputArea.setText(caesarCipher(text, key));
    }

    private void decryptText() {
        String text = inputField.getText();
        int key = Integer.parseInt(keyField.getText());
        outputArea.setText(caesarCipher(text, -key));
    }

    private String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + shift + 26) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }

    private void saveToFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pliki tekstowe", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(outputArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openFromFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pliki tekstowe", "*.txt"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                inputField.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
