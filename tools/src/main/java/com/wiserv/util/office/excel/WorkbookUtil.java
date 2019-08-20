package com.wiserv.util.office.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class WorkbookUtil {
	
	public static Workbook getWorkbook(String path) {
		Workbook book = null;
		InputStream is = null;
		boolean flag = false;
		try {
			is = new FileInputStream(path);
			book = new HSSFWorkbook(is);
			is.close();
		} catch (Exception e) {
			flag = true;
			e.printStackTrace();
		} 
		if(flag) {
			book = new HSSFWorkbook();
		}
		return book;
		
	}
	public static void saveWorkbook(Workbook book, String path) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(path);
			book.write(os);
			os.flush();		
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
