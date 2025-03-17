package com.example.javafxbasicsproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException
    {
        Button BTN = new Button("Sign in");
        Scene scene = new Scene(BTN,500,800);

        stage.setTitle("Sign in Project مشروع موقع تسجيلى");
        stage.setScene(scene);
        stage.show();
        //To set the size of the window to make it not resizable or resizable.
        stage.setResizable(true);
    }

    public static void main(String[] args)
    {
        launch();
    }
}