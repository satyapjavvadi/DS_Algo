package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private static Workbook workbook;
	private static Sheet sheet;

	public static List<String> getExpectedTexts(String filePath, String sheetName) throws IOException {
		List<String> texts = new ArrayList<>();
		FileInputStream fis = new FileInputStream("src/test/resources/DS_ExcelData.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet("StackPageContent");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(1);
			if (cell != null) {
				texts.add(cell.getStringCellValue().trim());
			}
		}

		workbook.close();
		fis.close();
		return texts;
	}

	public static List<Map<String, String>> getTopicNavigationRows(String filePath, String sheetName)
			throws IOException {
		List<Map<String, String>> rows = new ArrayList<>();
		FileInputStream fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);

		Row header = sheet.getRow(0);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				String type = row.getCell(0).getStringCellValue().trim(); // Scenario_type
				if ("topic_navigation".equalsIgnoreCase(type)) {
					Map<String, String> rowData = new HashMap<>();
					for (int j = 0; j < header.getLastCellNum(); j++) {
						String key = header.getCell(j).getStringCellValue().trim();
						Cell cell = row.getCell(j);
						String value = cell != null ? cell.toString().trim() : "";
						rowData.put(key, value);
					}
					rows.add(rowData);
				}
			}
		}

		workbook.close();
		fis.close();
		return rows;
	}

	public static List<Map<String, String>> getScrollValidationRows(String filePath, String sheetName)
			throws IOException {
		List<Map<String, String>> rows = new ArrayList<>();
		FileInputStream fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);

		Row header = sheet.getRow(0);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				String type = row.getCell(0).getStringCellValue().trim(); // Scenario_type
				if ("scroll_validation".equalsIgnoreCase(type)) {
					Map<String, String> rowData = new HashMap<>();
					for (int j = 0; j < header.getLastCellNum(); j++) {
						String key = header.getCell(j).getStringCellValue().trim();
						Cell cell = row.getCell(j);
						String value = cell != null ? cell.toString().trim() : "";
						rowData.put(key, value);
					}
					rows.add(rowData);
				}
			}
		}

		workbook.close();
		fis.close();
		return rows;
	}

}
