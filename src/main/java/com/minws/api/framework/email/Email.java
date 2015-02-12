/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.framework.email;

import java.io.Serializable;
import java.util.Properties;

public class Email implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1233687936996606444L;
	/**
	 * 收件人邮箱地址 /
	 */
	private String toAddress;
	/**
	 * cc收件人邮箱地址 /
	 */
	private String toCcAddress;
	/**
	 * bcc收件人邮箱地址 /
	 */
	private String toBccAddress;
	/**
	 * 邮件主题
	 */
	private String subject = "this is subject";
	/**
	 * 邮件正文
	 */
	private String content = "this is subject";;
	/**
	 * 邮件附件
	 */
	private String[] attachments;
	/**
	 * 发件人邮箱地址
	 */
	private String fromAddress = "admin@minws.com";
	/**
	 * SMTP服务器地址
	 */
	private String smtpServer = "smtp.ym.163.com";
	/**
	 * SMTP服务器地址
	 */
	private Integer smtpPort = 25;
	/**
	 * SMTP服务是否需要验证
	 */
	private boolean validate = false;
	/**
	 * 登录SMTP服务器的用户名
	 */
	private String username = "admin@minws.com";
	/**
	 * 登录SMTP服务器的密码
	 */
	private String password = "admin(minws)";

	/**
	 * 获得邮件会话属性
	 */
	public Properties getProperties() {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", this.smtpServer);
		prop.put("mail.smtp.port", this.smtpPort);
		prop.put("mail.smtp.auth", validate ? "true" : "false");
		return prop;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getToCcAddress() {
		return toCcAddress;
	}

	public void setToCcAddress(String toCcAddress) {
		this.toCcAddress = toCcAddress;
	}

	public String getToBccAddress() {
		return toBccAddress;
	}

	public void setToBccAddress(String toBccAddress) {
		this.toBccAddress = toBccAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAttachments() {
		return attachments;
	}

	public void setAttachments(String[] attachments) {
		this.attachments = attachments;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public Integer getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}