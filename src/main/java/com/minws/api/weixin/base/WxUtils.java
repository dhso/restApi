/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.weixin.base;

import java.util.Arrays;
import com.minws.api.framework.util.EncryptUtil;

public class WxUtils {

	/**
	 * @param signature
	 *            微信加密签名
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机数
	 * @param echostr
	 *            验证信息
	 * @param token
	 *            自定义token
	 * @return Boolean类型
	 */
	public static Boolean verifySignature(String signature, String timestamp, String nonce, String echostr, String token) {
		String[] str = { token, timestamp, nonce };
		Arrays.sort(str); // 字典序排序
		String sortStr = str[0] + str[1] + str[2];
		String signatureStr = EncryptUtil.encrypt(sortStr, EncryptUtil.SHA1);
		if (signature.equals(signatureStr)) {
			return true;
		} else {
			return false;
		}
	}

}