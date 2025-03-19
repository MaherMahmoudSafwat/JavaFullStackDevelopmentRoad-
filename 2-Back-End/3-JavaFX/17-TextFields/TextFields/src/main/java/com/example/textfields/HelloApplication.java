package com.example.textfields;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException
    {
        TextField Txt = new TextField("Type Here");
        String S = Txt.getText();
        Font Fnt = new Font(30);
        Fnt = Font.font("ink free",30);
        Txt.setFont(Fnt);
        /*
        Pane P = new Pane();
        P.getChildren().add(Txt);
        Scene scene = new Scene(P, 320, 240);
        */
        Scene scene = new Scene(Txt, 320, 240);
        System.out.println(S);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}