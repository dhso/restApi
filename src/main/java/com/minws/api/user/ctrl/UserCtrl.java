/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.user.ctrl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;

import com.minws.api.framework.aspect.checkApp.CheckApp;
import com.minws.api.framework.exception.RestResult;
import com.minws.api.framework.util.IoUtil;
import com.minws.api.framework.util.mybatis.MybatisUtil;
import com.minws.api.user.data.UserMapper;
import com.minws.api.user.entity.User;
import com.minws.api.user.entity.UserResult;
import com.minws.api.user.entity.UsersResult;

@Controller
@Path("/user")
public class UserCtrl {

	private SqlSessionFactory systemSqlSessionFactory = MybatisUtil.connectDb("com/minws/api/user/data/User-mybatis-config.xml", "public");// development

	@GET
	@Produces("application/json;charset=utf-8")
	@Path("/list")
	@CheckApp(allowAppId = { "1" })
	public UsersResult listAllUser() throws Exception {
		SqlSession systemSqlSession = systemSqlSessionFactory.openSession();
		UsersResult result = new UsersResult();
		try {
			UserMapper mapper = systemSqlSession.getMapper(UserMapper.class);
			List<User> users = mapper.selectUser();
			result.setUser(users);
		} finally {
			systemSqlSession.close();
		}
		return result;
	}

	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/add")
	@CheckApp(allowAppId = { "1" })
	public RestResult addUser(@Context HttpServletRequest request) throws Exception {
		SqlSession systemSqlSession = systemSqlSessionFactory.openSession();
		try {
			UserMapper userMapper = systemSqlSession.getMapper(UserMapper.class);
			User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			if (user.getNick().isEmpty()) {
				throw new Exception("nick is required!");
			}
			if (user.getPassword().isEmpty()) {
				throw new Exception("password is required!");
			}
			if (userMapper.checkUserExistByNick(user.getNick()) > 0) {
				throw new Exception("nick is already exists!");
			}
			userMapper.addUser(user.getNick(), user.getName(), user.getGender(), user.getBirthday(), user.getMobile(), user.getEmail(), user.getPassword());
			systemSqlSession.commit();
		} finally {
			systemSqlSession.close();
		}
		return new RestResult();
	}

	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/login")
	@CheckApp()
	public Response loginUser(@Context HttpServletRequest request) throws Exception {
		SqlSession systemSqlSession = systemSqlSessionFactory.openSession();
		UserResult result = new UserResult();
		try {
			UserMapper userMapper = systemSqlSession.getMapper(UserMapper.class);
			User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			if (user.getNick().isEmpty()) {
				throw new Exception("nick is required!");
			}
			if (user.getPassword().isEmpty()) {
				throw new Exception("password is required!");
			}
			User users = userMapper.selectUser(user.getNick(), user.getPassword());
			if (null == users) {
				throw new Exception("nick or password is not correct!");
			} else {
				result.setUser(users);
				return IoUtil.cors(result);
			}
		} finally {
			systemSqlSession.close();
		}

	}

	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/logout")
	@CheckApp()
	public Response logoutUser(@Context HttpServletRequest request) throws Exception {
		SqlSession systemSqlSession = systemSqlSessionFactory.openSession();
		try {
			UserMapper userMapper = systemSqlSession.getMapper(UserMapper.class);
			User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			if (user.getNick().isEmpty()) {
				throw new Exception("nick is required!");
			}
			if (user.getPassword().isEmpty()) {
				throw new Exception("password is required!");
			}
			User users = userMapper.selectUser(user.getNick(), user.getPassword());
			if (null == users) {
				throw new Exception("nick is not exists!");
			} else if (!user.getPassword().equals(users.getPassword())) {
				throw new Exception("password is not correct!");
			} else {
				return IoUtil.cors(new RestResult());
			}
		} finally {
			systemSqlSession.close();
		}
	}
}