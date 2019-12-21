package com.javafx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {

    // soundCheckBox and soundVolumeSlider are not being used
    @FXML
    CheckBox musicCheckBox;
    @FXML
    Slider musicVolumeSlider;
    @FXML
    TextField rangeNumbersFrom, rangeNumbersTo;
    @FXML
    ComboBox<String> changeMusicBox;

    public RootController rootController;
    public static MediaPlayer backgroundMusicPlayer, correctGuessPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicVolumeSlider.setValue(backgroundMusicPlayer.getVolume() * 100);
        musicVolumeSlider.valueProperty().addListener(observable -> backgroundMusicPlayer.setVolume(musicVolumeSlider.getValue() / 100));

        changeMusicBox.getItems().addAll("Video Game Level", "Going Through the Meadow", "Discovering New Place");
        changeMusicBox.setValue("Video Game Level");
    }

    public void changeTheMusic(ActionEvent event) {
            switch (changeMusicBox.getValue()) {
                case "Video Game Level":
                    backgroundMusicPlayer.stop();
                    backgroundMusic("C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameBackgroundMusic1.mp3");
                    break;
                case "Going Through the Meadow":
                    backgroundMusicPlayer.stop();
                    backgroundMusic("C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameBackgroundMusic2.mp3");
                    break;
                case "Discovering New Place":
                    backgroundMusicPlayer.stop();
                    backgroundMusic("C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameBackgroundMusic3.mp3");
                    break;
            }
    }

    public static void backgroundMusic(String backgroundMusicPath) {
        Media backgroundMusic = new Media(Paths.get(backgroundMusicPath).toUri().toString());
        backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusicPlayer.play();
    }

    public static void correctSoundEffect() {
        String correctGuessFile = "C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/correctGuess.mp3";
        Media correctGuessSound = new Media(new File(correctGuessFile).toURI().toString());
        correctGuessPlayer = new MediaPlayer(correctGuessSound);
        correctGuessPlayer.play();
    }

    public void pauseMusic(ActionEvent event) {
        boolean musicCheck = musicCheckBox.isSelected();
        if (!musicCheck) {
            backgroundMusicPlayer.pause();
        } else
            backgroundMusicPlayer.play();
    }

    public void backToMenu(ActionEvent event) throws IOException {
        rootController.loadMenuScreen();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
