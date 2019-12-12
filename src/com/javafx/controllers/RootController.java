package com.javafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class RootController {

    @FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize() throws IOException {
        loadMenuScreen();
    }

    @FXML
    public void loadMenuScreen() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/com/javafx/screens/MenuScreen.fxml"));
        mainStackPane.getChildren().add(pane);
    }

}
