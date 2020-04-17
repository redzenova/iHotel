
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Redzer0
 */
public class Authentication {
    Database db = new Database();

    public Authentication() {
    }
      
    public boolean isUser(String username , String password) throws IOException{   
        if(db.serach(username,"User")){
            System.out.println("have user");
            if(db.serach(password,"User")){
                System.out.println("have pass");
                if(db.getRowNum(username) == db.getRowNum(password) ){
                    System.out.println("pass");
                    return true;
                }    
            }
        } 
        return false;
    }
    
    public boolean isAdmin(String username, String password) throws IOException{
        String[] header = {"ID", "Username" , "Password"};
        db.create("Administrator");        
        db.addHeader("Administrator", header);
        if(db.serach(username,"Administrator")){
            if(db.serach(password, "Administrator")){
                if(db.getRowNum(username) == db.getRowNum(password)){
                    return true;
                }
            }
        }
        return false;
    }
    
    
}
