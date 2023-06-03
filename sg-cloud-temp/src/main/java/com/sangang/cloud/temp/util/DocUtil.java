package com.sangang.cloud.temp.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

import java.io.IOException;
import java.io.InputStream;
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
                    run.addPicture(picIs,XWPFDocument.PICTURE_TYPE_PNG,"bus.png,", Units.toEMU(100), Units.toEMU(200));
                }
            }
        }

    }

    private static void createParagraphs(XWPFParagraph xwpfParagraph, String[] paragraphs,XWPFDocument document) {
        if(xwpfParagraph!=null){
            for (int i = 0; i < paragraphs.length; i++) {
                XmlCursor cursor = xwpfParagraph.getCTP().newCursor();
                XWPFParagraph newParagraph = document.insertNewParagraph(cursor);
                newParagraph.setAlignment(xwpfParagraph.getAlignment());
                newParagraph.getCTP().insertNewR(0).insertNewT(0).setStringValue(paragraphs[i]);
                newParagraph.setNumID(xwpfParagraph.getNumID());
                List<XWPFRun> runs = xwpfParagraph.getRuns();
                List<XWPFRun> runs1 = newParagraph.getRuns();
                System.out.println("");
            }
            document.removeBodyElement(document.getPosOfParagraph(xwpfParagraph));
        }
    }


}
