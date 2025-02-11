package com.example.demo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button compressButton = new Button("Kompresuj pliki");
        Button decompressButton = new Button("Dekompresuj plik ZIP");

        compressButton.setOnAction(e -> compressFiles(primaryStage));
        decompressButton.setOnAction(e -> decompressFile(primaryStage));

        VBox layout = new VBox(10, compressButton, decompressButton);
        Scene scene = new Scene(layout, 300, 200);

        primaryStage.setTitle("Kompresja i Dekompresja ZIP");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void compressFiles(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz pliki do kompresji");
        List<File> files = fileChooser.showOpenMultipleDialog(stage);

        if (files != null) {
            FileChooser saveFileChooser = new FileChooser();
            saveFileChooser.setTitle("Zapisz plik ZIP");
            saveFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ZIP files", "*.zip"));
            File zipFile = saveFileChooser.showSaveDialog(stage);

            if (zipFile != null) {
                try (FileOutputStream fos = new FileOutputStream(zipFile);
                     ZipOutputStream zipOut = new ZipOutputStream(fos)) {

                    for (File file : files) {
                        try (FileInputStream fis = new FileInputStream(file)) {
                            ZipEntry zipEntry = new ZipEntry(file.getName());
                            zipOut.putNextEntry(zipEntry);
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = fis.read(buffer)) >= 0) {
                                zipOut.write(buffer, 0, length);
                            }
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void decompressFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik ZIP do dekompresji");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ZIP files", "*.zip"));
        File zipFile = fileChooser.showOpenDialog(stage);

        if (zipFile != null) {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Wybierz folder docelowy");
            File destDir = directoryChooser.showDialog(stage);

            if (destDir != null) {
                try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
                    ZipEntry zipEntry;
                    byte[] buffer = new byte[1024];

                    while ((zipEntry = zis.getNextEntry()) != null) {
                        File newFile = new File(destDir, zipEntry.getName());
                        new File(newFile.getParent()).mkdirs();

                        try (FileOutputStream fos = new FileOutputStream(newFile)) {
                            int len;
                            while ((len = zis.read(buffer)) > 0) {
                                fos.write(buffer, 0, len);
                            }
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}