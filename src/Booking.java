
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

    private String dbname = "BookingStock";
    private String[] header = {"Booking ID", "Customer name", "Check-in Date", "Check-out Date", "Room Type", "Room Class", "Building", "Floor", "Adult", "Young", "Breakfast", "Dinner", "Status", "Data Created"};
    private Database db;
    private RoomManagement room_man;

    public Booking() {
    }

    public void addBooking(String roomID, String Customer_name, String check_in_date, String check_out_date, String roomtype, String roomclass,
            String Building, String Floor, String Adult, String Young, String Breakfast, String Dinner, String status) throws FileNotFoundException, IOException {

        int numRow = 0;

        System.out.println("Room Id " + roomID);
        room_man = new RoomManagement();
        room_man.makeOccupied(roomID);
        
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

        row.createCell(0).setCellValue(String.valueOf(getRand(1000, 2000)));

        row.createCell(1).setCellValue(Customer_name);
        row.createCell(2).setCellValue(check_in_date);
        row.createCell(3).setCellValue(check_out_date);
        row.createCell(4).setCellValue(roomtype);
        row.createCell(5).setCellValue(roomclass);
        row.createCell(6).setCellValue(Building);
        row.createCell(7).setCellValue(Floor);
        row.createCell(8).setCellValue(Adult);
        row.createCell(9).setCellValue(Young);
        row.createCell(10).setCellValue(Breakfast);
        row.createCell(11).setCellValue(Dinner);
        row.createCell(12).setCellValue(status);

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);
        row.createCell(13).setCellValue(strDate);

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
}
