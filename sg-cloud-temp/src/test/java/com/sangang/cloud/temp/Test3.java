package com.sangang.cloud.temp;

import com.sangang.cloud.temp.util.DocUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Test3 {

    public static void main(String[] args) {
        //替换文字
        rdi();

    }

    public static void rdi(){
        try {
            FileInputStream is = new FileInputStream("D:/test/test.docx");
            XWPFDocument doc = new XWPFDocument(is);

            Map<String,String> dataMap = new HashMap<>();
            dataMap.put("name","大白\n小白");
            dataMap.put("age","18");
            dataMap.put("url","朝阳合生汇");

            Map<String, InputStream> imgMap = new HashMap<>();
            imgMap.put("img",new FileInputStream("D:/test/test.png"));

            DocUtil.refreshBooks(doc,dataMap,imgMap);

            doc.write(new FileOutputStream("d:/test/temp/03.docx"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void rtext(){
        try {
            FileInputStream is = new FileInputStream("D:/test/test.docx");
            XWPFDocument doc = new XWPFDocument(is);

            Map<String,String> dataMap = new HashMap<>();
            dataMap.put("wenzi","大白\n小白");
            DocUtil.refreshBooksData(doc,dataMap);

            doc.write(new FileOutputStream("d:/test/temp/01.docx"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void rimg(){
        try {
            FileInputStream is = new FileInputStream("D:/test/test.docx");
            XWPFDocument doc = new XWPFDocument(is);

            Map<String, InputStream> dataMap = new HashMap<>();
            dataMap.put("tupian",new FileInputStream("D:/test/test.png"));
            DocUtil.refreshBooksImg(doc,dataMap);

            doc.write(new FileOutputStream("d:/test/temp/02.docx"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
