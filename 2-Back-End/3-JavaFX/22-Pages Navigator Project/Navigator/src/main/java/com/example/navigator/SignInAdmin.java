package com.example.navigator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignInAdmin {
    // Remove static variables for UI components
    private Label LBL1;
    private Label LBL2;
    private TextField Txt1;
    private Label Message;
    private VBox Box;
    private Button BtnToGoBack;
    private Button BtnToGoNext;

    public static Scene AdminLoginScreen(Stage stage) {
        // Create a new instance of SignInAdmin
        SignInAdmin signInAdmin = new SignInAdmin();
        return signInAdmin.createScene(stage);
    }

    private Scene createScene(Stage stage) {
        // Initialize UI components
        InitializeUIComponents();

        // Create panes
        Pane P1 = new Pane();
        Pane P2 = new Pane();
        Pane P3 = new Pane();

        // Set up P1 (left pane)
        P1.setPrefSize(300, 700);
        P1.setStyle("-fx-background-color: LimeGreen");
        P1.getChildren().add(LBL2);

        // Set up P2 (right pane with VBox and buttons)
        P2.getChildren().addAll(Box, BtnToGoBack, BtnToGoNext);

        // Set up P3 (main container for P1 and P2)
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
        P1.prefWidthProperty().bind(P3.widthProperty().multiply(0.25)); // 25% of P3's width
        P1.prefHeightProperty().bind(P3.heightProperty());

        // Bind P2's width and height to P3's width and height
        P2.prefWidthProperty().bind(P3.widthProperty().multiply(0.7)); // 70% of P3's width
        P2.prefHeightProperty().bind(P3.heightProperty());

        // Set up button actions
        HandleButtonsAction(stage);

        // Create the scene and return it
        return new Scene(root, 1200, 800); // Set the scene size
    }

    private void InitializeUIComponents() {
        // Initialize UI components as instance variables
        LBL1 = new Label("Enter your admin password");
        LBL2 = new Label("Admin\nPassword");
        Txt1 = new TextField();
        Message = new Label("");
        Box = new VBox();
        BtnToGoBack = new Button("Back");
        BtnToGoNext = new Button("Next");

        HandleLabelsCustom();
        HandleTextFieldCustoms();
        HandleVBoxElements();
        HandleButtonsShape();
    }

    private void HandleButtonsAction(Stage stage) {
        HandleBackButton(stage);
        HandleNextButton(stage);
    }

    private void HandleLabelsCustom() {
        LBL1.setFont(new Font("Verdana", 30));
        LBL2.setFont(new Font("Verdana", 50));
        LBL2.setTranslateX(50);
        LBL2.setTranslateY(250);
        Message.setFont(new Font("Verdana", 30));
        Message.setTextFill(Color.RED);
    }

    private void HandleTextFieldCustoms() {
        Txt1.setMaxWidth(700);
        Txt1.setMaxHeight(950);
        Txt1.setScaleY(1.5);
        Txt1.setFont(new Font("Verdana", 15));
    }

    private void HandleVBoxElements() {
        // Clear the VBox before adding new children
        Box.getChildren().clear();

        // Add the children to the VBox
        Box.getChildren().addAll(LBL1, Txt1, Message);
        Box.setTranslateX(650);
        Box.setTranslateY(100);
        Box.setSpacing(30);
    }

    private void HandleButtonsShape() {
        BtnToGoBack.setPrefSize(200, 50);
        BtnToGoBack.setTranslateX(450);
        BtnToGoBack.setTranslateY(555);
        BtnToGoNext.setPrefSize(200, 50);
        BtnToGoNext.setTranslateX(1111);
        BtnToGoNext.setTranslateY(555);
    }

    private void HandleBackButton(Stage stage) {
        BtnToGoBack.setOnAction(e -> {
            Scene scene = UserStatusOptionsScreen.Login(stage);
            stage.setScene(scene);
        });
    }

    private void HandleNextButton(Stage stage) {
        BtnToGoNext.setOnAction(e -> {
            if (AdminPasswordBusinessLogic.IsAdminPasswordCorrect(Txt1.getText())) {
                Scene scene = AdminMainMenuScreen.AdminMainMenu(stage);
                stage.setScene(scene);
            } else {
                Message.setText("Invalid password, Please try again.");
            }
        });
    }
}