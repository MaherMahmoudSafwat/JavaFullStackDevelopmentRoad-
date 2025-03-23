package com.example.navigator;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChangeAdminPasswordMenu {
    private Button BtnGoToBackScreen = new Button("Back");
    private Button BtnToChange = new Button("Change");
    private Label LBL1 = new Label("Please Change your admin password.");
    private Label LBL2 = new Label("Change admin \n password");
    private Label Message = new Label("");
    private TextField Txt1 = new TextField("");
    private VBox Box = new VBox();

    private static Pane P1;

    // Static block to initialize the green pane
    static {
        P1 = new Pane();
        P1.setPrefSize(300, 700);
        P1.setStyle("-fx-background-color: LimeGreen");
    }

    public static Scene AdminChangePasswordMenuScreen(Stage stage) {
        ChangeAdminPasswordMenu CAPM = new ChangeAdminPasswordMenu();
        return CAPM.CreateSceneScreen(stage);
    }

    private Scene CreateSceneScreen(Stage stage) {
        HandleUIComponents();
        // Create panes
        Pane P2 = new Pane();
        Pane P3 = new Pane();

        P1.getChildren().add(LBL2);

        //Set up P2 (right pane with VBox)
        P2.getChildren().add(Box);

        // Set up P3 (main container for P1, P2, and the button)
        P3.getChildren().addAll(P1, P2, BtnGoToBackScreen, BtnToChange);

        // Create AnchorPane and add P3 to it
        AnchorPane root = new AnchorPane();
        root.getChildren().add(P3);

        // Bind P3's size to the root AnchorPane's size
        AnchorPane.setTopAnchor(P3, 0.0);
        AnchorPane.setBottomAnchor(P3, 0.0);
        AnchorPane.setLeftAnchor(P3, 0.0);
        AnchorPane.setRightAnchor(P3, 0.0);

        // Bind P1's width and height to P3's width and height
        P1.prefWidthProperty().bind(P3.widthProperty().multiply(0.25)); // Adjust multiplier as needed
        P1.prefHeightProperty().bind(P3.heightProperty());

        // Bind P2's width and height to P3's width and height
        P2.prefWidthProperty().bind(P3.widthProperty().multiply(0.7)); // Adjust multiplier as needed
        P2.prefHeightProperty().bind(P3.heightProperty());

        HandleButtonsAction(stage);
        // Create the scene and return it
        return new Scene(root, 1200, 800); // Set the scene size
    }

    private void HandleUIComponents() {
        HandleLabelsCustom();
        HandleButtonsScreen();
        HandleTextFieldCustoms();
        HandleVBoxElements();
    }

    private void HandleButtonsScreen() {
        BtnGoToBackScreen.setPrefSize(200, 50);
        BtnGoToBackScreen.setTranslateX(450);
        BtnGoToBackScreen.setTranslateY(555);
        BtnToChange.setPrefSize(200, 50);
        BtnToChange.setTranslateX(1111);
        BtnToChange.setTranslateY(555);
    }

    private void HandleLabelsCustom() {
        LBL1.setFont(new Font("Verdana", 30));
        LBL1.setTextFill(Color.BLACK);
        Message.setFont(new Font("Verdana", 30));
        Message.setTextFill(Color.GREEN);
        LBL2.setFont(new Font("Verdana", 30));
        LBL2.setTranslateX(50);
        LBL2.setTranslateY(250);
    }

    private void HandleTextFieldCustoms() {
        Txt1.setPrefSize(500, 60); // Increased size of the TextField
        Txt1.setFont(new Font("Verdana", 30)); // Increased font size
    }

    private void HandleVBoxElements() {
        Box.getChildren().addAll(LBL1, Txt1, Message);
        Box.setTranslateX(650);
        Box.setTranslateY(100);
        Box.setSpacing(30);
    }

    private void HandleButtonsAction(Stage stage) {
        BtnGoToBackScreen.setOnAction(e -> {
            Scene scene = AdminMainMenuScreen.AdminMainMenu(stage);
            stage.setScene(scene);
        });
        BtnToChange.setOnAction(e -> {
            if (Txt1.getText().isEmpty()) {
                Message.setTextFill(Color.RED);
                Message.setText("Password can't be empty.");
            } else {
                AdminPasswordBusinessLogic.ChangeAdminPassword(Txt1.getText());
                Message.setTextFill(Color.GREEN);
                Message.setText("Password has been changed successfully.");
            }
        });
    }
}