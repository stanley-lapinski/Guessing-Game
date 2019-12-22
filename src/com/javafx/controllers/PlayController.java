package com.javafx.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
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
    public MediaPlayer correctGuessPlayer, wrongGuessPlayer;
    int theNumber;

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


        /*
        FXMLLoader loader = new FXMLLoader();
        OptionsController controller = loader.getController();
        Integer rangeNumbersFrom = Integer.valueOf(controller.rangeNumbersFrom.getText());
        Integer rangeNumbersTo = Integer.valueOf(controller.rangeNumbersTo.getText());

        //int rangeNumbersFrom = Integer.parseInt(controller.rangeNumbersFrom.getText());
        //int rangeNumbersTo = Integer.parseInt(controller.rangeNumbersTo.getText());
        theNumber = random(rangeNumbersFrom, rangeNumbersTo); */
    }

    private static final Random RANDOM = new Random();
    public static int random(int min, int max) {
        return RANDOM.nextInt(max) + min;
    }

    public void checkAction() {


        int guess = Integer.parseInt(guessNumberInputField.getText());
        if (guess < theNumber) {
            result.setText("Your number is too low");
            wrongSoundEffect();
        }
        else if (guess > theNumber) {
            result.setText("Your number is too high");
            wrongSoundEffect();
        }
        else {
            result.setText("You are correct!");
            correctSoundEffect();
        }
    }

     public void correctSoundEffect() {
        String correctGuessFilePath = "C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/correctGuess.mp3";
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

    public void backToMenu(ActionEvent event) throws IOException {
        rootController.loadMenuScreen();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
