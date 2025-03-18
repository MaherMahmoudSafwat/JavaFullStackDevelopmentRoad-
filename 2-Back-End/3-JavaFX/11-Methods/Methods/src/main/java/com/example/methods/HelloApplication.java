package com.example.methods;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        // Create Button 1 with the text "Button 11111111"
        Button BTN1 = new Button("Button 11111111");

        // Create Button 2 with the text "Button 11111111"
        Button BTN2 = new Button("Button 11111111");

        // Create Button 3 with the text "Button 11111111"
        Button BTN3 = new Button("Button 11111111");

        // Set the text of Button 2 to wrap automatically if it is too long
        BTN2.setWrapText(true);
        // This ensures that if the text inside the button is too long, it will wrap to the next line to fit the button size.

        // Set the preferred width for Button 3
        BTN3.setPrefWidth(90); // This method sets the width to 90 pixels.

        // Set the preferred height for Button 3
        BTN3.setPrefHeight(150); // This method sets the height to 150 pixels.

        // Set both preferred width and preferred height for Button 3 at once
        BTN3.setPrefSize(250, 250); // This method sets the size of Button 3 to 250px by 250px.

        // Create a layout container (HBox) with a 15px gap between each button
        HBox Box = new HBox(15);
        // HBox is a layout that arranges the children (buttons) horizontally.
        // The parameter 15 means there will be 15px of space between each button.

        // Add the buttons to the HBox layout
        Box.getChildren().addAll(BTN1, BTN2, BTN3);
        // This method adds all three buttons (BTN1, BTN2, and BTN3) to the HBox container.

        // Print the text of BTN1 to the console
        System.out.println(BTN1.getText());
        // This line prints the text of the first button (BTN1) to the console. In this case, it will print "Button 11111111".

        // Create a Scene with the HBox as the root node and specify the scene dimensions (320px by 240px)
        Scene scene = new Scene(Box, 320, 240);
        // Scene is the container for all the UI components in JavaFX.
        // The Box (HBox layout) is set as the content of the scene, and the scene's width is 320px and height is 240px.

        // Set the title of the window (Stage)
        stage.setTitle("Hello!");
        // This sets the title that will appear in the title bar of the window.

        // Set the scene of the stage to the created scene
        stage.setScene(scene);
        // This line tells the stage (the window) to display the scene we just created.

        // Display the window
        stage.show();
        // This makes the stage (window) visible on the screen.
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch();
        // The launch() method starts the JavaFX application. It calls the start() method we defined above.
    }
}
