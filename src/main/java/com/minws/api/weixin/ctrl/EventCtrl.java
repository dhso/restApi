/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.weixin.ctrl;

import java.util.Date;

import com.minws.api.framework.util.DateUtil;
import com.minws.api.weixin.entity.TextXml;
import com.minws.api.weixin.entity.Weixin;

public class EventCtrl {

	public static Object eventCtrl(Weixin inWeixin) {
		if ("subscribe".equalsIgnoreCase(inWeixin.getEvent())) {
			return welcome(inWeixin);
		} else {
			return null;
		}
	}

	/**
	 * 首次关注，欢迎
	 * 
	 * @param inWeixin
	 * @return
	 */
	public static TextXml welcome(Weixin inWeixin) {
		return new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), "text", "亲爱的，感谢您关注本微信，么么哒~~ \n\n->功能提示，请输入： 帮助");
	}

}