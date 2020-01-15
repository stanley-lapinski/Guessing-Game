package com.javafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayController implements Initializable {

    @FXML
    private ImageView tooLowImage, tooHighImage;
    @FXML
    private Pane playAgainPane;
    @FXML
    private TextField guessNumberInputField;
    @FXML
    private Label resultLabel, resultLabelLimited;

    private RootController rootController;
    private MediaPlayer correctGuessPlayer, wrongGuessPlayer, gameOverPlayer;
    private int theNumber;
    private double checkNumberOfGuesses = 0;

    private int random(int min, int max) {
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
            resultLabelLimited.setText("Watch out! You have only " + (int)OptionsController.numberOfAllowedGuesses + " guesses.");
        System.out.println(theNumber);
    }

    @FXML
    private void checkAction() {
        if (guessNumberInputField.getText().equals("")) {
            resultLabelLimited.setText("Please enter a valid number...");
            wrongGuessSoundEffect();
            wrongGuessPlayer.play();
        }
        else {
            int guess = Integer.parseInt(guessNumberInputField.getText());
            checkNumberOfGuesses++;
            if (guess == theNumber) {
                resultLabel.setText("Correct!");
                resultLabelLimited.setText("You win!");
                OptionsController.backgroundMusicPlayer.stop();
                correctGuessSoundEffect();
                tooHighImage.setVisible(false);
                tooLowImage.setVisible(false);
                playAgainPane.setVisible(true);
            }
            else if (checkNumberOfGuesses == OptionsController.numberOfAllowedGuesses) {
                resultLabel.setText("You loose...");
                resultLabelLimited.setText("");
                OptionsController.backgroundMusicPlayer.stop();
                gameOverSoundEffect();
                tooHighImage.setVisible(false);
                tooLowImage.setVisible(false);
                playAgainPane.setVisible(true);
            }
            else if (guess < theNumber) {
                resultLabel.setText("Too low! Pick higher.");
                if (OptionsController.numberOfAllowedGuesses != Double.POSITIVE_INFINITY)
                    if ((int)(OptionsController.numberOfAllowedGuesses - checkNumberOfGuesses) != 1)
                        resultLabelLimited.setText("Only " + (int)(OptionsController.numberOfAllowedGuesses - checkNumberOfGuesses) + " guesses left.");
                    else
                        resultLabelLimited.setText("Only " + (int)(OptionsController.numberOfAllowedGuesses - checkNumberOfGuesses) + " guess left!");
                tooHighImage.setVisible(false);
                tooLowImage.setVisible(true);
                wrongGuessSoundEffect();
                wrongGuessPlayer.play();
            }
            else {
                resultLabel.setText("Too high! Pick lower.");
                if (OptionsController.numberOfAllowedGuesses != Double.POSITIVE_INFINITY)
                    if ((int)(OptionsController.numberOfAllowedGuesses - checkNumberOfGuesses) != 1)
                        resultLabelLimited.setText("Only " + (int)(OptionsController.numberOfAllowedGuesses - checkNumberOfGuesses) + " guesses left.");
                    else
                        resultLabelLimited.setText("Only " + (int)(OptionsController.numberOfAllowedGuesses - checkNumberOfGuesses) + " guess left!");
                tooHighImage.setVisible(true);
                tooLowImage.setVisible(false);
                wrongGuessSoundEffect();
                wrongGuessPlayer.play();
            }
        }
    }

    @FXML
    private void onEnter() {
        if (!playAgainPane.isVisible())
            checkAction();
        else
            playAgainAction();
    }

    @FXML
    private void playAgainAction() {
        endGameMusic();
        playAgainPane.setVisible(false);
        resultLabel.setText("");
        if (OptionsController.numberOfAllowedGuesses != Double.POSITIVE_INFINITY)
            resultLabelLimited.setText("You regained your " + (int)OptionsController.numberOfAllowedGuesses + " guesses.");
        guessNumberInputField.setText("");
        checkNumberOfGuesses = 0;
        theNumber = random(OptionsController.numberRangeFrom, OptionsController.numberRangeTo);
        System.out.println(theNumber);
    }

    @FXML
    private void backToMenuAction() throws IOException {
        if (playAgainPane.isVisible())
            endGameMusic();
        rootController.loadMenuScreen();
    }

    private void endGameMusic() {
        if (checkNumberOfGuesses == OptionsController.numberOfAllowedGuesses)
            gameOverPlayer.stop();
        else
            correctGuessPlayer.stop();
        if (OptionsController.savedMusicCheckBox)
            OptionsController.backgroundMusicPlayer.play();
    }

    private void correctGuessSoundEffect() {
        String correctGuessFilePath = "file:///C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/correctGuess.mp3";
        Media correctGuessSound = new Media(correctGuessFilePath);
        correctGuessPlayer = new MediaPlayer(correctGuessSound);
        correctGuessPlayer.play();
    }

    private void wrongGuessSoundEffect() {
        String wrongGuessFilePath = "file:///C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/wrongGuess.mp3";
        Media wrongGuessSound = new Media(wrongGuessFilePath);
        wrongGuessPlayer = new MediaPlayer(wrongGuessSound);
        //wrongGuessPlayer.play();
    }

    private void gameOverSoundEffect() {
        String gameOverFilePath = "file:///C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameOver.mp3";
        Media gameOverSound = new Media(gameOverFilePath);
        gameOverPlayer = new MediaPlayer(gameOverSound);
        gameOverPlayer.play();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
