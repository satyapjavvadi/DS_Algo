package utils;

import java.io.FileInputStream;
import java.util.*;
import org.apache.poi.ss.usermodel.*;

public class GraphExcelreader {

    public static List<Map<String, String>> getData() {
        List<Map<String, String>> data = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(
                    "src/test/resources/DS_ExcelData.xlsx");
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet("GraphPageContent");

            Row headerRow = sheet.getRow(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                Map<String, String> rowData = new HashMap<>();
                System.out.println("Reading row " + i);
                for (int j = 0; j < currentRow.getLastCellNum(); j++) {
                    String key = headerRow.getCell(j).toString();
                    String value = currentRow.getCell(j).toString();
                    rowData.put(key, value);
                }
                data.add(rowData);
            }
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
