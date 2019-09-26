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
public interface Level {

    public void checkLevel(Customer cus);
    public String getLevel(Customer cus);
    public double fee();
    
}
