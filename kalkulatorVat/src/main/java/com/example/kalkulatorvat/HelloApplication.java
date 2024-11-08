package com.example.kalkulatorvat;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Kalkulator VAT netto-brutto");

        RadioButton nettoDoBrutto = new RadioButton("Od netto do brutto");
        RadioButton bruttoDoNetto = new RadioButton("Od brutto do netto");
        RadioButton dopasujDoVAT = new RadioButton("Dopasuj do kwoty VAT");

        ToggleGroup metodaObliczenGroup = new ToggleGroup();
        nettoDoBrutto.setToggleGroup(metodaObliczenGroup);
        bruttoDoNetto.setToggleGroup(metodaObliczenGroup);
        dopasujDoVAT.setToggleGroup(metodaObliczenGroup);
        nettoDoBrutto.setSelected(true);

        VBox metodaObliczenBox = new VBox(10, nettoDoBrutto, bruttoDoNetto, dopasujDoVAT);
        metodaObliczenBox.setPadding(new Insets(10));
        metodaObliczenBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5;");
        Label metodaObliczenLabel = new Label("Metoda obliczeń");
        VBox metodaObliczenSection = new VBox(5, metodaObliczenLabel, metodaObliczenBox);

        TextField wartoscBazowa = new TextField("2000,00");
        ComboBox<String> stawkaVAT = new ComboBox<>();
        stawkaVAT.getItems().addAll("23%", "8%", "5%", "0%");
        stawkaVAT.setValue("23%");

        GridPane daneBox = new GridPane();
        daneBox.setHgap(10);
        daneBox.setVgap(10);
        daneBox.add(new Label("Wartość bazowa:"), 0, 0);
        daneBox.add(wartoscBazowa, 1, 0);
        daneBox.add(new Label("Stawka VAT:"), 0, 1);
        daneBox.add(stawkaVAT, 1, 1);
        daneBox.setPadding(new Insets(10));
        daneBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5;");
        Label daneLabel = new Label("Dane");
        VBox daneSection = new VBox(5, daneLabel, daneBox);

        Text wynikNetto = new Text("Netto:         0,00");
        Text wynikVAT = new Text("VAT:            0,00 @ 23%");
        Text wynikBrutto = new Text("Brutto:        0,00");

        VBox wynikiBox = new VBox(5, wynikNetto, wynikVAT, wynikBrutto);
        wynikiBox.setPadding(new Insets(10));
        wynikiBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5;");
        Label wynikiLabel = new Label("Wyniki");
        VBox wynikiSection = new VBox(5, wynikiLabel, wynikiBox);

        Button obliczButton = new Button("OBLICZ");
        Button zamknijButton = new Button("Zamknij");
        HBox buttonsBox = new HBox(10, obliczButton, zamknijButton);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox mainLayout = new VBox(10, metodaObliczenSection, daneSection, buttonsBox, wynikiSection);
        mainLayout.setPadding(new Insets(10));

        obliczButton.setOnAction(e -> {
            try {
                double kwota = Double.parseDouble(wartoscBazowa.getText().replace(",", "."));
                double stawka = Double.parseDouble(stawkaVAT.getValue().replace("%", "")) / 100;
                double netto, vat, brutto;

                if (nettoDoBrutto.isSelected()) {
                    netto = kwota;
                    vat = netto * stawka;
                    brutto = netto + vat;
                } else if (bruttoDoNetto.isSelected()) {
                    brutto = kwota;
                    netto = brutto / (1 + stawka);
                    vat = brutto - netto;
                } else {
                    vat = kwota;
                    netto = vat / stawka;
                    brutto = netto + vat;
                }

                wynikNetto.setText(String.format("Netto:        %.2f", netto));
                wynikVAT.setText(String.format("VAT:          %.2f @ %s", vat, stawkaVAT.getValue()));
                wynikBrutto.setText(String.format("Brutto:       %.2f", brutto));
            } catch (NumberFormatException ex) {
                wynikNetto.setText("Netto:         Błąd");
                wynikVAT.setText("VAT:            Błąd");
                wynikBrutto.setText("Brutto:        Błąd");
            }
        });

        zamknijButton.setOnAction(e -> stage.close());

        Scene scene = new Scene(mainLayout, 350, 450);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
