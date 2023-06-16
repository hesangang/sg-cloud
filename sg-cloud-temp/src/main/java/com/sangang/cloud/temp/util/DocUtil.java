package com.sangang.cloud.temp.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.w3c.dom.Node;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class DocUtil {

    public static void refreshBooksData(XWPFDocument doc, Map<String, String> dataMap) {
        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        for (XWPFParagraph xwpfParagraph : paragraphs) {
            CTP ctp = xwpfParagraph.getCTP();

            for (int dwI = 0; dwI < ctp.sizeOfBookmarkStartArray(); dwI++) {
                CTBookmark bookmark = ctp.getBookmarkStartArray(dwI);

                String data = dataMap.get(bookmark.getName());
                if (data != null) {
                    XWPFRun run = xwpfParagraph.createRun();

                        if (data.contains("\n")) {
                            String[] lines = data.split("\n");
                            run.setText(lines[0], 0); // set first line into XWPFRun
                            for(int i=1;i<lines.length;i++){
                                // add break and insert new text
                                run.addBreak();
                                run.setText(lines[i]);
                            }
                        } else {
                            run.setText(data, 0);
                        }
                    //run.setText(data);

                    /*Node firstNode = bookmark.getDomNode();
                    Node nextNode = firstNode.getNextSibling();
                    while (nextNode != null) {
                        String nodeName = nextNode.getNodeName();
                        if (nodeName.equals("BOOKMARK_END_TAG")) {
                            break;
                        }
                        Node delNode = nextNode;
                        nextNode = nextNode.getNextSibling();

                        ctp.getDomNode().removeChild(delNode);
                    }

                    if (nextNode == null) {
                        ctp.getDomNode().insertBefore(run.getCTR().getDomNode(), firstNode);
                    } else {
                        ctp.getDomNode().insertBefore(run.getCTR().getDomNode(), nextNode);
                    }*/
                }
            }
        }

    }

    public static void refreshBooksImg(XWPFDocument doc, Map<String, InputStream> dataMap) throws IOException, InvalidFormatException {
        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        for (XWPFParagraph xwpfParagraph : paragraphs) {
            CTP ctp = xwpfParagraph.getCTP();

            for (int dwI = 0; dwI < ctp.sizeOfBookmarkStartArray(); dwI++) {
                CTBookmark bookmark = ctp.getBookmarkStartArray(dwI);

                InputStream picIs = dataMap.get(bookmark.getName());
                if(picIs != null){
                    XWPFRun run = xwpfParagraph.createRun();
                    //bus.png为鼠标在word里选择图片时，图片显示的名字，400，400则为像素单元，根据实际需要的大小进行调整即可。
                    run.addPicture(picIs,XWPFDocument.PICTURE_TYPE_PNG,"bus.png,", Units.toEMU(400), Units.toEMU(400));
                }
            }
        }

    }

    public static void refreshBooks(XWPFDocument doc, Map<String, String> dataMap, Map<String, InputStream> imgMap) throws IOException, InvalidFormatException {
        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        for (XWPFParagraph xwpfParagraph : paragraphs) {
            CTP ctp = xwpfParagraph.getCTP();
            List<CTBookmark> bl = ctp.getBookmarkStartList();
            List<CTR> rl = ctp.getRList();
            for (CTBookmark bookmark: ctp.getBookmarkStartList()) {
                String xwpfRunText = bookmark.getName();
                String data = dataMap.get(bookmark.getName());
                /*xwpfParagraph.setAlignment(xwpfParagraph.getAlignment());
                //ctp.insertNewR(0).insertNewT(0).setStringValue(paragraphs[i]);
                xwpfParagraph.setNumID(xwpfParagraph.getNumID());*/




                if (data != null) {
                    XWPFRun run = xwpfParagraph.createRun();

                    if (data.contains("\n")) {
                        String[] lines = data.split("\n");
                        //run.setText(lines[0], 0); // set first line into XWPFRun
                        for (int i = 0; i < lines.length; i++) {
                            // add break and insert new text
                            /*run.addBreak();
                            run.setText(lines[i],0);*/
                            xwpfParagraph.getCTP().insertNewR(0).insertNewT(0).setStringValue(lines[i]);
                        }
                    } else {
                        run.setText(data, 0);
                    }
                }

                InputStream picIs = imgMap.get(bookmark.getName());
                if(picIs != null){
                    XWPFRun run = xwpfParagraph.createRun();
                    //bus.png为鼠标在word里选择图片时，图片显示的名字，400，400则为像素单元，根据实际需要的大小进行调整即可。
                    run.addPicture(picIs,XWPFDocument.PICTURE_TYPE_PNG,"bus.png,", Units.toEMU(200), Units.toEMU(100));
                }
            }
        }

    }


    /**
     * 替换书签
     *
     * @param document
     * @param bookTagMap 书签map
     */
    public static void replaceBookTag(XWPFDocument document, Map<String, Object> bookTagMap) {
        List<XWPFParagraph> paragraphList = document.getParagraphs();
        for (XWPFParagraph xwpfParagraph : paragraphList) {
            CTP ctp = xwpfParagraph.getCTP();

            for (int dwI = 0; dwI < ctp.sizeOfBookmarkStartArray(); dwI++) {
                CTBookmark bookmark = ctp.getBookmarkStartArray(dwI);
                if (bookTagMap.containsKey(bookmark.getName())) {

                    XWPFRun run = xwpfParagraph.createRun();
                    setText(run,bookTagMap.get(bookmark.getName()).toString());

                    Node firstNode = bookmark.getDomNode();
                    Node nextNode = firstNode.getNextSibling();
                    while (nextNode != null) {
                        // 循环查找结束符
                        String nodeName = nextNode.getNodeName();
                        if (nodeName.equals("w:bookmarkEnd")) {
                            break;
                        }

                        // 删除中间的非结束节点，即删除原书签内容
                        Node delNode = nextNode;
                        nextNode = nextNode.getNextSibling();

                        ctp.getDomNode().removeChild(delNode);
                    }

                    if (nextNode == null) {
                        // 始终找不到结束标识的，就在书签前面添加
                        ctp.getDomNode().insertBefore(run.getCTR().getDomNode(), firstNode);
                    } else {
                        // 找到结束符，将新内容添加到结束符之前，即内容写入bookmark中间
                        ctp.getDomNode().insertBefore(run.getCTR().getDomNode(), nextNode);
                    }
                }
            }
        }
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
                //xwpfRun.setFontSize(12);//字号
                //xwpfRun.setFontFamily("仿宋_GB2312");//字样
                xwpfRun.setUnderline(UnderlinePatterns.SINGLE);//下划线（类型比较多大家自己尝试）
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

            }
            document.removeBodyElement(document.getPosOfParagraph(xwpfParagraph));
        }
    }

    private static void setText(XWPFRun run,String text){
        if (text.contains("\n")) {
            String[] lines = text.split("\n");
            run.setText(lines[0], 0); // set first line into XWPFRun
            for (int i = 1; i < lines.length; i++) {
                // add break and insert new text
                run.addBreak();
                run.setUnderline(UnderlinePatterns.SINGLE);
                run.setText(lines[i]);
            }
        } else {
            run.setUnderline(UnderlinePatterns.SINGLE);
            run.setText(text, 0);
        }
    }



}
