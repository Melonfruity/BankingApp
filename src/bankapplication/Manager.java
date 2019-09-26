/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapplication;

import java.io.File;
import java.util.*;
/**
 *
 * @author Eric
 */
public class Manager extends Role{
    
    private File file;
    
    public Manager(){
    
        //admin must be user=admin, pw=admin
        setUsername("admin");
        setPassword("admin");
        
    }
    
    public boolean addCustomer(String username, String password){
        
        // Open text file named 'username'
        file = new File(username+".txt");
        
        // If file already exists then return false to not overwrite existing file
        if(file.exists()){
            return false;
        }
            try{
                // Create new txt file using new Customer username if it does not exist already
                customerFile = new Formatter(username+".txt");
            }
            catch(Exception e){
                // Return false if an exception is caught
                return false;
            }
            
            // Write new information to Customer file
            customerFile.format("%f %s %s", 100.00 ,username,password);
            customerFile.close();
            
            // Return true that Customer file has been created
            return true;
    }
    
    public void deleteCustomer(String username){
        
        // Open file with name 'username' and delete
        file = new File(username+".txt");
        file.delete();
    }
    
}