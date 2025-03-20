package com.example.registrationformexampledemoproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        // Create an instance of Class1 and set its scene initially
        Class1 class1 = new Class1();
        Scene scene1 = class1.createFirstScene(stage); // Get the first scene

        // Set the initial scene to Class1 scene
        stage.setTitle("Class 1");
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
