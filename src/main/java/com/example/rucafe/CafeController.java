package com.example.rucafe;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CafeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}