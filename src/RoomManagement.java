
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javafx.scene.control.CheckBox;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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
public class RoomManagement {

    private String dbname = "RoomStock";
    private String[] header = {"ID", "Room Number", "Room Type", "Room Class", "Building", "Floor", "Number of bed", "Base price", "Status", "Data Created"};
    private Database db;

    private Room room;
    private int numOfRoom = 0;

    public RoomManagement() throws IOException {
    }

    public void addRoom(Room room) throws FileNotFoundException, IOException {
        this.numOfRoom++;
        int numRow = 0;

        db = new Database();
        if (db.create(dbname)) {
            db.addHeader(dbname, header);
        }

        File excelFile = new File("src/db/" + this.dbname + ".xlsx");
        FileInputStream fileInput = new FileInputStream(excelFile);

        Workbook workbook = new XSSFWorkbook(fileInput);
        Sheet sheet = workbook.getSheet(this.dbname);

        // we iterate on rows
        Iterator<Row> rowIt = sheet.iterator();
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            numRow++;
        }

        Row row = sheet.createRow(numRow++);

        row.createCell(0).setCellValue(String.valueOf(2000 + numRow - 1));
        room.setRoomID(String.valueOf(2000 + numRow - 1));

        row.createCell(1).setCellValue(room.getRoomNumber());
        row.createCell(2).setCellValue(room.getRoomType());
        row.createCell(3).setCellValue(room.getRoomClass());
        row.createCell(4).setCellValue(room.getBuilding());
        row.createCell(5).setCellValue(room.getFloor());
        row.createCell(6).setCellValue(room.getNumBed());
        row.createCell(7).setCellValue(room.getBasePrice());

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);

        row.createCell(8).setCellValue(room.getStatus());
        row.createCell(9).setCellValue(strDate);
        room.setDateCreated(strDate);

        // Resize all columns to fit the content size
        for (int i = 0; i < header.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("src/db/" + dbname + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

    public ArrayList searchRoom(boolean all) throws FileNotFoundException, IOException {
        int numRow = 0;
        ArrayList<Room> temp_room_list = new ArrayList();

        File excelFile = new File("src/db/" + this.dbname + ".xlsx");
        FileInputStream fileInput = new FileInputStream(excelFile);

        Workbook workbook = new XSSFWorkbook(fileInput);
        Sheet sheet = workbook.getSheet(this.dbname);

        // we iterate on rows
        Iterator<Row> rowIt = sheet.iterator();
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            numRow++;
        }

        for (int i = 1; i <= numRow - 2; i++) {
            Room temp_room = new Room();
            for (int j = 0; j < 10; j++) {
                Cell cell = sheet.getRow(i).getCell(j);

                if (j == 0) {
                    temp_room.setRoomID(String.valueOf((int) cell.getNumericCellValue()));
                }
                if (j == 1) {
                    temp_room.setRoomNumber(String.valueOf((int) cell.getNumericCellValue()));
                }
                if (j == 2) {
                    temp_room.setRoomType(cell.getStringCellValue());
                }
                if (j == 3) {
                    temp_room.setRoomClass(cell.getStringCellValue());
                }
                if (j == 4) {
                    temp_room.setBuilding(cell.getStringCellValue());
                }
                if (j == 5) {
                    temp_room.setFloor(String.valueOf((int) cell.getNumericCellValue()));
                }
                if (j == 6) {
                    temp_room.setNumBed((int) cell.getNumericCellValue());
                }
                if (j == 7) {
                    temp_room.setBasePrice(cell.getNumericCellValue());
                }
                if (j == 8) {
                    temp_room.setStatus(cell.getStringCellValue());
                    //System.out.println("J = 8 " + temp_room.getStatus());
                }
                if (j == 9) {
                    temp_room.setDateCreated(cell.getStringCellValue());
                   
                }
            }

            if (all == false) {
                if (temp_room.getStatus().equals("Unoccupied") && temp_room.getStatus() != null) {
                    temp_room.setSelect(new CheckBox());
                    temp_room_list.add(temp_room);
                    //System.out.println("Room " + temp_room.getStatus());
                }
            }
            if (all == true) {
                temp_room.setSelect(new CheckBox());
                temp_room_list.add(temp_room);
            }
        }

        workbook.close();
        fileInput.close();

        return temp_room_list;
    }

    public boolean makeOccupied(String roomID) throws FileNotFoundException, IOException {
        db = new Database();
        int numRow = db.getRowNum(roomID, dbname);

        File excelFile = new File("src/db/" + dbname + ".xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Cell cell = sheet.getRow(numRow).getCell(8);
        cell.setCellValue("Occupied");

        FileOutputStream fileOut = new FileOutputStream("src/db/" + dbname + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
        fis.close();
        return true;
    }

    public boolean makeUnoccupied(String roomID) throws FileNotFoundException, IOException {
        db = new Database();
        int numRow = db.getRowNum(roomID, dbname);

        File excelFile = new File("src/db/" + dbname + ".xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Cell cell = sheet.getRow(numRow).getCell(8);
        cell.setCellValue("Unoccupied");

        FileOutputStream fileOut = new FileOutputStream("src/db/" + dbname + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
        fis.close();
        return true;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

}
