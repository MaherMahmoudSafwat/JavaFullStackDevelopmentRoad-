package com.example.thebuttonsmethods;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException
    {
        Button BTN1 = new Button("Click Me One");
        Button BTN2 = new Button("Click Me Two");
        Button BTN3 = new Button("Click Me Three");
        VBox Box = new VBox(50);
        int Number = Integer.parseInt(JOptionPane.showInputDialog("Please Enter Your Age"));
        if(Number > 15)
        {
            BTN1.setText("Your Age is Greater than 15");
            Box.getChildren().addAll(BTN1,BTN2,BTN3);
        }
        else if(Number < 15)
        {
            BTN2.setVisible(false);
            Box.getChildren().addAll(BTN1,BTN2,BTN3);
        }
        else if(Number == 15)
        {
            BTN3.setDisable(true);
            Box.getChildren().addAll(BTN1,BTN2,BTN3);
        }
        Scene scene = new Scene(Box,500,700);
        stage.setTitle("Test Example Button");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}