package com.sangang.cloud.temp;

import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test2 {
    public static void main(String[] args) throws IOException {
        String uploadPath = "D:/test/";
        String template = "test.docx";
        String expotr = "temp/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")) + "-" + template;
        String templateUrl = uploadPath + template;
        String exportUrl = uploadPath + expotr;

        Map<String, String> map = new HashMap<>();
        map.put("name","王五\n与赵六");
        map.put("age","25");
        map.put("url","ss");

        //获取当前系统支持的换行符
        String line = System.getProperty("line.separator");
        XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(templateUrl));

        Map<String, String> replacedElementsMap;
        List<XWPFParagraph> xwpfParagraphs = document.getParagraphs();
        /*for(XWPFParagraph xwpfParagraph : xwpfParagraphs) {
            List<XWPFRun> xwpfRuns = xwpfParagraph.getRuns();
            for(XWPFRun xwpfRun : xwpfRuns) {
                String xwpfRunText = xwpfRun.getText(xwpfRun.getTextPosition());
                for(Map.Entry<String, String> entry : map.entrySet()) {
                    if (xwpfRunText != null && xwpfRunText.contains(entry.getKey())) {
                        xwpfRunText = xwpfRunText.replaceAll(entry.getKey(), entry.getValue());
                    }
                }
                xwpfRun.setText(xwpfRunText, 0);
            }
        }*/
        replaceElementInParagraphs(xwpfParagraphs,map,document);
        FileOutputStream outStream = null;
        outStream = new FileOutputStream(exportUrl);
        document.write(outStream);
        outStream.close();
    }

    protected static void replaceElementInParagraphs(List<XWPFParagraph> xwpfParagraphs,
                                              Map<String, String> replacedMap,XWPFDocument document) {
        if (!searchInParagraphs(xwpfParagraphs, replacedMap,document)) {
            replaceElementInParagraphs(xwpfParagraphs, replacedMap,document);
        }
    }

    private static boolean searchInParagraphs(List<XWPFParagraph> xwpfParagraphs, Map<String, String> replacedMap,XWPFDocument document) {
        for(XWPFParagraph xwpfParagraph : xwpfParagraphs) {
            List<XWPFRun> xwpfRuns = xwpfParagraph.getRuns();
            for(XWPFRun xwpfRun : xwpfRuns) {
                String xwpfRunText = xwpfRun.getText(xwpfRun.getTextPosition());
                for(Map.Entry<String, String> entry : replacedMap.entrySet()) {
                    if (xwpfRunText != null && xwpfRunText.contains(entry.getKey())) {
                        if (entry.getValue().contains("\n")) {
                            xwpfRunText = xwpfRunText.replaceAll(entry.getKey(), entry.getValue());
                            String[] paragraphs = xwpfRunText.split("\n");
                            entry.setValue("");
                            createParagraphs(xwpfParagraph, paragraphs,document);
                            return false;
                        }
                        xwpfRunText = xwpfRunText.replaceAll(entry.getKey(), entry.getValue());
                        System.out.println(xwpfRunText);
                    }
                }
                xwpfRun.setText(xwpfRunText, 0);
            }
        }
        return true;
    }

    /**
     *
     * @param xwpfParagraph
     * @param paragraphs
     * @param document
     */
    private static void createParagraphs(XWPFParagraph xwpfParagraph, String[] paragraphs,XWPFDocument document) {
        if(xwpfParagraph!=null){
            for (int i = 0; i < paragraphs.length; i++) {
                XmlCursor cursor = xwpfParagraph.getCTP().newCursor();
                XWPFParagraph newParagraph = document.insertNewParagraph(cursor);

                ParagraphAlignment alignment = xwpfParagraph.getAlignment();
                BigInteger numID = xwpfParagraph.getNumID();
                newParagraph.setAlignment(alignment);
                newParagraph.setNumID(numID);
                newParagraph.getCTP().insertNewR(0).insertNewT(0).setStringValue(paragraphs[i]);

                List<XWPFRun> runs = xwpfParagraph.getRuns();
                List<XWPFRun> runs1 = newParagraph.getRuns();
                System.out.println("");
            }
            document.removeBodyElement(document.getPosOfParagraph(xwpfParagraph));
        }
    }

}
