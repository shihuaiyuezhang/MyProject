package com.wiserv.util.excel;

import java.io.File;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelExportUtil {
	private static final String PATH = "C:\\Users\\bing\\Desktop\\excel.xlsx";
	public static void main(String[] args) {
		String[][] data = {{"A","B","C"},{"D","E","F"}};
		new ExcelExportUtil().writeDataToSheet("表单1", data);
		System.out.println("Done");
	}
	
	public void writeDataToSheet(String sheetName,String[][] data) {
		Workbook book = WorkbookUtil.getWorkbook(PATH);
		Sheet sheet = book.createSheet(sheetName);
		sheet.setDisplayGridlines(false);
		if(data != null && data.length > 0){
			for(int i = 0;i < data.length;i++) {
				Row row = sheet.createRow(i);
				for(int j = 0;j < data[i].length;j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(data[i][j]);
				}	
			}
		}
		WorkbookUtil.saveWorkbook(book, PATH);
	}

}
