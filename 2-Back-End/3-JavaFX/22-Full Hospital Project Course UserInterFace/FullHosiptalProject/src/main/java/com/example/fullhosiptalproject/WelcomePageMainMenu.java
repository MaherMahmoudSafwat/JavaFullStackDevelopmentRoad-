package com.example.fullhosiptalproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WelcomePageMainMenu {

    public static Scene createFirstScreen(Stage stage) {
        // Create a button for navigation
        Button btnGoToSecondScreen = new Button("Go to Second Screen");

        // Set button properties
        btnGoToSecondScreen.setPrefSize(200, 50);
        btnGoToSecondScreen.setTranslateX(200);
        btnGoToSecondScreen.setTranslateY(200);

        // Event handler to switch to second screen
        btnGoToSecondScreen.setOnAction(e -> {
            // Switch to second screen
            Scene secondScene = SecondScreen.createSecondScreen(stage);
            stage.setScene(secondScene);
        });

        // Create a pane and add button
        Pane pane = new Pane();
        pane.getChildren().add(btnGoToSecondScreen);

        // Set the background color of the first screen
        pane.setStyle("-fx-background-color: lightblue;");

        // Return the scene for this screen
        return new Scene(pane, 600, 400);
    }
}

