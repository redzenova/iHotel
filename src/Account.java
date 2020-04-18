
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Redzer0
 */
public class Account {
    private int id;
    private String firstName;
    private String lastName;
    private String age;
    private String gender;
    private String email;
    private String phoneNumber;
    private String password;
    private String dataCreate;
    private int numOfAccount = 0;

    public Account(String firstName, String lastName, String age, String gender, String email, String phoneNumber, String password) {
        this.id = 10000 +  numOfAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");  
        String strDate = dateFormat.format(date); 
        
        this.dataCreate = strDate;
        numOfAccount++;
    }
    
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getDataCreate() {
        return dataCreate;
    }
    
    
}
