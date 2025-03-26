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

public class UpdateDoctorPersonalInfo
{
    private Button BtnGoToBackScreen = new Button("Back");
    private Button BtnToUpdateScreen = new Button("Update");
    private TextField Txt1 = new TextField();
    private TextField Txt2 = new TextField();
    private TextField Txt3 = new TextField();
    private TextField Txt4 = new TextField();
    private Label LBL1 = new Label("Doctor Update \n Personal Page.");
    private Label LBL2 = new Label("Update Your Name.");
    private Label MSG1 = new Label("");
    private Label LBL3 = new Label("Update your Password.");
    private Label MSG2 = new Label("");
    private Label LBL4 = new Label("Confirm Your Password");
    private Label MSG3 = new Label("");
    private Label LBL5 = new Label("Update Your Phone Number");
    private Label MSG4 = new Label("");
    private Label Message = new Label("");
    private VBox Box = new VBox();

    private static Pane P1;

    // Static block to initialize the green pane
    static {
        P1 = new Pane();
        P1.setPrefSize(300, 700);
        P1.setStyle("-fx-background-color: LimeGreen");
    }
    public static Scene Login(Stage stage)
    {
        UpdateDoctorPersonalInfo UDPI = new UpdateDoctorPersonalInfo();
        return UDPI.CreateNewScene(stage);
    }
    private Scene CreateNewScene(Stage stage)
    {
        HandleUIComponents();
        // Create panes
        Pane P2 = new Pane();
        Pane P3 = new Pane();

        P1.getChildren().add(LBL1);

        //Set up P2 (right pane with VBox)
        P2.getChildren().add(Box);

        // Set up P3 (main container for P1, P2, and the button)
        P3.getChildren().addAll(P1, P2, BtnGoToBackScreen, BtnToUpdateScreen);

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
        BtnGoToBackScreen.setTranslateX(475);
        BtnGoToBackScreen.setTranslateY(590);
        BtnToUpdateScreen.setPrefSize(200, 50);
        BtnToUpdateScreen.setTranslateX(1111);
        BtnToUpdateScreen.setTranslateY(590);
    }

    private void HandleLabelsCustom() {
        LBL1.setFont(new Font("Verdana", 30));
        LBL1.setTextFill(Color.BLACK);
        LBL1.setTranslateX(50);
        LBL1.setTranslateY(250);
        LBL2.setFont(new Font("Verdana", 15));
        LBL2.setTextFill(Color.BLACK);
        LBL3.setFont(new Font("Verdana", 15));
        LBL3.setTextFill(Color.BLACK);
        LBL4.setFont(new Font("Verdana", 15));
        LBL4.setTextFill(Color.BLACK);
        LBL5.setFont(new Font("Verdana", 15));
        LBL5.setTextFill(Color.BLACK);
        MSG1.setFont(new Font("verdena",15));
        MSG1.setTextFill(Color.RED);
        MSG2.setFont(new Font("verdena",15));
        MSG2.setTextFill(Color.RED);
        MSG3.setFont(new Font("verdena",15));
        MSG3.setTextFill(Color.RED);
        MSG4.setFont(new Font("verdena",15));
        MSG4.setTextFill(Color.RED);
        Message.setFont(new Font("Verdana", 15));
    }

    private void HandleTextFieldCustoms() {
        Txt1.setPrefSize(300, 30); // Increased size of the TextField
        Txt1.setFont(new Font("Verdana", 15)); // Increased font size
        Txt2.setPrefSize(300, 30); // Increased size of the TextField
        Txt2.setFont(new Font("Verdana", 15)); // Increased font size
        Txt3.setPrefSize(300, 30); // Increased size of the TextField
        Txt3.setFont(new Font("Verdana", 15)); // Increased font size
        Txt4.setPrefSize(300, 30); // Increased size of the TextField
        Txt4.setFont(new Font("Verdana", 15)); // Increased font size
    }

    private void HandleVBoxElements() {
        Box.getChildren().addAll(LBL2, Txt1, MSG1, LBL3, Txt2, MSG2, LBL4, Txt3, MSG3, LBL5, Txt4, MSG4, Message);
        Box.setTranslateX(650);
        Box.setTranslateY(100);
        Box.setSpacing(9);
    }

    private void HandleButtonsAction(Stage stage) {
        // Clear all error messages first
        MSG1.setText("");
        MSG2.setText("");
        MSG4.setText("");
        Message.setText(""); // Clear success message too
        BtnGoToBackScreen.setOnAction(e -> {
            Scene scene = DoctorMainMenu.DoctorMainMenu(stage);
            stage.setScene(scene);
        });
        BtnToUpdateScreen.setOnAction(e->
        {
            boolean AllFieldsFilled = true;
            if (Txt1.getText().isEmpty()) {
                MSG1.setText("This field is required.");
                AllFieldsFilled = false;
            }
            if (Txt2.getText().isEmpty()) {
                MSG2.setText("This field is required.");
                AllFieldsFilled = false;
            }
            if (Txt3.getText().isEmpty()) {
                MSG3.setText("This field is required.");
                AllFieldsFilled = false;
            }
            if(!Txt2.getText().equals(Txt3.getText()))
            {
                MSG3.setTextFill(Color.RED);
                MSG3.setText("These Passwords don't match");
                AllFieldsFilled = false;
            }
            if (Txt4.getText().isEmpty()) {
                MSG4.setText("This field is required.");
                AllFieldsFilled = false;
            }
            if(AllFieldsFilled)
            {
                Doctor D = Doctor.GetDoctorData(Utility.LoginUsersEmail);
                D.setFullName(Txt1.getText());
                D.setPassword(Txt2.getText());
                D.setPhoneNumber(Txt4.getText());
                D.UpdateDoctorData();
                Message.setText("Doctor Personal has been Updated Successfully.");
                Message.setTextFill(Color.GREEN);
            }
        });
    }
}
