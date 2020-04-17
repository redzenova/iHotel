
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
        String filename = "User";
        if(db.serach(username,filename)){
            if(db.readCell(db.getRowNum(username,filename), 7, "User").equals(password)){
                if(db.getRowNum(username,filename) == db.getRowNum(password,filename) ){
                    System.out.println("pass !!");
                    return true;
                }    
            } else System.out.println("password not correct !");
        } 
        return false;
    }
    
    public boolean isAdmin(String username, String password) throws IOException{
        String filename = "Administrator";
        String[] header = {"ID", "Username" , "Password"};
        db.create(filename);        
        db.addHeader(filename, header);
        if(db.serach(username,filename)){
            if(db.readCell(db.getRowNum(username,filename), 2, "Administrator").equals(password)){
                if(db.getRowNum(username,filename) == db.getRowNum(password,filename)){
                    return true;
                }
            } else System.out.println("password not correct !");
        }
        return false;
    }
    
    
}
