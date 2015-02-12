/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.codepad.entity;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class Tree implements Serializable {

	private static final long serialVersionUID = 9008975282589959901L;
	/**
	 * 树ID
	 */
	private String id;
	/**
	 * 树文本
	 */
	private String text;
	/**
	 * 树状态(open或者closed)
	 */
	private String state;
	/**
	 * 树图标(eg.icon-save)
	 */
	private String iconCls;
	/**
	 * 树附加属性
	 */
	private TreeExt attributes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public TreeExt getAttributes() {
		return attributes;
	}

	public void setAttributes(TreeExt attributes) {
		this.attributes = attributes;
	}

}