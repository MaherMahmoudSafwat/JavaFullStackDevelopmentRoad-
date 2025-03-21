package com.example.fullhosiptalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Set the initial scene to the first screen
        Scene MainProjectPage = WelcomePageMainMenu.WelcomePageMenu(stage);

        // Set the title for the window
        stage.setTitle("Hospital Full Project");

        // Set the initial scene
        stage.setScene(MainProjectPage);
        stage.setWidth(1500);
        stage.setHeight(777);
        // Show the window
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

