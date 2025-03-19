package com.example.eventhandler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    // Creating a Pane that will serve as the root of the scene
    Pane P = new Pane();

    @Override
    public void start(Stage stage) {
        // Create the button
        Button BTN1 = new Button("Click Me Button");

        // Create the handler for button click
        Handler handler111 = new Handler();
        BTN1.setOnAction(handler111);

        // Add the button to the Pane (P)
        P.getChildren().add(BTN1);

        // Set the size of the scene and set the Pane as the root
        Scene scene = new Scene(P, 300, 300);

        // Set up the stage
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    // Event Handler class to handle button click
    class Handler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            // Create the label
            Label Lbl1 = new Label("You have Clicked on the Button");

            // Position the label (optional)
            Lbl1.setLayoutX(100); // X position of the label
            Lbl1.setLayoutY(150); // Y position of the label

            // Add the label to the Pane
            P.getChildren().add(Lbl1);
        }
    }
}
