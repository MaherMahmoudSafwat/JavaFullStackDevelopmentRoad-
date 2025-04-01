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

public class ShowAllSignedUpDoctorUsers {
    private final Button btnBack = new Button("Back");

    public static Scene ShowAllUsers(Stage stage) {
        return new ShowAllSignedUpDoctorUsers().createScene(stage);
    }

    private Scene createScene(Stage stage) {
        // Main layout with spacing and padding
        VBox root = new VBox(20);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));

        // Table setup
        TableView<String[]> table = createDoctorTableView();
        loadDoctorData(table); // Load data into the table

        // Button setup
        setupBackButton(stage);

        // Add components to layout
        root.getChildren().addAll(table, btnBack);

        return new Scene(root, 800, 600);
    }

    private TableView<String[]> createDoctorTableView() {
        TableView<String[]> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Auto-resize columns

        // Columns setup
        TableColumn<String[], String> IdDoctorColumn = new TableColumn<>("ID");
        IdDoctorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));

        TableColumn<String[], String> NameDoctorColumn = new TableColumn<>("Full Name");
        NameDoctorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[1]));

        TableColumn<String[], String> EmailDoctorColumn = new TableColumn<>("Email");
        EmailDoctorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[2]));

        TableColumn<String[], String> PasswordDoctorColumn = new TableColumn<>("Password");
        PasswordDoctorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[2]));

        TableColumn<String[], String> PhoneDoctorColumn = new TableColumn<>("Phone");
        PhoneDoctorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[4]));

        TableColumn<String[], String> SpecializationDoctorColumn = new TableColumn<>("Specialization");
        SpecializationDoctorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[5]));

        table.getColumns().addAll(IdDoctorColumn, NameDoctorColumn, EmailDoctorColumn,
                PasswordDoctorColumn, PhoneDoctorColumn, SpecializationDoctorColumn);
        table.setPrefHeight(500); // Leave space for button

        return table;
    }

    private void loadDoctorData(TableView<String[]> table) {
        ObservableList<String[]> data = FXCollections.observableArrayList();
        ArrayList<String> fileData = Doctor.GetAllDoctorsStringFromFile();

        for (String record : fileData) {
            String[] fields = record.split(",");
            if (fields.length >= 6) { // Ensure correct number of fields
                data.add(fields);
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