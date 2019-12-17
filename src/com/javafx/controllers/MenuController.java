package com.javafx.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    Button playButton, howToPlayButton, optionsButton;


    private RootController rootController;

    // add graphic to buttons with 'button.setGraphic...'

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* SETTING ICONS FOR MENU BUTTONS
        Image playIcon = new Image(getClass().getResourceAsStream("playLogo.png"));
        ImageView playIconView = new ImageView(playIcon);
        playIconView.setFitHeight(30);
        playIconView.setFitWidth(30);

        Image optionsIcon = new Image(getClass().getResourceAsStream("optionsLogo.png"));
        ImageView optionsIconView = new ImageView(optionsIcon);
        optionsIconView.setFitWidth(30);
        optionsIconView.setFitHeight(30);

        Image howToPlayIcon = new Image(getClass().getResourceAsStream("play.png"));
        ImageView howToPlayIconView = new ImageView(howToPlayIcon);
        howToPlayIconView.setFitHeight(30);
        howToPlayIconView.setFitWidth(30);

        howToPlayButton.setGraphic(howToPlayIconView);
        optionsButton.setGraphic(optionsIconView);
        playButton.setGraphic(playIconView); */
    }

    @FXML
    public void playOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx/screens/PlayScreen.fxml"));
        Pane pane = loader.load();
        PlayController playController = loader.getController();
        playController.setRootController(rootController);
        rootController.setScreen(pane);
    }

    @FXML
    public void howToPlayOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx/screens/HowToPlayScreen.fxml"));
        Pane pane = loader.load();
        HowToPlayController howToPlayController = loader.getController();
        howToPlayController.setRootController(rootController);
        rootController.setScreen(pane);
    }

    @FXML
    public void optionsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx/screens/OptionScreen.fxml"));
        Pane pane = loader.load();
        OptionsController optionsController = loader.getController();
        optionsController.setRootController(rootController);
        rootController.setScreen(pane);
    }

    @FXML
    public void exitOnAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
