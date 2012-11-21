package com.orange.pobox.pdf;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public interface UPSPdfCreator {
	public FileOutputStream createDocument(OutputStream out) throws IOException;
	public void sendPDFtoUPS();
}
