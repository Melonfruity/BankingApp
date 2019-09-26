/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapplication;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;

/**
 *
 * @author Eric
 */
public class BankApplication extends Application {
//    
//  Stage window, managerStage, customerStage;
    Stage window;
    Scene loginPage, mngrPage, ctmrPage;
    
    @Override
    public void start(Stage primaryStage) {
        
//        managerStage = new Stage();
//        managerStage.setTitle("Manager Page");
//        managerStage.setScene(mngrPage);
//        managerStage.show();

//        customerStage = new Stage();
//        customerStage.setTitle("Customer Page");
//        customerStage.setScene(ctmrPage);
//        customerStage.show();
        
        window = primaryStage;
        
        final Manager manager = new Manager();
        final Authenticate authenticate = new Authenticate();
        
        //<---------------------------------------------------------------------------------------------------------------------------------->
        //Login Page  <---------------------------------------------------------------------------------------------------------------------->
        //<---------------------------------------------------------------------------------------------------------------------------------->
        
        //Log In Page Console
        Label logInconsolelbl = new Label("Current Activity:");
        GridPane.setConstraints(logInconsolelbl, 0, 4);
        
        TextField logInconsoleOutput = new TextField();
        logInconsoleOutput.setPromptText("Current Activity");
        GridPane.setConstraints(logInconsoleOutput, 1, 4);
        
        //Get Username 
        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 1);
        
        TextField nameInput = new TextField();
        nameInput.setPromptText("username");
        GridPane.setConstraints(nameInput, 1, 1);
        
        //Get Password
        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 2);
        
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("password");
        GridPane.setConstraints(passwordInput, 1, 2);
        
        //Manager Log in Button
        Button mngrLoginBtn = new Button();
        mngrLoginBtn.setText("Log in as Manager");
        mngrLoginBtn.setOnAction(e -> {
            
                if(authenticate.checkCredentials(nameInput.getText(),passwordInput.getText(),"manager")==true)
                    window.setScene(mngrPage);
                else
                    logInconsoleOutput.setText("Log in Failed");
                passwordInput.clear();
                
        });
        GridPane.setConstraints(mngrLoginBtn, 0, 3);
        
        //Customer Log in Button
        Button ctmrLoginBtn = new Button();
        ctmrLoginBtn.setText("Log in as Customer");
        ctmrLoginBtn.setOnAction(e -> {
            
                if(authenticate.checkCredentials(nameInput.getText(),passwordInput.getText(),"customer")==true){
                    window.setScene(ctmrPage);
                }
                else 
                    logInconsoleOutput.setText("Log in Failed");
                
                passwordInput.clear();
                
        });
        GridPane.setConstraints(ctmrLoginBtn, 1, 3);
        
        // Add everything to Login Layout
        GridPane loginLayout = new GridPane();
        loginLayout.setPadding(new Insets(10,10,10,10));
        loginLayout.setVgap(5);
        loginLayout.setHgap(5);

        loginLayout.getChildren().addAll(
                
                nameLabel, 
                nameInput, 
                passwordLabel, 
                passwordInput, 
                mngrLoginBtn,
                ctmrLoginBtn,
                logInconsoleOutput,
                logInconsolelbl
                
        );
        
        Scene loginPage = new Scene(loginLayout, 340, 140);
        window.setTitle("Banking Application");
        window.setScene(loginPage);
        window.show();
        
        //<---------------------------------------------------------------------------------------------------------------------------------->
        //Manager Page  <-------------------------------------------------------------------------------------------------------------------->
        //<---------------------------------------------------------------------------------------------------------------------------------->
        
        // Manager Console 
        Label managerconsolelbl = new Label("Current Activity: ");
        GridPane.setConstraints(managerconsolelbl, 0, 6);
        
        TextField managerconsoleOutput = new TextField();
        managerconsoleOutput.setPromptText("Log in Successful");
        GridPane.setConstraints(managerconsoleOutput, 1, 6);
        
        // Get Customer Username
        Label addUsername = new Label("Add New Customer User:");
        GridPane.setConstraints(addUsername, 0, 1);
        
        TextField addCustomerName = new TextField();
        addCustomerName.setPromptText("username");
        GridPane.setConstraints(addCustomerName, 1, 1);
        
        // Get Customer Password
        Label addUserPW = new Label("Add New Customer Password:");
        GridPane.setConstraints(addUserPW, 0, 2);
        
        TextField addCustomerPW = new TextField();
        addCustomerPW.setPromptText("password");
        GridPane.setConstraints(addCustomerPW, 1, 2);
        
        // Add new Customer
        Button mngrAddBtn = new Button();
        mngrAddBtn.setText("Add New Customer");
        mngrAddBtn.setOnAction(e -> {
            
                try{
                    if(manager.addCustomer(addCustomerName.getText(),addCustomerPW.getText())){
                        managerconsoleOutput.setText("Added: " + addCustomerName.getText());
                        addCustomerName.clear();
                        addCustomerPW.clear();
                    }
                    else{
                        managerconsoleOutput.setText(addCustomerName.getText() + " already exists");
                    }
                }
                catch(Exception ex){
                    managerconsoleOutput.setText("Invalid PW/USER");
                }
                
        });
        GridPane.setConstraints(mngrAddBtn, 1, 3);
        
        // Delete Existing Customer
        Label delUsername = new Label("Delete Customer:");
        GridPane.setConstraints(delUsername, 0, 4);
        
        TextField delCustomerName = new TextField();
        delCustomerName.setPromptText("username");
        GridPane.setConstraints(delCustomerName, 1, 4);
        
        Button mngrDelBtn = new Button();
        mngrDelBtn.setText("Delete Customer");
        mngrDelBtn.setOnAction(e -> {
            
                try{
                    manager.deleteCustomer(delCustomerName.getText());
                    managerconsoleOutput.setText("Deleted: " + delCustomerName.getText());
                    delCustomerName.clear();
                }
                catch(Exception ex){
                    managerconsoleOutput.setText("Invalid USER");
                }
                
        });
        GridPane.setConstraints(mngrDelBtn, 1, 5);
        
        // Manager Logout Button
        Button mngrlogOutBtn = new Button();
        mngrlogOutBtn.setText("Log out");
        mngrlogOutBtn.setOnAction(e -> {
            
                nameInput.clear();
                managerconsoleOutput.clear();
                window.setScene(loginPage);
                
        });
        GridPane.setConstraints(mngrlogOutBtn, 0, 0);
        
        // Add everything to Manager Layout
        GridPane managerLayout = new GridPane();
        managerLayout.setPadding(new Insets(10,10,10,10));
        managerLayout.setVgap(5);
        managerLayout.setHgap(5);
        
        managerLayout.getChildren().addAll(
                
                addUsername, 
                addCustomerName, 
                addUserPW, 
                addCustomerPW, 
                mngrAddBtn, 
                delUsername, 
                delCustomerName, 
                mngrlogOutBtn, 
                mngrDelBtn, 
                managerconsoleOutput,
                managerconsolelbl
                
        );
        
        mngrPage = new Scene(managerLayout, 340, 240);
        
        //<---------------------------------------------------------------------------------------------------------------------------------->
        //Customer Page  <------------------------------------------------------------------------------------------------------------------->
        //<---------------------------------------------------------------------------------------------------------------------------------->
        //Current Costomer
        
        //Customer Output Console
        Label customerconsolelbl = new Label("Current Activity: ");
        GridPane.setConstraints(customerconsolelbl, 0, 11);
        
        TextField customerconsoleOutput = new TextField();
        customerconsoleOutput.setPromptText("Log in Successful");
        GridPane.setConstraints(customerconsoleOutput, 1, 11);
        
        // Level
        Label currentLevel = new Label("Current Level: ");
        GridPane.setConstraints(currentLevel, 0, 9);
        
        TextField currentLeveltxt = new TextField();
        currentLeveltxt.setPromptText("level");
        GridPane.setConstraints(currentLeveltxt, 1, 9);
        
        Button checkLvlBtn = new Button();
        checkLvlBtn.setText("Check Level");
        checkLvlBtn.setOnAction(e -> {
            
                Customer currentCustomer = new Customer(nameInput.getText());
                customerconsoleOutput.setText("Getting Level");
                currentCustomer.changeContent();
                currentLeveltxt.setText(currentCustomer.getLevel());
                
        });
        GridPane.setConstraints(checkLvlBtn, 1, 10);
        
        // Get Balance
        Label getBalance = new Label("Balance: ");
        GridPane.setConstraints(getBalance, 0, 5);
        
        TextField getBalancetxt = new TextField();
        getBalancetxt.setPromptText("balance");
        GridPane.setConstraints(getBalancetxt, 1, 5);
        
        Button getBalanceBtn = new Button();
        getBalanceBtn.setText("Get Balance");
        getBalanceBtn.setOnAction(e -> {
            
                Customer currentCustomer = new Customer(nameInput.getText());
                getBalancetxt.setText("$"+currentCustomer.getBalance());
                customerconsoleOutput.setText("Retrieved Balance");
                currentLeveltxt.setText(currentCustomer.getLevel());
                
        });
        GridPane.setConstraints(getBalanceBtn, 1, 6);
        
        // Deposit
        Label depositAmount = new Label("Deposit Amount:");
        GridPane.setConstraints(depositAmount, 0, 1);
        
        TextField deposittxt = new TextField();
        deposittxt.setPromptText("amount");
        GridPane.setConstraints(deposittxt, 1, 1);
        
        Button depositBtn = new Button();
        depositBtn.setText("Deposit");
        depositBtn.setOnAction(e -> {
                
                Customer currentCustomer = new Customer(nameInput.getText());
                try{
                    if(currentCustomer.deposit(Double.parseDouble(deposittxt.getText()))==true){
                        customerconsoleOutput.setText("Deposited: $" + deposittxt.getText());
                        currentLeveltxt.setText(currentCustomer.getLevel());
                        getBalancetxt.setText("$"+currentCustomer.getBalance());
                        deposittxt.clear();
                    }
                    else
                        customerconsoleOutput.setText("Invalid Input");
                }
                catch(Exception ex){
                    customerconsoleOutput.setText("Invalid Input");
                }
                
        });
        GridPane.setConstraints(depositBtn, 1, 2);
        
        // Withdraw
        Label withdrawAmount = new Label("Withdraw Amount: ");
        GridPane.setConstraints(withdrawAmount, 0, 3);
        
        TextField withdrawtxt = new TextField();
        withdrawtxt.setPromptText("amount");
        GridPane.setConstraints(withdrawtxt, 1, 3);
        
        Button withdrawBtn = new Button();
        withdrawBtn.setText("Withdraw");
        withdrawBtn.setOnAction(e -> {
            
                Customer currentCustomer = new Customer(nameInput.getText());
                try{
                    if(currentCustomer.withdraw(Double.parseDouble(withdrawtxt.getText()))==true){
                        customerconsoleOutput.setText("Withdrew: $" + withdrawtxt.getText());
                        currentLeveltxt.setText(currentCustomer.getLevel());
                        getBalancetxt.setText("$"+currentCustomer.getBalance());
                        withdrawtxt.clear();
                    }
                    else
                        customerconsoleOutput.setText("Insufficient Funds/Invalid Input");
                }
                catch(Exception ex){
                    customerconsoleOutput.setText("Invalid Input");
                }
        });
        GridPane.setConstraints(withdrawBtn, 1, 4);
        
        // Make a purchase
        Label makeAPurchase = new Label("Item Value: ");
        GridPane.setConstraints(makeAPurchase, 0, 7);
        
        TextField makeAPurchasetxt = new TextField();
        makeAPurchasetxt.setPromptText("amount");
        GridPane.setConstraints(makeAPurchasetxt, 1, 7);
        
        Button makeAPurchaseBtn = new Button();
        makeAPurchaseBtn.setText("Purchase");
        makeAPurchaseBtn.setOnAction(e -> {
                
                try{
                    Customer currentCustomer = new Customer(nameInput.getText());
                    getBalancetxt.setText("$"+currentCustomer.getBalance());
                    if(currentCustomer.onlinePurchase(Double.parseDouble(makeAPurchasetxt.getText()))){
                        customerconsoleOutput.setText("Item Purchased");
                        getBalancetxt.setText("$"+currentCustomer.getBalance());
                        currentLeveltxt.setText(currentCustomer.getLevel());
                    }else
                        customerconsoleOutput.setText("Insufficient Funds");
                        makeAPurchasetxt.clear();
                }
                catch(Exception ex){
                    customerconsoleOutput.setText("Invalid Input");
                }
                
        });
        GridPane.setConstraints(makeAPurchaseBtn, 1, 8);
        
        // Customer Log out
        Button ctmrlogOutBtn = new Button();
        ctmrlogOutBtn.setText("Log out");
        ctmrlogOutBtn.setOnAction(e -> {
            
            currentLeveltxt.clear();
            nameInput.clear();
            getBalancetxt.clear();
            customerconsoleOutput.clear();
            getBalancetxt.clear();
            currentLeveltxt.clear();
            window.setScene(loginPage);
            
        });
        GridPane.setConstraints(ctmrlogOutBtn, 0, 0);
        
        // Add everything to Customer Layout
        GridPane ctmrLayout = new GridPane();
        ctmrLayout.setPadding(new Insets(10,10,10,10));
        ctmrLayout.setVgap(5);
        ctmrLayout.setHgap(5);
        
        ctmrLayout.getChildren().addAll(
                
                ctmrlogOutBtn,
                customerconsoleOutput,
                customerconsolelbl,
                depositAmount,
                deposittxt,
                depositBtn,
                withdrawAmount,
                withdrawtxt,
                withdrawBtn,
                getBalancetxt,
                getBalance,
                getBalanceBtn,
                currentLeveltxt,
                currentLevel,
                checkLvlBtn,
                makeAPurchase,
                makeAPurchasetxt,
                makeAPurchaseBtn
        );
        
        ctmrPage = new Scene(ctmrLayout, 340, 380);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
