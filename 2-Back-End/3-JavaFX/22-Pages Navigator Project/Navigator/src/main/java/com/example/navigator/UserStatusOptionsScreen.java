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
    private static final Label LBL1 = new Label("User\nStatus ");
    private static final Label LBL2 = new Label("Welcome to the hospital full project\n\t  Please select our status");
    private static final RadioButton RDR1 = new RadioButton("Admin Staff User.");
    private static final RadioButton RDR2 = new RadioButton("Patient.");
    private static final RadioButton RDR3 = new RadioButton("Doctor.");
    private static final VBox Box = new VBox();
    private static final ToggleGroup TGP = new ToggleGroup();
    private static final Button BtnToGoNext = new Button("Next");
    private static final Label Message = new Label("");

    public static Scene Login(Stage stage) {
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

        // Set up P3 (main container for P1, P2, and the button)
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

    private static void InitializeUIComponents() {
        HandleLabelsText();
        HandleElementsInsideTheVBox();
        HandleNextButton();
    }

    private static void HandleLabelsText() {
        LBL1.setFont(new Font("Verdana", 50));
        LBL1.setTextFill(Color.rgb(0, 0, 0)); // Equivalent to Color.BLACK
        LBL1.setTranslateX(50);
        LBL1.setTranslateY(250);

        LBL2.setFont(new Font("Verdana", 30));
        LBL2.setTextFill(Color.rgb(0, 0, 0)); // Equivalent to Color.BLACK

        Message.setTextFill(Color.RED);
    }

    private static void HandleElementsInsideTheVBox() {
        RDR1.setPrefSize(300, 50);
        RDR1.setStyle("-fx-font-size: 15px; -fx-padding: 10px;");
        RDR1.setToggleGroup(TGP);

        RDR2.setPrefSize(300, 50);
        RDR2.setStyle("-fx-font-size: 15px; -fx-padding: 10px;");
        RDR2.setToggleGroup(TGP);

        RDR3.setPrefSize(300, 50);
        RDR3.setStyle("-fx-font-size: 15px; -fx-padding: 10px;");
        RDR3.setToggleGroup(TGP);

        Box.getChildren().addAll(LBL2, RDR1, RDR2, RDR3, Message);
        Box.setTranslateX(650);
        Box.setTranslateY(100);
    }

    private static void HandleNextButton() {
        BtnToGoNext.setPrefSize(200, 50);
        BtnToGoNext.setTranslateX(1111);
        BtnToGoNext.setTranslateY(555);
    }

    private static void HandleButtonAction(Stage stage) {
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