package com.wiserv.util;


import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {

        String exitFile = "C:\\Users\\DELL\\Desktop\\002.xlsx";

        String exitFile1 = "C:\\Users\\DELL\\Desktop\\001.xlsx";

        List<String[]> list1 = ReadExcelTools.readExcelByfilePath(exitFile);

        List<String[]> list2 = ReadExcelTools.readExcelByfilePath(exitFile1);


        StringBuilder builder = new StringBuilder();
        for (String[] info1 : list1) {
            for (String[] info2 : list2) {
                if (info2[1].trim().equals(info1[0].trim())) {
                    JSONObject object = JSONObject.parseObject(info1[1].replaceAll("'",""));
                    object.put("paymentPeriod",Integer.valueOf(info2[0])/30);
                    builder.append("update contract set content = '" + object + "' where contract_id = " + object.get("contractId").toString() + ";");
                    builder.append(System.getProperty("line.separator"));
                }
            }
        }
        PrintWriter out = new PrintWriter(new File("C:\\Users\\DELL\\Desktop\\更新账期.sql").getAbsoluteFile());

        out.print(builder.toString());
        out.flush();
        out.close();




    }

}
