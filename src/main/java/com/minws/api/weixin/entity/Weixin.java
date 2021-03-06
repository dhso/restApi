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
public class Weixin implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -978237911918387249L;

	/**
	 * 开发者微信号
	 */
	@XmlElement(name = "ToUserName")
	private String ToUserName;

	/**
	 * 发送方帐号（一个OpenID）
	 */
	@XmlElement(name = "FromUserName")
	private String FromUserName;

	/**
	 * 消息创建时间 （整型）
	 */
	@XmlElement(name = "CreateTime")
	private Long CreateTime;

	/**
	 * text
	 */
	@XmlElement(name = "MsgType")
	private String MsgType;

	/**
	 * 文本消息内容
	 */
	@XmlElement(name = "Content")
	private String Content;

	/**
	 * 图片链接
	 */
	@XmlElement(name = "PicUrl")
	private String PicUrl;

	/**
	 * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	@XmlElement(name = "MediaId")
	private String MediaId;

	/**
	 * 语音格式，如amr，speex等
	 */
	@XmlElement(name = "Format")
	private String Format;

	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	@XmlElement(name = "ThumbMediaId")
	private String ThumbMediaId;

	/**
	 * 地理位置维度
	 */
	@XmlElement(name = "Location_X")
	private String Location_X;

	/**
	 * 地理位置经度
	 */
	@XmlElement(name = "Location_Y")
	private String Location_Y;

	/**
	 * 地图缩放大小
	 */
	@XmlElement(name = "Scale")
	private String Scale;

	/**
	 * 地理位置信息
	 */
	@XmlElement(name = "Label")
	private String Label;

	/**
	 * 消息标题
	 */
	@XmlElement(name = "Title")
	private String Title;

	/**
	 * 消息描述
	 */
	@XmlElement(name = "Description")
	private String Description;

	/**
	 * 消息链接
	 */
	@XmlElement(name = "Url")
	private String Url;

	/**
	 * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	 */
	@XmlElement(name = "Event")
	private String Event;

	/**
	 * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
	 */
	@XmlElement(name = "EventKey")
	private String EventKey;

	/**
	 * 二维码的ticket，可用来换取二维码图片
	 */
	@XmlElement(name = "Ticket")
	private String Ticket;

	/**
	 * 地理位置纬度
	 */
	@XmlElement(name = "Latitude")
	private String Latitude;

	/**
	 * 地理位置经度
	 */
	@XmlElement(name = "Longitude")
	private String Longitude;

	/**
	 * 地理位置精度
	 */
	@XmlElement(name = "Precision")
	private String Precision;

	/**
	 * 消息id，64位整型
	 */
	@XmlElement(name = "MsgId")
	private Long MsgId;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}

	public Long getMsgId() {
		return MsgId;
	}

	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}

}