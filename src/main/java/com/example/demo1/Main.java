package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Main extends Application {


    public static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
//        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setTitle("Coastal library!");
        stage.setScene(scene);
        stage.show();
    }

    public void changescene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stg.getScene().setRoot(pane);
    }



    public static void main(String[] args) throws IOException, InvocationTargetException, UnsupportedOperationException {
        launch();
    }
}