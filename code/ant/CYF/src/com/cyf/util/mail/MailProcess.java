package com.cyf.util.mail;

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

import com.cyf.config.Constants;
import com.cyf.exception.CYFMailException;
import com.cyf.util.ConfigHelper;

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

	public final static  String protocol = "smtp";
	public final static String filepath = "/com/config/common/cyf-mail.properties";
	public final static String MIXED="mixed",
								ALTERNATIVE="alternative",
								RELATED="related",
								PARALLEL="parallel",
								SIGNED="signed",
								PLAIN="plain";
	
	static{
		System.setProperty("mail.mime.encodefilename", "true");
	}
	
	/**
	 * 初始化
	 * <P>
	 * 通过taf-mail.properties文件加载连接信息
	 * @throws CYFMailException
	 * @since 2013-06-18
	 */
	public MailProcess() throws CYFMailException{
		Properties p = new Properties();
		try{
			p.load(ConfigHelper.getResourceAsStream(filepath));
		}catch (Exception e) {
			throw new CYFMailException(e);
		}
		session = Session.getDefaultInstance(p,new CYFMailAuth(p.getProperty("mail.smtp.username"), p.getProperty("mail.smtp.password")));
		message = new MimeMessage(session);
	}
	
	/**
	 * 初始化
	 * <p>
	 * 指定连接信息
	 * @param host 服务器地址
	 * @param port 服务器端口
	 * @param username 服务器用户名
	 * @param password 服务器密码
	 * @param from 发件人
	 * @since 2013-06-18
	 */
	public MailProcess(String host,String port,String username,String password,String from) throws CYFMailException{
		session = createSession(host,port,username,password);
		message = new MimeMessage(session);
		try{
			message.addFrom(InternetAddress.parse(from));
		}catch (Exception e) {
			throw new CYFMailException(e);
		}
	}
	
	public Session createSession(String host,String port,String username,String password){
		Properties p = new Properties();
		p.setProperty("mail.transport.protocol", MailProcess.protocol);
		p.setProperty("mail.smtp.host", host);
		p.setProperty("mail.smtp.port", port);
		p.setProperty("mail.smtp.auth", "true");
		
		return Session.getDefaultInstance(p,new CYFMailAuth(username, password));
	}
	
	/**
	 * 设置邮件头信息
	 * @param subject 主题
	 * @param charset 主题编码
	 * @param to 收件人（多个收件人用逗号分隔）
	 * @param cc 抄送（多个抄送人用逗号分隔）
	 * @param bcc 暗送（多个暗送人用逗号分隔）
	 * @throws CYFMailException
	 * @since 2013-06-18
	 */
	public void setMessageHead(String subject,String charset,String to,String cc,String bcc) throws CYFMailException{
		try{
			message.addRecipients(RecipientType.TO, InternetAddress.parse(to));
			if(cc != null)
				message.addRecipients(RecipientType.CC, InternetAddress.parse(cc));
			if(bcc != null)
				message.addRecipients(RecipientType.BCC, InternetAddress.parse(bcc));
			message.setSubject(subject,charset);
			message.setSentDate(new Date());
		}catch (Exception e) {
			throw new CYFMailException(e);
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
	public MimeMessage setMessage(String subject,String charset,String text,String charset2,String to,String cc,String bcc) throws CYFMailException{
		try{
			message.setText(text,charset2);
			setMessageHead(subject, charset, to, cc, bcc);
		}catch (Exception e) {
			throw new CYFMailException(e);
		}
		return message;
	}
	
	public MimeMessage createMessage(){
		if(this.attachmentParts != null && this.attachmentParts.length>0){
			mimeMultiPart = new MimeMultipart(MailProcess.MIXED);
		}
		return message;
	}
	
	/**
	 * 发送简单文本邮件
	 * @param text 邮件内容
	 * @throws CYFMailException
	 * @since 2013-06-18
	 */
	public void sendSimpleText(String text) throws CYFMailException{
		sendSimpleText(text, null);
	}
	
	/**
	 * 发送简单文本邮件
	 * @param text  邮件内容
	 * @param charset  邮件内容字符集
	 * @throws CYFMailException
	 * @since 2013-06-18
	 */
	public void sendSimpleText(String text,String charset) throws CYFMailException{
		try {
			message.setText(text,charset);
		} catch (MessagingException e) {
			throw new CYFMailException(e);
		}
		send();
	}
	
	/**
	 * 发送包含文本信息的复合邮件
	 * @param text 内容
	 * @param charset 内容编码
	 * @since 2013-06-18
	 */
	public void sendText(String text,String charset) throws CYFMailException{
		
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
			throw new CYFMailException(e);
		}
	}
	
	/**
	 * 发送html内容（不包含内嵌资源）
	 * @param html 内容
	 * @throws CYFMailException
	 * @since 2013-06-18
	 */
	public void sendHtmlOnly(String html) throws CYFMailException{
		sendHtmlOnly(html,null);
	}
	
	/**
	 * 发送html内容（不包含内嵌资源）
	 * @param html 内容
	 * @param charset  内容编码
	 * @throws CYFMailException
	 * @since 2013-06-18
	 */
	public void sendHtmlOnly(String html,String charset) throws CYFMailException{
		try{
			if(charset == null)
				message.setContent(html,"text/html;charset=utf-8");
			else 
				message.setContent(html,"text/html;charset="+charset);
			
			send();
		}catch (Exception e) {
			throw new CYFMailException(e);
		}
	}
	
	/**
	 * 发送复合html内容邮件（可包含内嵌资源和附件）
	 * @param html 邮件内容
	 * @param charset 内容字符集
	 * @param inlineAttachments 邮件内容中内嵌的附件资源 
	 * @since 2013-06-18
	 */
	public void sendHtml(String html,String charset,InlineAttachmentInfo[] inlineAttachments) throws CYFMailException{
		try{
			setInlineParts(inlineAttachments);
		}catch (Exception e) {
			throw new CYFMailException(e);
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
			throw new CYFMailException(e);
		}
		if(this.inlineParts != null && this.inlineParts.length > 0){
//			MimeBodyPart tmp = null;
			for(int x=0;x<this.inlineParts.length;x++){
				try{
					htmlMultipart.addBodyPart(this.inlineParts[x]);
				}catch (Exception e) {
					throw new CYFMailException(e);
				}
			}
		}
		try{
			htmlContentPart.setContent(htmlMultipart);
			mimeMultiPart.addBodyPart(htmlContentPart);
		}catch (Exception e) {
			throw new CYFMailException(e);
		}
		
		addAttachmentInfos();
		
		try {
			message.setContent(mimeMultiPart);
		} catch (MessagingException e) {
			throw new CYFMailException(e);
		}
		send();
	}
	
	private void send() throws CYFMailException{
		try{
			message.saveChanges();
			Transport transport = session.getTransport();
			transport.connect();
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}catch (Exception e) {
			throw new CYFMailException(e);
		}
	}
	
	/**
	 * 添加附件到邮件中
	 * @throws CYFMailException
	 */
	private void addAttachmentInfos() throws CYFMailException{
		if(attachmentParts != null && attachmentParts.length > 0){
			//附件
			for(int x=0;x<attachmentParts.length;x++){
				try {
					mimeMultiPart.addBodyPart(attachmentParts[x]);
				} catch (MessagingException e) {
					throw new CYFMailException(e);
				}
			}
		}
	}
	
	/**
	 * 设置附件
	 * @param attachmentInfos 附件集
	 * @throws CYFMailException
	 * @since 2013-06-18
	 */
	public void setAttachmentInfos(AttachmentInfo[] attachmentInfos) throws CYFMailException{
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
				throw new CYFMailException(e);
			}
			attachmentParts[x] = tmp;
			hasAttachment = true;
		}
	}
	
	/**
	 * 设置内嵌资源
	 * @param inlineAttachments
	 */
	public void setInlineParts(InlineAttachmentInfo[] inlineAttachments) throws CYFMailException{
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
				throw new CYFMailException(e);
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
	
	
	public static void main(String[] args) {
		MailProcess mailProcess = new MailProcess();
		mailProcess.setMessageHead("CYFTest", Constants.ENCODING, "xxx@qq.com", null, null);
		mailProcess.sendSimpleText("测试", Constants.ENCODING);
	}
}
