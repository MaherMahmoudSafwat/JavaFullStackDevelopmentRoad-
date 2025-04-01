package com.example.anchorpanescontainers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Create an AnchorPane container
        // AnchorPane allows you to position child nodes relative to its edges
        // You can anchor nodes to top, bottom, left, or right edges
        AnchorPane rootPane = new AnchorPane();

        // Create a button and a label to add to the AnchorPane
        Button helloButton = new Button("Hello");
        Label clickLabel = new Label("Hi please click me");

        // Position the button using anchor constraints:
        // - Anchor the button 5 pixels from the bottom
        // - Anchor it 500 pixels from both left and right (centering it horizontally)
        AnchorPane.setBottomAnchor(helloButton, 5.0);
        AnchorPane.setLeftAnchor(helloButton, 500.0);
        AnchorPane.setRightAnchor(helloButton, 500.0);

        // Position the label using anchor constraints:
        // - Anchor the label 111 pixels from the top
        // - Anchor it 500 pixels from both left and right (centering it horizontally)
        AnchorPane.setTopAnchor(clickLabel, 111.0);
        AnchorPane.setLeftAnchor(clickLabel, 500.0);
        AnchorPane.setRightAnchor(clickLabel, 500.0);

        // Add both the label and button to the AnchorPane
        rootPane.getChildren().addAll(clickLabel, helloButton);

        // Create a scene with the AnchorPane as the root node
        Scene scene = new Scene(rootPane);

        // Set up and show the stage
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}