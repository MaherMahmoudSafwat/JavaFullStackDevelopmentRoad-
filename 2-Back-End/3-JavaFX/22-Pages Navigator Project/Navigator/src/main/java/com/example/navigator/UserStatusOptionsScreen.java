package com.example.navigator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UserStatusOptionsScreen {
    // Remove static variables for UI components
    private Label LBL1;
    private Label LBL2;
    private RadioButton RDR1;
    private RadioButton RDR2;
    private RadioButton RDR3;
    private VBox Box;
    private ToggleGroup TGP;
    private Button BtnToGoNext;
    private Label Message;

    public static Scene Login(Stage stage) {
        // Create a new instance of UserStatusOptionsScreen
        UserStatusOptionsScreen userStatusOptionsScreen = new UserStatusOptionsScreen();
        return userStatusOptionsScreen.createScene(stage);
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
        P1.getChildren().add(LBL1);

        // Set up P2 (right pane with VBox)
        P2.getChildren().add(Box);

        // Set up P3 (main container for P1 and P2)
        P3.getChildren().addAll(P1, P2, BtnToGoNext);

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
        P2.prefWidthProperty().bind(P3.widthProperty().multiply(0.7)); // Adjust multiplier as needed
        P2.prefHeightProperty().bind(P3.heightProperty());

        // Set up button action
        HandleButtonAction(stage);

        // Create the scene and return it
        return new Scene(root, 1200, 800); // Set the scene size
    }

    private void InitializeUIComponents() {
        // Initialize UI components as instance variables
        LBL1 = new Label("User\nStatus ");
        LBL2 = new Label("Welcome to the hospital full project\n\t  Please select your status");
        RDR1 = new RadioButton("Admin Staff User.");
        RDR1.setFont(new Font("verdena",30));
        RDR2 = new RadioButton("Patient.");
        RDR2.setFont(new Font("verdena",30));
        RDR3 = new RadioButton("Doctor.");
        RDR3.setFont(new Font("verdena",30));
        Box = new VBox();
        TGP = new ToggleGroup();
        BtnToGoNext = new Button("Next");
        Message = new Label("");

        HandleLabelsText();
        HandleElementsInsideTheVBox();
        HandleNextButton();
    }

    private void HandleLabelsText() {
        LBL1.setFont(new Font("Verdana", 50));
        LBL1.setTextFill(Color.rgb(0, 0, 0)); // Equivalent to Color.BLACK
        LBL1.setTranslateX(50);
        LBL1.setTranslateY(250);

        LBL2.setFont(new Font("Verdana", 30));
        LBL2.setTextFill(Color.rgb(0, 0, 0)); // Equivalent to Color.BLACK

        Message.setTextFill(Color.RED);
    }

    private void HandleElementsInsideTheVBox() {
        // Clear the VBox before adding new children
        Box.getChildren().clear();

        // Add the children to the VBox
        Box.getChildren().addAll(LBL2, RDR1, RDR2, RDR3, Message);
        Box.setTranslateX(650);
        Box.setTranslateY(100);
        Box.setSpacing(30);
    }

    private void HandleNextButton() {
        BtnToGoNext.setPrefSize(200, 50);
        BtnToGoNext.setTranslateX(1111);
        BtnToGoNext.setTranslateY(555);
    }

    private void HandleButtonAction(Stage stage) {
        BtnToGoNext.setOnAction(e -> {
            if (RDR1.isSelected()) {
                Scene scene = SignInAdmin.AdminLoginScreen(stage);
                stage.setScene(scene);
            } else if (RDR2.isSelected()) {
                Scene scene = PatientLoginScreen.Login(stage);
                stage.setScene(scene);
            } else if (RDR3.isSelected()) {
                Scene scene = DoctorLoginScreen.Login(stage);
                stage.setScene(scene);
            } else {
                Message.setText("Choice Not Found, Please choose your status");
            }
        });
    }
}