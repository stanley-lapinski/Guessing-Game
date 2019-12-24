package com.javafx.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    @FXML
    Button playButton, howToPlayButton, optionsButton;

    private RootController rootController;

    public void playAction() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane pane = loader.load(getClass().getResource("/com/javafx/screens/PlayScreen.fxml").openStream());
        PlayController playController = loader.getController();
        playController.setRootController(rootController);
        rootController.setScreen(pane);
    }

    public void howToPlayAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx/screens/HowToPlayScreen.fxml"));
        Pane pane = loader.load();
        HowToPlayController howToPlayController = loader.getController();
        howToPlayController.setRootController(rootController);
        rootController.setScreen(pane);
    }

    public void optionsAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx/screens/OptionScreen.fxml"));
        Pane pane = loader.load();
        OptionsController optionsController = loader.getController();
        optionsController.setRootController(rootController);
        rootController.setScreen(pane);
    }

    public void exitAction() {
        Platform.exit();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
