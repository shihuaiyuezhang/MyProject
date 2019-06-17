package com.wiserv.util.excel;

import com.wiserv.util.util.IOUtil;
import org.apache.poi.ss.usermodel.*;
import java.io.InputStream;
import java.util.*;

public class ExcelHelper {


    private static ExcelHelper instance = new ExcelHelper();

    public static ExcelHelper getInstance() {
        return instance;
    }

    public List<Map<String,String>> getExcelData(InputStream ins) {
        Workbook book = null;
        try{
            book = WorkbookFactory.create(ins);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            IOUtil.quietClose(ins);
        }
        if(book == null) {
            return Collections.emptyList();
        }
        List<Map<String,String>> data = new ArrayList<Map<String,String>>();
        Sheet sheet = book.getSheetAt(0);
        Map<Integer,String> titleMap = new HashMap<Integer,String>();
        for(int i = sheet.getFirstRowNum();i <= sheet.getLastRowNum();i++) {
            Row row = sheet.getRow(i);
            Map<String,String> map;
            if(i == sheet.getFirstRowNum()) {
                map = null;
            }else {
                map = new HashMap<String,String>();
            }
            for(int j = row.getFirstCellNum();j < row.getLastCellNum();j++) {
                Cell cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                if(i == sheet.getFirstRowNum()) {
                    titleMap.put(j,cell.getStringCellValue().replaceAll("\n","").trim());
                }else{
                    String key = titleMap.get(j);
                    if(key == null) {
                        continue;
                    }
                    map.put(key,cell.getStringCellValue().trim());
                }
            }
            if(map != null) {
                data.add(map);
            }

        }
        return data;
    }
}
