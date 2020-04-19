
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

    public static void main(String[] args) throws IOException {
        RoomManagement manage = new RoomManagement();
        Room room = new Room();


        manage.addRoom(room);
    }
}
