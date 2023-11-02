package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Alert.AlertType;

public class TransactionManagerController
{

    public AccountDatabase db = new AccountDatabase();
    @FXML
    public TextField firstName, lastName;
    @FXML
    public RadioButton checking, collegeChecking, savings, market, nb, newark,camden;
    public DatePicker dob;
    public ToggleGroup accountGroup;
    public TextField deposit;
    public CheckBox loyal;
    public ToggleGroup campusGroup;

    //public ToggleGroup accountTypeGroup;
    @FXML
    private Label welcomeText;


    @FXML
    protected void onHelloButtonClick()
    {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
//    @FXML
//    private void initialize() {
//        accountGroup = new ToggleGroup();
//        checking.setToggleGroup(accountGroup);
//        collegeChecking.setToggleGroup(accountGroup);
//        savings.setToggleGroup(accountGroup);
//        market.setToggleGroup(accountGroup);
//    }
@FXML
public void initialize() {

        nb.setToggleGroup(campusGroup);
        newark.setToggleGroup(campusGroup);
        camden.setToggleGroup(campusGroup);

    deposit.addEventFilter(KeyEvent.KEY_TYPED, event -> {
        String character = event.getCharacter();
        if (!character.matches("[0-9]")) {
            event.consume(); // Consume the event to prevent non-numeric input
        }
    });

    nb.setDisable(true);
    newark.setDisable(true);
    camden.setDisable(true);
    loyal.setDisable(true);
    // Add an event handler for the collegeChecking RadioButton
    collegeChecking.setOnAction(e -> {
        if (collegeChecking.isSelected()) {
            // Enable the options when collegeChecking is selected
            nb.setDisable(false);
            newark.setDisable(false);
            camden.setDisable(false);
            loyal.setDisable(true);
        }
    });
    checking.setOnAction(e -> {
        if (checking.isSelected()) {
            // Enable the options when collegeChecking is selected
            nb.setDisable(true);
            newark.setDisable(true);
            camden.setDisable(true);
            loyal.setDisable(true);

        }
    });
    savings.setOnAction(e -> {
        if (savings.isSelected()) {
            // Enable the options when collegeChecking is selected
            nb.setDisable(true);
            newark.setDisable(true);
            camden.setDisable(true);
            loyal.setDisable(false);

        }
    });
    market.setOnAction(e -> {
        if (market.isSelected()) {
            // Enable the options when collegeChecking is selected
            nb.setDisable(true);
            newark.setDisable(true);
            camden.setDisable(true);
            loyal.setDisable(false);
        }
    });

}

    @FXML
    private void openAccount(ActionEvent event)
    {
        String firstNameStr = firstName.getText();
        String lastNameStr = lastName.getText();
        String dobStr = dob.getValue().toString();
        Date userDOB = new Date(dob.getValue().getMonthValue(), dob.getValue().getDayOfMonth(), dob.getValue().getYear());
        if (!userDOB.isValid())
        {
            //do something here
        }
        Profile user = new Profile(firstNameStr,lastNameStr,userDOB);
        String depositStr = deposit.getText();
        double deposit = Double.parseDouble(depositStr);
        //check for deposit amount

        if (checking.isSelected()) {
            Account account = new Checking(user, deposit);
            db.open(account);
            //check for return
        }
        if(savings.isSelected())
        {
            if(loyal.isSelected())
            {
                Account account = new Savings(user, deposit, true);
                db.open(account);
            }
            else{
                Account account = new Savings(user, deposit, false);
                db.open(account);
            }
        }
        //do money saving deposit check, for simplicity sake i am not gonna do it for now
        if(market.isSelected())
        {
            if(deposit>2000) {
                Account account = new MoneyMarket(user, deposit);
                db.open(account);
            }
        }

    }
    @FXML
    private void closeAccount(ActionEvent event)
    {

    }

    //make combo box for account type


}