/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.weixin.ctrl;

import java.util.Date;
import com.alibaba.appengine.api.cache.CacheService;
import com.alibaba.appengine.api.cache.CacheServiceFactory;
import com.minws.api.framework.util.DateUtil;
import com.minws.api.weixin.entity.LocationXml;
import com.minws.api.weixin.entity.TextXml;
import com.minws.api.weixin.entity.Weixin;

public class LocatinCtrl {

	public static TextXml saveLocation(Weixin inWeixin) {
		CacheService cacheService = CacheServiceFactory.getCacheService("webservice");
		boolean success = false;
		TextXml outXml = new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), "text", null);
		LocationXml locationXml = new LocationXml(inWeixin.getLocation_X(), inWeixin.getLocation_Y(), inWeixin.getScale(), inWeixin.getLabel());
		success = cacheService.put(inWeixin.getFromUserName(), locationXml, 172800);
		if (success) {
			outXml.setContent("您的当前位置 " + inWeixin.getLabel() + " ，记录保留48小时。");
		} else {
			outXml.setContent("记录您的当前位置失败，请重试。");
		}
		return outXml;
	}

}