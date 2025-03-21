package com.example.fullhosiptalproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainUserTypesScreen {

    private static final Button BtnBackToPreviousScreen = new Button("Back");
    private static final Button BtnGoToNextScreen = new Button("Next");
    private static final RadioButton RDR1 = new RadioButton("1-Staff Admin User.");
    private static final RadioButton RDR2 = new RadioButton("2-Patient.");
    private static final RadioButton RDR3 = new RadioButton("3-Doctor.");
    private static final Label LBL1 = new Label("Please select your status.");
    public static Scene MainUserStatusMenu(Stage stage) {
        // Create a button for navigation
        HandleButtonBackScreen(stage);
        HandleNextButton();
        HandleLabelButton();
        HandleRadioButtonsToggleGroup();
        VBox Box = HandleRadioButtonsCustoms();
        // Create a pane and add button
        Pane pane = new Pane();
        pane.getChildren().addAll(Box,BtnBackToPreviousScreen,BtnGoToNextScreen);

        // Set the background color of the second screen
        pane.setStyle("-fx-background-color: LimeGreen;");

        // Return the scene for this screen
        return new Scene(pane, 600, 400);
    }
    private static void HandleButtonBackScreen(Stage stage)
    {
        // Set button properties
        BtnBackToPreviousScreen.setPrefSize(200, 50);
        BtnBackToPreviousScreen.setTranslateX(111);
        BtnBackToPreviousScreen.setTranslateY(555);

        // Event handler to switch back to the first screen
        BtnBackToPreviousScreen.setOnAction(e -> {
            // Switch back to the first screen
            Scene firstScene = WelcomePageMainMenu.WelcomePageMenu(stage);
            stage.setScene(firstScene);
        });
    }
    private static void HandleRadioButtonsToggleGroup()
    {
        ToggleGroup Group = new ToggleGroup();
        RDR1.setToggleGroup(Group);
        RDR2.setToggleGroup(Group);
        RDR3.setToggleGroup(Group);
    }
    private static VBox HandleRadioButtonsCustoms()
    {
        RDR1.setFont(new Font("Verdena",30));
        RDR2.setFont(new Font("Verdena",30));
        RDR3.setFont(new Font("Verdena",30));
        VBox Box = new VBox();
        Box.getChildren().addAll(LBL1,RDR1,RDR2,RDR3);
        Box.setTranslateX(150);
        Box.setTranslateY(150);
        return Box;
    }
    private static void HandleLabelButton()
    {
        LBL1.setFont(new Font("Verdena",30));
    }
    private static void HandleNextButton()
    {
        BtnGoToNextScreen.setPrefSize(200, 50);
        BtnGoToNextScreen.setTranslateX(450);
        BtnGoToNextScreen.setTranslateY(555);
    }
}
