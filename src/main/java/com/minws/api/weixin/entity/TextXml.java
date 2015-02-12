/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.weixin.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class TextXml implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5579582092945882000L;

	/**
	 * 接收方帐号（收到的OpenID）
	 */
	
	private String ToUserName;

	/**
	 * 开发者微信号
	 */
	
	private String FromUserName;

	/**
	 * 消息创建时间 （整型）
	 */
	
	private Long CreateTime;

	/**
	 * text
	 */
	
	private String MsgType;

	/**
	 * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	 */
	
	private String Content;

	public TextXml() {

	}

	/**
	 * TextXml 文本信息
	 * @param ToUserName
	 * @param FromUserName
	 * @param CreateTime
	 * @param MsgType
	 * @param Content
	 */
	public TextXml(String ToUserName, String FromUserName, Long CreateTime, String MsgType, String Content) {
		this.ToUserName = ToUserName;
		this.FromUserName = FromUserName;
		this.CreateTime = CreateTime;
		this.MsgType = MsgType;
		this.Content = Content;
	}

	@XmlElement(name = "ToUserName")
	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	@XmlElement(name = "FromUserName")
	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	@XmlElement(name = "CreateTime")
	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	@XmlElement(name = "MsgType")
	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	@XmlElement(name = "Content")
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}