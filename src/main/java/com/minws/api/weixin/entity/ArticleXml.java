/**
 * @author hadong
 *
 */
package com.minws.api.weixin.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class ArticleXml implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9184098761645084921L;

	/**
	 * 图文消息标题
	 */

	private String Title;

	/**
	 * 图文消息描述
	 */

	private String Description;

	/**
	 * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 */

	private String PicUrl;

	/**
	 * 点击图文消息跳转链接
	 */

	private String Url;

	public ArticleXml() {

	}

	public ArticleXml(String Title, String Description, String PicUrl, String Url) {
		this.Title = Title;
		this.Description = Description;
		this.PicUrl = PicUrl;
		this.Url = Url;
	}

	@XmlElement(name = "Title")
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	@XmlElement(name = "Description")
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@XmlElement(name = "PicUrl")
	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	@XmlElement(name = "Url")
	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}