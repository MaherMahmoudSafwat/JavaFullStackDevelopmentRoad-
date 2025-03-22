package com.example.fullhosiptalproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AdminLoginPage {

    public static Scene AdminLoginScreen(Stage stage) {
        // Create a label with a larger font
        Label passwordLabel = new Label("Please enter your admin password.");
        passwordLabel.setFont(new Font("Verdana", 40)); // Bigger font

        // Create a text field
        TextField passwordField = new TextField();
        passwordField.setPromptText("Enter Password");
        passwordField.setPrefSize(400, 50); // Bigger text field
        passwordField.setStyle("-fx-font-size: 20;"); // Bigger font inside text field

        // Create buttons
        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 50);
        backButton.setStyle("-fx-font-size: 20;"); // Bigger button text

        Button nextButton = new Button("Next");
        nextButton.setPrefSize(200, 50);
        nextButton.setStyle("-fx-font-size: 20;"); // Bigger button text

        // Create a message label
        Label messageLabel = new Label();
        messageLabel.setFont(new Font("Verdana", 20)); // Bigger font for messages

        // Center everything in a VBox
        VBox root = new VBox(20); // 20 is the spacing between elements
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(passwordLabel, passwordField, backButton, nextButton, messageLabel);

        // Set the background color
        root.setStyle("-fx-background-color: LimeGreen;");

        // Handle back button click
        backButton.setOnAction(e -> {
            Scene previousScene = MainUserTypesScreen.MainUserStatusMenu(stage);
            stage.setScene(previousScene);
        });

        // Handle next button click
        nextButton.setOnAction(e -> {
            if ("admin123".equals(passwordField.getText())) {
                messageLabel.setText("Login Successful");
                messageLabel.setStyle("-fx-text-fill: green;");
                // Proceed to the next page or functionality after successful login
            } else {
                messageLabel.setText("Incorrect Password");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        // Return the scene
        return new Scene(root, 1500, 777);
    }
}