package com.javafx.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx/screens/RootScreen.fxml"));
        StackPane rootStackPane = loader.load();
        Scene scene = new Scene(rootStackPane);
        primaryStage.setScene(scene);

        primaryStage.initStyle(StageStyle.UNDECORATED);

        rootStackPane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        rootStackPane.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getScreenX() - x);
            primaryStage.setY(mouseEvent.getScreenY() - y);
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
