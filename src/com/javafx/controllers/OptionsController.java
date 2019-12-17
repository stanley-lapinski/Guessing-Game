package com.javafx.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {

    @FXML
    CheckBox musicCheckBox, soundCheckBox;
    @FXML
    Slider musicVolumeSlider, soundVolumeSlider;

    public RootController rootController;
    public static MediaPlayer backgroundMusicPlayer, correctGuessPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicVolumeSlider.setValue(backgroundMusicPlayer.getVolume() * 100);
        musicVolumeSlider.valueProperty().addListener(observable -> backgroundMusicPlayer.setVolume(musicVolumeSlider.getValue() / 100));
    }

    public static void backgroundMusic() {
        String backgroundMusicPath = "C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameBackgroundMusic.mp3";
        Media backgroundMusic = new Media(Paths.get(backgroundMusicPath).toUri().toString());
        backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusicPlayer.play();
    }

    public static void soundEffects() {
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

    public void pauseSound(ActionEvent event) {

    }

    public void backToMenu(ActionEvent event) throws IOException {
        rootController.loadMenuScreen();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
