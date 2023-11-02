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
    public TextField depOrWithFirstName, depOrWithLastName;
    @FXML
    public RadioButton checking, collegeChecking, savings, market, nb, newark,camden;
    public RadioButton closechecking,closecollegeChecking,closesavings, closemarket, closenb, closenewark, closecamden;
    public RadioButton depWithChecking, depWithCollegeChecking, depWithSavings, depWithMarket, depWithNB, depWithNewark,
                        depWithCamden;
    public DatePicker dob, closedob, depOrWithdob;
    public ToggleGroup accountGroup,closeaccountGroup, depWithAccountGroup;
    public TextField balance, depOrWithBalance;
    public CheckBox loyal, closeloyal;
    public ToggleGroup campusGroup,closecampusGroup, depWithCampusGroup;

    //public ToggleGroup accountTypeGroup;
    @FXML
    private Label welcomeText;


//    @FXML
//    protected void onHelloButtonClick()
//    {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
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
    private void openAccount() //I removed the ActionEvent event param bc we never use it
    {
        String firstNameStr = firstName.getText();
        String lastNameStr = lastName.getText();
        String dobStr = dob.getValue().toString();
        Date userDOB = new Date(dob.getValue().getMonthValue(), dob.getValue().getDayOfMonth(), dob.getValue().getYear());
        if (!userDOB.isValid())
        {
            //error label for invalid dob, toggle visibility on
        }
        Profile user = new Profile(firstNameStr,lastNameStr,userDOB);
        String balanceStr = balance.getText();
        double balance = Double.parseDouble(balanceStr);
        //check for balance amount
        if (balance <= 0)
        {
            //error label that says balance must be positive, toggle visibility on
        }
        Account account = null;
        if (checking.isSelected())
        {
            account = new Checking(user, balance);
        }
        else if(savings.isSelected())
        {
            if(loyal.isSelected())
            {
                account = new Savings(user, balance, true);
            }
            else
            {
                account = new Savings(user, balance, false);
            }
        }
        else if(market.isSelected())
        {
            if(balance>2000)
            {
                account = new MoneyMarket(user, balance);
            }
            else
            {
                //display error here by making a label and having its visibility set to false,
            }
        }
        else if(collegeChecking.isSelected())
        {
            CollegeChecking collegeAcc = new CollegeChecking(user, balance, null);
            if(nb.isSelected())
            {
                collegeAcc.setCampus(Campus.NEW_BRUNSWICK);
            }
            else if(newark.isSelected())
            {
                collegeAcc.setCampus(Campus.NEWARK);
            }
            else if(camden.isSelected())
            {
                collegeAcc.setCampus(Campus.CAMDEN);
            }
            account = collegeAcc;
        }
        if (!db.open(account))
        {
            //print error- this user is already in the database
        }
    }

    @FXML
    private void closeAccount()
    {
        String firstNameStr = closefirstName.getText();
        String lastNameStr = closelastName.getText();
        String dobStr = closedob.getValue().toString();
        Date userDOB = new Date(closedob.getValue().getMonthValue(), closedob.getValue().getDayOfMonth(),
                closedob.getValue().getYear());
        if (!userDOB.isValid())
        {
            //make label with error message and toggle visibility on
        }
        Profile user = new Profile(firstNameStr,lastNameStr,userDOB);
        Account account = null;
        if (closechecking.isSelected())
        {
            account = new Checking(user, 0);
        }
        else if(closesavings.isSelected())
        {
            if(closeloyal.isSelected())
            {
                account = new Savings(user, 0, true);
            }
            else
            {
                account = new Savings(user, 0, false);
            }
        }
        else if(closemarket.isSelected())
        {
            account = new MoneyMarket(user, 0);
        }
        else if(closecollegeChecking.isSelected())
        {
            CollegeChecking collegeAcc = new CollegeChecking(user, 0, null);
            if(closenb.isSelected())
            {
                collegeAcc.setCampus(Campus.NEW_BRUNSWICK);
            }
            else if(closenewark.isSelected())
            {
                collegeAcc.setCampus(Campus.NEWARK);
            }
            else if(closecamden.isSelected())
            {
                collegeAcc.setCampus(Campus.CAMDEN);
            }
            account = collegeAcc;
        }
        if (!db.close(account))
        {
            //print error- this user is already in the database
        }
    }

    @FXML
    private void depositAccount()
    {

    }

    @FXML
    private void withdrawAccount()
    {

    }




}