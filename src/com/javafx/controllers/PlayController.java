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
        if (!(OptionsController.numberOfAllowedGuesses == Double.POSITIVE_INFINITY))
            resultLabel.setText("Watch out! You have only " + (int)OptionsController.numberOfAllowedGuesses + " guesses...");
        System.out.println(theNumber);
    }

    public void checkAction() {
        if (guessNumberInputField.getText().equals("")) {
            resultLabel.setText("Please enter a valid number...");
            wrongGuessSoundEffect();
        }
        else {
            int guess = Integer.parseInt(guessNumberInputField.getText());
            checkNumberOfGuesses++;
            if (checkNumberOfGuesses == OptionsController.numberOfAllowedGuesses) {
                resultLabel.setText("You loose...");
                OptionsController.backgroundMusicPlayer.stop();
                gameOverSoundEffect();
                playAgainPane.setVisible(true);
            } else {
                if (guess < theNumber) {
                    if (OptionsController.numberOfAllowedGuesses == Double.POSITIVE_INFINITY)
                        resultLabel.setText("Too low!");
                    else
                        resultLabel.setText("Too low!\nNumber of guesses left: "
                                + (int)(OptionsController.numberOfAllowedGuesses - checkNumberOfGuesses));
                    wrongGuessSoundEffect();
                } else if (guess > theNumber) {
                    if (OptionsController.numberOfAllowedGuesses == Double.POSITIVE_INFINITY)
                        resultLabel.setText("Too high!");
                    else
                        resultLabel.setText("Too high!\nNumber of guesses left: "
                                + (int)(OptionsController.numberOfAllowedGuesses - checkNumberOfGuesses));
                    wrongGuessSoundEffect();
                } else {
                    resultLabel.setText("Correct!\nYou win!");
                    OptionsController.backgroundMusicPlayer.stop();
                    correctGuessSoundEffect();
                    playAgainPane.setVisible(true);
                }
            }
        }
    }

    public void onEnter() {
        if (!playAgainPane.isVisible())
            checkAction();
        else
            playAgainAction();
    }

    public void playAgainAction() {
        endGameMusic();
        playAgainPane.setVisible(false);
        resultLabel.setText("");
        guessNumberInputField.setText("");
        checkNumberOfGuesses = 0;
        theNumber = random(OptionsController.numberRangeFrom, OptionsController.numberRangeTo);
        System.out.println(theNumber);
    }

    public void backToMenuAction() throws IOException {
        if (playAgainPane.isVisible())
            endGameMusic();
        rootController.loadMenuScreen();
    }

    private void endGameMusic() {
        OptionsController.backgroundMusicPlayer.play();
        try {
            if (wrongGuessPlayer.getStatus() == MediaPlayer.Status.PLAYING)
                wrongGuessPlayer.stop();
        } catch (NullPointerException ignored) {}
        try {
            if (correctGuessPlayer.getStatus() == MediaPlayer.Status.PLAYING)
                correctGuessPlayer.stop();
        } catch (NullPointerException ignored) {}
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

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
