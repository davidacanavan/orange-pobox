package com.orange.pobox.pdf;


import java.awt.Color;
import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.klg.jclass.page.JCDocument;
import com.klg.jclass.page.JCDrawStyle;
import com.klg.jclass.page.JCFlow;
import com.klg.jclass.page.JCFrame;
import com.klg.jclass.page.JCPageTable;
import com.klg.jclass.page.JCPrinter;
import com.klg.jclass.page.JCTextStyle;
import com.klg.jclass.page.JCUnit;
import com.klg.jclass.page.adobe.pdf.JCPDFPrinter;

public class UPSpdfGenerator implements UPSPdfCreator{
	
	private UPSCustomerInfo custInfo;
	
	public UPSCustomerInfo getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(UPSCustomerInfo custInfo) {
		this.custInfo = custInfo;
	}

	public UPSpdfGenerator(UPSCustomerInfo custInfo) {
		super();
		this.custInfo = custInfo;
	}

	public FileOutputStream createDocument(OutputStream output) throws IOException{
		//File file = new File("test.pdf");
		//OutputStream output = new FileOutputStream(this.custInfo.getCustName()+"'s_UPS_MailBox_Application.pdf");
		BufferedOutputStream bos = new BufferedOutputStream(output, 4096);

        // Create a PDF printer, output to stdout
        JCPrinter printer = new JCPDFPrinter(output);

        // Create a document using the PDF printer for formatting
        // setting the page template to be a simple 8.5 x 11 Letter page
        JCDocument document = new JCDocument(printer, JCDocument.BLANK_A4);
        
		try {
	        Image logo = java.awt.Toolkit.getDefaultToolkit().getImage("ups_example_logo_1.jpg");
	        // instantiate a flow object on the document
	        JCFlow flow = new JCFlow(document);
	        JCFrame frame;
	        
	        JCTextStyle default_plain = (JCTextStyle) flow.getCurrentTextStyle().clone();
	        default_plain.setParagraphSpacing(3);
	        //default_plain.makeImmutable();
	        
	        // make center style
	        JCTextStyle default_center = (JCTextStyle) default_plain.clone();
	        default_center.setAlignment(JCTextStyle.ALIGNMENT_CENTER);
	        default_center.setPointSize(12);
	        default_center.setParagraphSpacing(2.5);
	        default_center.setFontFamily("Arial");
	        default_center.makeImmutable();
	        
	        // make left style
	        JCTextStyle default_left = (JCTextStyle) default_plain.clone();
	        default_left.setAlignment(JCTextStyle.ALIGNMENT_LEFT);
	        default_left.setPointSize(12);
	        default_left.setParagraphSpacing(2.5);
	        default_left.setFontFamily("Arial");
	        default_left.setColor(Color.BLACK);
	        default_left.makeImmutable();
	        
	        // make right style
	        JCTextStyle default_right = (JCTextStyle) default_plain.clone();
	        default_right.setAlignment(JCTextStyle.ALIGNMENT_RIGHT);
	        default_right.setPointSize(12);
	        default_right.setParagraphSpacing(2.5);
	        default_right.setFontFamily("Arial");
	        default_right.setColor(Color.BLACK);
	        default_right.makeImmutable();
	
	        JCDrawStyle drawStyle_NOLINE = new JCDrawStyle();
	        drawStyle_NOLINE.setLineType(JCDrawStyle.LINE_TYPE_NONE);
	        
	        JCDrawStyle drawStyle_SINGLE = new JCDrawStyle();
	        drawStyle_SINGLE.setLineType(JCDrawStyle.LINE_TYPE_SINGLE);
	        
	        JCDrawStyle cellstyle = new JCDrawStyle();
	        JCDrawStyle.stringToStyle("default line").clone();
	        cellstyle.setLineType(JCDrawStyle.LINE_TYPE_SINGLE);
	        	        
	        JCPageTable logoTable = new JCPageTable(document, 1);
	        logoTable.fitToFrame(flow.getCurrentFrame(), flow.getCurrentTextStyle());
	        logoTable.setAllBorders(drawStyle_NOLINE);
	        logoTable.setAlignment(JCTextStyle.ALIGNMENT_LEFT);
	        
	        frame = logoTable.getCellFrame(0, 0);
	        frame.embedImage(default_left, logo);
	        frame.print(default_left, "");
	        frame.print(default_left, "");
	        flow.print(logoTable);
	        
	        JCPageTable custTab = new JCPageTable(document,2);
	        JCPageTable.Cell cell = null;
	        custTab.fitToFrame(flow.getCurrentFrame(), flow.getCurrentTextStyle());
	        custTab.setAllBorders(drawStyle_NOLINE);
	        custTab.setColumnWidth(0, new JCUnit.Measure(JCUnit.POINTS, 100));
	        custTab.setColumnWidth(1, new JCUnit.Measure(JCUnit.POINTS, 400));
	        custTab.setAlignment(JCTextStyle.ALIGNMENT_LEFT);
	        //custTab.spanCells(0, 0, 1, 2);
	        frame = custTab.getCellFrame(0, 0);
	        frame.print(default_right, "Date:");
	        frame = custTab.getCellFrame(0, 1);
	        cell = custTab.getCell(0, 1);
	        cell.setBottomBorderStyle(cellstyle);
	        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        frame.print(default_left, formatter.format(new Date()));
	        
	        frame.newLine(flow.getCurrentTextStyle());
	        frame = custTab.getCellFrame(1, 0);
	        frame.print(default_right, "Name of Client:");
	        frame = custTab.getCellFrame(1, 1);
	        cell = custTab.getCell(1, 1);
	        cell.setBottomBorderStyle(cellstyle);
	        frame.print(default_left, custInfo.getCustName());

	        frame.newLine(flow.getCurrentTextStyle());
	        frame = custTab.getCellFrame(2, 0);
	        frame.print(default_right, "Address:");
	        frame = custTab.getCellFrame(2, 1);
	        cell = custTab.getCell(2, 1);
	        cell.setBottomBorderStyle(cellstyle);
	        frame.print(default_left, custInfo.getAddress());
	        
	        frame.newLine(flow.getCurrentTextStyle());
	        frame = custTab.getCellFrame(3, 0);
	        frame.print(default_right, "Tel:");
	        frame = custTab.getCellFrame(3, 1);
	        cell = custTab.getCell(3, 1);
	        cell.setBottomBorderStyle(cellstyle);
	        frame.print(default_left, custInfo.getTel());
	        
	        frame.newLine(flow.getCurrentTextStyle());
	        frame = custTab.getCellFrame(4, 0);
	        frame.print(default_right, "Cell:");
	        frame = custTab.getCellFrame(4, 1);
	        cell = custTab.getCell(4, 1);
	        cell.setBottomBorderStyle(cellstyle);
	        frame.print(default_left, custInfo.getCell());
	        
	        frame.newLine(flow.getCurrentTextStyle());
	        frame = custTab.getCellFrame(5, 0);
	        frame.print(default_right, "Fax:");
	        frame = custTab.getCellFrame(5, 1);
	        cell = custTab.getCell(5, 1);
	        cell.setBottomBorderStyle(cellstyle);
	        frame.print(default_left, custInfo.getFax());
	        
	        frame.newLine(flow.getCurrentTextStyle());
	        frame = custTab.getCellFrame(6, 0);
	        frame.print(default_right, "Email:");
	        frame = custTab.getCellFrame(6, 1);
	        cell = custTab.getCell(6, 1);
	        cell.setBottomBorderStyle(cellstyle);
	        frame.print(default_left, custInfo.getEmail());
	        
	        frame.newLine(flow.getCurrentTextStyle());
	        frame = custTab.getCellFrame(7, 0);
	        frame.print(default_left, " ");
	        frame = custTab.getCellFrame(7, 1);
	        frame.print(default_left, " ");
	        frame.newLine(flow.getCurrentTextStyle());
	        frame = custTab.getCellFrame(8, 0);
	        cell = custTab.getCell(8, 0);
	        cell.setBottomBorderStyle(cellstyle);
	        frame.print(default_left, " ");
	        frame = custTab.getCellFrame(8, 1);
	        cell = custTab.getCell(8, 1);
	        cell.setBottomBorderStyle(cellstyle);
	        frame.newLine(flow.getCurrentTextStyle());
	        frame.print(default_left, " ");
	        flow.print(custTab);
	        flow.newLine();
	        flow.newLine();
	        //half table:
	        // make right style
	        JCDrawStyle cellstyle_nonBord = new JCDrawStyle();
	        cellstyle_nonBord.setLineType(JCDrawStyle.LINE_TYPE_NONE);
	        cellstyle_nonBord.setLineSpacing(new JCUnit.Measure(JCUnit.POINTS, 30));
	        
	        JCTextStyle half_right = (JCTextStyle) default_plain.clone();
	        half_right.setAlignment(JCTextStyle.ALIGNMENT_RIGHT);
	        half_right.setPointSize(10);
	        half_right.setParagraphSpacing(4);
	        half_right.setFontFamily("Arial");
	        half_right.setColor(Color.BLACK);
	        half_right.makeImmutable();
	        
	        JCTextStyle half_left = (JCTextStyle) default_plain.clone();
	        half_left.setAlignment(JCTextStyle.ALIGNMENT_LEFT);
	        half_left.setPointSize(10);
	        half_left.setParagraphSpacing(4);
	        half_left.setFontFamily("Arial");
	        half_left.setColor(Color.BLACK);
	        half_left.makeImmutable();
	        
	        JCTextStyle half_center = (JCTextStyle) default_plain.clone();
	        half_center.setAlignment(JCTextStyle.ALIGNMENT_CENTER);
	        half_center.setPointSize(10);
	        half_center.setParagraphSpacing(4);
	        half_center.setFontFamily("Arial");
	        half_center.setColor(Color.BLACK);
	        half_center.makeImmutable();
	        
	        JCPageTable halfTab = new JCPageTable(document,11);
	        halfTab.fitToFrame(flow.getCurrentFrame(), flow.getCurrentTextStyle());
	        double pagFrameWid = (flow.getCurrentFrame().getColumnWidth()).getAs(JCUnit.POINTS);
	        System.out.println("Page Frame Width is: " + pagFrameWid);
	        
	        
	        halfTab.setColumnWidth(0, new JCUnit.Measure(JCUnit.POINTS, 20));
	        halfTab.setColumnWidth(1, new JCUnit.Measure(JCUnit.POINTS, 200));
	        halfTab.setColumnWidth(2, new JCUnit.Measure(JCUnit.POINTS, 10));
	        halfTab.setColumnWidth(3, new JCUnit.Measure(JCUnit.POINTS, 10));
	        halfTab.setColumnWidth(4, new JCUnit.Measure(JCUnit.POINTS, 55));
	        halfTab.setColumnWidth(5, new JCUnit.Measure(JCUnit.POINTS, 55));
	        halfTab.setColumnWidth(6, new JCUnit.Measure(JCUnit.POINTS, 10));
	        halfTab.setColumnWidth(7, new JCUnit.Measure(JCUnit.POINTS, 55));
	        halfTab.setColumnWidth(8, new JCUnit.Measure(JCUnit.POINTS, 10));
	        halfTab.setColumnWidth(9, new JCUnit.Measure(JCUnit.POINTS, 55));
	        halfTab.setColumnWidth(10, new JCUnit.Measure(JCUnit.POINTS, 10));
	        halfTab.setAllBorders(drawStyle_SINGLE);
	        halfTab.setAlignment(JCTextStyle.ALIGNMENT_CENTER);
	        halfTab.spanCells(13, 0, 1, 2);
	        halfTab.spanCells(0, 4, 1, 6);
	        halfTab.spanCells(8, 4, 1, 4);
	        halfTab.spanCells(9, 4, 1, 4);
	        halfTab.spanCells(10, 4, 1, 4);
	        halfTab.spanCells(11, 4, 1, 4);
	        halfTab.spanCells(12, 4, 1, 4);
	        halfTab.spanCells(13, 4, 1, 4);
	        
	        //Half left table, begin:----------------------------
	        for(int i=0;i<12;i++){
	        	frame.newLine(half_right);
	        	frame = halfTab.getCellFrame(i, 0);
		        cell = halfTab.getCell(i, 0);
		        cell.setBottomBorderStyle(cellstyle_nonBord);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        String text = " ";
		        String lineText = "";
		        if(i%3 == 0){
		        	text = i/3+1+"-";
		        }
		        System.out.print(text);
		        frame.print(half_right, text);
		        
		        if(i==0 && custInfo.getStartDate1() != null){
	        		lineText = custInfo.getStartDateStr1();
	        	}
		        if(i==1 && custInfo.getEndDate1() != null){
	        		lineText = custInfo.getEndDateStr1();
	        	}
	        	frame = halfTab.getCellFrame(i, 1);
		        cell = halfTab.getCell(i, 1);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        frame.print(half_center, lineText);
	        }
	        
	        frame.newLine(half_center);
	        frame = halfTab.getCellFrame(12, 0);
	        cell = halfTab.getCell(12, 0);
	        cell.setBottomBorderStyle(cellstyle_nonBord);
	        cell.setRightBorderStyle(cellstyle_nonBord);
	        frame.print(half_center, " ");
	        
	        frame.newLine(half_center);
	        frame = halfTab.getCellFrame(12, 1);
	        cell = halfTab.getCell(12, 1);
	        cell.setBottomBorderStyle(cellstyle_nonBord);
	        cell.setRightBorderStyle(cellstyle_nonBord);
	        frame.print(half_right, " ");
	        
	        frame.newLine(half_center);
	        frame = halfTab.getCellFrame(13, 0);
	        cell = halfTab.getCell(13, 0);
	        cell.setBottomBorderStyle(cellstyle_nonBord);
	        cell.setRightBorderStyle(cellstyle_nonBord);
	        frame.print(half_center, "__CD/DVD __USB __Email __Memory Card");
	        
	        frame.newLine(half_center);
	        frame = halfTab.getCellFrame(14, 0);
	        cell = halfTab.getCell(14, 0);
	        cell.setRightBorderStyle(cellstyle_nonBord);
	        frame.print(half_right, " ");
	        
	        frame.newLine(flow.getCurrentTextStyle());
	        frame = halfTab.getCellFrame(14, 1);
	        cell = halfTab.getCell(14, 1);
	        cell.setRightBorderStyle(cellstyle_nonBord);
	        frame.print(half_right, " ");
	        
	        for(int i=0;i<15;i++){
	        	frame.newLine(flow.getCurrentTextStyle());
	        	frame = halfTab.getCellFrame(i, 2);
		        cell = halfTab.getCell(i, 2);
		        cell.setBottomBorderStyle(cellstyle_nonBord);
		        frame.print(half_right, " ");
	        }
	        
	      //Half left table, end:----------------------------
	        
	      //Half middle table, begin:----------------------------
	        for(int i=0;i<15;i++){
	        	frame.newLine(flow.getCurrentTextStyle());
	        	frame = halfTab.getCellFrame(i, 3);
		        cell = halfTab.getCell(i, 3);
		        cell.setBottomBorderStyle(cellstyle_nonBord);
		        frame.print(half_right, " ");
	        }
	      //Half middle table, end:----------------------------
	        
	      //Half right table, begin:----------------------------
	        frame.newLine(flow.getCurrentTextStyle());
	        frame = halfTab.getCellFrame(0, 4);
	        cell = halfTab.getCell(0, 4);
	        cell.setBottomBorderStyle(cellstyle_nonBord);
	        cell.setRightBorderStyle(cellstyle_nonBord);
	        frame.print(half_left, "PRIX");
	        
	        for(int i=0;i<6;i++){
	        	frame.newLine(flow.getCurrentTextStyle());
	        	frame = halfTab.getCellFrame(1, i+4);
		        cell = halfTab.getCell(1, i+4);
		        cell.setBottomBorderStyle(cellstyle_nonBord);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        frame.print(half_right, " ");
	        }
	        
	        for(int i=1;i<5;i++){
	        	frame.newLine(half_right);
	        	frame = halfTab.getCellFrame(i+1,4);
		        cell = halfTab.getCell(i+1, 4);
		        cell.setBottomBorderStyle(cellstyle_nonBord);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        frame.print(half_right, i+" - $");
		        
		        frame.newLine(half_right);
		        frame = halfTab.getCellFrame(i+1,5);
		        cell = halfTab.getCell(i+1, 5);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        frame.print(half_center, " ");
		        
		        frame.newLine(flow.getCurrentTextStyle());
		        frame = halfTab.getCellFrame(i+1,6);
		        cell = halfTab.getCell(i+1, 6);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        cell.setBottomBorderStyle(cellstyle_nonBord);
		        frame.print(half_center, "x");
		        
		        frame.newLine(flow.getCurrentTextStyle());
		        frame = halfTab.getCellFrame(i+1,7);
		        cell = halfTab.getCell(i+1, 7);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        frame.print(half_center, " ");
		        
		        frame.newLine(flow.getCurrentTextStyle());
		        frame = halfTab.getCellFrame(i+1,8);
		        cell = halfTab.getCell(i+1, 8);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        cell.setBottomBorderStyle(cellstyle_nonBord);
		        frame.print(half_center, "=");
		        
		        frame.newLine(flow.getCurrentTextStyle());
		        frame = halfTab.getCellFrame(i+1,9);
		        cell = halfTab.getCell(i+1, 9);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        frame.print(half_center, " ");
	        }
	        
	        for(int i=4;i<10;i++){
	        	frame.newLine(flow.getCurrentTextStyle());
	        	frame = halfTab.getCellFrame(6, i);
		        cell = halfTab.getCell(6, i);
		        cell.setBottomBorderStyle(cellstyle_nonBord);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        frame.print(half_center, " ");
		        
		        frame.newLine(flow.getCurrentTextStyle());
		        frame = halfTab.getCellFrame(7, i);
		        cell = halfTab.getCell(7, i);
		        cell.setBottomBorderStyle(cellstyle_nonBord);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        frame.print(half_center, " ");
	        }
	        
	        frame.newLine(half_right);
	        frame = halfTab.getCellFrame(8, 4);
	        cell = halfTab.getCell(8, 4);
	        cell.setRightBorderStyle(cellstyle_nonBord);
	        cell.setBottomBorderStyle(cellstyle_nonBord);
	        frame.print(half_right, "Sub-Total:");
	        
	        frame.newLine(half_right);
	        frame = halfTab.getCellFrame(9, 4);
	        cell = halfTab.getCell(9, 4);
	        cell.setRightBorderStyle(cellstyle_nonBord);
	        cell.setBottomBorderStyle(cellstyle_nonBord);
	        frame.print(half_right, "Tax: #812412807 TPS:");
	        
	        frame.newLine(half_right);
	        frame = halfTab.getCellFrame(10, 4);
	        cell = halfTab.getCell(10, 4);
	        cell.setRightBorderStyle(cellstyle_nonBord);
	        cell.setBottomBorderStyle(cellstyle_nonBord);
	        frame.print(half_right, "#1217531833 TVQ:");
	        
	        frame.newLine(half_right);
	        frame = halfTab.getCellFrame(11, 4);
	        cell = halfTab.getCell(11, 4);
	        cell.setRightBorderStyle(cellstyle_nonBord);
	        cell.setBottomBorderStyle(cellstyle_nonBord);
	        frame.print(half_right, "TOTAL:");
	        
	        frame.newLine(half_right);
	        frame = halfTab.getCellFrame(12, 4);
	        cell = halfTab.getCell(12, 4);
	        cell.setRightBorderStyle(cellstyle_nonBord);
	        cell.setBottomBorderStyle(cellstyle_nonBord);
	        frame.print(half_right, "Depot:");
	        
	        frame.newLine(half_right);
	        frame = halfTab.getCellFrame(13, 4);
	        cell = halfTab.getCell(13, 4);
	        cell.setRightBorderStyle(cellstyle_nonBord);
	        cell.setBottomBorderStyle(cellstyle_nonBord);
	        frame.print(half_right, "BALANCE:");
	        
	        for(int i=8;i<14;i++){
	        	frame.newLine(half_right);
	        	frame = halfTab.getCellFrame(i, 8);
		        cell = halfTab.getCell(i, 8);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        frame.print(half_right, " ");
		        
		        frame.newLine(half_right);
		        frame = halfTab.getCellFrame(i, 9);
		        cell = halfTab.getCell(i, 9);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        frame.print(half_right, " ");
	        }
	        
	        for(int i=4;i<10;i++){
	        	frame.newLine(half_right);
		        frame = halfTab.getCellFrame(14, i);
		        cell = halfTab.getCell(14, i);
		        cell.setBottomBorderStyle(cellstyle_nonBord);
		        cell.setRightBorderStyle(cellstyle_nonBord);
		        frame.print(half_right, " ");
	        }
	        
	        for(int i=0;i<15;i++){
	        	frame.newLine(half_right);
	        	frame = halfTab.getCellFrame(i, 10);
		        cell = halfTab.getCell(i, 10);
		        cell.setBottomBorderStyle(cellstyle_nonBord);
		        frame.print(half_right, " ");
		        
	        }
	        
	        flow.print(halfTab);
	        
			// finally, print the document        
            document.print();
            return (FileOutputStream) output;
        } catch (Exception ex) {
        	ex.printStackTrace();
            System.out.println(ex.toString());
        }finally{
        	//flush the remaining bytes and close the pipe
        	bos.flush();
        	bos.close();
        }
		
        return (FileOutputStream) output;
        
	}

	public void sendPDFtoUPS(){
		try{
			String zipFileName = custInfo.getCustName()+"'s_UPS_MailBox_Application.pdf";
			File f = new File(zipFileName);
			FileOutputStream outStr = new FileOutputStream(f);
			outStr = this.createDocument(outStr);
			EmailSender mail = new EmailSender(f, zipFileName, custInfo.getUpsEmailAddr(),custInfo.getCustName(), custInfo.getEmail());
			mail.sendEmail();
			f.deleteOnExit();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
