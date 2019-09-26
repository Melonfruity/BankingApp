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
public class fee {
    
    public double getFee(Level level){
        if(level instanceof Silver){
            return 20.00;
        }
        return 0;
    }
    
}
