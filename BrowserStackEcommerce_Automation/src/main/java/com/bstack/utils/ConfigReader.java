package com.bstack.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConfigReader {

	private static Properties prop = new Properties();

	public static final long PAGE_LOAD_TIMEOUT = 20;
	public static final long IMPLICIT_WAIT = 20;
	public static final String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "bstack"
			+ File.separator + "testdata" + File.separator + "LoginData.xlsx";

	static XSSFWorkbook book;
	static XSSFSheet sheet;
	static XSSFCell cell;
	static XSSFRow row;

	public static Object[][] getTestData(String sheetName) throws IOException {

		FileInputStream file = new FileInputStream(TESTDATA_SHEET_PATH);

		book = new XSSFWorkbook(file);

		sheet = book.getSheet(sheetName);

		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getLastRowNum());

		// Loop through rows and cells
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i + 1); // i + 1 to skip header row
			if (row != null) { // Check if the row is not null
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					cell = row.getCell(k); // Get the cell
					if (cell != null) { // Check if the cell is not null
						data[i][k] = cell.toString(); // Safe to call toString()
					} else {
						data[i][k] = ""; // Assign empty string or default value for null cells
					}
				}
			}

		}
		return data;
	}

}
