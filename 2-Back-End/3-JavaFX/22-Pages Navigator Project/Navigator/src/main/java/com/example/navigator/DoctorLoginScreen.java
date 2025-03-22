package com.example.navigator;


import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DoctorLoginScreen
{
    public static Scene Login(Stage stage)
    {
        Pane P1 = new Pane();
        Pane P2 = new Pane();
        Pane P3 = new Pane();

        P1.setPrefSize(300, 700);
        P1.setStyle("-fx-background-color: LimeGreen");
        /*  P1.getChildren().add();
        P2.getChildren().add();*/
        // Add P1 and P2 to P3

        P3.getChildren().addAll(P1,P2);
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
}
