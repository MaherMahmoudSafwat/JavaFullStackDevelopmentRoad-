package com.example.fullhosiptalproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WelcomePageMainMenu {

    private static Label LBL1 = new Label("Welcome to the Hospital Full Project");
    private static Button BtnGoToSecondScreen = new Button("Next");
    public static Scene WelcomePageMenu(Stage stage)
    {
        HandleNextButton(stage);
        HandleLabelInTheScreen();
        Pane Pane1 = new Pane();
        Pane1.getChildren().addAll(LBL1,BtnGoToSecondScreen);
        Pane1.setStyle("-fx-background-color: LimeGreen;");
        return new Scene(Pane1, 600, 400);
    }
    private static void HandleNextButton(Stage stage)
    {
        BtnGoToSecondScreen.setPrefSize(200, 50);
        BtnGoToSecondScreen.setTranslateX(250);
        BtnGoToSecondScreen.setTranslateY(500);
        // Event handler to switch to second screen
        BtnGoToSecondScreen.setOnAction(e -> {
            // Switch to second screen
            Scene secondScene = MainUserTypesScreen.MainUserStatusMenu(stage);
            stage.setScene(secondScene);
        });
    }
    private static void HandleLabelInTheScreen() {
        LBL1.setTranslateX(111);
        LBL1.setTranslateY(50);
        LBL1.setFont(new Font("Verdena", 30));
    }
}
