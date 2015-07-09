package com.cy.cyf.net.mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.cy.cyf.core.exception.MailException;

/**
 * 执行邮件组装及发送
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-22 下午12:59:25
 */
public class MailProcess {
	
	private Session session = null;
	private MimeMessage message = null;
	private MimeMultipart mimeMultiPart = null;
	//附件
	private transient MimeBodyPart[] attachmentParts = null;
	private boolean hasAttachment = false;
	//内嵌资源
	private transient MimeBodyPart[] inlineParts = null;

	public final static String protocol = "smtp";
	public final static String MIXED="mixed";
	public final static String ALTERNATIVE="alternative";
	public final static String RELATED="related";
	public final static String PARALLEL="parallel";
	public final static String SIGNED="signed";
	public final static String PLAIN="plain";
	
	static{
		System.setProperty("mail.mime.encodefilename", "true");
	}
	
	/**
	 * 初始化
	 * <p>
	 * 指定连接信息
	 * @param host 服务器地址
	 * @param port 服务器端口
	 * @param username 服务器用户名
	 * @param password 服务器密码
	 * @param from 发件人邮箱地址
	 * @since 2013-06-18
	 */
	public MailProcess(String host,String port,String username,String password,String from,boolean isSSL) throws MailException{
		session = createSession(host,port,username,password,isSSL);
		message = new MimeMessage(session);
		try{
			message.addFrom(InternetAddress.parse(from));
		}catch (Exception e) {
			throw new MailException(e);
		}
		
	}
	
	public Session createSession(String host,String port,String username,String password,boolean isSSL){
		Properties p = new Properties();
		p.setProperty("mail.transport.protocol", MailProcess.protocol);
		p.setProperty("mail.smtp.host", host);
		p.setProperty("mail.smtp.port", port);
		p.setProperty("mail.smtp.timeout", "60000");
		p.setProperty("mail.smtp.auth", "true");
		if(isSSL){
			/**
			 * SSL需要证书，可以使用CertUtil类生成，生成后将证书放入java_home/lib/security目录中
			 */
			p.setProperty("mail.smtp.starttls.enable","true");
			p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
			p.setProperty("mail.smtp.socketFactory.fallback", "false");
		}
		
		return Session.getDefaultInstance(p,new CYFMailAuth(username, password));
	}
	
	/**
	 * 设置邮件头信息
	 * @param subject 主题
	 * @param charset 主题编码
	 * @param to 收件人（多个收件人用逗号分隔）
	 * @param cc 抄送（多个抄送人用逗号分隔）
	 * @param bcc 暗送（多个暗送人用逗号分隔）
	 * @throws MailException
	 * @since 2013-06-18
	 */
	public void setMessageHead(String subject,String charset,String to,String cc,String bcc) throws MailException{
		try{
			message.addRecipients(RecipientType.TO, InternetAddress.parse(to));
			if(cc != null)
				message.addRecipients(RecipientType.CC, InternetAddress.parse(cc));
			if(bcc != null)
				message.addRecipients(RecipientType.BCC, InternetAddress.parse(bcc));
			message.setSubject(subject,charset);
			message.setSentDate(new Date());
		}catch (Exception e) {
			throw new MailException(e);
		}
	}
	
	/**
	 * 设置发送的内容（标题、内容）及编码
	 * @param subject 邮件标题
	 * @param charset 标题字符集
	 * @param text 邮件内容
	 * @param charset2 内容字符集
	 * @param to 收件人
	 * @param cc 抄送人
	 * @param bcc 暗送人
	 * @return MimeMessage对象
	 * @since 2013-6-18
	 */
	public MimeMessage setMessage(String subject,String charset,String text,String charset2,String to,String cc,String bcc) throws MailException{
		try{
			message.setText(text,charset2);
			setMessageHead(subject, charset, to, cc, bcc);
		}catch (Exception e) {
			throw new MailException(e);
		}
		return message;
	}
	
	public MimeMessage createMessage(){
		if(this.attachmentParts != null && this.attachmentParts.length>0){
			mimeMultiPart = new MimeMultipart(MailProcess.MIXED);
		}
		return message;
	}
	
	public void addSSLSetting(String host,String port) throws Exception{
		CertUtil.createCert(new String[]{host+":"+port});
	}
	
	/**
	 * 发送简单文本邮件
	 * @param text 邮件内容
	 * @throws MailException
	 * @since 2013-06-18
	 */
	public void sendSimpleText(String text) throws MailException{
		sendSimpleText(text, null);
	}
	
	/**
	 * 发送简单文本邮件
	 * @param text  邮件内容
	 * @param charset  邮件内容字符集
	 * @throws MailException
	 * @since 2013-06-18
	 */
	public void sendSimpleText(String text,String charset) throws MailException{
		try {
			message.setText(text,charset);
		} catch (MessagingException e) {
			throw new MailException(e);
		}
		send();
	}
	
	/**
	 * 发送包含文本信息的复合邮件
	 * @param text 内容
	 * @param charset 内容编码
	 * @since 2013-06-18
	 */
	public void sendText(String text,String charset) throws MailException{
		
		if(!hasAttachment){
			sendSimpleText(text, charset);
			return;
		}
		
		mimeMultiPart = new MimeMultipart("mixed");
		
		try{
			//文本消息
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText(text, charset);
			
			mimeMultiPart.addBodyPart(textPart);
			
			addAttachmentInfos();
			
			message.setContent(mimeMultiPart);
			
			send();
		}catch (Exception e) {
			throw new MailException(e);
		}
	}
	
	/**
	 * 发送html内容（不包含内嵌资源）
	 * @param html 内容
	 * @throws MailException
	 * @since 2013-06-18
	 */
	public void sendHtmlOnly(String html) throws MailException{
		sendHtmlOnly(html,null);
	}
	
	/**
	 * 发送html内容（不包含内嵌资源）
	 * @param html 内容
	 * @param charset  内容编码
	 * @throws MailException
	 * @since 2013-06-18
	 */
	public void sendHtmlOnly(String html,String charset) throws MailException{
		try{
			if(charset == null)
				message.setContent(html,"text/html;charset=utf-8");
			else 
				message.setContent(html,"text/html;charset="+charset);
			
			send();
		}catch (Exception e) {
			throw new MailException(e);
		}
	}
	
	/**
	 * 发送复合html内容邮件（可包含内嵌资源和附件）
	 * @param html 邮件内容
	 * @param charset 内容字符集
	 * @param inlineAttachments 邮件内容中内嵌的附件资源 
	 * @since 2013-06-18
	 */
	public void sendHtml(String html,String charset,InlineAttachmentInfo[] inlineAttachments) throws MailException{
		try{
			setInlineParts(inlineAttachments);
		}catch (Exception e) {
			throw new MailException(e);
		}
		
		String incharset = (charset==null||"".equals(charset))?"utf-8":charset;
		mimeMultiPart = new MimeMultipart(MailProcess.MIXED);
		
		//内嵌资源
		MimeBodyPart htmlContentPart = new MimeBodyPart();
		MimeMultipart htmlMultipart = null;
		if(inlineAttachments!=null && inlineAttachments.length>0){
			htmlMultipart = new MimeMultipart(MailProcess.RELATED);
		}else{
			htmlMultipart = new MimeMultipart(MailProcess.PLAIN);
		}
		try{
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(html, "text/html;charset="+incharset);
			htmlMultipart.addBodyPart(htmlPart);
		}catch (Exception e) {
			throw new MailException(e);
		}
		if(this.inlineParts != null && this.inlineParts.length > 0){
//			MimeBodyPart tmp = null;
			for(int x=0;x<this.inlineParts.length;x++){
				try{
					htmlMultipart.addBodyPart(this.inlineParts[x]);
				}catch (Exception e) {
					throw new MailException(e);
				}
			}
		}
		try{
			htmlContentPart.setContent(htmlMultipart);
			mimeMultiPart.addBodyPart(htmlContentPart);
		}catch (Exception e) {
			throw new MailException(e);
		}
		
		addAttachmentInfos();
		
		try {
			message.setContent(mimeMultiPart);
		} catch (MessagingException e) {
			throw new MailException(e);
		}
		send();
	}
	
	private void send() throws MailException{
		try{
			message.saveChanges();
			Transport transport = session.getTransport();
			transport.connect();
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}catch (Exception e) {
			throw new MailException(e);
		}
	}
	
	/**
	 * 添加附件到邮件中
	 * @throws MailException
	 */
	private void addAttachmentInfos() throws MailException{
		if(attachmentParts != null && attachmentParts.length > 0){
			//附件
			for(int x=0;x<attachmentParts.length;x++){
				try {
					mimeMultiPart.addBodyPart(attachmentParts[x]);
				} catch (MessagingException e) {
					throw new MailException(e);
				}
			}
		}
	}
	
	/**
	 * 设置附件
	 * @param attachmentInfos 附件集
	 * @throws MailException
	 * @since 2013-06-18
	 */
	public void setAttachmentInfos(AttachmentInfo[] attachmentInfos) throws MailException{
		if(attachmentInfos == null || attachmentInfos.length <= 0)
			return;
		attachmentParts = new MimeBodyPart[attachmentInfos.length];
		MimeBodyPart tmp = null;
		for(int x=0;x<attachmentInfos.length;x++){
			tmp = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(attachmentInfos[x].getFile());
			DataHandler dh = new DataHandler(fds);
			try{
				tmp.setDataHandler(dh);
				tmp.addHeader("Content-Disposition", Part.ATTACHMENT);
				tmp.setFileName(attachmentInfos[x].getFileName());
			}catch (Exception e) {
				throw new MailException(e);
			}
			attachmentParts[x] = tmp;
			hasAttachment = true;
		}
	}
	
	/**
	 * 设置内嵌资源
	 * @param inlineAttachments
	 */
	public void setInlineParts(InlineAttachmentInfo[] inlineAttachments) throws MailException{
		if(inlineAttachments == null || inlineAttachments.length <= 0)
			return;
		inlineParts = new MimeBodyPart[inlineAttachments.length];
		MimeBodyPart tmp = null;
		for(int x=0;x<inlineAttachments.length;x++){
			tmp = new MimeBodyPart();
			try{
				tmp.addHeader("Content-Dispositon", Part.INLINE+";filename="+inlineAttachments[x].getFileName());
				tmp.setContentID(inlineAttachments[x].getCid());
				
				FileDataSource fds = new FileDataSource(inlineAttachments[x].getFile());
				DataHandler dh = new DataHandler(fds);
				
				tmp.setDataHandler(dh);
			}catch (Exception e) {
				throw new MailException(e);
			}
			inlineParts[x] = tmp;
		}
	}

	class CYFMailAuth extends Authenticator{
		private String username;
		private String password;
		public CYFMailAuth(String username,String password){
			this.username = username;
			this.password = password;
		}
		
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}
	
	public void setDebug(boolean isDebug) {
		session.setDebug(isDebug);
	}

	public static void main(String[] args) {
		MailProcess mailProcess = new MailProcess("smtp.163.com","25","cheny201","chenying201.","cheny201@163.com",false);
//		MailProcess mailProcess = new MailProcess("mail.msthamc.com","465","jiwo","thamco2012","jiwo@msthamc.com",true);
		mailProcess.setMessageHead("CYFTest", "utf-8", "404369230@qq.com", null, null);
//		mailProcess.sendSimpleText("测试", "utf-8");
		AttachmentInfo a = new AttachmentInfo();
		a.setFileName("C:\\Users\\Administrator.2013-20140324PL\\Desktop\\民生通惠测试邮箱.txt");
		AttachmentInfo[] attachmentInfos = new AttachmentInfo[]{a};
		mailProcess.setAttachmentInfos(attachmentInfos);
		mailProcess.sendText("测试", "utf-8");
	}
}
