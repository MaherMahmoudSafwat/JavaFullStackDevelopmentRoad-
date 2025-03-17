package com.example.myfirstprogramonjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class HelloApplication extends Application {  // HelloApplication class extends the JavaFX Application class.
    // The start() method is overridden to set up the UI for the application.
    @Override
    public void start(Stage stage) throws IOException {  // This method is automatically called when the JavaFX application starts.

        // FXMLLoader is used to load the FXML file that defines the user interface.
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // `hello-view.fxml` is an external file that contains the structure of the user interface.
        // It is an XML file with JavaFX components (buttons, labels, etc.) defined in it.

        // Load the FXML file and create a Scene object (320px by 240px size).
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // Set the title of the window (Stage).
        stage.setTitle("Hello!");

        // Set the Scene (UI components) for the Stage (window).
        stage.setScene(scene);

        // Show the Stage (i.e., display the window).
        stage.show();
    }

    // Main method to launch the JavaFX application.
    public static void main(String[] args) {
        launch();  // Launch the JavaFX application. This triggers the start() method.
    }
    /*Using the Application Class (Traditional Way):

    How: This is the approach we’ve used above. You extend the Application class and override the start() method to set up your UI and logic.
    Where it fits: It’s the standard method for creating most JavaFX applications.
    Code Structure: You define your start() method, create a Scene, and display it within a Stage.
    Using FXML and Controller:

    How: FXML files are used to define the layout of the UI (UI components like buttons, text fields, etc.), and then you
    associate a Controller class that handles the event logic for the UI. The FXML file is loaded by an FXMLLoader.
    Where it fits: This is the most common and modular way to develop JavaFX applications, especially if the UI is complex or needs
    to follow MVC principles.
    Code Structure: You separate the UI and logic into different files (FXML and Controller).
    Using Scene Builder (GUI Tool):

    How: Scene Builder is a drag-and-drop GUI tool that generates FXML files. You can design your UI visually, then link the generated
    FXML file to a Java controller class.
    Where it fits: Scene Builder is useful when you want a graphical interface for designing UIs, which helps speed up the process.
        It’s a good option if you prefer not to write FXML by hand.
    Code Structure: After designing the UI in Scene Builder, you load the FXML file in your Java code (using FXMLLoader), just
    like in the second approach.*/
}
