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
public class DeviceData implements Serializable {

	private static final long serialVersionUID = 6255986667904569696L;

	private String id;

	private String name;

	private String type;

	private String unit;

	private Integer status;

	private Integer dee_bur_id;

	private String device_value;

	private Long device_date;

	public String getDevice_value() {
		return device_value;
	}

	public Long getDevice_date() {
		return device_date;
	}

	public void setDevice_value(String device_value) {
		this.device_value = device_value;
	}

	public void setDevice_date(Long device_date) {
		this.device_date = device_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDee_bur_id() {
		return dee_bur_id;
	}

	public void setDee_bur_id(Integer dee_bur_id) {
		this.dee_bur_id = dee_bur_id;
	}

}