package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





public class ExcelReader {

private static Workbook workbook;
private static Sheet sheet;

//load excelfile
public static void loadExcel(String filePath, String sheetName) throws IOException {
	FileInputStream file = new FileInputStream(filePath);
	workbook = new XSSFWorkbook(file);
	sheet= workbook.getSheet(sheetName);
}

//get celldata as string
public static String getcelldata(int row,int col) {
	Cell cell = sheet.getRow(row).getCell(col);
	if(cell.getCellType() == CellType.STRING) {
		return cell.getStringCellValue();
	}
	
	else if(cell.getCellType() == CellType.NUMERIC) {
		//typecasting numeric data in to string
		 return String.valueOf((int) cell.getNumericCellValue());
	}
	else {
		return "";
	}
}
//get rowcount

public static int getRowcount() {
	return sheet.getPhysicalNumberOfRows();
	
}
//close workbook
	public static void closeExcel() throws IOException {
		workbook.close();
	}
	


}
