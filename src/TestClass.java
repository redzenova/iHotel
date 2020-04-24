
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
               db.toTextFile("Administrator");
                db.toTextFile("BookingStock");
                 db.toTextFile("Check_IN_OUT");
                  db.toTextFile("Manager");
                   db.toTextFile("RoomStock");
    }
}
