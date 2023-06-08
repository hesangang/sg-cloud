package com.sangang.cloud.temp;

import java.io.*;

import java.io.File;

import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.pdfbox.text.PDFTextStripper;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import org.apache.poi.xwpf.usermodel.XWPFRun;

public class PdfToWord {

    public static void main(String[] args) throws Exception {
        String pdfFilePath = "D:/test/test.pdf";

        String docxFilePath = "D:/test/2022.docx";

        convertPdfToWord(pdfFilePath, docxFilePath);




    }

    public static void pdfToWord(String pdfFilePath, String docxFilePath) throws IOException {
        // 读取PDF文件

        PDDocument document = PDDocument.load(new File(pdfFilePath));

        PDFTextStripper stripper = new PDFTextStripper();

        String pdfText = stripper.getText(document);

        document.close();

// 创建Word文档并添加内容

        XWPFDocument wordDocument = new XWPFDocument();

        XWPFParagraph paragraph = wordDocument.createParagraph();

        XWPFRun run = paragraph.createRun();

        run.setText(pdfText);

// 保存生成的Word文档

        FileOutputStream outputStream = new FileOutputStream(new File(docxFilePath));

        wordDocument.write(outputStream);

        outputStream.close();

        wordDocument.close();

    }

    public static void convertPdfToWord(String pdfFilePath, String docxFilePath) {

        try {
            String text = readPDF(pdfFilePath);
// 加载PDF文档
            //PDDocument document = PDDocument.load(new FileInputStream(pdfFilePath));
// 创建Word文档
            XWPFDocument doc = new XWPFDocument();
// 提取PDF文本内容
            //PDFTextStripper stripper = new PDFTextStripper();
            //String text = stripper.getText(document);
// 创建段落并添加文本内容
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(text);
// 保存Word文档
            FileOutputStream out = new FileOutputStream(docxFilePath);
            doc.write(out);
            out.close();
// 关闭文档
            //document.close();
            doc.close();
            System.out.println("PDF转Word成功！");

        } catch (IOException e) {
            System.out.println("PDF转Word失败：" + e.getMessage());
        }

    }

    public static String readPDF(String file) throws IOException {
        StringBuilder result = new StringBuilder();
        FileInputStream is = null;
        PDDocument document = null;
        is = new FileInputStream(file);
        PDFParser parser = new PDFParser(new RandomAccessBuffer(is));
        parser.parse();
        PDDocument doc = parser.getPDDocument();
        PDFTextStripper textStripper =new PDFTextStripper();
        for(int i=1;i<=doc.getNumberOfPages();i++)
        {
            textStripper.setStartPage(i);
            textStripper.setEndPage(i);
            textStripper.setSortByPosition(true);//按顺序行读
            String s=textStripper.getText(doc);
            result.append(s);
        }
//        //文本为空，读图片提取图片里的文字，
//        if(result.toString().trim().length()==0){
//            for(int i=1;i<doc.getNumberOfPages();i++){
//                PDPage page=doc.getPage(i-1);
//                PDResources resources = page.getResources();
//                Iterable<COSName> xobjects =resources.getXObjectNames();
//                if(xobjects!=null) {
//                    Iterator<COSName> imageIter = xobjects.iterator();
//                    while (imageIter.hasNext()) {
//                        COSName cosName = imageIter.next();
//                        boolean isImageXObject = resources.isImageXObject(cosName);
//                        if (isImageXObject) {
//                            //获取每页资源的图片
//                            PDImageXObject ixt = (PDImageXObject) resources.getXObject(cosName);
//                            File outputfile = new File("D:\\tmp\\" + cosName.getName() + ".jpg");
//                            ImageIO.write(ixt.getImage(), "jpg", outputfile);//可保存图片到本地
//							 //调用图片识别文字接口
//							//...
//                        }
//                    }
//                }
//            }
//        }
        doc.close();
        return result.toString();
    }


}