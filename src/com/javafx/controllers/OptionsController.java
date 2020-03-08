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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
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

    private static MediaPlayer backgroundMusicPlayer;
    private double currentBackgroundVolume;
    private static String backgroundMusicPath =
            "file:///C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameBackgroundMusic_VideoGameLevel.mp3";
    static int numberRangeFrom = 0, numberRangeTo = 100;
    static double numberOfAllowedGuesses = Double.POSITIVE_INFINITY;
    private static String savedRangeFrom = "", savedRangeTo = "", savedAllowedTries = "", savedChangeMusicBox = "Video Game Level";
    protected static boolean savedMusicCheckBox = true;
    private static double savedBackgroundVolume = 100.0;
    static Stage warningStage;
    private boolean checkWarning = false;
    private RootController rootController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicCheckBox.selectedProperty()
                .addListener((observable, oldValue, newValue) -> checkWarning = savedMusicCheckBox == musicCheckBox.isSelected());
        rangeFromTextField.textProperty()
                .addListener((observableValue, oldValue, newValue) -> checkWarning = savedRangeFrom.equals(rangeFromTextField.getText()));
        rangeToTextField.textProperty()
                .addListener((observableValue, oldValue, newValue) -> checkWarning = savedRangeTo.equals(rangeToTextField.getText()));
        allowedTriesTextField.textProperty()
                .addListener((observableValue, oldValue, newValue) -> checkWarning = savedAllowedTries.equals(allowedTriesTextField.getText()));
        changeMusicBox.valueProperty()
                .addListener((observableValue, oldValue, newValue) -> checkWarning = savedChangeMusicBox.equals(changeMusicBox.getValue()));
        musicVolumeSlider.valueProperty()
                .addListener((observableValue, oldValue, newValue) -> checkWarning = savedBackgroundVolume == musicVolumeSlider.getValue());

        musicVolumeSlider.setValue(savedBackgroundVolume);
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
        Media backgroundMusic = new Media(backgroundMusicPath);
        backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusicPlayer.play();
    }

    public static MediaPlayer getBackgroundMusicPlayer() {
        return backgroundMusicPlayer;
    }

    @FXML
    private void changeTheMusicAction() {
        changeTheMusicMethod(changeMusicBox.getValue());
    }

    private void changeTheMusicMethod(String songName) {
        switch (songName) {
            case "Video Game Level":
                currentBackgroundVolume = backgroundMusicPlayer.getVolume();
                setBackgroundMusicPath(
                        "file:///C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameBackgroundMusic_VideoGameLevel.mp3");
                if (backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    backgroundMusicPlayer.stop();
                    backgroundMusic();
                }
                backgroundMusicPlayer.setVolume(currentBackgroundVolume);
                break;
            case "Walk through Meadow":
                currentBackgroundVolume = backgroundMusicPlayer.getVolume();
                setBackgroundMusicPath(
                        "file:///C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameBackgroundMusic_WalkThroughMeadow.mp3");
                if (backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    backgroundMusicPlayer.stop();
                    backgroundMusic();
                }
                backgroundMusicPlayer.setVolume(currentBackgroundVolume);
                break;
            case "Discovering New Place":
                currentBackgroundVolume = backgroundMusicPlayer.getVolume();
                setBackgroundMusicPath(
                        "file:///C:/Users/Stanisław/IdeaProjects/GuessingApp_GUI/src/com/javafx/sounds/gameBackgroundMusic_DiscoveringNewPlace.mp3");
                if (backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    backgroundMusicPlayer.stop();
                    backgroundMusic();
                }
                backgroundMusicPlayer.setVolume(currentBackgroundVolume);
                break;
        }
    }


    @FXML
    private void pauseMusicAction() {
        if (!musicCheckBox.isSelected())
            backgroundMusicPlayer.pause();
        else {
            currentBackgroundVolume = backgroundMusicPlayer.getVolume();
            backgroundMusic();
            backgroundMusicPlayer.setVolume(currentBackgroundVolume);
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
        checkWarning = true;
        savedRangeFrom = rangeFromTextField.getText();
        savedRangeTo = rangeToTextField.getText();
        savedAllowedTries = allowedTriesTextField.getText();
        savedMusicCheckBox = musicCheckBox.isSelected();
        savedChangeMusicBox = changeMusicBox.getValue();
        savedBackgroundVolume = musicVolumeSlider.getValue();

        if (rangeFromTextField.getText().equals("")) { numberRangeFrom = 0; }
        else { numberRangeFrom = Integer.parseInt(savedRangeFrom); }

        if (rangeToTextField.getText().equals("")) {numberRangeTo = 100;}
        else { numberRangeTo = Integer.parseInt(savedRangeTo); }

        if (allowedTriesTextField.getText().equals("")) {numberOfAllowedGuesses = Double.POSITIVE_INFINITY;}
        else { numberOfAllowedGuesses = Integer.parseInt(savedAllowedTries); }
    }

    @FXML
    private void backToMenuAction() throws IOException {
        if (!checkWarning) {
            checkWarning = true;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/javafx/screens/warningScreen.fxml"));
            Scene scene = new Scene(loader.load());
            warningStage = new Stage();
            warningStage.setScene(scene);
            warningStage.initStyle(StageStyle.UNDECORATED);
            warningStage.initModality(Modality.APPLICATION_MODAL);
            warningStage.show();
        } else {
            if (savedMusicCheckBox != musicCheckBox.isSelected())
                pauseMusicExit();
            if (!savedChangeMusicBox.equals(changeMusicBox.getValue())) {
                if (savedMusicCheckBox != musicCheckBox.isSelected())
                    pauseMusicExit();
                else
                    changeTheMusicMethod(savedChangeMusicBox);
            }
            if (savedBackgroundVolume != musicVolumeSlider.getValue()) {
                if (savedMusicCheckBox != musicCheckBox.isSelected())
                    pauseMusicExit();
                else {
                    backgroundMusicPlayer.setVolume(savedBackgroundVolume);
                    musicVolumeSlider.setValue(savedBackgroundVolume);
                }
            }
            rootController.loadMenuScreen();
        }
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
