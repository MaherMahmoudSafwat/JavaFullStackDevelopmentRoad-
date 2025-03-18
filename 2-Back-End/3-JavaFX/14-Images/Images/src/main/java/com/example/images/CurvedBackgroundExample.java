package com.example.images;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;

public class CurvedBackgroundExample extends Application {

    @Override
    public void start(Stage stage) {
        // Create a StackPane layout to hold the content
        StackPane layout = new StackPane();

        Image IMG = new Image("C:\\Users\\Maher\\Pictures\\Camera Roll\\Picture1.jpg");
        ImageView ImgViewer1 = new ImageView(IMG);


        System.out.println(IMG.getWidth());
        System.out.println(IMG.getHeight());
        ImgViewer1.setPreserveRatio(true);
        ImgViewer1.setFitWidth(500);
        ImgViewer1.setFitHeight(500);
        ImgViewer1.setTranslateX(10);
        ImgViewer1.setTranslateY(70);
        // Create a LinearGradient for the curved background effect
        LinearGradient LG = new LinearGradient(
                0,      // Starting X coordinate (horizontal) of the gradient, from left
                0.5,      // Starting Y coordinate (vertical) of the gradient, from top
                0.7,      // Ending X coordinate (horizontal) of the gradient, from right
                0.9,      // Ending Y coordinate (vertical) of the gradient, from bottom
                true,   // Make the gradient proportional to the container size
                CycleMethod.REFLECT,  // No repeating of the gradient
                new Stop(0.0, Color.WHITE),  // Start with white at the top
                new Stop(1.0, Color.GREEN)   // End with green at the bottom
        );
        // Set the gradient as the background for the layout
        BackgroundFill backgroundFill = new BackgroundFill(LG, null, null);
        Background background = new Background(backgroundFill);
        layout.setBackground(background);
        layout.getChildren().addAll(ImgViewer1);

        // Create a Scene with the layout and set a size for the scene
        Scene scene = new Scene(layout, 800, 600);  // Set the scene size

        // Set up the stage (window)
        stage.setTitle("Curved Background Example");
        stage.setScene(scene);

        // Show the window
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
