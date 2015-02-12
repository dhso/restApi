/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.minws.api.ehome.entity;

import java.util.List;

import com.minws.api.framework.exception.RestResult;

public class ListDeviceResult extends RestResult {

	private List<Device> device;

	public List<Device> getDevice() {
		return device;
	}

	public void setDevice(List<Device> device) {
		this.device = device;
	}

	public ListDeviceResult() {

	}

	public ListDeviceResult(List<Device> device) {
		this.device = device;
	}

}