package com.wisev.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.collect.Lists;
import com.wiserv.util.excel.ExcelHelper;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

public class Test{



	public static void main(String[] args) throws Exception  {
        System.out.println("sdg");
        HashMap<Integer,Character> hashMap = new HashMap<>(16,2);
        String a = "update customer_info set department_company_id = ";
		for(int i = 0 ;i < 16;i++) {
            hashMap.put(i,a.charAt(i));
		}
        System.out.println(hashMap);



	}







}