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
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static List<Map<String, String>> getScenarioRows(String filePath, String sheetName, String scenarioType)
			throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = WorkbookFactory.create(fis);
		// Sheet sheet = workbook.getSheet(sheetName);

		// Debug: List all sheet names
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			System.out.println("Sheet found: " + workbook.getSheetName(i));
		}

		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			throw new IllegalStateException("Sheet not found: " + sheetName);
		}

		// Place your header check here
		Row header = sheet.getRow(0);
		if (header == null) {
			throw new IllegalStateException("Header row is missing in sheet: " + sheetName);
		}

		List<Map<String, String>> rows = new ArrayList<>();

		// Now loop through data rows safely
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row == null)
				continue;

			Map<String, String> rowData = new HashMap<>();
			for (int j = 0; j < header.getLastCellNum(); j++) {
				String key = header.getCell(j).getStringCellValue();
				String value = row.getCell(j) != null ? row.getCell(j).toString() : "";
				rowData.put(key, value);
			}

			if (rowData.get("scenario_type").equalsIgnoreCase(scenarioType)) {
				rows.add(rowData);
			}
		}

		workbook.close();
		fis.close();
		return rows;
	}

	public static List<Map<String, String>> getTestData(String filePath, String sheetName) {
		List<Map<String, String>> dataList = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("❌ Sheet not found: " + sheetName);
			}

			// First row = header
			Row headerRow = sheet.getRow(0);
			int colCount = headerRow.getLastCellNum();

			// Iterate rows
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row == null)
					continue;

				Map<String, String> rowData = new HashMap<>();
				for (int j = 0; j < colCount; j++) {
					Cell headerCell = headerRow.getCell(j);
					Cell cell = row.getCell(j);

					String header = headerCell.getStringCellValue().trim();
					String value = (cell == null) ? "" : cell.toString().trim();

					rowData.put(header, value);
				}
				dataList.add(rowData);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("❌ Error reading Excel file: " + filePath, e);
		}

		return dataList;
	}

}
