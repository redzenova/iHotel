
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
public class CheckManagement {

    private String BookedID;
    private String CustomerRoom;
    private String CustomerName;
    private String Status;
    private String dateCreated;

    private String dbname = "Check_IN_OUT";
    private String[] header = {"Booking ID", "Customer name", "Room Number", " Status", "Data Created"};
    private Database db;

    public CheckManagement() throws IOException {
        db = new Database();
        if (db.create(dbname)) {
            db.addHeader(dbname, header);
        }
    }

    public void addCheckIn(String bookingID, String roomNumber, String customer) throws FileNotFoundException, IOException {
        this.BookedID = bookingID;
        this.CustomerRoom = roomNumber;
        this.CustomerName = customer;
        this.Status = "Waiting";
        int numRow = 0;

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

        row.createCell(0).setCellValue(this.getBookedID());
        row.createCell(1).setCellValue(this.getCustomerName());
        row.createCell(2).setCellValue(this.getCustomerRoom());
        row.createCell(3).setCellValue(this.getStatus());

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);

        this.dateCreated = strDate;
        row.createCell(4).setCellValue(strDate);

        // Resize all columns to fit the content size
        for (int i = 0; i < header.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("src/db/" + dbname + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getBookedID() {
        return BookedID;
    }

    public void setBookedID(String BookedID) {
        this.BookedID = BookedID;
    }

    public String getCustomerRoom() {
        return CustomerRoom;
    }

    public void setCustomerRoom(String CustomerRoom) {
        this.CustomerRoom = CustomerRoom;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public ArrayList searchCheck() throws FileNotFoundException, IOException {
        int numRow = 0;
        ArrayList<Check> temp_booking_list = new ArrayList();

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
            Check temp = new Check();
            for (int j = 0; j < 5; j++) {
                Cell cell = sheet.getRow(i).getCell(j);

                if (j == 0) {
                    temp.setBookedID(cell.getStringCellValue());
                }
                if (j == 1) {
                    temp.setCustomerName(cell.getStringCellValue());
                }
                if (j == 2) {
                    temp.setCustomerRoom(cell.getStringCellValue());
                }
                if (j == 3) {
                    temp.setStatus(cell.getStringCellValue());
                }
                if (j == 4) {
                    temp.setDateCreated(cell.getStringCellValue());
                }
            }
            temp_booking_list.add(temp);
        }
        workbook.close();
        fileInput.close();

        return temp_booking_list;
    }

}
