package com.example.thedatepicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.print.attribute.standard.DateTimeAtProcessing;
import java.io.IOException;
import java.time.LocalDate;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane P = new Pane();
        DatePicker DP = new DatePicker(LocalDate.now());
        P.getChildren().addAll(DP);
        Scene scene = new Scene(P, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}