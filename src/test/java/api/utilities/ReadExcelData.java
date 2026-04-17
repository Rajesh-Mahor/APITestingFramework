package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	
	public static File file;
	public static FileInputStream inputStream;
	public static XSSFWorkbook workbook;
	public static XSSFSheet excelSheet;

	public static int getRowCount(String fileName, String sheetName) throws IOException {

		// String file = "D:\\eclipse-workspace\\Java Selenium
		// Project\\RestAPITestingFramework\\RestAssuredAPIFramework\\TestDataExcel\\UserTestData.xlsx";
		file = new File(fileName);
		inputStream = new FileInputStream(file);
		workbook = new XSSFWorkbook(inputStream);
		excelSheet = workbook.getSheet(sheetName);
		int rowCount = excelSheet.getLastRowNum();
		workbook.close();
		return rowCount;
	}

	public static int getCellCount(String fileName, String sheetName) throws IOException {
		
		file = new File(fileName);
		inputStream = new FileInputStream(file);
		workbook = new XSSFWorkbook(inputStream);
		excelSheet = workbook.getSheet(sheetName);
		int cellCount = excelSheet.getRow(0).getLastCellNum();
		workbook.close();
		return cellCount;
	}

	public static String getCellValue(String fileName, String sheetName,int rowNo, int cellNo) throws IOException {

		file = new File(fileName);
		inputStream = new FileInputStream(file);
		workbook = new XSSFWorkbook(inputStream);
		excelSheet = workbook.getSheet(sheetName);
		String cellValue = excelSheet.getRow(rowNo).getCell(cellNo).getStringCellValue();
		workbook.close();
		return cellValue;
	}
	
	
}
