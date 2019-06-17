package com.wiserv.util.word;

import com.wiserv.util.util.IOUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class WordExportService {

    private  static Configuration configuration;

    static  {
        try{
            configuration = new Configuration(Configuration.VERSION_2_3_21);
            String path = WordExportService.class.getClassLoader().getResource("/template").getPath();
            configuration.setDirectoryForTemplateLoading(new File(path));
            configuration.setDefaultEncoding("UTF-8");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建word文档
     * @param dataMap
     * @param fileName
     * @param docTemplate
     */
    public OutputStream getDocFileOutputStream(Map<String,Object> dataMap,String fileName, InputStream docTemplate) {
        ZipInputStream zipInputStream = null;
        ByteArrayOutputStream bos = null;
        try{
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            ZipOutputStream zipOutputStream = new ZipOutputStream(fos);
            zipInputStream = new ZipInputStream(docTemplate);
            ZipEntry zipEntry;
            while((zipEntry = zipInputStream.getNextEntry())!=null){//通过zip.getNextEntry（）来得到ZipEntry对象。
                String fileName_zip = zipEntry.getName();//得到文件名称
                zipOutputStream.putNextEntry(new ZipEntry(fileName_zip));
                if("word/document.xml".equals(fileName_zip)){
                    bos = new ByteArrayOutputStream();
                    int i;
                    while((i = zipInputStream.read()) != -1) {
                        bos.write(i);
                    }
                    byte[] bytes = bos.toByteArray();
                    bytes = getReplacedDocumentXml(dataMap,new ByteArrayInputStream(bytes));
                    zipOutputStream.write(bytes);
                }else {
                    int i;
                    while((i = zipInputStream.read()) != -1) {
                        zipOutputStream.write(i);
                    }
                }
            }
            return zipOutputStream;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            IOUtil.quietClose(bos);
            IOUtil.quietClose(zipInputStream);
            IOUtil.quietClose(docTemplate);
        }
        return null;
    }

    /**
     * 替换word模板中的document.xml文件
     * @param dataMap 填充的数据
     * @param documentXml
     * @return
     */
    private byte[] getReplacedDocumentXml(Map<String,Object> dataMap,InputStream documentXml) {
        InputStreamReader reader = null;
        OutputStreamWriter writer = null;
        ByteArrayOutputStream byteOut = null;
        try{
            reader = new InputStreamReader(documentXml);
            Template template = new Template("document.xml",reader,configuration);
            byteOut = new ByteArrayOutputStream();
            writer = new OutputStreamWriter(byteOut);
            template.process(dataMap,writer);
            return byteOut.toByteArray();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            IOUtil.quietClose(documentXml);
            IOUtil.quietClose(byteOut);
            IOUtil.quietClose(writer);
            IOUtil.quietClose(reader);
        }
       return null;
    }


}
