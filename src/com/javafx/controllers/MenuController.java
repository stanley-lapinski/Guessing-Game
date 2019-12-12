package com.javafx.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    private RootController rootController;

    @FXML
    public void playOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx/screens/PlayScreen.fxml"));
        Pane pane = loader.load();
        PlayController playController = loader.getController();
        playController.setRootController(rootController);
        rootController.setScreen(pane);
    }

    @FXML
    public void howToPlayOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx/screens/HowToPlayScreen.fxml"));
        Pane pane = loader.load();
        HowToPlayController howToPlayController = loader.getController();
        howToPlayController.setRootController(rootController);
        rootController.setScreen(pane);
    }

    @FXML
    public void exitOnAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void optionsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx/screens/OptionScreen.fxml"));
        Pane pane = loader.load();
        OptionsController optionsController = loader.getController();
        optionsController.setRootController(rootController);
        rootController.setScreen(pane);
    }

    @FXML
    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
