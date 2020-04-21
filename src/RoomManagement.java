
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
public class RoomManagement {

    private String dbname = "RoomStock";
    private String[] header = {"ID", "Room Number", "Room Type", "Room Class", "Building", "Floor", "Number of bed", "Base price", "Status", "Data Created"};
    private Database db;
    private Room room;
    private int numOfRoom =0;

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
            row.createCell(0).setCellValue(String.valueOf(2000 + numRow-1));
            room.setRoomID(String.valueOf(2000 + numRow-1));
            row.createCell(1).setCellValue(room.getRoomNumber());
            row.createCell(2).setCellValue(room.getRoomType());
            row.createCell(3).setCellValue(room.getRoomClass());
            row.createCell(4).setCellValue(room.getBuilding());
            row.createCell(5).setCellValue(room.getFloor());
            row.createCell(6).setCellValue(room.getNumBed());
            row.createCell(7).setCellValue(room.getPrice());

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
            String strDate = dateFormat.format(date);

            row.createCell(8).setCellValue(room.getStatus());
            row.createCell(9).setCellValue(strDate);

        // Resize all columns to fit the content size
        for (int i = 0; i < header.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("src/db/" + dbname + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

}
