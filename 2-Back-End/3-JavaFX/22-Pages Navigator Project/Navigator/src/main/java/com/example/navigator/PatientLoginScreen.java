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

public class PatientLoginScreen
{
        private Button BtnGoToBackScreen = new Button("Back");
        private Button BtnToGoNextScreen = new Button("Next");
        private TextField Txt1 = new TextField();
        private TextField Txt2 = new TextField();
        private Label LBL1 = new Label("Patient Login Page.");
        private Label LBL2 = new Label("Please enter your email.");
        private Label LBL3 = new Label("Please enter your Password.");
        private Button BtnToGoSignUp = new Button("Don't have an account ? Sign Up.");
        private Label Message = new Label("");
        private VBox Box = new VBox();

        private static Pane P1;

        // Static block to initialize the green pane
        static {
        P1 = new Pane();
        P1.setPrefSize(300, 700);
        P1.setStyle("-fx-background-color: LimeGreen");
    }

        public static Scene PatientLoginMenu(Stage stage) {
        PatientLoginScreen PLS = new PatientLoginScreen();
        return PLS.CreateSceneScreen(stage);
    }

        private Scene CreateSceneScreen(Stage stage) {
        HandleUIComponents();
        // Create panes
        Pane P2 = new Pane();
        Pane P3 = new Pane();

        P1.getChildren().add(LBL1);

        //Set up P2 (right pane with VBox)
        P2.getChildren().add(Box);

        // Set up P3 (main container for P1, P2, and the button)
        P3.getChildren().addAll(P1, P2, BtnGoToBackScreen, BtnToGoNextScreen);

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
        BtnToGoNextScreen.setPrefSize(200, 50);
        BtnToGoNextScreen.setTranslateX(1111);
        BtnToGoNextScreen.setTranslateY(555);
        BtnToGoSignUp.setStyle(
                    "-fx-background-color: transparent; " + // Remove background
                            "-fx-border-color: transparent; " + // Remove border
                            "-fx-font-family: 'Arial'; " + // Match font family
                            "-fx-font-size: 14px; " + // Match font size
                            "-fx-font-weight: normal; " + // Match font weight
                            "-fx-text-fill: blue; " +
                            "-fx-padding: 0; " + // Remove padding
                            "-fx-focus-traversable: false;" // Remove focus border
            );
    }

        private void HandleLabelsCustom() {
        LBL1.setFont(new Font("Verdana", 30));
        LBL1.setTextFill(Color.BLACK);
        Message.setFont(new Font("Verdana", 30));
    }

        private void HandleTextFieldCustoms() {
        Txt1.setPrefSize(500, 60); // Increased size of the TextField
        Txt1.setFont(new Font("Verdana", 30)); // Increased font size
        Txt2.setPrefSize(500, 60); // Increased size of the TextField
        Txt2.setFont(new Font("Verdana", 30)); // Increased font size
    }

        private void HandleVBoxElements() {
        Box.getChildren().addAll(LBL1, Txt1, Txt2,BtnToGoSignUp,Message);
        Box.setTranslateX(650);
        Box.setTranslateY(100);
        Box.setSpacing(30);
    }

        private void HandleButtonsAction(Stage stage) {
        BtnGoToBackScreen.setOnAction(e -> {
            Scene scene = UserStatusOptionsScreen.Login(stage);
            stage.setScene(scene);
        });
        BtnToGoNextScreen.setOnAction(e -> {
            if (Txt1.getText().isEmpty()) {
                Message.setTextFill(Color.RED);
                Message.setText("Invalid email or password");
            } else {
                AdminPasswordBusinessLogic.ChangeAdminPassword(Txt1.getText());
                Message.setTextFill(Color.GREEN);
                Message.setText("Password has been changed successfully.");
            }
        });
    }
}