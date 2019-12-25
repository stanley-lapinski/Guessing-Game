package com.javafx.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    /**
     * cooperation between checkbox and savebutton needs to be fixed
     */

    @FXML
    CheckBox musicCheckBox;
    @FXML
    Slider musicVolumeSlider;
    @FXML
    TextField rangeFromTextField, rangeToTextField, allowedTriesTextField;
    @FXML
    ComboBox<String> changeMusicBox;

    public RootController rootController;
    public static MediaPlayer backgroundMusicPlayer;
    public static String savedRangeFrom = "", savedRangeTo = "", savedAllowedTries = "";
    public static boolean savedMusicCheckBox = true;
    public static int numberRangeFrom = 0, numberRangeTo = 100;
    public static double numberOfAllowedTries = Double.POSITIVE_INFINITY;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicVolumeSlider.setValue(backgroundMusicPlayer.getVolume() * 100);
        musicVolumeSlider.valueProperty().addListener(observable -> backgroundMusicPlayer.setVolume(musicVolumeSlider.getValue() / 100));

        changeMusicBox.getItems().addAll("Video Game Level", "Walk through Meadow", "Discovering New Place");
        changeMusicBox.setValue("Video Game Level");
        changeMusicBox.setStyle("-fx-font: 15px \"Berlin Sans FB Demi\";");

        rangeFromTextField.setPadding(new Insets(0, 30, 0, 30));
        rangeToTextField.setPadding(new Insets(0, 30, 0, 30));
        allowedTriesTextField.setPadding(new Insets(0, 30, 0, 30));

        rangeFromTextField.setText(savedRangeFrom);
        rangeToTextField.setText(savedRangeTo);
        allowedTriesTextField.setText(savedAllowedTries);
        musicCheckBox.setSelected(savedMusicCheckBox);

        rangeFromTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                rangeFromTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        rangeToTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                rangeToTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        allowedTriesTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    allowedTriesTextField.setText(newValue.replaceAll("[^\\d]",""));
                }
            }
        });
    }

    public void changeTheMusicAction() {
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

    public void pauseMusicAction() {
        boolean musicCheck = musicCheckBox.isSelected();
        if (!musicCheck) {
            backgroundMusicPlayer.pause();
        } else
            backgroundMusicPlayer.play();
    }

    public void saveOptionsAction() {
        savedRangeFrom = rangeFromTextField.getText();
        savedRangeTo = rangeToTextField.getText();
        savedAllowedTries = allowedTriesTextField.getText();
        savedMusicCheckBox = musicCheckBox.isSelected();

        if (rangeFromTextField.getText().equals("")) { numberRangeFrom = 0; }
        else { numberRangeFrom = Integer.parseInt(savedRangeFrom); }

        if (rangeToTextField.getText().equals("")) {numberRangeTo = 100;}
        else { numberRangeTo = Integer.parseInt(savedRangeTo); }

        if (allowedTriesTextField.getText().equals("")) {numberOfAllowedTries = Double.POSITIVE_INFINITY;}
        else { numberOfAllowedTries = Integer.parseInt(savedAllowedTries); }
    }

    public void backToMenuAction() throws IOException {
        rootController.loadMenuScreen();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
