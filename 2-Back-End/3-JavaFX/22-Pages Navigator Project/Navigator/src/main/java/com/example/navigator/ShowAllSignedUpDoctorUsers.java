package com.example.navigator;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ShowAllSignedUpDoctorUsers {
    public static Scene ShowAllUsers(Stage stage) {
        com.example.navigator.ShowAllSignedUpPatientUsers SASUS = new com.example.navigator.ShowAllSignedUpPatientUsers();
        return SASUS.CreateNewScene(stage);
    }

    private Scene CreateNewScene(Stage stage) {
        VBox Box = new VBox();

        // Create TableView with raw String data
        TableView<String[]> PTV = new TableView<>();
        ObservableList<String[]> PatientDataList = FXCollections.observableArrayList();
        PTV.setItems(PatientDataList);

        // Create columns with custom cell value factories
        TableColumn<String[], String> IDPatientColumn = new TableColumn<>("ID");
        IDPatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));

        TableColumn<String[], String> NamePatientColumn = new TableColumn<>("FullName");
        NamePatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[1]));

        TableColumn<String[], String> EmailPatientColumn = new TableColumn<>("Email");
        EmailPatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[2]));

        TableColumn<String[], String> PasswordPatientColumn = new TableColumn<>("Password");
        PasswordPatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[3]));

        TableColumn<String[], String> PhoneNumberPatientColumn = new TableColumn<>("PhoneNumber");
        PhoneNumberPatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[4]));

        TableColumn<String[], String> AgePatientColumn = new TableColumn<>("Specialization");
        AgePatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[5]));

        TableColumn<String[], String> GenderPatientColumn = new TableColumn<>("Gender");
        GenderPatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[6]));

        TableColumn<String[], String> DiseasesPatientColumn = new TableColumn<>("Diseases");
        DiseasesPatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[7]));

        PTV.getColumns().addAll(IDPatientColumn, NamePatientColumn, EmailPatientColumn,
                PasswordPatientColumn, PhoneNumberPatientColumn, AgePatientColumn,
                GenderPatientColumn, DiseasesPatientColumn);

        // Load data from file
        ArrayList<String> Data = Doctor.GetAllDoctorsStringFromFile();

        // Add data to TableView
        for (String patientData : Data) {
            String[] patientFields = patientData.split(",");
            if (patientFields.length >= 8) { // Ensure we have all fields
                PatientDataList.add(patientFields);
            }
        }

        Box.getChildren().add(PTV);
        return new Scene(Box, 800, 600); // Set preferred size
    }
}
