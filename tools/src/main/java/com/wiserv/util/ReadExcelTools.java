package com.wiserv.util;
 
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
 
/**
 * Created by Lenovo on 2018/1/21.
 */
public class ReadExcelTools {


    private static final Log logger = LogFactory.getLog(ReadExcelTools.class);
   // private static final Gson gson = new GsonBuilder().serializeNulls().create();
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";
    
    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    
    private static String baseSql ="";
    
    
    
    /**
     * 读入excel文件，解析后返回
     * @param file
     * @throws IOException
     */
    public static List<String[]> readExcelByfilePath(String file) throws IOException{
        //检查文件
      //  checkFile(file);
        //获得Workbook工作薄对象
        Workbook workbook = readExcel(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){ //为了过滤到第一行因为我的第一行是数据库的列
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getLastCellNum();//为空列获取
//                    int lastCellNum = row.getPhysicalNumberOfCells();//为空列不获取
//                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    String[] cells = new String[row.getLastCellNum()];
                    //循环当前行
                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
        }
        logger.info(JSON.toJSONString(list));
        return list;
    }


    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
//                cellValue = String.valueOf(cell.getCellFormula());
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
    
 // 读取csv文件的内容
    public static ArrayList<String[]> readCsv(String filepath) {
        return readCsv(filepath,"GBK");
        
    }
    
    public static ArrayList<String[]> readCsv(String filepath,String charsetName) {
        File csv = new File(filepath); // CSV文件路径
        csv.setReadable(true);//设置可读
        csv.setWritable(true);//设置可写
        BufferedReader br = null;
        
        
        try {
        	DataInputStream in = new DataInputStream(new FileInputStream(csv));
			br= new BufferedReader(new InputStreamReader(in,charsetName));
			 //这里如果csv文件编码格式是utf-8,改成utf-8即可
            //br = new BufferedReader(new InputStreamReader(in,"GBK"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
			// TODO: handle exception
		}
        String line = "";
        String everyLine = "";
        ArrayList<String[]> allString = new ArrayList<>();
        int i= 0;
        try {
            while ((line = br.readLine()) != null) // 读取到的内容给line变量
            {
            	/*i++;
            	if(i==1) { //跳过标题
            		continue;
            	}*/
                everyLine = line;
                //System.out.println(everyLine);
              
                allString.add(everyLine.split(","));
            }
            System.out.println("csv表格中所有行数：" + allString.size());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allString;
        
    }

    
}
