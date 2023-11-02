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
    public TextField depfirstName, deplastName;
    public TextField witfirstName, witlastName;
    @FXML
    public RadioButton checking, collegeChecking, savings, market, nb, newark,camden;
    public RadioButton closechecking,closecollegeChecking,closesavings, closemarket, closenb, closenewark, closecamden;
    public RadioButton depchecking, depcollegeChecking, depsavings, depmarket, depnb, depnewark, depcamden;

    public RadioButton witchecking, witcollegeChecking, witsavings, witmarket, witnb, witnewark, witcamden;
    public DatePicker dob, closedob, depdob, witdob;
    public ToggleGroup accountGroup,closeaccountGroup, depaccountGroup, witaccountGroup;
    public TextField balance, deposit, withdraw;
    public CheckBox loyal, closeloyal;
    public ToggleGroup campusGroup,closecampusGroup, depcampusGroup, witcampusGroup;

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

        checking.setToggleGroup(accountGroup);
        collegeChecking.setToggleGroup(accountGroup);
        savings.setToggleGroup(accountGroup);
        market.setToggleGroup(accountGroup);

        closechecking.setToggleGroup(closeaccountGroup);
        closecollegeChecking.setToggleGroup(closeaccountGroup);
        closemarket.setToggleGroup(closeaccountGroup);
        closesavings.setToggleGroup(closeaccountGroup);

        closenb.setToggleGroup(closecampusGroup);
        closenewark.setToggleGroup(closecampusGroup);
        closecamden.setToggleGroup(closecampusGroup);

        depchecking.setToggleGroup(depaccountGroup);
        depcollegeChecking.setToggleGroup(depaccountGroup);
        depmarket.setToggleGroup(depaccountGroup);
        depsavings.setToggleGroup(depaccountGroup);

        depnb.setToggleGroup(depcampusGroup);
        depnewark.setToggleGroup(depcampusGroup);
        depcamden.setToggleGroup(depcampusGroup);

        witchecking.setToggleGroup(witaccountGroup);
        witcollegeChecking.setToggleGroup(witaccountGroup);
        witmarket.setToggleGroup(witaccountGroup);
        witsavings.setToggleGroup(witaccountGroup);

        witnb.setToggleGroup(witcampusGroup);
        witnewark.setToggleGroup(witcampusGroup);
        witcamden.setToggleGroup(witcampusGroup);

    balance.addEventFilter(KeyEvent.KEY_TYPED, event -> {
        String character = event.getCharacter();
        if (!character.matches("[0-9]")) {
            event.consume(); // Consume the event to prevent non-numeric input
        }
    });
    deposit.addEventFilter(KeyEvent.KEY_TYPED, event -> {
        String character = event.getCharacter();
        if (!character.matches("[0-9]")) {
            event.consume(); // Consume the event to prevent non-numeric input
        }
    });

    withdraw.addEventFilter(KeyEvent.KEY_TYPED, event -> {
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

    depnb.setDisable(true);
    depnewark.setDisable(true);
    depcamden.setDisable(true);

    witnb.setDisable(true);
    witnewark.setDisable(true);
    witcamden.setDisable(true);

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



    depcollegeChecking.setOnAction(e -> {
        if (depcollegeChecking.isSelected()) {
            depnb.setDisable(false);
            depnewark.setDisable(false);
            depcamden.setDisable(false);
        }
    });
    depchecking.setOnAction(e -> {
        if (depchecking.isSelected()) {
            depnb.setDisable(true);
            depnewark.setDisable(true);
            depcamden.setDisable(true);
        }
    });
    depsavings.setOnAction(e -> {
        if (depsavings.isSelected()) {
            depnb.setDisable(true);
            depnewark.setDisable(true);
            depcamden.setDisable(true);
        }
    });
    depmarket.setOnAction(e -> {
        if (depmarket.isSelected()) {
            depnb.setDisable(true);
            depnewark.setDisable(true);
            depcamden.setDisable(true);
        }
    });



    witcollegeChecking.setOnAction(e -> {
        if (witcollegeChecking.isSelected()) {
            witnb.setDisable(false);
            witnewark.setDisable(false);
            witcamden.setDisable(false);
        }
    });
    witchecking.setOnAction(e -> {
        if (witchecking.isSelected()) {
            witnb.setDisable(true);
            witnewark.setDisable(true);
            witcamden.setDisable(true);
        }
    });
    witsavings.setOnAction(e -> {
        if (witsavings.isSelected()) {
            witnb.setDisable(true);
            witnewark.setDisable(true);
            witcamden.setDisable(true);
        }
    });
    witmarket.setOnAction(e -> {
        if (witmarket.isSelected()) {
            witnb.setDisable(true);
            witnewark.setDisable(true);
            witcamden.setDisable(true);
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
        //store in variablle because calling it multiple times will give it diff results
        boolean opened = db.open(account);
        if(!opened)
        {

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
        //store in variable because calling it multiple times will give it diff results
        boolean opened = db.open(account);
        if(!opened)
        {

        }
    }

    @FXML
    private void depositAccount()
    {
        String firstNameStr = depfirstName.getText();
        String lastNameStr = deplastName.getText();
        String dobStr = depdob.getValue().toString();
        Date userDOB = new Date(depdob.getValue().getMonthValue(), depdob.getValue().getDayOfMonth(), depdob.getValue().getYear());
        if (!userDOB.isValid())
        {
            //error label for invalid dob, toggle visibility on
        }
        Profile user = new Profile(firstNameStr,lastNameStr,userDOB);
        String depositStr = deposit.getText();
        double deposit = Double.parseDouble(depositStr);
        //check for balance amount
        if (deposit <= 0)
        {
            //error label that says balance must be positive, toggle visibility on
        }
        Account account = null;
        if (depchecking.isSelected())
        {
            account = new Checking(user, deposit);
        }
        else if(depsavings.isSelected())
        {

                account = new Savings(user, deposit, true);

        }
        else if(depmarket.isSelected())
        {

                account = new MoneyMarket(user, deposit);

        }
        else if(depcollegeChecking.isSelected())
        {
            CollegeChecking collegeAcc = new CollegeChecking(user, deposit, null);
            if(depnb.isSelected())
            {
                collegeAcc.setCampus(Campus.NEW_BRUNSWICK);
            }
            else if(depnewark.isSelected())
            {
                collegeAcc.setCampus(Campus.NEWARK);
            }
            else if(depcamden.isSelected())
            {
                collegeAcc.setCampus(Campus.CAMDEN);
            }
            account = collegeAcc;
        }
        db.deposit(account);


    }

    @FXML
    private void withdrawAccount()
    {
        String firstNameStr = witfirstName.getText();
        String lastNameStr = witlastName.getText();
        String dobStr = witdob.getValue().toString();
        Date userDOB = new Date(witdob.getValue().getMonthValue(), witdob.getValue().getDayOfMonth(), witdob.getValue().getYear());
        if (!userDOB.isValid())
        {
            //error label for invalid dob, toggle visibility on
        }
        Profile user = new Profile(firstNameStr,lastNameStr,userDOB);
        String withdrawStr = withdraw.getText();
        double withdraw = Double.parseDouble(withdrawStr);
        //check for balance amount
        if (withdraw <= 0)
        {
            //error label that says balance must be positive, toggle visibility on
        }
        Account account = null;
        if (witchecking.isSelected())
        {
            account = new Checking(user, withdraw);
        }
        else if(witsavings.isSelected())
        {

            account = new Savings(user, withdraw, true);

        }
        else if(witmarket.isSelected())
        {

            account = new MoneyMarket(user, withdraw);

        }
        else if(witcollegeChecking.isSelected())
        {
            CollegeChecking collegeAcc = new CollegeChecking(user, withdraw, null);
            if(witnb.isSelected())
            {
                collegeAcc.setCampus(Campus.NEW_BRUNSWICK);
            }
            else if(witnewark.isSelected())
            {
                collegeAcc.setCampus(Campus.NEWARK);
            }
            else if(witcamden.isSelected())
            {
                collegeAcc.setCampus(Campus.CAMDEN);
            }
            account = collegeAcc;
        }
        boolean withdrew = db.withdraw(account);
        if(!withdrew)
        {
            //failed
        }

    }




}