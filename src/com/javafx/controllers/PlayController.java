package com.javafx.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayController implements Initializable {

    @FXML
    Pane playAgainPane;
    @FXML
    TextField guessNumberInputField;
    @FXML
    Label resultLabel;

    private RootController rootController;
    public MediaPlayer correctGuessPlayer, wrongGuessPlayer;
    int theNumber;
    double checkNumberOfGuesses = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        guessNumberInputField.setPadding(new Insets(0, 30, 0, 30));
        guessNumberInputField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    guessNumberInputField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        theNumber = random(OptionsController.numberRangeFrom, OptionsController.numberRangeTo);
    }

    public static int random(int min, int max) {
        return (int) (min + (Math.random() * (max - min)));
    }

    public void checkAction() {

        checkNumberOfGuesses++;
        if (checkNumberOfGuesses < OptionsController.numberOfAllowedTries) {
            int guess = Integer.parseInt(guessNumberInputField.getText());
            if (guess < theNumber) {
                resultLabel.setText("Your number is too low");
                wrongSoundEffect();
            } else if (guess > theNumber) {
                resultLabel.setText("Your number is too high");
                wrongSoundEffect();
            } else {
                resultLabel.setText("You are correct!");
                correctSoundEffect();
                playAgainPane.setVisible(true);
                checkNumberOfGuesses = 0;
            }
        } else {
            resultLabel.setText("You lost...");
            playAgainPane.setVisible(true);
            checkNumberOfGuesses = 0;
        }
    }

    public void playAgainAction() {
        playAgainPane.setVisible(false);
        theNumber = random(OptionsController.numberRangeFrom, OptionsController.numberRangeTo);
        resultLabel.setText("");
        guessNumberInputField.setText("");
    }

     public void correctSoundEffect() {
        String correctGuessFilePath = "C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/correctGuess2.mp3";
        Media correctGuessSound = new Media(new File(correctGuessFilePath).toURI().toString());
        correctGuessPlayer = new MediaPlayer(correctGuessSound);
        correctGuessPlayer.play();
    }

    public void wrongSoundEffect() {
        String wrongGuessFilePath = "C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/wrongGuess.mp3";
        Media wrongGuessSound = new Media(new File(wrongGuessFilePath).toURI().toString());
        wrongGuessPlayer = new MediaPlayer(wrongGuessSound);
        wrongGuessPlayer.play();
    }

    public void backToMenuAction() throws IOException {
        rootController.loadMenuScreen();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
