
import javafx.scene.control.CheckBox;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Redzer0
 */
public class Room {

    private String roomID;
    private String roomNumber;
    private String roomType;
    private String roomClass;
    private String building;
    private String floor;
    private String Status;
    private int numBed;
    private double basePrice;
    private CheckBox select;
    private String dateCreated;

    public Room() {
    }
    
    public Room(String roomNumber, String roomType, String roomClass, String building, String floor, int numBed, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomClass = roomClass;
        this.building = building;
        this.floor = floor;
        this.numBed = numBed;
        this.basePrice = price;
        this.Status = "Unoccupied";
        this.select = new CheckBox();
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getNumBed() {
        return numBed;
    }

    public void setNumBed(int numBed) {
        this.numBed = numBed;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
    
    public boolean isSelect(){
        if(this.select.isSelected()){
            return true;
        }
        return false;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    
}
