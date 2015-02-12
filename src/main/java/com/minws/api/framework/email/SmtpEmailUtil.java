/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.framework.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SmtpEmailUtil {
	/**
	 * 发送文本邮件
	 * 
	 * @param smtpServer
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @param attachments
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean sendTextEmail(Email email) {
		EmailAuthenticator authenticator = null;
		Properties emailProps = email.getProperties();
		if (email.isValidate()) {// 如果需要身份认证，则创建一个密码验证器
			authenticator = new EmailAuthenticator(email.getUsername(), email.getPassword());
		}
		Session emailSession = Session.getDefaultInstance(emailProps, authenticator);// 创建Session对象，以匿名内部类的形式创建登录服务器的认证对象
		try {
			MimeMessage mmsg = new MimeMessage(emailSession);// 构造MimeMessage并设置相关属性值
			mmsg.setFrom(new InternetAddress(email.getFromAddress()));// 设置发件人
			mmsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getToAddress()));// 设置收件人
			mmsg.setSubject(MimeUtility.encodeText(email.getSubject()));// 设置邮件主题
			mmsg.setSentDate(new Date());// 设置发送日期
			mmsg.setText(email.getContent());// 设置发送文本
			Multipart mp = new MimeMultipart();// 构造Multipart容器
			MimeBodyPart mbpContent = new MimeBodyPart();// 向Multipart添加正文
			mbpContent.setText(email.getContent());
			mp.addBodyPart(mbpContent);// 将BodyPart添加到MultiPart中
			for (String efile : email.getAttachments()) {// 向Multipart添加附件，遍历附件列表，将所有文件添加到邮件消息里
				MimeBodyPart mbpFile = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(efile);// 以文件名创建FileDataSource对象
				mbpFile.setDataHandler(new DataHandler(fds));// 处理附件
				mbpFile.setFileName(fds.getName());
				mp.addBodyPart(mbpFile);// 将BodyPart添加到MultiPart中
			}
			mmsg.setContent(mp);// 向Multipart添加MimeMessage
			
			Transport.send(mmsg);// 发送邮件
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 发送邮件
	 * 
	 * @param smtpServer
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @param attachments
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean send2(String smtpServer, String from, String to, String subject, String content, List<String> attachments, final String username, final String password) {
		Properties props = new Properties();// 创建邮件Session所需的Properties对象
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});// 创建Session对象，以匿名内部类的形式创建登录服务器的认证对象
		try {
			MimeMessage msg = new MimeMessage(session);// 构造MimeMessage并设置相关属性值
			msg.setFrom(new InternetAddress(from));// 设置发件人
			msg.setRecipients(Message.RecipientType.TO, new Address[] { new InternetAddress(to) });// 设置收件人
			msg.setSubject(MimeUtility.encodeText(subject));// 设置邮件主题MimeUtility.encodeText(new
															// String(subject.getBytes(),
															// "GB2312"),
															// "GB2312", "B")
			Multipart mp = new MimeMultipart();// 构造Multipart
			MimeBodyPart mbpContent = new MimeBodyPart();// 向Multipart添加正文
			mbpContent.setText(content);
			mp.addBodyPart(mbpContent);// 将BodyPart添加到MultiPart中
			for (String efile : attachments) {// 向Multipart添加附件，遍历附件列表，将所有文件添加到邮件消息里
				MimeBodyPart mbpFile = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(efile);// 以文件名创建FileDataSource对象
				mbpFile.setDataHandler(new DataHandler(fds));// 处理附件
				mbpFile.setFileName(fds.getName());
				mp.addBodyPart(mbpFile);// 将BodyPart添加到MultiPart中
			}
			msg.setContent(mp);// 向Multipart添加MimeMessage
			msg.setSentDate(new Date());// 设置发送日期
			Transport.send(msg);// 发送邮件
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}