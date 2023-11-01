package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class TransactionManagerController
{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick()
    {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    //make combo box for account type
    ComboBox<String> cmb = new ComboBox<>();


}