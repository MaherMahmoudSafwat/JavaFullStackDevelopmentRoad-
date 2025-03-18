package com.example.colors;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Button BTN1 = new Button();
        BTN1.setText("Click Me Button");
        BTN1.setPrefSize(150,150);
        //To change the colors texts inside the Buttons.
        Color color1 = new Color(0.5,0.5,0.5,1);
        BTN1.setTextFill(color1);
        Color color2 = Color.color(0.1,0.1,0.1,0.5);
        BTN1.setTextFill(color2);
        Color color3 = Color.rgb(83,255,0,0.9);
        BTN1.setTextFill(color3);
        Color color4 = Color.MAGENTA;
        BTN1.setTextFill(color4);
        //To change the background color of the Button.
        BTN1.setStyle("-fx-background-color: red;");
        HBox Box = new HBox();
        Box.getChildren().add(BTN1);
        Scene scene = new Scene(Box,800,800);
        //To change the color of the entire Scene.
        scene.setFill(Color.BLACK);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY); // Borderless window, remove title bar
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}