/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.minws.api.ehome.entity;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class Data implements Serializable {

	private static final long serialVersionUID = 6255986667904569696L;

	private Integer device_id;

	private String device_value;

	private Long device_date;

	public Integer getDevice_id() {
		return device_id;
	}

	public String getDevice_value() {
		return device_value;
	}

	public Long getDevice_date() {
		return device_date;
	}

	public void setDevice_id(Integer device_id) {
		this.device_id = device_id;
	}

	public void setDevice_value(String device_value) {
		this.device_value = device_value;
	}

	public void setDevice_date(Long device_date) {
		this.device_date = device_date;
	}

}