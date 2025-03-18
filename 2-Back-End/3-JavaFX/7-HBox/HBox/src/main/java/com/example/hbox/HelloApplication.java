package com.example.hbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Button BTN1 = new Button("B1");
        Button BTN2 = new Button("B2");
        Button BTN3 = new Button("B3");

        HBox Box = new HBox(30);
        /*
        Box.getChildren().add(BTN1);
        Box.getChildren().add(BTN2);
        Box.getChildren().add(BTN3);
        */

        Box.getChildren().addAll(BTN1,BTN2,BTN3);
        Scene scene = new Scene(Box, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}