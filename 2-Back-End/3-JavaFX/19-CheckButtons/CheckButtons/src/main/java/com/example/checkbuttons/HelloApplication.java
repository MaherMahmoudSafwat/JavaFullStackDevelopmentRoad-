package com.example.checkbuttons;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        CheckBox CheckBox1 = new CheckBox("1");
        CheckBox CheckBox2 = new CheckBox("2");
        CheckBox CheckBox3 = new CheckBox("3");
        CheckBox CheckBox4 = new CheckBox("4");

        VBox Pane = new VBox();
        Pane.getChildren().addAll(CheckBox1,CheckBox2,CheckBox3,CheckBox4);
        Scene scene = new Scene(Pane, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}