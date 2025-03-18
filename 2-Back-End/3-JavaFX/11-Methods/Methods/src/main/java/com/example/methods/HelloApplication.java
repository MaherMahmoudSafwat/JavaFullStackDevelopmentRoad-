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
        Button BTN1 = new Button("Click");
        Button BTN2 = new Button("Click");
        Button BTN3 = new Button("Click");
        HBox Box = new HBox();
        //To control the Dimensions of the Buttons.
        BTN1.setTranslateY(90);
        BTN1.setTranslateX(30);

        BTN2.setScaleX(1.5);
        BTN2.setScaleY(1.7);

        BTN3.setRotate(45);
        Box.getChildren().addAll(BTN1,BTN2,BTN3);
        Scene scene = new Scene(Box, 320, 320);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}