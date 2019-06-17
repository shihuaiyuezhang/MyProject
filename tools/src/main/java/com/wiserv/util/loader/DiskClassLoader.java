package com.wiserv.util.loader;

import java.io.*;

/***
 * 加载本地的class文件
 */
public class DiskClassLoader extends ClassLoader {

    private String loadPath;

    private DiskClassLoader(){}


    public DiskClassLoader(String loadPath) {
        this.loadPath = loadPath;
    }

    public Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = getFileName(name);
        File file = new File(fileName);
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            ByteArrayOutputStream byteArrayOutputStream  = new ByteArrayOutputStream();
            int len = 0;
            while((len = fileInputStream.read()) != -1) {
                byteArrayOutputStream.write(len);
            }
            byte[] data = byteArrayOutputStream.toByteArray();
            return this.defineClass(name,data,0,data.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private String getFileName(String name) {
        int index = name.lastIndexOf('.');
        if(index == -1){
            return name + ".class";
        }else{
            return name.substring(index + 1) + ".class";
        }
    }
}
