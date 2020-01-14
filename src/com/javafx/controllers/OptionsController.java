package com.javafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {

    @FXML
    private CheckBox musicCheckBox;
    @FXML
    private Slider musicVolumeSlider;
    @FXML
    private TextField rangeFromTextField, rangeToTextField, allowedTriesTextField;
    @FXML
    private ComboBox<String> changeMusicBox;

    static int numberRangeFrom = 0, numberRangeTo = 100;
    static double numberOfAllowedGuesses = Double.POSITIVE_INFINITY;

    private static String savedRangeFrom = "", savedRangeTo = "", savedAllowedTries = "", savedChangeMusicBox = "Video Game Level";
    private static boolean savedMusicCheckBox = true;

    static MediaPlayer backgroundMusicPlayer;
    private static String backgroundMusicPath = "C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameBackgroundMusic1.mp3";

    static Stage warningStage;
    private boolean checkWarning = false;
    private RootController rootController;
    private double backgroundVolume;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicVolumeSlider.setValue(backgroundMusicPlayer.getVolume() * 100);
        musicVolumeSlider.valueProperty().addListener(observable -> backgroundMusicPlayer.setVolume(musicVolumeSlider.getValue() / 100));

        changeMusicBox.getItems().addAll("Video Game Level", "Walk through Meadow", "Discovering New Place");
        changeMusicBox.setStyle("-fx-font: 15px \"Berlin Sans FB Demi\";");

        rangeFromTextField.setPadding(new Insets(0, 30, 0, 30));
        rangeToTextField.setPadding(new Insets(0, 30, 0, 30));
        allowedTriesTextField.setPadding(new Insets(0, 30, 0, 30));

        rangeFromTextField.setText(savedRangeFrom);
        rangeToTextField.setText(savedRangeTo);
        allowedTriesTextField.setText(savedAllowedTries);
        musicCheckBox.setSelected(savedMusicCheckBox);
        changeMusicBox.setValue(savedChangeMusicBox);

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
        allowedTriesTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                allowedTriesTextField.setText(newValue.replaceAll("[^\\d]",""));
            }
        });
    }

    public static void setBackgroundMusicPath(String backgroundMusicPath) {
        OptionsController.backgroundMusicPath = backgroundMusicPath;
    }

    public static void backgroundMusic() {
        Media backgroundMusic = new Media(Paths.get(backgroundMusicPath).toUri().toString());
        backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusicPlayer.play();
    }

    @FXML
    private void changeTheMusicAction() {
        changeTheMusicMethod(changeMusicBox.getValue());
    }

    private void changeTheMusicMethod(String songName) {
        switch (songName) {
            case "Video Game Level":
                backgroundVolume = backgroundMusicPlayer.getVolume();
                setBackgroundMusicPath("C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameBackgroundMusic1.mp3");
                if (backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    backgroundMusicPlayer.stop();
                    backgroundMusic();
                }
                backgroundMusicPlayer.setVolume(backgroundVolume);
                break;
            case "Walk through Meadow":
                backgroundVolume = backgroundMusicPlayer.getVolume();
                setBackgroundMusicPath("C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameBackgroundMusic2.mp3");
                if (backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    backgroundMusicPlayer.stop();
                    backgroundMusic();
                }
                backgroundMusicPlayer.setVolume(backgroundVolume);
                break;
            case "Discovering New Place":
                backgroundVolume = backgroundMusicPlayer.getVolume();
                setBackgroundMusicPath("C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameBackgroundMusic3.mp3");
                if (backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    backgroundMusicPlayer.stop();
                    backgroundMusic();
                }
                backgroundMusicPlayer.setVolume(backgroundVolume);
                break;
        }
    }


    @FXML
    private void pauseMusicAction() {
        if (!musicCheckBox.isSelected())
            backgroundMusicPlayer.pause();
        else {
            backgroundVolume = backgroundMusicPlayer.getVolume();
            backgroundMusic();
            backgroundMusicPlayer.setVolume(backgroundVolume);
        }
    }

    private void pauseMusicExit() {
        if (savedMusicCheckBox)
            backgroundMusic();
        else
            backgroundMusicPlayer.stop();
    }

    @FXML
    private void saveOptionsAction() {
        savedRangeFrom = rangeFromTextField.getText();
        savedRangeTo = rangeToTextField.getText();
        savedAllowedTries = allowedTriesTextField.getText();
        savedMusicCheckBox = musicCheckBox.isSelected();
        savedChangeMusicBox = changeMusicBox.getValue();

        if (rangeFromTextField.getText().equals("")) { numberRangeFrom = 0; }
        else { numberRangeFrom = Integer.parseInt(savedRangeFrom); }

        if (rangeToTextField.getText().equals("")) {numberRangeTo = 100;}
        else { numberRangeTo = Integer.parseInt(savedRangeTo); }

        if (allowedTriesTextField.getText().equals("")) {numberOfAllowedGuesses = Double.POSITIVE_INFINITY;}
        else { numberOfAllowedGuesses = Integer.parseInt(savedAllowedTries); }
    }

    @FXML
    private void backToMenuAction() throws IOException {


        if ((!savedRangeFrom.equals(rangeFromTextField.getText())
                || !savedRangeTo.equals(rangeToTextField.getText())
                || !savedAllowedTries.equals(allowedTriesTextField.getText())
                || !savedMusicCheckBox == musicCheckBox.isSelected()
                || !savedChangeMusicBox.equals(changeMusicBox.getValue()))
                && !checkWarning) {
            checkWarning = true;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/javafx/screens/warningScreen.fxml"));
            Scene scene = new Scene(loader.load());
            warningStage = new Stage();
            warningStage.setScene(scene);
            warningStage.initStyle(StageStyle.UNDECORATED);
            warningStage.show();
        } else {


            if (savedMusicCheckBox != musicCheckBox.isSelected())
                pauseMusicExit();

            if (!savedChangeMusicBox.equals(changeMusicBox.getValue())) {
                changeTheMusicMethod(savedChangeMusicBox);
            }


            rootController.loadMenuScreen();
        }
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
