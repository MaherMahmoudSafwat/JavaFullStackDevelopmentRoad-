package com.example.fullhosiptalproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainUserTypesScreen {

    private static Button BtnBackToPreviousScreen = new Button("Back");
    public static Scene MainUserStatusMenu(Stage stage) {
        // Create a button for navigation
        HandleButtonScreen(stage);
        // Create a pane and add button
        Pane pane = new Pane();
        pane.getChildren().add(BtnBackToPreviousScreen);

        // Set the background color of the second screen
        pane.setStyle("-fx-background-color: lightgreen;");

        // Return the scene for this screen
        return new Scene(pane, 600, 400);
    }
    private static void HandleButtonScreen(Stage stage)
    {
        // Set button properties
        BtnBackToPreviousScreen.setPrefSize(200, 50);
        BtnBackToPreviousScreen.setTranslateX(150);
        BtnBackToPreviousScreen.setTranslateY(500);

        // Event handler to switch back to the first screen
        BtnBackToPreviousScreen.setOnAction(e -> {
            // Switch back to the first screen
            Scene firstScene = WelcomePageMainMenu.WelcomePageMenu(stage);
            stage.setScene(firstScene);
        });
    }
}
