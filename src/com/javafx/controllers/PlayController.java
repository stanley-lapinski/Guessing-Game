package com.javafx.controllers;

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
    public MediaPlayer correctGuessPlayer, wrongGuessPlayer, gameOverPlayer;
    private int theNumber;
    private double checkNumberOfGuesses = 0;

    public static int random(int min, int max) {
        return (int) (min + (Math.random() * (max - min)));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        guessNumberInputField.setPadding(new Insets(0, 30, 0, 30));
        guessNumberInputField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                guessNumberInputField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        theNumber = random(OptionsController.numberRangeFrom, OptionsController.numberRangeTo);
    }

    public void checkAction() {
        if (checkNumberOfGuesses < OptionsController.numberOfAllowedTries) {
            int guess = Integer.parseInt(guessNumberInputField.getText());
            if (guess < theNumber) {
                resultLabel.setText("Too low!");
                wrongGuessSoundEffect();
            } else if (guess > theNumber) {
                resultLabel.setText("Too high!");
                wrongGuessSoundEffect();
            } else {
                resultLabel.setText("Correct!\nYou win!");
                OptionsController.backgroundMusicPlayer.stop();
                correctGuessSoundEffect();
                playAgainPane.setVisible(true);
                checkNumberOfGuesses = 0;
            }
        } else {
            resultLabel.setText("You loose...");
            gameOverSoundEffect();
            playAgainPane.setVisible(true);
            checkNumberOfGuesses = 0;
        }
        checkNumberOfGuesses++;
    }

    public void onEnter() {
        if (!playAgainPane.isVisible())
            checkAction();
        else
            playAgainAction();
    }

    public void playAgainAction() {
        playAgainPane.setVisible(false);
        OptionsController.backgroundMusicPlayer.play();
        correctGuessPlayer.stop();
        theNumber = random(OptionsController.numberRangeFrom, OptionsController.numberRangeTo);
        resultLabel.setText("");
        guessNumberInputField.setText("");
    }

    public void correctGuessSoundEffect() {
        String correctGuessFilePath = "C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/correctGuess2.mp3";
        Media correctGuessSound = new Media(new File(correctGuessFilePath).toURI().toString());
        correctGuessPlayer = new MediaPlayer(correctGuessSound);
        correctGuessPlayer.play();
    }

    public void wrongGuessSoundEffect() {
        String wrongGuessFilePath = "C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/wrongGuess.mp3";
        Media wrongGuessSound = new Media(new File(wrongGuessFilePath).toURI().toString());
        wrongGuessPlayer = new MediaPlayer(wrongGuessSound);
        wrongGuessPlayer.play();
    }

    public void gameOverSoundEffect() {
        String gameOverFilePath = "C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameOver.mp3";
        Media gameOverSound = new Media(new File(gameOverFilePath).toURI().toString());
        gameOverPlayer = new MediaPlayer(gameOverSound);
        gameOverPlayer.play();

    }

    public void backToMenuAction() throws IOException {
        rootController.loadMenuScreen();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
