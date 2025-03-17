// This is the main package for the application. The package name is 'com.example.myfirstjavafxproject'.
package com.example.myfirstjavafxproject;

// Importing necessary JavaFX classes.
import javafx.application.Application; // For JavaFX Application class.
import javafx.scene.Scene; // To create a scene for displaying the UI.
import javafx.scene.control.Button; // For creating a button.
import javafx.stage.Stage; // The primary window stage for the application.

public class HelloApplication extends Application // 'HelloApplication' class extends the JavaFX 'Application' class.
{

    // The 'start' method is the entry point for the JavaFX application.
    // This method is automatically called when the application is launched.
    public void start(Stage PrimaryStage)
    {
        // Creating a new button with the text "Click Me".
        Button BTN = new Button("Click Me");

        // Creating a scene that will contain the button.
        // The scene will have a width of 600 pixels and a height of 400 pixels.
        Scene scene = new Scene(BTN, 600, 400);

        // Setting the title of the primary stage (window).
        PrimaryStage.setTitle("My JavaFx Program");

        // Setting the scene that will be displayed inside the primary stage.
        PrimaryStage.setScene(scene);

        // Making the primary stage (window) visible to the user.
        PrimaryStage.show();
    }

    // The 'main' method is the entry point for a regular Java program.
    // It launches the JavaFX application using 'Application.launch()'.
    public static void main(String[] args)
    {
        // Launches the JavaFX application by calling 'launch' from the 'Application' class.
        // The 'launch' method will call the 'start' method internally when the application starts.
        Application.launch(args);
    }
}
