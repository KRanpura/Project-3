package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Scanner;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;

public class TransactionManagerController
{

    private static final double DAYS_IN_YEAR = 365.25;
    private static final int MAX_COLLEGE_AGE = 24;
    private static final int MIN_AGE_FOR_ACCOUNT = 16;
    public AccountDatabase db = new AccountDatabase();
    @FXML
    public TextField firstName, lastName;
    public TextField closefirstName, closelastName;
    public TextField depfirstName, deplastName;
    public TextField witfirstName, witlastName;
    public TextArea displayField;
    @FXML
    public RadioButton checking, collegeChecking, savings, market, nb, newark,camden;
    public RadioButton closechecking,closecollegeChecking,closesavings, closemarket, closenb, closenewark, closecamden;
    public RadioButton depchecking, depcollegeChecking, depsavings, depmarket, depnb, depnewark, depcamden;
    public RadioButton witchecking, witcollegeChecking, witsavings, witmarket, witnb, witnewark, witcamden;
    public DatePicker dob, closedob, depdob, witdob;
    public ToggleGroup accountGroup,closeaccountGroup, depaccountGroup, witaccountGroup;
    public TextField balance, deposit, withdraw;
    public CheckBox loyal, closeloyal;
    public ToggleGroup campusGroup,closecampusGroup;
    public Button loadAccounts;

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

        witchecking.setToggleGroup(witaccountGroup);
        witcollegeChecking.setToggleGroup(witaccountGroup);
        witmarket.setToggleGroup(witaccountGroup);
        witsavings.setToggleGroup(witaccountGroup);


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
        if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || balance.getText().isEmpty() || dob.getValue()==null)
        {
            displayError("Please fill in missing fields!");
            return;
        }
        if(!(checking.isSelected()|| collegeChecking.isSelected()||savings.isSelected()||market.isSelected()))
        {
            displayError("Please choose an account type!");
            return;
        }
        if(collegeChecking.isSelected())
        {
            if(!(nb.isSelected() || newark.isSelected() || camden.isSelected()))
            {
                displayError("Please choose a campus!");
                return;
            }
        }
        String firstNameStr = firstName.getText();
        String lastNameStr = lastName.getText();
        //String dobStr = dob.getValue().toString();
        Date userDOB = new Date(dob.getValue().getMonthValue(), dob.getValue().getDayOfMonth(), dob.getValue().getYear());
        Date today = new Date();
        if (!userDOB.isValid())
        {
            String error = "DOB invalid:" + userDOB.toString() + " not a valid calendar date!";
            displayError(error);
            return;
        }
        if (userDOB.compareTo(today) < 0)
        {
            displayError("DOB invalid: " + userDOB.toString() + " cannot be today or a future day!");
            return;
        }
        else if (userDOB.compareTo(today) / DAYS_IN_YEAR < MIN_AGE_FOR_ACCOUNT)
        {
            displayError("DOB invalid: " + userDOB.toString() + " under 16.");
            return;
        }
        Profile user = new Profile(firstNameStr,lastNameStr,userDOB);
        String balanceStr = balance.getText();
        double balance = Double.parseDouble(balanceStr);
        if (balance <= 0)
        {
            displayError("Initial deposit cannot be 0 or negative.");
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
                displayError("Minimum of $2000 to open a Money Market account.");
                return;
            }
        }
        else if(collegeChecking.isSelected())
        {
            CollegeChecking collegeAcc = new CollegeChecking(user, balance, null);
            if (userDOB.compareTo(today) / DAYS_IN_YEAR > MAX_COLLEGE_AGE)
            {
                displayError("DOB invalid: " + userDOB.toString() + " over 24.");
                return;
            }
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
            displayError(user.toString() + "(" + account.getTypeInitial() + ") is already in the database");
            return;
        }
        else
        {
            displayMessage(user.toString() + "(" + account.getTypeInitial() + ") opened.");
        }
    }

    @FXML
    private void closeAccount()
    {
        if(closefirstName.getText().isEmpty() || closelastName.getText().isEmpty() ||  closedob.getValue()==null)
        {
            displayError("Please fill in missing fields!");
            return;
        }
        if(!(closechecking.isSelected()|| closecollegeChecking.isSelected()||closesavings.isSelected()||closemarket.isSelected()))
        {
            displayError("Please choose an account type!");
            return;
        }
        if(closecollegeChecking.isSelected())
        {
            if(!(closenb.isSelected() || closenewark.isSelected() || closecamden.isSelected()))
            {
                displayError("Please choose a campus!");
                return;
            }
        }
        String firstNameStr = closefirstName.getText();
        String lastNameStr = closelastName.getText();
      //  String dobStr = closedob.getValue().toString();
        Date userDOB = new Date(closedob.getValue().getMonthValue(), closedob.getValue().getDayOfMonth(),
                closedob.getValue().getYear());
        if (!userDOB.isValid())
        {
            String error = "DOB invalid:" + userDOB.toString() + "cannot be today or a future day.";
            displayError(error);
            return;
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
        boolean closed = db.close(account);
        if(!closed)
        {
            displayError(user.toString() + "(" + account.getTypeInitial() + ") is not in the database.");
        }
        else
        {
            displayError(user.toString() + "(" + account.getTypeInitial() + ") has been closed.");
        }
    }

    @FXML
    private void depositAccount()
    {
        if(depfirstName.getText().isEmpty() || deplastName.getText().isEmpty() || deposit.getText().isEmpty() || depdob.getValue()==null)
        {
            displayError("Please fill in missing fields!");
            return;
        }
        if(!(depchecking.isSelected()|| depcollegeChecking.isSelected()||depsavings.isSelected()||depmarket.isSelected()))
        {
            displayError("Please choose an account type!");
            return;
        }
        String firstNameStr = depfirstName.getText();
        String lastNameStr = deplastName.getText();
      //  String dobStr = depdob.getValue().toString();
        Date userDOB = new Date(depdob.getValue().getMonthValue(), depdob.getValue().getDayOfMonth(), depdob.getValue().getYear());
        if (!userDOB.isValid())
        {
            String error = "DOB invalid:" + userDOB.toString() + "cannot be today or a future day.";
            displayError(error);
            return;
        }
        Profile user = new Profile(firstNameStr,lastNameStr,userDOB);
        String depositStr = deposit.getText();
        double deposit = Double.parseDouble(depositStr);
        if (deposit <= 0)
        {
            displayError("Deposit - amount cannot be 0 or negative.");
            return;
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
            account = collegeAcc;
        }
        if (!db.contains(account))
        {
            displayError(user.toString() + "(" + account.getTypeInitial() + ") not in database.");
        }
        else
        {
            db.deposit(account);
            displayMessage(user.toString() + "(" + account.getTypeInitial() + ") Deposit - balance updated.");
        }
    }

    @FXML
    private void withdrawAccount()
    {
        if(witfirstName.getText().isEmpty() || witlastName.getText().isEmpty() || withdraw.getText().isEmpty() || witdob.getValue()==null)
        {
            displayError("Please fill in missing fields!");
            return;
        }
        if(!(witchecking.isSelected()|| witcollegeChecking.isSelected()||witsavings.isSelected()||witmarket.isSelected()))
        {
            displayError("Please choose an account type!");
            return;
        }

        String firstNameStr = witfirstName.getText();
        String lastNameStr = witlastName.getText();
        String dobStr = witdob.getValue().toString();
        Date userDOB = new Date(witdob.getValue().getMonthValue(), witdob.getValue().getDayOfMonth(), witdob.getValue().getYear());
        if (!userDOB.isValid())
        {
            String error = "DOB invalid:" + userDOB.toString() + "cannot be today or a future day.";
            displayError(error);
            return;
        }
        Profile user = new Profile(firstNameStr,lastNameStr,userDOB);
        String withdrawStr = withdraw.getText();
        double withdraw = Double.parseDouble(withdrawStr);
        if (withdraw <= 0)
        {
            displayError("Withdraw - amount cannot be 0 or negative.");
            return;
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
            account = collegeAcc;
        }
        if (!db.contains(account))
        {
            String s = user.toString() + "(" + account.getTypeInitial() + ") is not in the database.";
            displayError(s);
            return;
        }
        boolean withdrew = db.withdraw(account);
        if(!withdrew)
        {
            displayError("Withdraw- insufficient funds!");
            return;
        }
        else
        {
            displayMessage("Withdraw- balance updated.");
        }

    }

    private void displayError (String s)
    {
//        if (!verbose)
//            return;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.show();
    }

    private void displayMessage(String s)
    {
//        if (!verbose)
//            return;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.show();
    }
    @FXML
    private void printAllAccounts()
    {
        String displayString = db.printSorted();
        displayField.setText(displayString);
    }

    @FXML
    private void printInterestsAndFees()
    {
        String displayString = db.printFeesAndInterests();
        displayField.setText(displayString);
    }

    @FXML
    private void updateAccWithFees()
    {
        String displayString = db.printUpdatedBalances();
        displayField.setText(displayString);
    }

    @FXML
    private void loadAccounts(ActionEvent event) throws FileNotFoundException
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open text file");

        Stage stage = (Stage) loadAccounts.getScene().getWindow();
        File textFile = fileChooser.showOpenDialog(stage);
        File file = new File (textFile.getAbsolutePath());

        if (file != null)
        {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine().trim(); // Read the entire line
                if (!line.isEmpty())
                {
                    String[] accTypeInfo = line.split(",", 2); // Split the line into acc type and arguments
                    String accType = accTypeInfo[0];
                    String userString = (accTypeInfo.length > 1) ? accTypeInfo[1] : "";
                    userString = userString.trim();

                    String [] accInfo = userString.split(",");
                    String firstName = accInfo[0];
                    String lastName = accInfo[1];
                    String dateStr = accInfo[2];
                    int month = Integer.parseInt(dateStr.split("/")[0]);
                    int day = Integer.parseInt(dateStr.split("/")[1]);
                    int year = Integer.parseInt(dateStr.split("/")[2]);
                    Date dob = new Date(month, day, year);
                    Profile profile = new Profile(firstName, lastName, dob);
                    double balance = Double.parseDouble(accInfo[3].trim());
                    switch (accType)
                    {
                        case "C":
                            Checking checking = new Checking (profile, balance);
                            db.open(checking);
                            break;
                        case "CC":
                            Campus campus = Campus.values()[Integer.parseInt(accInfo[4])]; // Parse the campus code
                            CollegeChecking collegeChecking = new CollegeChecking(profile, balance, campus);
                            db.open(collegeChecking);
                            break;
                        case "S":
                            Savings savings = null;
                            if (Integer.parseInt(accInfo[4]) == 0)
                            {
                                savings = new Savings(profile, balance, false);
                            }
                            else //if 1
                            {
                                savings = new Savings(profile, balance, true);
                            }
                            db.open(savings);
                            break;
                        case "MM":
                            MoneyMarket market = new MoneyMarket(profile, balance);
                            db.open(market);
                            break;
                        default:
                            break;
                    }
                }
            }
            displayMessage("Accounts loaded successfully!");
        }
    }
}