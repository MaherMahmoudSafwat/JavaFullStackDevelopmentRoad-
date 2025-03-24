package com.example.navigator;

import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PatientSignUpScreen implements SignUp {
    private Button BtnGoToBackScreen = new Button("Back");
    private Button BtnToCreateANewAccount = new Button("Create");
    private TextField Txt1 = new TextField();
    private TextField Txt2 = new TextField();
    private TextField Txt3 = new TextField();
    private TextField Txt4 = new TextField();
    private TextField Txt5 = new TextField();
    private TextField Txt6 = new TextField();
    private TextField Txt7 = new TextField();
    private ComboBox<String> Genders = new ComboBox<>();
    private TextField Txt8 = new TextField(); // TextField for Diseases
    private Label LBL1 = new Label("Patient SignUp Page.");
    private Label MSG = new Label("Sign Up");
    private Label LBL2 = new Label("Please enter your first name.");
    private Label LBL3 = new Label("Please enter your last name.");
    private Label LBL4 = new Label("Please enter your email.");
    private Label LBL5 = new Label("Please enter your password");
    private Label LBL6 = new Label("Please enter your PhoneNumber");
    private Label LBL7 = new Label("Please enter your Age");
    private Label LBL8 = new Label("Please select your gender.");
    private Label LBL9 = new Label("Please enter your Diseases.");
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
        PatientSignUpScreen PSUS = new PatientSignUpScreen();
        return PSUS.UserSignUpAccount(stage);
    }

    public Scene UserSignUpAccount(Stage stage) {
        HandleUIComponents();

        // Create panes
        Pane P2 = new VBox(); // Use VBox for the content area
        Pane P3 = new Pane();

        P1.getChildren().add(LBL1);

        // Set up P2 (right pane with VBox)
        P2.getChildren().add(Box);

        // Create ScrollPane and add P2 to it
        ScrollPane SLP = new ScrollPane(P2);
        SLP.setFitToWidth(true); // Ensure the content fits the width
        SLP.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Always show vertical scrollbar

        // Set up P3 (main container for P1, P2, and the buttons)
        P3.getChildren().addAll(P1, SLP, BtnGoToBackScreen, BtnToCreateANewAccount);

        // Create AnchorPane and add P3 to it
        AnchorPane root = new AnchorPane();
        root.getChildren().add(P3);

        // Bind P3's size to the root AnchorPane's size
        AnchorPane.setTopAnchor(P3, 0.0);
        AnchorPane.setBottomAnchor(P3, 0.0);
        AnchorPane.setLeftAnchor(P3, 0.0);
        AnchorPane.setRightAnchor(P3, 0.0);

        // Bind P1's width and height to P3's width and height
        P1.prefWidthProperty().bind(P3.widthProperty().multiply(0.25)); // Green pane takes 25% of the width
        P1.prefHeightProperty().bind(P3.heightProperty());

        // Bind P2's width and height to P3's width and height
        P2.prefWidthProperty().bind(P3.widthProperty().multiply(0.75)); // Content area takes 75% of the width
        P2.prefHeightProperty().bind(P3.heightProperty());

        // Position the buttons at the bottom
        AnchorPane.setBottomAnchor(BtnGoToBackScreen, 20.0);
        AnchorPane.setLeftAnchor(BtnGoToBackScreen, 350.0); // Positioned on the left side of the content area
        AnchorPane.setBottomAnchor(BtnToCreateANewAccount, 20.0);
        AnchorPane.setRightAnchor(BtnToCreateANewAccount, 20.0); // Positioned on the right side of the content area

        HandleButtonsAction(stage);

        // Create the scene and return it
        return new Scene(root, 1200, 800); // Set the scene size
    }

    private void HandleUIComponents() {
        HandleLabelsCustom();
        HandleButtonsScreen();
        HandleTextFieldCustoms();
        HandleComboBoxSize();
        HandleVBoxElements();
    }

    private void HandleButtonsScreen() {
        BtnGoToBackScreen.setPrefSize(200, 50);
        BtnToCreateANewAccount.setPrefSize(200, 50);
    }

    private void HandleLabelsCustom() {
        LBL1.setFont(new Font("Verdana", 30));
        LBL1.setTextFill(Color.BLACK);
        LBL1.setTranslateX(50);
        LBL1.setTranslateY(250);
        MSG.setFont(new Font("Verdena", 15));
        MSG.setTextFill(Color.BLACK);
        LBL2.setFont(new Font("Verdana", 15));
        LBL2.setTextFill(Color.BLACK);
        LBL3.setFont(new Font("Verdana", 15));
        LBL3.setTextFill(Color.BLACK);
        LBL4.setFont(new Font("Verdana", 15));
        LBL4.setTextFill(Color.BLACK);
        LBL5.setFont(new Font("Verdana", 15));
        LBL5.setTextFill(Color.BLACK);
        LBL6.setFont(new Font("Verdana", 15));
        LBL6.setTextFill(Color.BLACK);
        LBL7.setFont(new Font("Verdana", 15));
        LBL7.setTextFill(Color.BLACK);
        LBL8.setFont(new Font("Verdana", 15));
        LBL8.setTextFill(Color.BLACK);
        LBL9.setFont(new Font("Verdana", 15));
        LBL9.setTextFill(Color.BLACK);
        Message.setFont(new Font("Verdana", 30));
    }

    private void HandleTextFieldCustoms() {
        Txt1.setPrefSize(350, 35); // Increased size of the TextField
        Txt1.setFont(new Font("Verdana", 15)); // Increased font size
        Txt2.setPrefSize(350, 35); // Increased size of the TextField
        Txt2.setFont(new Font("Verdana", 15)); // Increased font size
        Txt3.setPrefSize(350, 35); // Increased size of the TextField
        Txt3.setFont(new Font("Verdana", 15)); // Increased font size
        Txt4.setPrefSize(350, 35); // Increased size of the TextField
        Txt4.setFont(new Font("Verdana", 15)); // Increased font size
        Txt5.setPrefSize(350, 35); // Increased size of the TextField
        Txt5.setFont(new Font("Verdana", 15)); // Increased font size
        Txt6.setPrefSize(350, 35); // Increased size of the TextField
        Txt6.setFont(new Font("Verdana", 15)); // Increased font size
        Txt7.setPrefSize(350, 35); // Increased size of the TextField
        Txt7.setFont(new Font("Verdana", 15)); // Increased font size
        Txt8.setPrefSize(350, 35); // Increased size of the TextField
        Txt8.setFont(new Font("Verdana", 15)); // Increased font size
    }

    private void HandleComboBoxSize() {
        Genders.setPrefSize(350, 35);
    }

    private void HandleVBoxElements() {
        Box.getChildren().addAll(MSG, LBL2, Txt1, LBL3, Txt2, LBL4, Txt4, LBL5, Txt5,
                LBL6, Txt6, LBL7, Txt7, LBL8, Genders, LBL9, Txt8, Message);
        Box.setTranslateX(50); // Adjust the X position of the content
        Box.setTranslateY(50); // Adjust the Y position of the content
        Box.setSpacing(20); // Spacing between elements
    }

    private void HandleButtonsAction(Stage stage) {
        BtnGoToBackScreen.setOnAction(e -> {
            Scene scene = PatientLoginScreen.PatientLoginMenu(stage);
            stage.setScene(scene);
        });
    }
}