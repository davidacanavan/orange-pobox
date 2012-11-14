package com.orange.pobox.pdf;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {

	private String fileName;
	private File file;
	private String emailAddr;
	private String ccAddr;
	private String custName;
	
	public EmailSender( File file,String fileName,String emailAddr, String custName, String ccAddr) {
		super();
		this.fileName = fileName;
		this.file = file;
		this.emailAddr = emailAddr;
		this.custName = custName;
		this.ccAddr = ccAddr;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCcAddr() {
		return ccAddr;
	}

	public void setCcAddr(String ccAddr) {
		this.ccAddr = ccAddr;
	}

	private Session createSmtpSession() {
		  final Properties props = new Properties();
		  props.setProperty("mail.smtp.host", "smtp.gmail.com");
		  props.setProperty("mail.smtp.auth", "true");
		  props.setProperty("mail.smtp.port", "" + 587);
		  props.setProperty("mail.smtp.starttls.enable", "true");
		  // props.setProperty("mail.debug", "true");

		  return Session.getDefaultInstance(props, new javax.mail.Authenticator() {

		    protected PasswordAuthentication getPasswordAuthentication() {
		      return new PasswordAuthentication("monica.tian.2012@gmail.com", "ucdcs2012");
		    }
		  });
	}
	
	public void sendEmail(){
		try {
			if(file.exists()){
				// create properties, including the sending server address
//				Properties mailProps = new Properties();
//				mailProps.put("mail.smtp.host", server);
				// create session
				Session mailSession = null;
				mailSession = createSmtpSession();  
				// create message
				MimeMessage message = new MimeMessage(mailSession);
				// set the sender
				message.setFrom(new InternetAddress("monica.tian.2012@gmail.com"));
				// set receiver
				//message.setRecipient();
				message.setRecipients(Message.RecipientType.TO, emailAddr);
				//message.setRecipient();
				message.setRecipients(Message.RecipientType.CC, ccAddr);
				//set subject
				message.setSubject(this.custName+" -- UPS MailBox Request Application Form");
				//create Mimemultipart
				MimeMultipart multi = new MimeMultipart();
				// create first BodyPart
				//it's used to set the content of the eamil
				BodyPart textBodyPart = new MimeBodyPart(); 
				textBodyPart.setText("This is a UPS MailBox Request Application Form from: "+this.custName+", please check the attatchment.");
				// compress the BodyPart to MimeMultipart.
				multi.addBodyPart(textBodyPart);
				//add attachment:
				String path = file.getAbsolutePath();
				FileDataSource fds = new FileDataSource(path); 
				BodyPart fileBodyPart = new MimeBodyPart(); 
				fileBodyPart.setDataHandler(new DataHandler(fds));
				fileBodyPart.setFileName(fileName); 
				multi.addBodyPart(fileBodyPart);
				//MimeMultPart added to message as Content
				message.setContent(multi);
				// save the operation before
				message.saveChanges();
				// sending by the class of Transport,the protocol is SMTP
				Transport.send(message);
			}else{
				throw new Exception("No pdf file!");
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
