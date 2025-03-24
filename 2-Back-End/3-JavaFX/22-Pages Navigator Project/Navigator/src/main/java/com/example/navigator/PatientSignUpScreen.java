package com.example.navigator;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
    private TextField Txt8 = new TextField();
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

    private static Pane P1;

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

        // Main layout container
        BorderPane mainPane = new BorderPane();

        // Left green pane with proper padding
        VBox leftPane = new VBox(P1);
        leftPane.setStyle("-fx-padding: 20;");
        mainPane.setLeft(leftPane);

        // Center content with scrolling
        VBox contentBox = new VBox(10);
        contentBox.getChildren().addAll(MSG, LBL2, Txt1, LBL3, Txt2, LBL4, Txt4,
                LBL5, Txt5, LBL6, Txt6, LBL7, Txt7,
                LBL8, Genders, LBL9, Txt8, Message);
        contentBox.setStyle("-fx-padding: 20;");

        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // Bottom buttons
        HBox buttonBox = new HBox(20, BtnGoToBackScreen, BtnToCreateANewAccount);
        buttonBox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Combine scroll and buttons in VBox
        VBox centerBox = new VBox(scrollPane, buttonBox);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        mainPane.setCenter(centerBox);

        // Set preferred sizes
        BtnGoToBackScreen.setPrefSize(200, 50);
        BtnToCreateANewAccount.setPrefSize(200, 50);

        // Ensure green pane content is visible
        LBL1.setFont(new Font("Verdana", 24)); // Slightly smaller font
        LBL1.setTextFill(Color.BLACK);
        LBL1.setLayoutX(20);
        LBL1.setLayoutY(250);
        P1.getChildren().add(LBL1);

        HandleButtonsAction(stage);
        return new Scene(mainPane, 1200, 800);
    }

    private void HandleUIComponents() {
        HandleLabelsCustom();
        HandleTextFieldCustoms();
        HandleComboBoxSize();
    }

    private void HandleLabelsCustom() {
        MSG.setFont(new Font("Verdana", 15));
        MSG.setTextFill(Color.BLACK);

        Font labelFont = new Font("Verdana", 15);
        LBL2.setFont(labelFont);
        LBL3.setFont(labelFont);
        LBL4.setFont(labelFont);
        LBL5.setFont(labelFont);
        LBL6.setFont(labelFont);
        LBL7.setFont(labelFont);
        LBL8.setFont(labelFont);
        LBL9.setFont(labelFont);

        Message.setFont(new Font("Verdana", 30));
    }

    private void HandleTextFieldCustoms() {
        Font textFont = new Font("Verdana", 15);
        Txt1.setFont(textFont);
        Txt2.setFont(textFont);
        Txt3.setFont(textFont);
        Txt4.setFont(textFont);
        Txt5.setFont(textFont);
        Txt6.setFont(textFont);
        Txt7.setFont(textFont);
        Txt8.setFont(textFont);

        Txt1.setMaxWidth(350);
        Txt2.setMaxWidth(350);
        Txt3.setMaxWidth(350);
        Txt4.setMaxWidth(350);
        Txt5.setMaxWidth(350);
        Txt6.setMaxWidth(350);
        Txt7.setMaxWidth(350);
        Txt8.setMaxWidth(350);
    }

    private void HandleComboBoxSize() {
        Genders.setMaxWidth(350);
        Genders.getItems().addAll("1-Male","2-Female");
    }

    private void HandleButtonsAction(Stage stage) {
        BtnGoToBackScreen.setOnAction(e -> {
            Scene scene = PatientLoginScreen.PatientLoginMenu(stage);
            stage.setScene(scene);
        });
        BtnToCreateANewAccount.setOnAction(e->
        {
            if(Txt1.getText().isEmpty())
            {
                Message.setText("This field is required");
                VBox
            }
        });
    }
}