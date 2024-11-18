package com.example.kostki;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class HelloApplication extends Application {

    private final Random random = new Random();

    @Override
    public void start(Stage stage) {
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));

        Label promptLabel = new Label("Ile kostek chcesz rzucić? (3 - 10)");
        TextField diceInput = new TextField();
        Button rollButton = new Button("Rzuć!");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        HBox buttonsBox = new HBox(10);
        Button continueButton = new Button("Jeszcze raz");
        Button exitButton = new Button("Zakończ");
        buttonsBox.getChildren().addAll(continueButton, exitButton);

        mainLayout.getChildren().addAll(promptLabel, diceInput, rollButton, resultArea, buttonsBox);

        Scene scene = new Scene(mainLayout, 400, 300);
        stage.setTitle("Gra w Kości");
        stage.setScene(scene);
        stage.show();

        rollButton.setOnAction(event -> {
            String input = diceInput.getText();
            try {
                int numberOfDice = Integer.parseInt(input);

                if (numberOfDice < 3 || numberOfDice > 10) {
                    resultArea.appendText("Podaj liczbę z przedziału 3-10!\n");
                    return;
                }

                int[] rolls = rollDice(numberOfDice);
                int points = calculatePoints(rolls);

                resultArea.appendText("Wyniki rzutów:\n");
                for (int i = 0; i < rolls.length; i++) {
                    resultArea.appendText("Kostka " + (i + 1) + ": " + rolls[i] + "\n");
                }
                resultArea.appendText("Liczba uzyskanych punktów: " + points + "\n");
            } catch (NumberFormatException e) {
                resultArea.appendText("Podaj poprawną liczbę!\n");
            }
        });

        continueButton.setOnAction(event -> {
            resultArea.clear();
            diceInput.clear();
        });

        exitButton.setOnAction(event -> stage.close());
    }

    private int[] rollDice(int numberOfDice) {
        int[] rolls = new int[numberOfDice];
        for (int i = 0; i < numberOfDice; i++) {
            rolls[i] = random.nextInt(6) + 1; // Liczby od 1 do 6
        }
        return rolls;
    }
    
    private int calculatePoints(int[] rolls) {
        Map<Integer, Integer> rollCounts = new HashMap<>();
        for (int roll : rolls) {
            rollCounts.put(roll, rollCounts.getOrDefault(roll, 0) + 1);
        }

        int points = 0;
        for (Map.Entry<Integer, Integer> entry : rollCounts.entrySet()) {
            if (entry.getValue() > 1) { // Liczba oczek została wylosowana więcej niż raz
                points += entry.getKey() * entry.getValue();
            }
        }

        return points;
    }

    public static void main(String[] args) {
        launch();
    }
}
