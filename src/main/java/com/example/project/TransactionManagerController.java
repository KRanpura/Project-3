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
    public TextField closefirstName, closelastName;
    @FXML
    public RadioButton checking, collegeChecking, savings, market, nb, newark,camden;
    public RadioButton closechecking,closecollegeChecking,closesavings, closemarket, closenb, closenewark, closecamden;
    public DatePicker dob, closedob;
    public ToggleGroup accountGroup,closeaccountGroup;
    public TextField balance;
    public CheckBox loyal, closeloyal;
    public ToggleGroup campusGroup,closecampusGroup;

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


        closechecking.setToggleGroup(closeaccountGroup);
        closecollegeChecking.setToggleGroup(closeaccountGroup);
        closemarket.setToggleGroup(closeaccountGroup);
        closesavings.setToggleGroup(closeaccountGroup);

        closenb.setToggleGroup(closecampusGroup);
        closenewark.setToggleGroup(closecampusGroup);
        closecamden.setToggleGroup(closecampusGroup);

    balance.addEventFilter(KeyEvent.KEY_TYPED, event -> {
        String character = event.getCharacter();
        if (!character.matches("[0-9]")) {
            event.consume(); // Consume the event to prevent non-numeric input
        }
    });

    nb.setDisable(true);
    newark.setDisable(true);
    camden.setDisable(true);
    loyal.setDisable(true);

    closenb.setDisable(true);
    closenewark.setDisable(true);
    closecamden.setDisable(true);
    closeloyal.setDisable(true);

    collegeChecking.setOnAction(e -> {
        if (collegeChecking.isSelected()) {
            nb.setDisable(false);
            newark.setDisable(false);
            camden.setDisable(false);
            loyal.setDisable(true);
        }
    });
    checking.setOnAction(e -> {
        if (checking.isSelected()) {
            nb.setDisable(true);
            newark.setDisable(true);
            camden.setDisable(true);
            loyal.setDisable(true);

        }
    });
    savings.setOnAction(e -> {
        if (savings.isSelected()) {
            nb.setDisable(true);
            newark.setDisable(true);
            camden.setDisable(true);
            loyal.setDisable(false);

        }
    });
    market.setOnAction(e -> {
        if (market.isSelected()) {
            nb.setDisable(true);
            newark.setDisable(true);
            camden.setDisable(true);
            loyal.setDisable(false);
        }
    });


    closecollegeChecking.setOnAction(e -> {
        if (closecollegeChecking.isSelected()) {
            closenb.setDisable(false);
            closenewark.setDisable(false);
            closecamden.setDisable(false);
            closeloyal.setDisable(true);
        }
    });
    closechecking.setOnAction(e -> {
        if (closechecking.isSelected()) {
            closenb.setDisable(true);
            closenewark.setDisable(true);
            closecamden.setDisable(true);
            closeloyal.setDisable(true);

        }
    });
    closesavings.setOnAction(e -> {
        if (closesavings.isSelected()) {
            closenb.setDisable(true);
            closenewark.setDisable(true);
            closecamden.setDisable(true);
            closeloyal.setDisable(false);

        }
    });
    closemarket.setOnAction(e -> {
        if (closemarket.isSelected()) {
            closenb.setDisable(true);
            closenewark.setDisable(true);
            closecamden.setDisable(true);
            closeloyal.setDisable(false);
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
        String balanceStr = balance.getText();
        double balance = Double.parseDouble(balanceStr);
        //check for balance amount

        if (checking.isSelected()) {
            Account account = new Checking(user, balance);
            db.open(account);
            //check for return
        }
        if(savings.isSelected())
        {
            if(loyal.isSelected())
            {
                Account account = new Savings(user, balance, true);
                db.open(account);
            }
            else{
                Account account = new Savings(user, balance, false);
                db.open(account);
            }
        }
        //do money saving balance check, for simplicity sake i am not gonna do it for now
        if(market.isSelected())
        {
            if(balance>2000) {
                Account account = new MoneyMarket(user, balance);
                db.open(account);
            }
        }

        //college account stuff
        if(collegeChecking.isSelected())
        {
            if(nb.isSelected())
            {
                Campus newBrunswick = Campus.NEW_BRUNSWICK;
                Account account = new CollegeChecking(user, balance, newBrunswick);
                db.open(account);
            }
            if(newark.isSelected())
            {
                Campus NEWARK = Campus.NEWARK;
                Account account = new CollegeChecking(user, balance, NEWARK);
                db.open(account);
            }
            if(camden.isSelected())
            {
                Campus CAMDEN = Campus.CAMDEN;
                Account account = new CollegeChecking(user, balance, CAMDEN);
                db.open(account);
            }

        }

    }
    @FXML
    private void closeAccount(ActionEvent event)
    {
        String firstNameStr = closefirstName.getText();
        String lastNameStr = closelastName.getText();
        String dobStr = closedob.getValue().toString();
        Date userDOB = new Date(closedob.getValue().getMonthValue(), closedob.getValue().getDayOfMonth(), closedob.getValue().getYear());
        if (!userDOB.isValid())
        {
            //do something here
        }
        Profile user = new Profile(firstNameStr,lastNameStr,userDOB);
        if(closechecking.isSelected())
        {
            Account account = new Checking(user,0);
            db.close(account);
        }

    }


}