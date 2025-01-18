package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

public class ExcelUtils {
	
	private Workbook workbook;
	
	public ExcelUtils(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = WorkbookFactory.create(fis);
    }
	
	public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        if (row == null) return "";
        Cell cell = row.getCell(colNum);
        if (cell == null) return "";
        return cell.toString();
    }

	public int getRowCount(String sheetName) {
        return workbook.getSheet(sheetName).getLastRowNum() + 1;
    }


}
