/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapplication;

/**
 *
 * @author Eric
 */
import java.util.*;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Customer extends Role {

    private double balance;
    private Level level;
    private Scanner content;

    public Customer(String username) {

        // Accessing folder of a existing customer
        try {
            content = new Scanner(new File(username + ".txt"));
        } catch (Exception e) {
            System.out.println("NO SUCH FILE");
        }
        
        // Reads the file of the current customer
        while (content.hasNext()) {
            balance = Double.parseDouble(content.next());
            this.setUsername(content.next());
            this.setPassword(content.next());
        }
        content.close();
        
        round(this.balance,2);
        level = new Silver();
        level.checkLevel(this);
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getLevel() {
        return level.getLevel(this);
    }

    public boolean deposit(double amount) {
        
        if(amount>0){
            this.balance = this.balance + amount;
            round(this.balance,2);
            level.checkLevel(this);
            changeContent();
            return true;
        }
        return false;
        
    }
    
    public boolean withdraw(double amount) {
        
        if((this.balance - amount > 0) && amount>=0){
            this.balance = this.balance - amount;
            level.checkLevel(this);
            round(this.balance,2);
            changeContent();
            return true;
        }
            return false;
           
    }
    
    public double getBalance() {
        return round(this.balance,2);
    }
    
    // Updates content of the text file of current customer 
    public void changeContent() {
        
        try {
            customerFile = new Formatter(this.getUsername() + ".txt");
        } catch (Exception e) {
            System.out.println("changeContent: No Such File");
        }
        
        customerFile.format("%f %s %s", round(this.balance,2), getUsername(), getPassword());
        customerFile.close();
    }
    
    public boolean onlinePurchase(double cost){
        return withdraw(cost + this.level.fee());
    }
    
    // Rounds doubles to two decimals
    public static double round(double value, int places) {
        
        if (places < 1) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        
        return bd.doubleValue();
    }

}
