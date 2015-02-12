/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.app;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;

import com.minws.api.app.data.AppMapper;
import com.minws.api.app.entity.App;
import com.minws.api.framework.util.DateUtil;
import com.minws.api.framework.util.EncryptUtil;
import com.minws.api.framework.util.IoUtil;

public class AppUtil {

	/**
	 * 校验app数据
	 * 
	 * @param signature
	 *            签名
	 * @param timestamp
	 *            时间戳
	 * @param appid
	 *            appId
	 * @param iappid
	 *            应用appid,程序内部设定
	 * @param oauthSessionFactory
	 *            认证sql session
	 * @return
	 * @throws Exception
	 */
	public static Boolean verifyAppSignature(String signature, String timestamp, String appid, String[] allowAppId, SqlSession sqlSession) throws Exception {
		if (null == timestamp || !DateUtil.difDate(DateUtil.convertUnixTimestamp(timestamp), DateUtil.addDateNode(new Date(), DateUtil.node_second, -120), true)) {
			throw new Exception("timestamp is out of date");
		}
		if (allowAppId.length != 0) {
			if (null == appid || !IoUtil.inArray(allowAppId, appid)) {
				throw new Exception("appid can not access this api");
			}
		}
		App app = selectAppKeyByAppId(appid, sqlSession);
		String appkey = "";
		if (null == app) {
			throw new Exception("appid is not correct");
		} else {
			if (null != app.getAppKey()) {
				appkey = app.getAppKey();
			}
		}
		String[] str = { appid, appkey, timestamp };
		Arrays.sort(str); // 字典序排序
		String sortStr = str[0] + str[1] + str[2];
		String signatureStr = EncryptUtil.encrypt(sortStr, EncryptUtil.SHA1);
		if (null == signature || !signature.equals(signatureStr)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 查询appkey
	 * 
	 * @param oauthSessionFactory
	 * @param appid
	 * @return
	 */
	private static App selectAppKeyByAppId(String appid, SqlSession sqlSession) {
		try {
			AppMapper appMapper = sqlSession.getMapper(AppMapper.class);
			return appMapper.selectAppByAppId(appid);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 生成AuthToken
	 * 
	 * @return AuthToken
	 */
	public static String createAuthToken() {
		return EncryptUtil.encrypt(UUID.randomUUID().toString(), EncryptUtil.SHA1);
	}

	/**
	 * 生成AuthToken过期时间
	 * 
	 * @param min
	 * @return
	 */
	public static Date authTokenExpDt(Integer min) {
		return DateUtil.addDateNode(new Date(), DateUtil.node_minute, min);
	}

}