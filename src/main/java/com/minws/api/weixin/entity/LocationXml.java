/**
 * @author hadong
 *
 */
package com.minws.api.weixin.entity;

import java.io.Serializable;

public class LocationXml implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9184098761645084921L;

	/**
	 * 地理位置纬度
	 */

	private String Location_X;

	/**
	 * 地理位置经度
	 */

	private String Location_Y;

	/**
	 * 地理位置精度
	 */

	private String Scale;

	/**
	 * 地理位置标签
	 */

	private String Label;

	public LocationXml() {

	}

	/**
	 * LocationXml
	 * 
	 * @param Location_X
	 * @param Location_Y
	 * @param Scale
	 * @param Label
	 */
	public LocationXml(String Location_X, String Location_Y, String Scale, String Label) {
		this.Location_X = Location_X;
		this.Location_Y = Location_Y;
		this.Scale = Scale;
		this.Label = Label;
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

}