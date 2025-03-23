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

import java.lang.management.MemoryUsage;


public class AdminMainMenuScreen
{

    private Button BtnGoToBackScreen = new Button("Back");
    private Button BtnGoToNextScreen = new Button("Next");
    private Label LBL1 = new Label("Please select one of the following:");
    private Label LBL2 = new Label("Admin Main\n  Menu");
    private Label Message = new Label("");
    private RadioButton RDR1 = new RadioButton("1-Change Admin Password.");
    private RadioButton RDR2 = new RadioButton("2-Show All Signed In Users");
    private ToggleGroup TGP = new ToggleGroup();
    private VBox Box = new VBox();
    public static Scene AdminMainMenu(Stage stage)
    {
        AdminMainMenuScreen AMMS = new AdminMainMenuScreen();
        return AMMS.CreateSceneScreen(stage);
    }
    private Scene CreateSceneScreen(Stage stage)
    {

        HandleUIComponents();
        // Create panes
        Pane P1 = new Pane();
        Pane P2 = new Pane();
        Pane P3 = new Pane();

        // Set up P1 (left pane)
        P1.setPrefSize(300, 700);
        P1.setStyle("-fx-background-color: LimeGreen");
        P1.getChildren().add(LBL2);

        //Set up P2 (right pane with VBox)
        P2.getChildren().add(Box);

        // Set up P3 (main container for P1, P2, and the button)
        P3.getChildren().addAll(P1, P2, BtnGoToBackScreen,BtnGoToNextScreen);

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
    private void HandleUIComponents()
    {
        HandleLabelsCustom();
        HandleButtonsScreen();
        HandleRadioButtons();
        HandleVBoxElements();
    }
    private void HandleButtonsScreen()
    {
        BtnGoToBackScreen.setPrefSize(200, 50);
        BtnGoToBackScreen.setTranslateX(450);
        BtnGoToBackScreen.setTranslateY(555);
        BtnGoToNextScreen.setPrefSize(200, 50);
        BtnGoToNextScreen.setTranslateX(1111);
        BtnGoToNextScreen.setTranslateY(555);
    }
    private void HandleLabelsCustom()
    {
        LBL1.setFont(new Font("verdena",30));
        LBL1.setTextFill(Color.BLACK);
        Message.setFont(new Font("verdena",30));
        Message.setTextFill(Color.RED);
        LBL2.setFont(new Font("Verdena",30));
        LBL2.setTranslateX(50);
        LBL2.setTranslateY(250);
    }
    private void HandleRadioButtons()
    {
        RDR1.setFont(new Font("verdena",30));
        RDR1.setToggleGroup(TGP);
        RDR2.setFont(new Font("verdena",30));
        RDR2.setToggleGroup(TGP);
    }
    private void HandleVBoxElements()
    {
        Box.getChildren().addAll(LBL1,RDR1,RDR2,Message);
        Box.setTranslateX(650);
        Box.setTranslateY(100);
        Box.setSpacing(30);
    }
    private void HandleButtonsAction(Stage stage)
    {
        BtnGoToBackScreen.setOnAction(e->
        {
            Scene scene = SignInAdmin.AdminLoginScreen(stage);
            stage.setScene(scene);
        });
        BtnGoToNextScreen.setOnAction(e -> {
            if (RDR1.isSelected()) {
                Scene scene = ChangeAdminPasswordMenu.AdminChangePasswordMenuScreen(stage);
                stage.setScene(scene);
            } else if (RDR2.isSelected()) {
                Scene scene = PatientLoginScreen.PatientLoginMenu(stage);
                stage.setScene(scene);
            }
            else {
                Message.setText("Please choose one");
            }
        });
    }
}
