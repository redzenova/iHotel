
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
public class BookingManagement {

    private String dbname = "BookingStock";
    private String[] header = {"Booking ID", "Customer name", "Check-in Date", "Check-out Date", "Room Type", "Room Class", "Building",
        "Floor", "Adult", "Young", "Breakfast", "Dinner", "Total Price", "Status", "Data Created"};
    private Database db;
    private RoomManagement room_man;

    public BookingManagement() {
    }

    public void addBooking(String roomId, Booking book) throws FileNotFoundException, IOException {

        int numRow = 0;

        System.out.println("Room Id " + roomId);
        room_man = new RoomManagement();
        room_man.makeOccupied(roomId);

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

        int rand = getRand(1000, 2000);
        row.createCell(0).setCellValue(String.valueOf(rand));
        book.setID(roomId);

        row.createCell(1).setCellValue(book.getCustomerName());
        row.createCell(2).setCellValue(book.getCheckInDate());
        row.createCell(3).setCellValue(book.getCheckOutDate());
        row.createCell(4).setCellValue(book.getRoomType());
        row.createCell(5).setCellValue(book.getRoomClass());
        row.createCell(6).setCellValue(book.getBuilding());
        row.createCell(7).setCellValue(book.getFloor());
        row.createCell(8).setCellValue(book.getAdult());
        row.createCell(9).setCellValue(book.getYoung());
        row.createCell(10).setCellValue(book.getBreakfast());
        row.createCell(11).setCellValue(book.getDinner());
        row.createCell(12).setCellValue(book.getTotalPrice());
        row.createCell(13).setCellValue(book.getStatus());

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);

        row.createCell(14).setCellValue(strDate);

        // Resize all columns to fit the content size
        for (int i = 0; i < header.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("src/db/" + dbname + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

    public int getRand(double min, double max) {
        double x = (int) (Math.random() * ((max - min) + 1)) + min;
        return (int) x;
    }

    public ArrayList searchBooking(String name) throws FileNotFoundException, IOException {
        int numRow = 0;
        ArrayList<Booking> temp_booking_list = new ArrayList();

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

        for (int i = 1; i <= numRow - 1; i++) {
            Booking temp = new Booking();
            for (int j = 0; j < 15; j++) {
                Cell cell = sheet.getRow(i).getCell(j);

                if (j == 0) {
                    temp.setID(cell.getStringCellValue());
                }
                if (j == 1) {
                    temp.setCustomerName(String.valueOf(cell.getStringCellValue()));
                }
                if (j == 2) {
                    temp.setCheckInDate(cell.getStringCellValue());
                }
                if (j == 3) {
                    temp.setCheckOutDate(cell.getStringCellValue());
                }
                if (j == 4) {
                    temp.setRoomType(cell.getStringCellValue());
                }
                if (j == 5) {
                    temp.setRoomClass(cell.getStringCellValue());
                }
                if (j == 6) {
                    temp.setBuilding(cell.getStringCellValue());
                }
                if (j == 7) {
                    temp.setFloor(cell.getStringCellValue());
                }
                if (j == 8) {
                    temp.setAdult(cell.getStringCellValue());
                }
                if (j == 9) {
                    temp.setYoung(cell.getStringCellValue());
                }
                if (j == 10) {
                    temp.setBreakfast(cell.getStringCellValue());
                }
                if (j == 11) {
                    temp.setDinner(cell.getStringCellValue());
                }
                if (j == 12) {
                    temp.setTotalPrice(cell.getNumericCellValue());
                }
                if (j == 13) {
                    temp.setStatus(cell.getStringCellValue());
                }
                if (j == 14) {
                    temp.setDateCreated(cell.getStringCellValue());
                }
            }

            if (name != "ALL") {
                if (temp.getCustomerName().equals(name)) {
                    temp_booking_list.add(temp);
                }
            }
            if(name == "ALL"){
                 temp_booking_list.add(temp);
            }
        }

        workbook.close();
        fileInput.close();

        return temp_booking_list;
    }

}
