
import java.io.IOException;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Redzer0
 */
public class Validate {

    
    public Validate() {
    }
    
    public boolean isName(String name) {
        return name.matches("[a-zA-Zก-ฮูึ๊ีัะำไโ็้๋่าื์ิ_]+");
    }
    
    public boolean isValidEmail(String email) {
        String emailRegex;
        emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }
    
        public boolean isDupEmail(String email) throws IOException {
            Database db =new Database();
            
            if(db.serachEmail(email)){
                return true;
            }else return false;
    }
    
    public boolean isPhoneNum(String message) {
        return message.matches("[0-9]+");
    }
    
}
