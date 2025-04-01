package com.example.ElTawoonHospitalFullProject;

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
        TableColumn<String[], String> IdPatientColumn = new TableColumn<>("ID");
        IdPatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));

        TableColumn<String[], String> NamePatientColumn = new TableColumn<>("Full Name");
        NamePatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[1]));

        TableColumn<String[], String> EmailPatientColumn = new TableColumn<>("Email");
        EmailPatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[2]));

        TableColumn<String[], String> PasswordPatientColumn = new TableColumn<>("Password");
        PasswordPatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[3]));

        TableColumn<String[], String> PhonePatientColumn = new TableColumn<>("Phone");
        PhonePatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[4]));

        TableColumn<String[], String> AgePatientColumn = new TableColumn<>("Age");
        AgePatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[5]));

        TableColumn<String[], String> GenderPatientColumn = new TableColumn<>("Gender");
        GenderPatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[6]));

        TableColumn<String[], String> DiseasesPatientColumn = new TableColumn<>("Diseases");
        DiseasesPatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[7]));

        table.getColumns().addAll(IdPatientColumn, NamePatientColumn, EmailPatientColumn,PasswordPatientColumn,
                PhonePatientColumn, AgePatientColumn, GenderPatientColumn, DiseasesPatientColumn);
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