package com.example.radiobuttons;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        RadioButton RD1 = new RadioButton("Radio 1");
        RadioButton RD2 = new RadioButton("Radio 2");
        RadioButton RD3 = new RadioButton("Radio 3");
        RadioButton RD4 = new RadioButton("Radio 4");

        RD1.setSelected(true);

        ToggleGroup Group1 = new ToggleGroup();
        RD1.setToggleGroup(Group1);
        RD2.setToggleGroup(Group1);
        RD3.setToggleGroup(Group1);
        RD4.setToggleGroup(Group1);

        VBox Box = new VBox(10);
        Box.getChildren().addAll(RD1,RD2,RD3,RD4);
        Scene scene = new Scene(Box, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}