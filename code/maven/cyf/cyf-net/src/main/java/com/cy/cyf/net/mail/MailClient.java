package com.cy.cyf.net.mail;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.cy.cyf.net.dto.AuthDTO;
import com.cy.cyf.util.ValidateUtil;

public class MailClient {
	
	private String host;
	private String fromAddress;
	private String fromName;
	private String userName;
	private String password;
	private boolean isSSL;
	private Session session;
	
	public final static String PROTOCOL = "smtp";
	public final static String MIXED="mixed";
	public final static String ALTERNATIVE="alternative";
	public final static String RELATED="related";
	public final static String PARALLEL="parallel";
	public final static String SIGNED="signed";
	public final static String PLAIN="plain";
	
	public MailClient(String host, String fromAddress,String fromName,
			String userName, String password, boolean isSSL) {
		this.host = host;
		this.fromAddress = fromAddress;
		this.fromName = fromName;
		this.userName = userName;
		this.password = password;
		this.isSSL = isSSL;
	}

	private MimeMessage createMessage(MailInfo mailInfo) throws MessagingException, IOException{
		MimeMessage message = new MimeMessage(this.session);
		
		//添加发送人
		Address address = new InternetAddress(this.fromAddress,this.fromName==null?"":this.fromName);
		message.setFrom(address);
		//添加收件人
		message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mailInfo.getToAddress()));
		//添加抄送人
		if(!ValidateUtil.isEmpty(mailInfo.getCcAddress())){
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(mailInfo.getCcAddress()));
		}
		//添加暗送人
		if(!ValidateUtil.isEmpty(mailInfo.getBccAddress())){
			message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(mailInfo.getBccAddress()));
		}
		
		message.setSubject(mailInfo.getSubject());//添加主题
		
		if(mailInfo.getFiles() != null){
			MimeMultipart mimeMultiPart = new MimeMultipart(MIXED);
			File[] files = mailInfo.getFiles();
			for (int i = 0; i < files.length; i++) {
				MimeBodyPart filePart = new MimeBodyPart();
				filePart.attachFile(files[i]);
				filePart.addHeader("Content-Disposition", Part.ATTACHMENT);
				filePart.setFileName(MimeUtility.encodeText(files[i].getName()));
				mimeMultiPart.addBodyPart(filePart);
			}
			
			MimeBodyPart textPart = new MimeBodyPart();
			if(mailInfo.isHTML()){
				textPart.setContent(mailInfo.getContent(),"text/html;charset="+mailInfo.getCharset());
			}else{
				textPart.setText(mailInfo.getContent(), mailInfo.getCharset());
			}
			
			
			
			mimeMultiPart.addBodyPart(textPart);
			
			message.setContent(mimeMultiPart);
		}else if(mailInfo.isHTML()){
			message.setContent(mailInfo.getContent(),"text/html;charset="+mailInfo.getCharset());
		}else{
			message.setText(mailInfo.getContent(), mailInfo.getCharset());
		}
		return message;
	}
	
	private void createSession() throws Exception{
		Properties props = new Properties();
		props.put("mail.smtp.host", this.host);
		props.put("mail.smtp.auth", "true");
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.timeout", "60000");//默认不超时
		props.put("mail.smtp.port", "25");
		if(this.isSSL){
			/**
			 * SSL需要证书，可以使用CertUtil类生成，生成后将证书放入java_home/lib/security目录中
			 */
			props.put("mail.smtp.port", "465");
			props.setProperty("mail.smtp.starttls.enable","true");
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
		}
		
		props.put("mail.transport.protocol", PROTOCOL);
//		props.put("mail.store.protocol", MAIL_SRORE_PROTOCOL);
		this.session = Session.getDefaultInstance(props, new AuthDTO(userName,password));
	}
	
	public void send(MailInfo[] mailInfos) throws Exception{
		createSession();
		Transport transport = session.getTransport(PROTOCOL);
		transport.connect();
		MimeMessage message;
		for (int i = 0; i < mailInfos.length; i++) {
			mailInfos[i].check();
			message = createMessage(mailInfos[i]);
			transport.sendMessage(message, message.getAllRecipients());
		}
		transport.close();
	}
	
}
