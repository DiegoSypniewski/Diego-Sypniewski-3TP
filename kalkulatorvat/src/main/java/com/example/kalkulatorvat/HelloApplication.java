package com.example.kalkulatorvat;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        //gridpane1 rzeczy
        Label metodaobliczen = new Label();
        metodaobliczen.setGraphic(new Label(" Panel plików "));
        metodaobliczen.getGraphic().setStyle("-fx-background-color: #f4f4f4;");
        metodaobliczen.setPadding(new Insets(-30,-10,0,0));
        ToggleGroup grupametod = new ToggleGroup();
        RadioButton nettodobrutto = new RadioButton("Od netto do brutto");
        nettodobrutto.setToggleGroup(grupametod);
        RadioButton bruttodonetto = new RadioButton("Od brutto do netto");
        bruttodonetto.setToggleGroup(grupametod);
        RadioButton dopasujdovat = new RadioButton("Dopasuj do kwoty VAT");
        dopasujdovat.setToggleGroup(grupametod);

        //gridpane1 ustawienia
        GridPane gridPane1 = new GridPane();
        gridPane1.setStyle("-fx-border-style: solid inside;");
        gridPane1.setStyle("-fx-border-width: 1;");
        gridPane1.setStyle("-fx-border-insets: 1;");
        gridPane1.setStyle("-fx-border-radius: 1;");
        gridPane1.setStyle("-fx-border-color: black;");
        gridPane1.setVgap(5);
        gridPane1.setHgap(5);
        gridPane1.setPadding(new Insets(15));

        //gridpane2 rzeczy
        Label dane = new Label();
        dane.setGraphic(new Label(" Panel plików "));
        dane.getGraphic().setStyle("-fx-background-color: #f4f4f4;");


        //gridpane2 ustawienia
        GridPane gridPane2 = new GridPane();
        gridPane2.setStyle("-fx-border-style: solid inside;");
        gridPane2.setStyle("-fx-border-width: 1;");
        gridPane2.setStyle("-fx-border-insets: 1;");
        gridPane2.setStyle("-fx-border-radius: 1;");
        gridPane2.setStyle("-fx-border-color: black;");
        gridPane2.setVgap(5);
        gridPane2.setHgap(5);
        gridPane2.setPadding(new Insets(15));

        GridPane gridPane3 = new GridPane();



        GridPane gridPaneall = new GridPane();
        gridPaneall.add(gridPane1,0,0);
        gridPaneall.add(gridPane2,0,1);
        //gridPaneall.add(gridPane3,0,0);
        gridPaneall.setPadding(new Insets(10,10,10,10));
        gridPane1.add(metodaobliczen, 0, 0);
        gridPane1.add(nettodobrutto, 0, 1);
        gridPane1.add(bruttodonetto, 0, 2);
        gridPane1.add(dopasujdovat, 0, 3);
        gridPaneall.setMinSize(500, 500);
        gridPaneall.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(gridPaneall);
        stage.setTitle("Kalkulator VAT netto-brutto");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}