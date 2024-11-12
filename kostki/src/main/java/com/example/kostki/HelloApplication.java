package com.example.kostki;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane gridpane = new GridPane();
        Text ilekostek = new Text("Ile kostek chesz rzucić (3-10)");
        TextField odpowiedz = new TextField();
        int a = 0;
        int wartosckostki = 0;
        Text numerykostek = new Text("Kostka " + a + ": " + wartosckostki);
        Button ok = new Button("ok");

            gridpane.add(ilekostek, 0 ,0);
            gridpane.add(odpowiedz,0,1);
            gridpane.add(ok, 1,1);
            ok.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    int int1 = Integer.parseInt(odpowiedz.getText());
                    for (int i = 0; i < int1; i++) {

                        gridpane.add(numerykostek,0,i+2);
                    }
                }
            });




        gridpane.setMinSize(500, 500);

        Scene scene = new Scene(gridpane);
        stage.setTitle("Gra z Kostkami!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}