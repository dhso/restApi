/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.framework.util;

import java.util.HashMap;
import com.alibaba.appengine.api.fetchurl.FetchUrlService;
import com.alibaba.appengine.api.fetchurl.FetchUrlServiceFactory;

public class AceUtil {

	/**
	 * ACE内置的HTTPclient
	 * 
	 * @param type
	 * @param url
	 * @param parameters
	 * @param charset
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String httpConnect(String type, String url, HashMap parameters, String charset) {
		FetchUrlService fetchUrlService = FetchUrlServiceFactory.getFetchUrlService();
		String result = null;
		if ("POST".equals(type)) {
			if (charset != null) {
				result = fetchUrlService.post(url, parameters, charset);
			} else {
				result = fetchUrlService.post(url, parameters);
			}
		} else {
			result = fetchUrlService.get(url);
		}
		return result;
	}
}