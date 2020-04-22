
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Redzer0
 */
public class Booking {

    private String ID;
    private String CustomerName;
    private String CheckInDate;
    private String CheckOutDate;
    private String RoomType;
    private String RoomClass;
    private String Building;
    private String Floor;
    private String Adult;
    private String Young;
    private String Breakfast;
    private String Dinner;
    private String Status;
    private double totalPrice;
    private String dateCreated;

    public Booking() {
    }

    public Booking(String CustomerName, String CheckInDate, String CheckOutDate, String RoomType, String RoomClass,
            String Building, String Floor, String Adult, String Young, String Breakfast, String Dinner, double price,  String Status ) {
        
        this.CustomerName = CustomerName;
        this.CheckInDate = CheckInDate;
        this.CheckOutDate = CheckOutDate;
        this.RoomType = RoomType;
        this.RoomClass = RoomClass;
        this.Building = Building;
        this.Floor = Floor;
        this.Adult = Adult;
        this.Young = Young;
        this.Breakfast = Breakfast;
        this.Dinner = Dinner;
        this.totalPrice = price;
        this.Status = Status;

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);
        this.dateCreated = strDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getCheckInDate() {
        return CheckInDate;
    }

    public void setCheckInDate(String CheckInDate) {
        this.CheckInDate = CheckInDate;
    }

    public String getCheckOutDate() {
        return CheckOutDate;
    }

    public void setCheckOutDate(String CheckOutDate) {
        this.CheckOutDate = CheckOutDate;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String RoomType) {
        this.RoomType = RoomType;
    }

    public String getBuilding() {
        return Building;
    }

    public void setBuilding(String Building) {
        this.Building = Building;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String Floor) {
        this.Floor = Floor;
    }

    public String getAdult() {
        return Adult;
    }

    public void setAdult(String Adult) {
        this.Adult = Adult;
    }

    public String getYoung() {
        return Young;
    }

    public void setYoung(String Young) {
        this.Young = Young;
    }

    public String getBreakfast() {
        return Breakfast;
    }

    public void setBreakfast(String Breakfast) {
        this.Breakfast = Breakfast;
    }

    public String getDinner() {
        return Dinner;
    }

    public void setDinner(String Dinner) {
        this.Dinner = Dinner;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getRoomClass() {
        return RoomClass;
    }

    public void setRoomClass(String RoomClass) {
        this.RoomClass = RoomClass;
    }

}
