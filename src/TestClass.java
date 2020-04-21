
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
            Room room = new Room();
            
            room.getStatus();
        
            Account acc_temp ;
            Database db = new Database();
            
            acc_temp = db.getAccount("few_raweroj@hotmail.com", "123456789");
            
            
            System.out.println("id " + acc_temp.getId());
            System.out.println("Firstname " + acc_temp.getFirstName());
            System.out.println("Lastname " + acc_temp.getLastName());
            System.out.println("Age " + acc_temp.getAge());
            System.out.println("Gender " + acc_temp.getGender());
            System.out.println("Email " + acc_temp.getEmail());
            System.out.println("phone " + acc_temp.getPhoneNumber());
            System.out.println("pass " + acc_temp.getPassword());
            System.out.println("data " + acc_temp.getDataCreate());
            
    }
}
