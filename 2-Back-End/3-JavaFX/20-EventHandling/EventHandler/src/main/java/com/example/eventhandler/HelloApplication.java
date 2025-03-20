package com.example.eventhandler;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        // Create a Pane as the root node
        Pane P = new Pane();

        // Set the preferred size of the pane to match the scene size
        P.setPrefSize(300, 300);  // Scene width and height

        // Set the background color for the Scene
        Scene scene = new Scene(P, 300, 300, Color.LIGHTSKYBLUE);

        BackgroundFill BF = new BackgroundFill(Color.RED,null,null);
        Background BGD = new Background(BF);
        P.setBackground(BGD);
        // Create a button and add it to the Pane
        Button btn = new Button("Click Me");
        btn.setLayoutX(100);  // X position
        btn.setLayoutY(100);  // Y position
        P.getChildren().add(btn); // Add button to the Pane

        // Set up the stage and show the scene
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
