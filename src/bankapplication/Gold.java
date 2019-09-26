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
public class Gold implements Level{
    
    @Override
    public String getLevel(Customer cus){
        return "Gold";
    }
    
    @Override
    public void checkLevel(Customer cus){
        
        double balance = cus.getBalance();
        
        if(balance<10000){
            Level movetoSilver = new Silver();
            cus.setLevel(movetoSilver);
        }
        if(balance>=20000){
            Level movetoPlatinum = new Platinum();
            cus.setLevel(movetoPlatinum);
        }
    }
    
    @Override
    public double fee(){
        return 10.00;
    }
    
}
