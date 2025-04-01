package com.example.scrollbar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox Box1 = new VBox();
        for(int i=0;i<50;i++)
        {
            Label LBL1 = new Label("Item " + i);
            Box1.getChildren().add(LBL1);
        }
        /*
        ScrollBar:

A ScrollBar is a single, independent control that allows the user to scroll a value within a specific range. It's a low-level component.
It's used to control a numerical value, which you then typically use to manipulate the display of some other content.
You'd use it if you needed very fine-grained control over scrolling and wanted to handle the content layout and updates yourself.
It is not designed to contain content directly, rather it manipulates a numerical value.
Essentially, a ScrollBar is just the visual representation of a slider that can be used for scrolling.
ScrollPane:

A ScrollPane is a higher-level container that automatically manages scroll bars for you.
It's designed to hold other JavaFX nodes (like VBox, TableView, ImageView, etc.) and provide scrolling functionality when the content exceeds the visible area.
It handles the layout and display of scroll bars automatically, based on the content's size and the ScrollPane's viewport.
It’s a complete scrolling container, meaning that it is designed to contain content that needs to be scrolled.
ScrollPane is much easier to use for most common scrolling scenarios.
In simpler terms:

Think of ScrollBar as just the "slider" part of a scroll bar.
ScrollPane is the complete "container" that includes the sliders and the area where the content scrolls.
When to Use Which:

Use ScrollBar:
When you need precise control over the scrolling behavior.
When you need to synchronize scrolling with other UI elements manually.
When you are implementing very custom scrolling behaviors.
Use ScrollPane:
For most typical scrolling scenarios where you want to display content that might exceed the visible area.
When you want JavaFX to handle the scrolling logic automatically.
When you have a layout that you want to be scrollable.
         */
        ScrollPane SLP =new ScrollPane(Box1);
        ScrollBar SLB = new ScrollBar();
        SLB.setMin(0);
        SLB.setMax(100);
        SLB.setValue(50);
        SLB.valueProperty().addListener((Obs,   OldValue,NewValue)->
        {
            SLP.setVvalue(NewValue.doubleValue());
        });
        HBox H = new HBox();
        H.getChildren().addAll(SLP,SLB);
        Scene scene = new Scene(H,320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}