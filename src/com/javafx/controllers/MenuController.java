package com.javafx.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    private RootController rootController;

    @FXML
    private void playAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx/screens/playScreen.fxml"));
        Pane pane = loader.load();
        PlayController playController = loader.getController();
        playController.setRootController(rootController);
        rootController.setScreen(pane);
    }

    @FXML
    private void howToPlayAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx/screens/howToPlayScreen.fxml"));
        Pane pane = loader.load();
        HowToPlayController howToPlayController = loader.getController();
        howToPlayController.setRootController(rootController);
        rootController.setScreen(pane);
    }

    @FXML
    private void optionsAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx/screens/optionScreen.fxml"));
        Pane pane = loader.load();
        OptionsController optionsController = loader.getController();
        optionsController.setRootController(rootController);
        rootController.setScreen(pane);
    }

    @FXML
    private void exitAction() {
        Platform.exit();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
