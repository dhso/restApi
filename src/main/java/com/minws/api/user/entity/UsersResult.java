/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.user.entity;

import java.util.List;

import com.minws.api.framework.exception.RestResult;

public class UsersResult extends RestResult {
	private List<User> user;

	public List<User> getUser() {
		return user;
	}

	public UsersResult() {

	}

	public UsersResult(List<User> user) {
		this.user = user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

}
