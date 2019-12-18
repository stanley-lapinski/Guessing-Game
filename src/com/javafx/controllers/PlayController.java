package com.javafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class PlayController implements Initializable {

    @FXML
    TextField guessNumberInputField;
    @FXML
    TextField result;

    private RootController rootController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        guessNumberInputField.setPadding(new Insets(0, 30, 0, 30));
    }

    /* RNG method
    private static final Random RANDOM = new Random();
    public static int random(int min, int max) {
        return RANDOM.nextInt(max) + min;
    } */

    public void checkAction() {

        OptionsController.correctSoundEffects();

        int theNumber = (int) (Math.random() * 100 + 1);
        int guess = Integer.parseInt(guessNumberInputField.getText());
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
