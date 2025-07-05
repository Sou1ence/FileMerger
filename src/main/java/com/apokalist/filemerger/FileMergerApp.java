/**
 * @author: Era (Sou1ence)
 */

package com.apokalist.filemerger;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;
import java.util.List;


public class FileMergerApp extends Application {

    private ListView fLV; // ListView to display files
    private List<File> selectedFiles;
    private TextArea statusTA; // TextArea to display file content


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Era FileMerger");

        Label titleL = new Label("Choose files to merge:");
        titleL.getStyleClass().add("title-label");

        fLV = new ListView<>();
        fLV.setPrefHeight(200);

        Button selectFilesBtn = new Button("Choose files");
        selectFilesBtn.setPrefWidth(140);
        selectFilesBtn.setOnAction(e -> selectFiles(primaryStage));
        selectFilesBtn.getStyleClass().add("select-button");

        Button clearBtn = new Button("Clear list");
        clearBtn.setPrefWidth(140);
        clearBtn.setOnAction(e -> clearFileList());
        clearBtn.getStyleClass().add("clear-button");

        Button mergeBtn = new Button ("Merge files");
        mergeBtn.setPrefWidth(140);
        mergeBtn.setOnAction(e -> mergeFiles());
        mergeBtn.getStyleClass().add("merge-button");

        statusTA = new TextArea();
        statusTA.setEditable(false);
        statusTA.setPrefHeight(100);
        statusTA.setWrapText(true);
        statusTA.getStyleClass().add("status-textarea");

        // Set up the layout
        VBox root = new VBox(10);
        root.setPadding(new Insets (15));
        root.getChildren().addAll(titleL, fLV, selectFilesBtn, clearBtn, mergeBtn, new Label("Status"), statusTA);

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        statusTA.setText("Welcome to Era FileMerger!\n" +
                "Select files to merge and click 'Merge files' to combine them.\n" +
                "You can clear the list at any time");
    }

    private void selectFiles (Stage stage) {
        FileChooser fC = new FileChooser();
        fC.setTitle("Select Files to Merge");
        fC.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Java Files", "*.java")
        );

        selectedFiles = fC.showOpenMultipleDialog(stage);

        if (selectedFiles != null && !selectedFiles.isEmpty()) {
            fLV.getItems().clear();
            for (File file : selectedFiles) {
                fLV.getItems().add(file.getName());
            }
            statusTA.setText("Selected files:\n" +
                    String.join("\n", fLV.getItems().toString()));
        } else {
            statusTA.setText("No files selected.");
        }
    }

    private void clearFileList() {
        fLV.getItems().clear();
        selectedFiles = null;
        statusTA.setText("File list cleared.");
    }
}