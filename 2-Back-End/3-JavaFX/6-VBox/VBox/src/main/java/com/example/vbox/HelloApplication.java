package com.example.vbox;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;//Pane - Layout - Container.
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Button BTN1 = new Button("First Button One");
        Button BTN2 = new Button("Second Button Two");
        Button BTN3 = new Button("Third Button Three");

        //VBox is used to add Bunch and group of containers inside it in a veritcally way.
        VBox Box1 = new VBox();
        Box1.getChildren().add(BTN1);
        Box1.getChildren().add(BTN2);
        Box1.getChildren().add(BTN3);
        Scene scene = new Scene(Box1,320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}