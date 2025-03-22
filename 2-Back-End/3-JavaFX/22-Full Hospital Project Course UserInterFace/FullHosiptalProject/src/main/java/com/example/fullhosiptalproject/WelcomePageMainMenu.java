package com.example.fullhosiptalproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WelcomePageMainMenu {

    public static Scene WelcomePageMenu(Stage stage) {
        // Create a label with a larger font
        Label welcomeLabel = new Label("Welcome to the Hospital Full Project");
        welcomeLabel.setFont(new Font("Verdana", 40));

        welcomeLabel.setTranslateX(450);
        welcomeLabel.setTranslateY(100);
        // Create a button
        Button nextButton = new Button("Next");
        nextButton.setPrefSize(200, 50);

        nextButton.setTranslateX(650);
        nextButton.setTranslateY(500);
        // Center the button and label in a VBox
        VBox root = new VBox(20); // 20 is the spacing between elements
        root.getChildren().addAll(welcomeLabel, nextButton);

        // Set the background color
        root.setStyle("-fx-background-color: LimeGreen;");

        // Handle button click to switch to the next screen
        nextButton.setOnAction(e -> {
            Scene secondScene = MainUserTypesScreen.MainUserStatusMenu(stage);
            stage.setScene(secondScene);
        });

        // Return the scene
        return new Scene(root);
    }
}