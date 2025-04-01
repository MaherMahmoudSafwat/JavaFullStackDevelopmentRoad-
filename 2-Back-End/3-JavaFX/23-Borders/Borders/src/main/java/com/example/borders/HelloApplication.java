package com.example.borders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException
    {
        Label LBL1 = new Label("Hello");
        LBL1.setAlignment(Pos.CENTER);

// Set a multi-style border using the Border constructor
        LBL1.setBorder(new Border(
                new BorderStroke(
                        Color.BLUE,      // Top border color
                        Color.GREY,      // Right border color
                        Color.RED,       // Bottom border color
                        Color.ORANGE,    // Left border color

                        // Border stroke styles (applied in same order as colors):
                        BorderStrokeStyle.DOTTED,  // Top style
                        BorderStrokeStyle.DASHED,  // Right style
                        BorderStrokeStyle.NONE,    // Bottom style (invisible)
                        BorderStrokeStyle.SOLID,   // Left style

                        CornerRadii.EMPTY,  // No rounded corners
                        BorderWidths.DEFAULT,  // Default thickness (1px)
                        new Insets(15)       // Padding inside border (15px on all sides)
                )
        ));

// Add the Label to a layout
        VBox box = new VBox(LBL1);
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(700, 700);

// Set up the scene
        LBL1.setTooltip(new Tooltip("Java"));
        VBox Box = new VBox();
        Box.setAlignment(Pos.BASELINE_CENTER);
        Box.getChildren().addAll(LBL1);
        Box.setPrefSize(700,700);
        Scene scene = new Scene(Box);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}