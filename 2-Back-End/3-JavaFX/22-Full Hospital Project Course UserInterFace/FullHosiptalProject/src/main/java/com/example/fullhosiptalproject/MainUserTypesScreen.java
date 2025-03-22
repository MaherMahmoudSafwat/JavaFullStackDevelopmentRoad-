package com.example.fullhosiptalproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainUserTypesScreen {

    public static Scene MainUserStatusMenu(Stage stage) {
        // Create a label with a larger font
        Label statusLabel = new Label("Please select your status.");
        statusLabel.setFont(new Font("Verdana", 40)); // Bigger font

        // Create radio buttons
        RadioButton adminRadio = new RadioButton("1-Staff Admin User.");
        RadioButton patientRadio = new RadioButton("2-Patient.");
        RadioButton doctorRadio = new RadioButton("3-Doctor.");

        // Set font for radio buttons
        adminRadio.setFont(new Font("Verdana", 20));
        patientRadio.setFont(new Font("Verdana", 20));
        doctorRadio.setFont(new Font("Verdana", 20));

        // Align radio buttons neatly under each other
        VBox radioBox = new VBox(10); // 10 is the spacing between radio buttons
        radioBox.getChildren().addAll(statusLabel,adminRadio, patientRadio, doctorRadio);
        radioBox.setTranslateX(500);
        radioBox.setTranslateY(100);

        // Create buttons
        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 50);
        backButton.setStyle("-fx-font-size: 20;"); // Bigger button text

        Button nextButton = new Button("Next");
        nextButton.setPrefSize(200, 50);
        nextButton.setStyle("-fx-font-size: 20;"); // Bigger button text

        // Stack buttons vertically
        VBox buttonBox = new VBox(20); // 20 is the spacing between buttons
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(backButton, nextButton);

        // Center everything in a VBox
        VBox root = new VBox(30); // 30 is the spacing between elements
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(statusLabel, radioBox, buttonBox);

        // Set the background color
        root.setStyle("-fx-background-color: LimeGreen;");

        // Handle back button click
        backButton.setOnAction(e -> {
            Scene welcomeScene = WelcomePageMainMenu.WelcomePageMenu(stage);
            stage.setScene(welcomeScene);
        });

        // Handle next button click
        nextButton.setOnAction(e -> {
            if (adminRadio.isSelected()) {
                // Go to Admin Login Screen
                Scene adminScene = AdminLoginPage.AdminLoginScreen(stage);
                stage.setScene(adminScene);
            } else if (patientRadio.isSelected() || doctorRadio.isSelected()) {
                // Handle Patient or Doctor selection (you can add logic here)
                System.out.println("Patient or Doctor selected.");
            }
        });

        // Return the scene
        return new Scene(root);
    }
}