package com.beerlot.crawler;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Writer {

    private XSSFWorkbook workbook;
    private XSSFSheet spreadsheet;
    private FileOutputStream fileOutputStream;
    private String OUTPUT_PATH;

    Writer(String fileName, String sheetName) throws FileNotFoundException {
        OUTPUT_PATH = fileName;
        workbook = new XSSFWorkbook();
        spreadsheet = workbook.createSheet(sheetName);
    }

    public void addHeader() throws IOException {
        fileOutputStream = new FileOutputStream(new File(OUTPUT_PATH));
        String[] headers = {"Brewery", "Calorie", "CalorieUnit", "Volume", "Category", "Glassware",
                            "Name_EN", "Description_EN", "OriginCity_EN", "OriginCountry_EN",
                            "Name_KR", "Description_KR", "OriginCity_KR", "OriginCountry_KR"};

        XSSFRow row = spreadsheet.createRow(0);

        int columnId = 0;

        for (String header : headers) {
            Cell cell = row.createCell(columnId++);
            cell.setCellValue(header);
        }

        workbook.write(fileOutputStream);
    }

    public void addRow(Object[] data) throws IOException {
        fileOutputStream = new FileOutputStream(new File(OUTPUT_PATH));
        int rowId = spreadsheet.getLastRowNum() + 1;
        // Write row
        XSSFRow row = spreadsheet.createRow(rowId);

        // Write columns
        int columnId = 0;

        for (Object obj : data) {
            Cell cell = row.createCell(columnId++);
            if (obj instanceof String) {
                cell.setCellValue((String)obj);
            } else if (obj instanceof Integer) {
                cell.setCellValue((Integer)obj);
            } else if (obj instanceof Float) {
                cell.setCellValue((Float)obj);
            }
        }
        workbook.write(fileOutputStream);
    }

    public void close() throws IOException {
        workbook.close();
        fileOutputStream.close();
    }
}
