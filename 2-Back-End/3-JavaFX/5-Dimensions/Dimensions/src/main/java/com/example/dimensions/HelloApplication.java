package com.example.dimensions;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Button BTN = new Button("Click Me Okay");
        Scene scene = new Scene(BTN, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        System.out.println(scene.getWidth());
        System.out.println(scene.getHeight());
        System.out.println(stage.getWidth());
        System.out.println(stage.getHeight());
    }

    public static void main(String[] args) {
        launch();
    }
}