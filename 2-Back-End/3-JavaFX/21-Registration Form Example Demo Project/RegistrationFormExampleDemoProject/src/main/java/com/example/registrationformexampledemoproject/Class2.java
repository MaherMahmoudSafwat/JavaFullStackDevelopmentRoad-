package com.example.registrationformexampledemoproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Class2 {

    Label Lbl1 = new Label("Please enter your name");
    Label Lbl2 = new Label("Please enter your age");
    Label Lbl3 = new Label("Please enter your email");
    // Method to create the second scene
    public Scene createSecondScene(Stage stage, TextField[]Txts) {
        ShowUserInfo(Txts);
        Button btn = new Button("Go to Class 1");
        btn.setOnAction(e -> {
            // Switch back to Class1 when button is clicked
            Class1 class1 = new Class1();
            stage.setScene(class1.createFirstScene(stage)); // Set previous scene
        });

        VBox root = new VBox();
        root.getChildren().addAll(Lbl1,Lbl2,Lbl3,btn);
        return new Scene(root, 300, 250);
    }
    void ShowUserInfo(TextField[]Txts)
    {
        Lbl1.setText(Txts[0].getText());
        Lbl2.setText(Txts[1].getText());
        Lbl3.setText(Txts[2].getText());
    }
}
