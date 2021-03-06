
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
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
public class Database {

    public void read(String name) throws FileNotFoundException, IOException {

        //Set name of file xlsx
        File excelFile = new File("src/db/" + name + ".xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        // we create an XSSF Workbook object for our XLSX Excel File
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // we get first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);

        // we iterate on rows
        Iterator<Row> rowIt = sheet.iterator();

        while (rowIt.hasNext()) {
            Row row = rowIt.next();

            // iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.print(cell.toString());
                System.out.println("[" + row.getRowNum() + "," + cell.getColumnIndex() + "]");

            }

            System.out.println();
        }

        workbook.close();
        fis.close();

    }

    public boolean create(String filename) throws FileNotFoundException, IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(filename);

        File excelFile = new File("src/db/" + filename + ".xlsx");
        if (excelFile.exists()) {
            System.out.println("File exist");
            return false;
        } else {
            // Write the output to a file
            FileOutputStream fileOut = new FileOutputStream("src/db/" + filename + ".xlsx");
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Create File Seccess !");
            return true;
        }
    }

    public void addHeader(String filename, String[] header) throws FileNotFoundException, IOException {

        File excelFile = new File("src/db/" + filename + ".xlsx");
        FileInputStream fileInput = new FileInputStream(excelFile);

        Workbook workbook = new XSSFWorkbook(fileInput);
        Sheet sheet = workbook.getSheet(filename);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < header.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(header[i]);

        }

        // Resize all columns to fit the content size
        for (int i = 0; i < header.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("src/db/" + filename + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
        fileInput.close();
    }

    public void addAccount(String filename, int length, Account a) throws FileNotFoundException, IOException {
        int rowNum = 0;

        File excelFile = new File("src/db/" + filename + ".xlsx");
        FileInputStream fileInput = new FileInputStream(excelFile);

        Workbook workbook = new XSSFWorkbook(fileInput);
        Sheet sheet = workbook.getSheet(filename);

        // we iterate on rows
        Iterator<Row> rowIt = sheet.iterator();
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            rowNum++;
        }

        if (rowNum > 0) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(String.valueOf(10000 + rowNum - 1));
            row.createCell(1).setCellValue(a.getFirstName());
            row.createCell(2).setCellValue(a.getLastName());
            row.createCell(3).setCellValue(a.getAge());
            row.createCell(4).setCellValue(a.getGender());
            row.createCell(5).setCellValue(String.valueOf(a.getEmail()));
            row.createCell(6).setCellValue(a.getPhoneNumber());
            row.createCell(7).setCellValue(a.getPassword());
            row.createCell(8).setCellValue(a.getDataCreate());
            row.createCell(9).setCellValue(a.getDataCreate());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("src/db/" + filename + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

    public void toTextFile(String filename) throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();

        File excelFile = new File("src/db/" + filename + ".xlsx");
        try (FileInputStream fileInput = new FileInputStream(excelFile)) {
            Workbook workbook = new XSSFWorkbook(fileInput);
            Sheet firstSheet = workbook.getSheetAt(0);
            File file = new File("src/db/" + filename + ".txt");
            Iterator<Row> iterator = firstSheet.iterator();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            //System.out.print(cell.getStringCellValue());
                            sb.append(cell.getStringCellValue() + ", ");
                            break;
                        case BOOLEAN:
                            //System.out.print(cell.getBooleanCellValue());
                            sb.append(String.valueOf(cell.getBooleanCellValue() + ", "));
                            break;
                        case NUMERIC:
                            //System.out.print(cell.getNumericCellValue());
                            sb.append(String.valueOf(cell.getNumericCellValue() + ", "));
                            break;
                        case FORMULA:
                            //System.out.print(cell.getCellFormula());
                            sb.append(String.valueOf(cell.getCellFormula() + ", "));
                            break;
                        default:
                            //System.out.print(cell.getStringCellValue());
                            sb.append(cell.getStringCellValue() + ", ");
                    }
                    //System.out.print(" - ");
                }
                //System.out.println();
                sb.append("\n");
            }
            workbook.close();
        }
        Path path = Paths.get("src/db/" + filename + ".txt");
        Files.write(path, Arrays.asList(sb.toString()));
    }

    public void toExcelFile(String textname, String excelname) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");

        // Read your input file and make cells into the workbook
        try (BufferedReader br = new BufferedReader(new FileReader("src/db/" + textname + ".txt"))) {
            String line;
            Row row;
            Cell cell;
            int rowIndex = 0;
            while ((line = br.readLine()) != null) {
                row = sheet.createRow(rowIndex);
                String[] tokens = line.split("[,]");
                for (int iToken = 0; iToken < tokens.length; iToken++) {
                    cell = row.createCell(iToken);
                    cell.setCellValue(tokens[iToken]);
                }
                rowIndex++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Write your xlsx file
        try (FileOutputStream outputStream = new FileOutputStream("src/db/" + excelname + ".xlsx")) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toCsvFile(String excelname, String csvname) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        File excelFile = new File("src/db/" + excelname + ".xlsx");
        FileInputStream fileInput = new FileInputStream(excelFile);
        Workbook wb = new XSSFWorkbook(fileInput);

        DataFormatter formatter = new DataFormatter();
        PrintStream out = new PrintStream(new FileOutputStream("src/db/" + csvname + ".csv"),
                true, "UTF-8");
        for (Sheet sheet : wb) {
            for (Row row : sheet) {
                boolean firstCell = true;
                for (Cell cell : row) {
                    if (!firstCell) {
                        out.print(',');
                    }
                    String text = formatter.formatCellValue(cell);
                    out.print(text);
                    firstCell = false;
                }
                out.println();
            }
        }
    }

    public boolean serach(String data, String filename) throws FileNotFoundException, IOException {

        File excelFile = new File("src/db/" + filename + ".xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIt = sheet.iterator();

        while (rowIt.hasNext()) {
            Row row = rowIt.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.toString().equals(data)) {
                    return true;
                }
            }
        }

        workbook.close();
        fis.close();
        return false;
    }

    public int getRowNum(String data, String filename) throws FileNotFoundException, IOException {

        File excelFile = new File("src/db/" + filename + ".xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIt = sheet.iterator();

        while (rowIt.hasNext()) {
            Row row = rowIt.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.toString().equals(data)) {
                    return row.getRowNum();
                }
            }
        }

        workbook.close();
        fis.close();
        return 0;
    }

    public int getCellNum(String data, String filename) throws FileNotFoundException, IOException {

        File excelFile = new File("src/db/" + filename + ".xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIt = sheet.iterator();

        while (rowIt.hasNext()) {
            Row row = rowIt.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.toString().equals(data)) {
                    return cell.getColumnIndex();
                }
            }
        }

        workbook.close();
        fis.close();
        return 0;
    }

    public void writeCell(String string, int rowNum, int collumNum, String filename) throws FileNotFoundException, IOException {
        File excelFile = new File("src/db/" + filename + ".xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Cell cell = sheet.getRow(rowNum).getCell(collumNum);
        cell.setCellValue(string);

        
         FileOutputStream fileOut = new FileOutputStream("src/db/"+ filename+".xlsx");
        workbook.write(fileOut);
        fileOut.close();
        fis.close();
      
    }

    public String readCell(int rowNum, int collumNum, String filename) throws FileNotFoundException, IOException {
        String s;

        File excelFile = new File("src/db/" + filename + ".xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIt = sheet.iterator();
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (row.getRowNum() == rowNum && cell.getColumnIndex() == collumNum) {
                    s = cell.toString();
                    //System.out.println("pass is " + s);
                    return s;
                }
            }

            workbook.close();
            fis.close();

        }
        return null;
    }

    public Account getAccount(String username, String password) throws FileNotFoundException, IOException {
        Account acc = new Account();
        Authentication auth = new Authentication();
        int numRow = this.getRowNum(username, "User");
        File excelFile = new File("src/db/User.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        if (auth.isUser(username, password)) {
            Iterator<Row> rowIt = sheet.iterator();
            while (rowIt.hasNext()) {
                Row row = rowIt.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (row.getRowNum() == numRow) {

                        if (cell.getColumnIndex() == 0) {
                            acc.setId(cell.getStringCellValue());
                        }
                        if (cell.getColumnIndex() == 1) {
                            acc.setFirstName(cell.getStringCellValue());
                        }
                        if (cell.getColumnIndex() == 2) {
                            acc.setLastName(cell.getStringCellValue());
                        }
                        if (cell.getColumnIndex() == 3) {
                            acc.setAge(cell.getStringCellValue());
                        }
                        if (cell.getColumnIndex() == 4) {
                            acc.setGender(cell.getStringCellValue());
                        }
                        if (cell.getColumnIndex() == 5) {
                            acc.setEmail(cell.getStringCellValue());
                        }
                        if (cell.getColumnIndex() == 6) {
                            acc.setPhoneNumber(cell.getStringCellValue());
                        }
                        if (cell.getColumnIndex() == 7) {
                            acc.setPassword(cell.getStringCellValue());
                        }
                        if (cell.getColumnIndex() == 8) {
                            acc.setDataCreate(cell.toString());
                        }
                    }
                }
            }
        }

        workbook.close();
        fis.close();
        return acc;
    }

    public boolean editAccount(Account temp) throws FileNotFoundException, IOException {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);

        int numRow = this.getRowNum(temp.getId(), "User");

        File excelFile = new File("src/db/User.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
   
        Iterator<Row> rowIt = sheet.iterator();
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (row.getRowNum() == numRow) {

                    if (cell.getColumnIndex() == 0) {
                        cell.setCellValue(temp.getId());
                    }
                    if (cell.getColumnIndex() == 1) {
                        cell.setCellValue(temp.getFirstName());
                    }
                    if (cell.getColumnIndex() == 2) {
                        cell.setCellValue(temp.getLastName());
                    }
                    if (cell.getColumnIndex() == 3) {
                        cell.setCellValue(temp.getAge());
                    }
                    if (cell.getColumnIndex() == 4) {
                        cell.setCellValue(temp.getGender());
                    }
                    if (cell.getColumnIndex() == 5) {
                        cell.setCellValue(temp.getEmail());
                    }
                    if (cell.getColumnIndex() == 6) {
                        cell.setCellValue(temp.getPhoneNumber());
                    }
                    if (cell.getColumnIndex() == 7) {
                        cell.setCellValue(temp.getPassword());
                    }
                    if (cell.getColumnIndex() == 8) {
                        cell.setCellValue(temp.getDataCreate());
                    }
                    if (cell.getColumnIndex() == 9) {
                        cell.setCellValue(strDate);
                    }
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream("src/db/User.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        fis.close();
        return true;
    }

}
