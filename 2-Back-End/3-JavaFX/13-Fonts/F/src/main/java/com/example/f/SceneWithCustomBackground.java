package com.example.f;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SceneWithCustomBackground extends Application {

    @Override
    public void start(Stage stage) {
        // Create a Button
        Button button = new Button("Click Me!");
        Font F1 = new Font(30);
        F1=Font.font("Arial Black");
        F1 = Font.font("Arial Black", FontWeight.BOLD, FontPosture.ITALIC,30);
        button.setFont(F1);

        // Create a StackPane layout to hold the button
        HBox layout = new HBox();
        layout.getChildren().add(button);  // Add button to layout

        // Set the background color of the layout
        BackgroundFill backgroundFill = new BackgroundFill(Color.GREEN, null, null);
        Background background = new Background(backgroundFill);
        layout.setBackground(background);  // Apply background to layout

        // Create a Scene with the layout and set a size for the scene
        Scene scene = new Scene(layout, 800, 600);  // Scene size 800x600

        // Set up the stage (window)
        stage.setTitle("Scene with Custom Background");
        stage.setScene(scene);

        // Show the window
        stage.show();
        System.out.println(Font.getFamilies());
    }

    public static void main(String[] args) {
        launch();
    }
}
