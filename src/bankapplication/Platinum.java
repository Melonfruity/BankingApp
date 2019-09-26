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
public class Platinum implements Level{
    
    @Override
    public String getLevel(Customer cus){
        return "Platinum";
    }
    
    @Override
    public void checkLevel(Customer cus){
        
        double balance = cus.getBalance();
        
        if(balance>=10000&&balance<20000){
            Level movetoGold = new Gold();
            cus.setLevel(movetoGold);
        }
        if(balance<10000){
            Level movetoSilver = new Silver();
            cus.setLevel(movetoSilver);
        }
    }
    @Override
    public double fee(){
        return 0.00;
    }
}
