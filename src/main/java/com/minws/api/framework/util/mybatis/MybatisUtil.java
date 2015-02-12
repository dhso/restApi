/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.framework.util.mybatis;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {

	/**
	 * connectDb连接数据库
	 * 
	 * @param confXml
	 * @param environment
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	public static SqlSessionFactory connectDb(String confXml, String environment) {
		try {
			return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(confXml), environment);
		} catch (IOException e) {
			return null;
		}
	}
}