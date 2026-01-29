package Utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	private static final Workbook Null = null;
	private static Workbook workbook; // Loads Excel Files (support .xls and .xlsx)

	public static void loadExcel(String filepath) {
		try (FileInputStream fis = new FileInputStream(filepath)) {
			workbook = WorkbookFactory.create(fis);
		} catch (IOException e) {
			throw new RuntimeException("X Fail to load Excel file : " + filepath, e);
		}
	}

	// Get row count (Excluding header row)
	public static int getrowcount(String sheetname) {
		// return workbook.getsheet(sheetname).getlastrownum();
		Sheet sheet = workbook.getSheet(sheetname);
		return sheet.getLastRowNum();
	}

	// Get column count
	public static int getcolcount(String sheetname) {
		// return workbook.getsheet(sheetName).getrow(0).getlastCellNum
		Sheet sheet = workbook.getSheet(sheetname);
		return sheet.getRow(0).getLastCellNum();
	}

	// Get single cell data
	public static String getdata(String sheetname, int row, int colm) {
		if (workbook == Null) {
			throw new IllegalStateException("Excel file not loaded. call loadExcel() first.");
		}
		Sheet sheet = workbook.getSheet(sheetname);
		Row r = sheet.getRow(row); // 4
		Cell cell = r.getCell(colm); // 2

		DataFormatter formatter = new DataFormatter();
		return formatter.formatCellValue(cell);
	}

	// Read Entire sheet into 2D object array (for dataprovider)
	public static Object[][] getSheetData(String sheetName) {
		if (workbook == Null) {
			throw new IllegalStateException("Excel file not loaded. call loadExcel() first.");
		}
		Sheet sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		int colmcount = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rowcount][colmcount];
		DataFormatter formatter = new DataFormatter();

		// Start from row 1(skip header)
		for (int i = 1; i <= rowcount; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < colmcount; j++) {
				Cell cell = row.getCell(j);
				data[i - 1][j] = formatter.formatCellValue(cell); // data[0][0], data[0][1], data[1][0], data[1][1].

			}
		}
		return data;
	}

}
