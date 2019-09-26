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

public class Silver implements Level{
    
    @Override
    public String getLevel(Customer cus){
        return "Silver";
    }
    
    @Override
    public void checkLevel(Customer cus){
        
        double balance = cus.getBalance();
        
        if(balance>=10000&&balance<20000){
            Level movetoGold = new Gold();
            cus.setLevel(movetoGold);
        }
        if(balance>=20000){
            Level movetoPlatinum = new Platinum();
            cus.setLevel(movetoPlatinum);
        }
    }
    
    @Override
    public double fee(){
        return 20.00;
    }
    
}
