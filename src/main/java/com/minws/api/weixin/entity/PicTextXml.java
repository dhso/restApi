/**
 * @author hadong
 *
 */
package com.minws.api.weixin.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.minws.api.weixin.entity.ArticleXml;

@XmlRootElement(name = "xml")
public class PicTextXml implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2527831665242389412L;

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
	 * news
	 */

	private String MsgType;

	/**
	 * 图文消息个数，限制为10条以内
	 */

	private Long ArticleCount;

	/**
	 * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	 */

	private List<ArticleXml> Articles = new ArrayList<ArticleXml>();

	public PicTextXml() {

	}

	/**
	 * PicTextXml图文信息
	 * @param ToUserName
	 * @param FromUserName
	 * @param CreateTime
	 * @param MsgType
	 * @param ArticleCount
	 * @param Articles
	 */
	public PicTextXml(String ToUserName, String FromUserName, Long CreateTime, String MsgType, Long ArticleCount, List<ArticleXml> Articles) {
		this.ToUserName = ToUserName;
		this.FromUserName = FromUserName;
		this.CreateTime = CreateTime;
		this.MsgType = MsgType;
		this.ArticleCount = ArticleCount;
		this.Articles = Articles;
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

	@XmlElement(name = "ArticleCount")
	public Long getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(Long articleCount) {
		ArticleCount = articleCount;
	}

	@XmlElement(name = "item")
	@XmlElementWrapper(name = "Articles")
	public List<ArticleXml> getArticles() {
		return Articles;
	}

	public void setArticles(List<ArticleXml> articles) {
		Articles = articles;
	}

}