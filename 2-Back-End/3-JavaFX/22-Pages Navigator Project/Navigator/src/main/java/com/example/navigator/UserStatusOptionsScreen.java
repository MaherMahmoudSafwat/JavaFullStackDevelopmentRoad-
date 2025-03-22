package com.example.navigator;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

public class UserStatusOptionsScreen {
    private static Label LBL1 = new Label("User\nStatus ");
    private static Label LBL2 = new Label("Welcome to the hospital full project\n\t  Please select our status");
    private static RadioButton RDR1 = new RadioButton("Admin Staff User.");
    private static RadioButton RDR2 = new RadioButton("Patient.");
    private static RadioButton RDR3 = new RadioButton("Doctor.");
    private static VBox Box = new VBox();

    public static Scene Login(Stage stage) {
        // Create panes
        Pane P1 = new Pane();
        Pane P2 = new Pane();
        Pane P3 = new Pane();

        HandleLabelsText();
        // Set initial size and style for P1
        HandleElementsInsideTheVBox();
        P1.setPrefSize(300, 700);
        P1.setStyle("-fx-background-color: LimeGreen");
        P1.getChildren().add(LBL1);
        P2.getChildren().add(Box);
        // Add P1 and P2 to P3
        P3.getChildren().addAll(P1, P2);

        // Create AnchorPane and add P3 to it
        AnchorPane root = new AnchorPane();
        root.getChildren().add(P3);

        // Bind P3's size to the root AnchorPane's size
        AnchorPane.setTopAnchor(P3, 0.0);
        AnchorPane.setBottomAnchor(P3, 0.0);
        AnchorPane.setLeftAnchor(P3, 0.0);
        AnchorPane.setRightAnchor(P3, 0.0);

        // Bind P1's width and height to P3's width and height
        P1.prefWidthProperty().bind(P3.widthProperty().multiply(0.3)); // Adjust multiplier as needed
        P1.prefHeightProperty().bind(P3.heightProperty());

        // Bind P2's width and height to P3's width and height
        P2.prefWidthProperty().bind(P3.widthProperty().multiply(0.3)); // Adjust multiplier as needed
        P2.prefHeightProperty().bind(P3.heightProperty());
        return new Scene(root);
    }
    private static void HandleLabelsText()
    {
        LBL1.setFont(new Font("Verdena",50));
        LBL1.setTextFill(Color.rgb(0, 0, 0)); // Equivalent to Color.BLACK
        LBL1.setTranslateX(50);
        LBL1.setTranslateY(250);
        LBL2.setFont(new Font("Verdena",30));
        LBL2.setTextFill(Color.rgb(0, 0, 0)); // Equivalent to Color.BLACK
        /*LBL2.setTranslateX(650);
        LBL2.setTranslateY(100);*/
    }
    private static void HandleElementsInsideTheVBox()
    {
        RDR1.setPrefSize(300,50);
        RDR2.setPrefSize(300,50);
        RDR3.setPrefSize(300,50);
        Box.getChildren().addAll(LBL2,RDR1,RDR2,RDR3);
        Box.setTranslateX(650);
        Box.setTranslateY(100);
    }
}