package com.javafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PlayController {

    private RootController rootController;

    @FXML
    public void backToMenu(ActionEvent event) throws IOException {
        rootController.loadMenuScreen();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
