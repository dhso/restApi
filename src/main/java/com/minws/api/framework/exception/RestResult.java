/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.framework.exception;

import com.minws.api.framework.Constant.RespondType;

public class RestResult {

	private int responseCode = RespondType.SUCESS;

	private String responseDesc = RespondType.SUCESS_MSG;

	public RestResult() {

	}

	/**
	 * 基础接口返回
	 * 
	 * @param responseCode
	 * @param responseDesc
	 */
	public RestResult(int responseCode, String responseDesc) {
		this.responseCode = responseCode;
		this.responseDesc = responseDesc;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

}