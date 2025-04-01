package com.example.passwords;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane P = new GridPane();
        VBox Box1 = new VBox();
        Box1.setPrefSize(700,700);
        VBox Box = new VBox();
        HBox HBox1 = new HBox();
        Label LBL1 = new Label("Login");
        Label LBL2 = new Label("Please enter your email");
        TextField TXT1 = new TextField();
        Label LBL3 = new Label("Please enter your password");
        PasswordField PF = new PasswordField();
        Button BTN1 = new Button("Login");
        HBox1.getChildren().add(BTN1);
        HBox1.setAlignment(Pos.BOTTOM_RIGHT);
        Box.getChildren().addAll(LBL1,LBL2,TXT1,LBL3,PF,HBox1);
        Box1.getChildren().add(Box);
        Box1.setAlignment(Pos.CENTER);
        P.add(Box1,0,1);
        Scene scene = new Scene(Box1, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}