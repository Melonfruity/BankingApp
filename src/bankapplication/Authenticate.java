/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapplication;

import java.io.File;
import java.util.Scanner;

/**
 * @overview Authenticate is a mutable class. 
 * Returns true or false depending on Actor inputs of password, username and role
 * 
 * @author Eric Luo
 * 
 * @abstractf 
 * AF(a) = {a.checkCredentials(user,pass,role) == false || true}
 * 
 * @repinvariant 
 * RI(a) = {a.checkCredentials(user,pass,role) is false | true and role = "manager" | "customer"}
 * 
 */

public class Authenticate {
    
    /**
     * @param content the content of the a text file that will be read
     * @param user the username from the text file
     * @param pass the password from the text file
     * @param role the role of the Actor to be authenticated
     */
    
    private Scanner content;
    private String user;
    private String pass;
    private String role;
    
    /**
     * @overview Checks input Strings to that of an existing file
     * 
     * @modifies 'this'
     * @effects return boolean
     * @requires username and password of type String and not null. 
     * accType to be either String "manager" or "customer"  
     * 
     * @param username the input username
     * @param password the input password
     * @param accType the input account type
     * 
     * @return boolean
     * 
     */
    
    public boolean checkCredentials(String username, String password, String accType){
        
        role = accType;
        
        if(accType.equals("manager")){
            return username.equals("admin")&&password.equals("admin");
        }
        
        else if(accType.equals("customer")){
            try{
                content = new Scanner(new File(username+".txt"));
                } 
            catch (Exception e) {
                return false;
            }
            while(content.hasNext()){
                content.next();
                user = content.next();
                pass = content.next();
            }
            if(user.equals(username)&&pass.equals(password)){
                return true;
            }
            content.close();
        }
        return false; 
    }
    
    /**
     * @overview repOK is the Invariant Representation of 'this'
     * repOK will check if the parameters set to Authenticate are valid
     * 
     * @effects returns the Rep Invariant as true or false
     * 
     * @comment 'this' is only a valid object if it is passed with the 
     * correct parameters through the checkCredentials method with no 
     * null parameters and accType of either "manager" or "customer"
     * 
     * @return boolean
     */
    
    public boolean repOk(){
        
        boolean check;
        
        try{
            check = (this.checkCredentials(user,pass,role) == true || this.checkCredentials(user,pass,role) == false) && (this.role.equals("manager") || this.role.equals("customer"))  ;
        }
        catch(Exception incompatibleParameter){
            check = false;
        }
        
        return check;
    }
    
    /**
     * @overview String representation of the Abstraction Function of 'this'
     * 
     * @effects returns the String representation of the Abstraction Function
     * @return String
     */
    
    @Override
    public String toString(){
        
        if(this.repOk() == true)
            return "Current results for authentication is: " + this.checkCredentials(user, pass, role);
        
        return "There are currently no authentication parameters";
    }
    
}
