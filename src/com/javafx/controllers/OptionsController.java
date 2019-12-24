package com.javafx.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {

    @FXML
    CheckBox musicCheckBox;
    @FXML
    Slider musicVolumeSlider;
    @FXML
    TextField rangeFromTextField, rangeToTextField, numberOfTries; //numberoftries not used
    @FXML
    ComboBox<String> changeMusicBox;

    public RootController rootController;
    public static MediaPlayer backgroundMusicPlayer;
    public static String savedRangeFrom = "", savedRangeTo = "";
    public static int numberRangeFrom = 0, numberRangeTo = 100;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicVolumeSlider.setValue(backgroundMusicPlayer.getVolume() * 100);
        musicVolumeSlider.valueProperty().addListener(observable -> backgroundMusicPlayer.setVolume(musicVolumeSlider.getValue() / 100));

        changeMusicBox.getItems().addAll("Video Game Level", "Walk through Meadow", "Discovering New Place");
        changeMusicBox.setValue("Video Game Level");
        changeMusicBox.setStyle("-fx-font: 15px \"Berlin Sans FB Demi\";");

        rangeFromTextField.setPadding(new Insets(0, 30, 0, 30));
        rangeToTextField.setPadding(new Insets(0, 30, 0, 30));
        numberOfTries.setPadding(new Insets(0, 30, 0, 30));

        if (!savedRangeFrom.equals("") && !savedRangeTo.equals("")) {
            rangeFromTextField.setText(savedRangeFrom);
            rangeToTextField.setText(savedRangeTo);
        }

        rangeFromTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    rangeFromTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        rangeToTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    rangeToTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void changeTheMusicAction(ActionEvent event) {
            switch (changeMusicBox.getValue()) {
                case "Video Game Level":
                    backgroundMusicPlayer.stop();
                    backgroundMusic("C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameBackgroundMusic1.mp3");
                    break;
                case "Walk through Meadow":
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

    public void pauseMusicAction(ActionEvent event) {
        boolean musicCheck = musicCheckBox.isSelected();
        if (!musicCheck) {
            backgroundMusicPlayer.pause();
        } else
            backgroundMusicPlayer.play();
    }

    public void saveOptionsAction(ActionEvent event) {
        if (rangeFromTextField.getText().equals("") && rangeToTextField.getText().equals("")) {
            numberRangeFrom = 0;
            numberRangeTo = 100;
        } else {
            savedRangeFrom = rangeFromTextField.getText();
            savedRangeTo = rangeToTextField.getText();
            numberRangeFrom = Integer.parseInt(savedRangeFrom);
            numberRangeTo = Integer.parseInt(savedRangeTo);
        }
    }

    public void backToMenuAction(ActionEvent event) throws IOException {
        rootController.loadMenuScreen();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
