package com.javafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PlayController {

    @FXML
    TextField guessNumber;
    @FXML
    TextField result;

    private RootController rootController;

    public void checkAction() {

        int theNumber = (int) (Math.random() * 100 + 1);
        int guess = Integer.parseInt(guessNumber.getText());
        if (guess < theNumber)
            result.setText("Your number is too low");
        else if (guess > theNumber)
            result.setText("Your number is too high");
        else
            result.setText("You are correct!");
    }

    public void backToMenu(ActionEvent event) throws IOException {
        rootController.loadMenuScreen();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
