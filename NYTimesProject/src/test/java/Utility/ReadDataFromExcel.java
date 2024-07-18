package Utility;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class ReadDataFromExcel {

    public static final String currentDir = System.getProperty("user.dir");
    public static String testDataExcelPath = null;
    static XSSFSheet sheet;
    static XSSFWorkbook xssfWorkbook;

    public static void setExcelSheetFileName(String excelFIleName) {

        try {
            testDataExcelPath=currentDir+"\\src\\main\\resources\\"+ excelFIleName;
            File src = new File(testDataExcelPath);
            FileInputStream fis = new FileInputStream(src);
            xssfWorkbook = new XSSFWorkbook(fis);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getData(int sheetno, int rowno, int columnno) {
        sheet = xssfWorkbook.getSheetAt(sheetno);
        String data = sheet.getRow(rowno).getCell(columnno).getStringCellValue();
        return data;
    }
}