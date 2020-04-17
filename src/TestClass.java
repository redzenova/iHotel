
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
public class TestClass {
    public static void main2(String[] args) throws IOException {
       Database db = new Database();
       if(db.serachEmail("raweeroj@everythink.dev")){
           System.out.println("Email is already !");
       }else {
           System.out.println("You can use email");
       }
       
    }
}
