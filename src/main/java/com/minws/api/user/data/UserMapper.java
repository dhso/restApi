/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.minws.api.user.data;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.minws.api.user.entity.User;

public interface UserMapper {
	/**
	 * 查询用户
	 * 
	 * @param nick
	 * @param password
	 * @return
	 */
	User selectUser(@Param("nick") String nick, @Param("password") String password);

	/**
	 * 查询全部用户
	 * 
	 * @return
	 */
	List<User> selectUser();

	/**
	 * 添加用户
	 * 
	 * @param nick
	 * @param name
	 * @param gender
	 * @param birthday
	 * @param mobile
	 * @param email
	 * @param password
	 */
	void addUser(@Param("nick") String nick, @Param("name") String name, @Param("gender") String gender, @Param("birthday") Date birthday, @Param("mobile") String mobile, @Param("email") String email, @Param("password") String password);

	/**
	 * 根据nick校验用户是否存在
	 * 
	 * @param nick
	 */
	Integer checkUserExistByNick(@Param("nick") String nick);

}