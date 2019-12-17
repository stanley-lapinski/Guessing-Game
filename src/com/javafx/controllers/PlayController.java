package com.javafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *  1. Add theme music to all windows
 *  2. Add sounds for correct and wrong guess
 */

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

    public void checkAction() {
        /*
        final java.net.URL resource = getClass().getResource("correctGuess.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play(); */

        String correctGuessFile = "C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/correctGuess.mp3";
        Media correctGuessSound = new Media(new File(correctGuessFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(correctGuessSound);
        mediaPlayer.play();

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
