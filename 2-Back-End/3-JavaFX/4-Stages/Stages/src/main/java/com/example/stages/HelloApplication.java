package com.example.stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Button BTN = new Button("Click me");
        Scene scene = new Scene(BTN, 400, 500);
        stage.setTitle("Project");
        stage.setScene(scene);
        stage.show();

        Stage S = new Stage();
        S.setTitle("Clicker");
        S.setScene(new Scene(new Button("Playable"),500,700));
        S.show();

        Stage Windows = new Stage();
        Windows.setTitle("Default");
        Windows.setScene(new Scene(new Button("Set Okay Button")));
        Windows.show();
    }
    public static void main(String[] args) {
        launch();
    }
}