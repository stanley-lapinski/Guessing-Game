package com.javafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WarningController {

    @FXML
    private Button warningCloseButton;

    @FXML
    private void confirmOnAction() {
        OptionsController.setWarningStage((Stage) warningCloseButton.getScene().getWindow());
        OptionsController.getWarningStage().close();
    }
}
