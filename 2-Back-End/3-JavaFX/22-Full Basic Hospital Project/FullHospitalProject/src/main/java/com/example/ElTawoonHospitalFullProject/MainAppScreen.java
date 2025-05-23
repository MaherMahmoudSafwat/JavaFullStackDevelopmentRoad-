package com.example.ElTawoonHospitalFullProject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAppScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException
    {
        Scene scene = UserStatusOptionsScreen.Login(stage);
        stage.setWidth(1357);
        stage.setHeight(700);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
