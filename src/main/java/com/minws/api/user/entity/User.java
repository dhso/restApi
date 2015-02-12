/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.user.entity;

import java.io.Serializable;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class User implements Serializable {

	private static final long serialVersionUID = -4855907560904110979L;
	/**
	 * 唯一标识
	 */
	private Long id;
	/**
	 * 昵称
	 */
	private String nick;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 是否开发者
	 */
	private String isdev;
	/**
	 * 等级
	 */
	private String level;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsdev() {
		return isdev;
	}

	public void setIsdev(String isdev) {
		this.isdev = isdev;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}