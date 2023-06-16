package com.sangang.cloud.temp;
/**
 * paragraph.setAlignment(ParagraphAlignment.LEFT);//---对齐方式 指定应适用于此段落中的文本的段落对齐方式。CENTER LEFT...
 * paragraph.setBorderBetween(Borders.APPLES);//--//边界
 * paragraph.setBorderBottom(Borders.APPLES);
 * paragraph.setBorderLeft(Borders.APPLES);//---指定应显示在左边页面指定段周围的边界。
 * paragraph.setBorderRight(Borders.ARCHED_SCALLOPS);//---指定应显示在右侧的页面指定段周围的边界。
 * paragraph.setBorderTop(Borders.ARCHED_SCALLOPS);//---指定应显示上方一组有相同的一组段边界设置的段落的边界。这几个是对段落之间的格式的统一，相当于格式刷
 * paragraph.setFirstLineIndent(99);//---首行缩进：正文宽度会稍微变窄
 * paragraph.setFontAlignment(1);//---段落中的字体的对齐方式 1左 2中 3右 4往上 左 不可写0和负数
 * paragraph.setIndentationFirstLine(400);//---首行缩进,指定额外的缩进，应适用于父段的第一行。567==1厘米
 * paragraph.setIndentationHanging(400);//---首行前进,指定的缩进量，应通过第一行回到开始的文本流的方向上移动缩进从父段的第一行中删除。
 * paragraph.setIndentationLeft(400);//---整段缩进（右移）指定应为从左到右段，该段的内容的左边的缘和这一段文字左边的距和右边文本边距和左段权中的那段文本的右边缘之间的缩进,如果省略此属性，则应假定其值为零。
 * paragraph.setIndentationRight(400);//---指定应放置这一段，该段的内容从左到右段的右边缘的正确文本边距和右边文本边距和左段权中的那段文本的右边缘之间的缩进,如果省略此属性，则应假定其值为零。
 * paragraph.setIndentFromLeft(400);//---整段右移
 * paragraph.setIndentFromRight(400);
 * paragraph.setNumID(BigInteger.TEN);//设置段落编号-----有效果看不懂（仅仅是整段缩进4个字）
 * paragraph.setPageBreak(true);//--指定当渲染此分页视图中的文档，这一段的内容都呈现在文档中的新页的开始。
 * paragraph.setSpacingAfter(6);//--指定应添加在文档中绝对单位这一段的最后一行之后的间距。
 * paragraph.setSpacingAfterLines(6);//--指定应添加在此线单位在文档中的段落的最后一行之后的间距。
 * paragraph.setSpacingBefore(6);//--指定应添加上面这一段文档中绝对单位中的第一行的间距。
 * paragraph.setSpacingBeforeLines(6);//--指定应添加在此线单位在文档中的段落的第一行之前的间距。
 * paragraph.setSpacingLineRule(LineSpacingRule.AT_LEAST);//--指定行之间的间距如何计算存储在行属性中。
 * paragraph.setStyle("标题 3");//--此方法提供了样式的段落，这非常有用. 需要结合addCustomHeadingStyle(docxDocument, "标题 3", 3)配合使用
 * paragraph.setVerticalAlignment(TextAlignment.CENTER);//---指定的文本的垂直对齐方式将应用于此段落中的文本
 * paragraph.setWordWrapped(true);//--此元素指定是否消费者应中断超过一行的文本范围，通过打破这个词 （打破人物等级） 的两行或通过移动到下一行 （在词汇层面上打破） 这个词的拉丁文字。
 * XWPFRun run=paragraph.createRun();//paragraph.createRun()将一个新运行追加到这一段
 * setText(String value)或setText(String value,int pos)
 * run.setText(data);
 * run.setTextPosition(20);//这个相当于设置行间距的，具体这个20是怎么算的，不清楚,此元素指定文本应为此运行在关系到周围非定位文本的默认基线升降的量。不是真正意义上的行间距
 * run.setStrike(true);//---设置删除线的,坑人!!!
 * run.setStrikeThrough(true);---也是设置删除线，可能有细微的区别吧
 * run.setEmbossed(true);---变的有重影（变黑了一点）
 * run.setDoubleStrikethrough(true);---设置双删除线
 * run.setColor("33CC00");//---设置字体颜色 ★
 * run.setFontFamily("fantasy");
 * run.setFontFamily("cursive");//---设置ASCII(0 - 127)字体样式
 * run.setBold(jiacu);//---"加黑加粗"
 * run.setFontSize(size);//---字体大小
 * run.setImprinted(true);//感觉与setEmbossed(true)类似，有重影
 * run.setItalic(true);//---文本会有倾斜，是一种字体？
 * run.setShadow(true);//---文本会变粗有重影，与前面两个有重影效果的方法感觉没什么区别
 * run.setSmallCaps(true);//---改变了  英文字母  的格式
 * run.setSubscript(VerticalAlign.BASELINE);//---valign垂直对齐的
 * run.setUnderline(UnderlinePatterns.DASH);//--填underline type设置下划线
 * document.createTable(2, 2);//--创建一个制定行列的表
 * document.enforceReadonlyProtection();//--强制执行制度保护
 * run.setDocumentbackground(doc, "FDE9D9");//设置页面背景色
 * run.testSetUnderLineStyle(doc);//设置下划线样式以及突出显示文本
 * run.addNewPage(doc, BreakType.PAGE);
 * run.testSetShdStyle(doc);//设置文字底纹
 */