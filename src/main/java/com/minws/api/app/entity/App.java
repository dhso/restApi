/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.app.entity;

import java.io.Serializable;

public class App implements Serializable {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 4933924729962555893L;
	/**
	 * AppId
	 */
	private String appId;
	/**
	 * AppKey
	 */
	private String appKey;
	/**
	 * AppName
	 */
	private String appName;
	/**
	 * UsrId
	 */
	private String usrId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
}