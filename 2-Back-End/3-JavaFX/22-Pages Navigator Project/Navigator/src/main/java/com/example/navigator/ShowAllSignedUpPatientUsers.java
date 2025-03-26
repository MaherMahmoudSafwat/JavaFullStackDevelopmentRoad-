package com.example.navigator;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ShowAllSignedUpPatientUsers {
    private final Button btnBack = new Button("Back");

    public static Scene ShowAllUsers(Stage stage) {
        return new ShowAllSignedUpPatientUsers().createScene(stage);
    }

    private Scene createScene(Stage stage) {
        // Main layout with spacing and padding
        VBox root = new VBox(20);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));

        // Table setup
        TableView<String[]> table = createPatientTableView();
        loadPatientData(table);

        // Button setup
        setupBackButton(stage);

        // Add components to layout
        root.getChildren().addAll(table, btnBack);

        return new Scene(root, 900, 650); // Slightly larger to accommodate more columns
    }

    private TableView<String[]> createPatientTableView() {
        TableView<String[]> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Columns setup
        TableColumn<String[], String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));

        TableColumn<String[], String> nameColumn = new TableColumn<>("Full Name");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[1]));

        TableColumn<String[], String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[2]));

        TableColumn<String[], String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[4]));

        TableColumn<String[], String> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[5]));

        TableColumn<String[], String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[6]));

        TableColumn<String[], String> diseasesColumn = new TableColumn<>("Diseases");
        diseasesColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[7]));

        table.getColumns().addAll(idColumn, nameColumn, emailColumn,
                phoneColumn, ageColumn, genderColumn, diseasesColumn);
        table.setPrefHeight(550);

        return table;
    }

    private void loadPatientData(TableView<String[]> table) {
        ObservableList<String[]> data = FXCollections.observableArrayList();
        ArrayList<String> fileData = Patient.GetAllPatientsStringFromFile();

        for (String record : fileData) {
            String[] fields = record.split(",");
            if (fields.length >= 8) { // Ensure all 8 fields are present
                data.add(fields);
            } else {
                System.err.println("Skipping incomplete record: " + record);
            }
        }

        table.setItems(data);
    }

    private void setupBackButton(Stage stage) {
        btnBack.setPrefSize(200, 40);
        btnBack.setStyle("-fx-font-size: 14px; -fx-background-color: #2196F3; -fx-text-fill: white;");
        btnBack.setOnAction(e -> stage.setScene(AdminMainMenuScreen.AdminMainMenu(stage)));
    }
}