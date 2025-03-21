package com.example.fullhosiptalproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainUserTypesScreen {

    public static Scene createSecondScreen(Stage stage) {
        // Create a button for navigation
        Button btnBackToFirstScreen = new Button("Back to First Screen");

        // Set button properties
        btnBackToFirstScreen.setPrefSize(200, 50);
        btnBackToFirstScreen.setTranslateX(200);
        btnBackToFirstScreen.setTranslateY(200);

        // Event handler to switch back to the first screen
        btnBackToFirstScreen.setOnAction(e -> {
            // Switch back to the first screen
            Scene firstScene = WelcomePageMainMenu.createFirstScreen(stage);
            stage.setScene(firstScene);
        });

        // Create a pane and add button
        Pane pane = new Pane();
        pane.getChildren().add(btnBackToFirstScreen);

        // Set the background color of the second screen
        pane.setStyle("-fx-background-color: lightgreen;");

        // Return the scene for this screen
        return new Scene(pane, 600, 400);
    }
}
