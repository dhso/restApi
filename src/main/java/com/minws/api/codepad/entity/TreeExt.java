/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.codepad.entity;

import java.io.Serializable;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class TreeExt implements Serializable {

	private static final long serialVersionUID = -1678139595611493680L;
	/**
	 * 附加属性:节点属性
	 */
	private String node_type;

	/**
	 * 附加属性:父节点
	 */
	private String pid;

	/**
	 * 附加属性:是否公开
	 */
	private String open;

	/**
	 * 附加属性:writer
	 */
	private String writer;

	/**
	 * 附加属性:update_dt
	 */
	private Date update_dt;

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getUpdate_dt() {
		return update_dt;
	}

	public void setUpdate_dt(Date update_dt) {
		this.update_dt = update_dt;
	}

}