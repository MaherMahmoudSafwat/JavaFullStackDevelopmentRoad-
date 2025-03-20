package com.example.registrationformexampledemoproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Class1 {

    TextField Txt1 = new TextField();
    TextField Txt2 = new TextField();
    TextField Txt3 = new TextField();
    Label Lbl1 = new Label("Please enter your name");
    Label Lbl2 = new Label("Please enter your age");
    Label Lbl3 = new Label("Please enter your email");
    VBox P = new VBox(10); // Set vertical spacing between components
    TextField[] TxtFields = new TextField[]{Txt1, Txt2, Txt3};

    // Method to create the first scene
    public Scene createFirstScene(Stage stage) {
        P = TakeUserInfo(); // Prepare user info layout
        Button BTN = new Button("Go to Class 2");
        BTN.setOnAction(e -> {
            // Switch to Class2 when button is clicked
            Class2 class2 = new Class2();
            stage.setScene(class2.createSecondScene(stage, TxtFields)); // Set new scene
        });

        P.getChildren().add(BTN); // Add button to VBox
        return new Scene(P, 300, 250); // Set size of the scene
    }

    // Method to handle user input and layout
    public VBox TakeUserInfo() {
        String S1 = ""; // Default text for TextFields

        // Set text for TextFields
        Txt1.setText(S1);
        Txt2.setText(S1);
        Txt3.setText(S1);

        // Set preferred size for TextFields
        Txt1.setMaxWidth(200);   // Prevent resizing horizontally
        Txt1.setMinWidth(200);   // Prevent resizing horizontally

        Txt2.setPrefWidth(200);  // Width: 200
        Txt2.setPrefHeight(30);  // Height: 30
        Txt2.setMaxWidth(200);   // Prevent resizing horizontally
        Txt2.setMinWidth(200);   // Prevent resizing horizontally

        Txt3.setPrefWidth(200);  // Width: 200
        Txt3.setPrefHeight(30);  // Height: 30
        Txt3.setMaxWidth(200);   // Prevent resizing horizontally
        Txt3.setMinWidth(200);   // Prevent resizing horizontally

        // Add labels and text fields to VBox
        P.getChildren().add(Lbl1);
        P.getChildren().add(Txt1);
        P.getChildren().add(Lbl2);
        P.getChildren().add(Txt2);
        P.getChildren().add(Lbl3);
        P.getChildren().add(Txt3);

        // Set VBox alignment to center and spacing between components
        P.setPrefWidth(300);  // Set preferred width for VBox
        P.setPrefHeight(250); // Set preferred height for VBox

        return P;
    }
}
