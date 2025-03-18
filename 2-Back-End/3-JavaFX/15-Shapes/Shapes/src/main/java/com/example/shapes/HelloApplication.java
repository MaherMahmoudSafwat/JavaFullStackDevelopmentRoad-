package com.example.shapes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Line L1 = new Line(100,200,300,500);
        L1.setStroke(Color.BISQUE);
        L1.setStrokeWidth(15);
        Circle C1 = new Circle(100,111,90);
        C1.setStroke(Color.GREEN);
        C1.setStrokeWidth(21);
        C1.setFill(Color.GRAY);
        Polygon P1 = new Polygon();
        P1.setFill(Color.ORANGE);
        P1.getPoints().addAll(150.0, 50.0, 100.0, 150.0, 200.0, 150.0);
        Rectangle R1 = new Rectangle(150,150,150,150);
        R1.setFill(Color.DIMGREY);
        R1.setStrokeWidth(150);
        R1.setArcHeight(123);
        R1.setArcHeight(179);
        Pane P = new Pane();
        P.getChildren().add(L1);
        P.getChildren().add(C1);
        P.getChildren().add(P1);
        P.getChildren().add(R1);
        Scene scene = new Scene(P, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}