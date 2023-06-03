package com.sangang.cloud.temp;

import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception {

        String uploadPath = "D:/test/";
        String template = "test.docx";
        String expotr = "temp/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")) + "-" + template;
        String templateUrl = uploadPath + template;
        String exportUrl = uploadPath + expotr;

        Map<String, String> map = new HashMap<>();
        map.put("{{name}}","张三\n与李四");
        map.put("{{age}}","25");
        map.put("{{url}}","ss");

        //获取当前系统支持的换行符
        String line = System.getProperty("line.separator");
        XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(templateUrl));
        Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
        while (itPara.hasNext()) {
            XWPFParagraph xwpfParagraph = (XWPFParagraph) itPara.next();
            List<XWPFRun> xwpfRuns = xwpfParagraph.getRuns();
            for(XWPFRun xwpfRun : xwpfRuns) {
                String oneparaString = xwpfRun.getText(xwpfRun.getTextPosition());
                //String oneparaString = runs.get(i).getText(runs.get(i).getTextPosition()).trim();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (oneparaString.contains(entry.getKey())) {
                        String text = entry.getValue();
                        if (text.contains("\n")) {
                            String[] lines = text.split("\n");
                            xwpfRun.setText(lines[0], 0); // set first line into XWPFRun
                            for(int j=1;j<lines.length;j++){
                                // add break and insert new text
                                xwpfRun.addBreak();
                                xwpfRun.setText(lines[j]);
                            }
                        } else {
                            xwpfRun.setText(text, 0);
                        }
                        oneparaString = oneparaString.replace(entry.getKey(), entry.getValue());
                    }
                }
                //runs.get(i).setText(getFormatText(oneparaString), 0);
            }
        }
        FileOutputStream outStream = null;
        outStream = new FileOutputStream(exportUrl);
        document.write(outStream);
        outStream.close();
    }

    protected void replaceElementInParagraphs(List<XWPFParagraph> xwpfParagraphs,
                                              Map<String, String> replacedMap) {
        if (!searchInParagraphs(xwpfParagraphs, replacedMap)) {
            replaceElementInParagraphs(xwpfParagraphs, replacedMap);
        }
    }

    private boolean searchInParagraphs(List<XWPFParagraph> xwpfParagraphs, Map<String, String> replacedMap) {
        for(XWPFParagraph xwpfParagraph : xwpfParagraphs) {
            List<XWPFRun> xwpfRuns = xwpfParagraph.getRuns();
            for(XWPFRun xwpfRun : xwpfRuns) {
                String xwpfRunText = xwpfRun.getText(xwpfRun.getTextPosition());
                for(Map.Entry<String, String> entry : replacedMap.entrySet()) {
                    if (xwpfRunText != null && xwpfRunText.contains(entry.getKey())) {
                        if (entry.getValue().contains("\n")) {
                                String[] paragraphs = entry.getValue().split("\n");
                                entry.setValue("");
                        //createParagraphs(xwpfParagraph, paragraphs);
                        return false;
                    }
                    xwpfRunText = xwpfRunText.replaceAll(entry.getKey(), entry.getValue());
                }
            }
            xwpfRun.setText(xwpfRunText, 0);
            }
        }
            return true;
    }

    /*private void createParagraphs(XWPFParagraph xwpfParagraph, String[] paragraphs) {
        if(xwpfParagraph!=null){
            for (int i = 0; i < paragraphs.length; i++) {
                XmlCursor cursor = xwpfParagraph.getCTP().newCursor();
                XWPFParagraph newParagraph = document.insertNewParagraph(cursor);
                newParagraph.setAlignment(xwpfParagraph.getAlignment());
                newParagraph.getCTP().insertNewR(0).insertNewT(0).setStringValue(paragraphs[i]);
                newParagraph.setNumID(xwpfParagraph.getNumID());
            }
            document.removeBodyElement(document.getPosOfParagraph(xwpfParagraph));
        }
    }*/


    public static String getFormatText(String text){

        //处理转义字符
        text = transform(text);
        //TODO 此处只考虑word成文过程中换行、空格显示问题，在实际开发过程中，可能会遇到其他格式显示，可在此处根据业务需求进行维护
        //freemarker 换行
        text = text.replaceAll("\r\n","<w:br/>");
        //freemarker enter换行
        text = text.replaceAll("\n","<w:br/>");
        //freemarker 空格 采用ASCII编码值进行替换
        text = text.replaceAll(" ","&#160;");
        //freemarker tab
        text = text.replaceAll("\t","&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;");


        return text ;
    }

    /**
     * 处理转义字符
     * @param str
     * @return
     */
    private static String transform(String str){
        if(str.contains("<")||str.contains(">")||str.contains("&")){
            str=str.replaceAll("&", "&amp;");
            str=str.replaceAll("<", "&lt;");
            str=str.replaceAll(">", "&gt;");
        }
        return str;
    }
}
