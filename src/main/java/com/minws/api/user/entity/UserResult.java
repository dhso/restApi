/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.user.entity;

import com.minws.api.framework.exception.RestResult;

public class UserResult extends RestResult {
	private User user;

	public User getUser() {
		return user;
	}

	public UserResult() {

	}

	public UserResult(User user) {
		this.user = user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
