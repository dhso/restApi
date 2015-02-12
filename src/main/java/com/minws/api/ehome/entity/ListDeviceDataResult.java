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

public class ListDeviceDataResult extends RestResult {

	private List<DeviceData> deviceData;

	public List<DeviceData> getDeviceData() {
		return deviceData;
	}

	public void setDeviceData(List<DeviceData> deviceData) {
		this.deviceData = deviceData;
	}

	public ListDeviceDataResult() {

	}

	public ListDeviceDataResult(List<DeviceData> deviceData) {
		this.deviceData = deviceData;
	}

}