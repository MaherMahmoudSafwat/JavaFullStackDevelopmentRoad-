package com.example.obseravblelistarray;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ArrayList<Integer> ArrList = new ArrayList<Integer>(10);
        for(int i =0;i<10;i++)
        {
            ArrList.add(i);
        }

        ObservableList List = FXCollections.observableList(ArrList);
        ComboBox CBX = new ComboBox(List);
        CBX.setPrefSize(100,100);
        Scene scene = new Scene(CBX,500,700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}