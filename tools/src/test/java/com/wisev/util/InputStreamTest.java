package com.wisev.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class InputStreamTest {


    public static void main(String[] args) throws Exception {

        BufferedReader fis = new BufferedReader(new FileReader(new File("C:\\Users\\DELL\\Desktop\\读取文档.txt")));


        String n;
        while((n = fis.readLine()) != null) {
//            fis.read(buffer);
            System.out.println(n);
        }
    }
}
