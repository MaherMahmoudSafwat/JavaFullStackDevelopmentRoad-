package com.example.labels;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Button BTN1 = new Button("Click");
        Label label = new Label("This is a label");
        label.setTextFill(Color.RED);
        label.setTextAlignment(TextAlignment.CENTER);
        Label Lbl = new Label();
        Lbl.setText("This is a Label");
        Lbl.setTranslateX(30);
        Lbl.setTranslateY(90);
        Pane P = new Pane();
        P.getChildren().addAll(BTN1,label,Lbl);
        Scene scene = new Scene(P, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}