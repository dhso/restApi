/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.weixin.ctrl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.appengine.api.cache.CacheService;
import com.alibaba.appengine.api.cache.CacheServiceFactory;
import com.minws.api.framework.Constant.EncodeType;
import com.minws.api.framework.Constant.HeaderType;
import com.minws.api.framework.util.AceUtil;
import com.minws.api.framework.util.DateUtil;
import com.minws.api.framework.util.IoUtil;
import com.minws.api.weixin.base.WxType;
import com.minws.api.weixin.entity.ArticleXml;
import com.minws.api.weixin.entity.LocationXml;
import com.minws.api.weixin.entity.PicTextXml;
import com.minws.api.weixin.entity.TextXml;
import com.minws.api.weixin.entity.Weixin;

public class TextCtrl {

	public static Object textCtrl(Weixin inWeixin) throws IllegalStateException, IOException {
		String[] inContents = inWeixin.getContent().split("#");
		Integer inConsNum = inContents.length;
		if ("帮助".equals(inContents[0]) && inConsNum == 1) {
			return help(inWeixin, "指令");
		} else if ("帮助".equals(inContents[0]) && inConsNum == 2) {
			return help(inWeixin, inContents[1]);
		} else if ("翻译".equals(inContents[0]) && inConsNum == 2) {
			return youdao_translate(inWeixin, inContents[1]);
		} else if ("1".equals(inContents[0]) && inConsNum == 1) {
			return test(inWeixin, "服务号");
		} else if ("2".equals(inContents[0]) && inConsNum == 1) {
			return test(inWeixin, "订阅号");
		} else if ("3".equals(inContents[0]) && inConsNum == 1) {
			return test(inWeixin, "other");
		} else if ("天气".equals(inContents[0]) && inConsNum == 2) {
			return apix_weather(inWeixin, inContents[1]);
		} else if ("菜谱".equals(inContents[0]) && inConsNum == 2) {
			return apix_cookbook(inWeixin, inContents[1]);
		} else if ("附近".equals(inContents[0]) && inConsNum == 2) {
			return baidu_place_search(inWeixin, inContents[1]);
		} else if ("查询".equals(inContents[0]) && inConsNum == 2) {
			return query(inWeixin, inContents[1]);
		} else if ("测试".equals(inContents[0]) && inConsNum == 2) {
			return test(inWeixin, inContents[1]);
		} else {
			return simsimi(inWeixin);
		}
	}
	
	public static PicTextXml test(Weixin inWeixin, String info) {
		List<ArticleXml> oArticleXml = new ArrayList<ArticleXml>();
		ArticleXml sArticleXml = new ArticleXml();
		if ("服务号".equals(info)) {
			sArticleXml.setTitle("服务号");
			sArticleXml.setDescription("服务号测试数据");
			sArticleXml.setPicUrl("http://wcdn.u.qiniudn.com/img/weatherreport.jpg");
			sArticleXml.setUrl("http://p-cn.acxiom-online.com/pixel/trc?pid=4032&frm=p&uid=0&camid=NRJvIz&adgid=BbuyQj&redir=http%3A%2F%2FMemberdaysep.lorealparis.com.cn%2F%3Futm_source%3Dweixin%26utm_medium%3Dsocial%26utm_term%3Dloreal%2520%25E7%25BE%258E%25E4%25B8%25BD%25E6%25AE%25BF%25E5%25A0%2582%26utm_content%3D%26utm_campaign%3DMemberdaysep%26adg_id%3DBbuyQj");
		}else if ("订阅号".equals(info)) {
			sArticleXml.setTitle("订阅号");
			sArticleXml.setDescription("订阅号测试数据");
			sArticleXml.setPicUrl("http://wcdn.u.qiniudn.com/img/weatherreport.jpg");
			sArticleXml.setUrl("http://p-cn.acxiom-online.com/pixel/trc?pid=4032&frm=p&uid=0&camid=NRJvIz&adgid=2EjaUn&redir=http%3A%2F%2FMemberdaysep.lorealparis.com.cn%2F%3Futm_source%3Dweixin%26utm_medium%3Dsocial%26utm_term%3Dloreal%26utm_content%3D%26utm_campaign%3DMemberdaysep%26adg_id%3D2EjaUn");
		}else if ("other".equals(info)) {
			sArticleXml.setTitle("other");
			sArticleXml.setDescription("other测试数据");
			sArticleXml.setPicUrl("http://wcdn.u.qiniudn.com/img/weatherreport.jpg");
			sArticleXml.setUrl("http://p-cn.acxiom-online.com/pixel/trc?pid=4032&frm=p&uid=0&camid=NRJvIz&adgid=yyy6fe&redir=http%3A%2F%2FMemberdaysep.lorealparis.com.cn%2F%3Futm_source%3Dweixin%26utm_medium%3Dsocial%26utm_term%3Dloreal%2520%25E7%25BE%258E%25E4%25B8%25BD%25E6%25AE%25BF%25E5%25A0%2582%26utm_content%3Dnonesource%26utm_campaign%3DMemberdaysep%26adg_id%3Dyyy6fe");
		}
		
		oArticleXml.add(sArticleXml);
		return new PicTextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_NEWS, 1L, oArticleXml);
	}

	/**
	 * 默认帮助
	 * 
	 * @param inWeixin
	 * @return
	 */
	public static TextXml help(Weixin inWeixin, String info) {
		TextXml outXml = new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, null);
		if ("指令".equals(info)) {
			outXml.setContent("->聊天：任意与下面功能不冲突的语句\n->翻译：翻译#你好\n->天气：天气#北京\n->菜谱：菜谱#烤鸭\n->附近：附近#银行\n->查询：查询#公交南通41|火车|汽车|地铁");
		}
		return outXml;
	}

	/**
	 * simsimi小黄鸡
	 * 
	 * @param inWeixin
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public static TextXml simsimi(Weixin inWeixin) throws IllegalStateException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getCredentialsProvider().setCredentials(new AuthScope("10.242.175.127", 3128), new UsernamePasswordCredentials("1723560127772410_default_54", "rro0530e9o"));
		client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost("10.242.175.127", 3128));
		String uri = "http://www.simsimi.com/func/reqN?lc=ch&ft=0.0&req=" + URLEncoder.encode(inWeixin.getContent(), EncodeType.UTF_8) + "&fl=http%3A%2F%2Fwww.simsimi.com%2Ftalk.htm";
		HttpGet request = new HttpGet(uri);
		request.addHeader(HeaderType.USER_AGENT, HeaderType.USER_AGENT_CHROME);
		request.addHeader(HeaderType.COOKIE, "simsimi_uid=59406430");
		try {
			HttpResponse response = client.execute(request);
			String data = IoUtil.inputStream2String(response.getEntity().getContent());
			TextXml outXml = new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, null);
			if (data.isEmpty()) {
				outXml.setContent("小黄鸡被大象打嘴巴了，暂时不能说话！");
			} else {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(data); // 读取Json
				outXml.setContent(rootNode.path("sentence_resp").asText());
			}
			return outXml;
		} finally {
			request.releaseConnection();
		}
	}

	/**
	 * youdao有道翻译
	 * 
	 * @param inWeixin
	 * @param word
	 *            单词
	 * @return
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static TextXml youdao_translate(Weixin inWeixin, String word) throws JsonProcessingException, IOException {
		String uri = "http://fanyi.youdao.com/openapi.do?keyfrom=apphui&key=1411065029&type=data&doctype=json&version=1.1&q=" + URLEncoder.encode(word, EncodeType.UTF_8);
		/* ResteasyClient client = new ResteasyClientBuilder().build(); */
		/* ResteasyWebTarget target = client.target(uri); */
		/* Builder request = target.request(); */
		/* Response response = request.get(); */
		/* String data = response.readEntity(String.class); */
		String data = AceUtil.httpConnect("GET", uri, null, null);
		TextXml outXml = new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, null);
		if (data.isEmpty()) {
			outXml.setContent("有道词典抽风了，过会再来查吧！");
		} else {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(data); // 读取Json
			if (rootNode.path("errorCode").asInt() != 0) {
				outXml.setContent("有道词典都被你打败了！");
			} else {
				String translation = "";
				if (rootNode.has("basic")) {
					if (rootNode.path("basic").has("phonetic")) {
						translation = translation + "拼音:" + rootNode.path("basic").path("phonetic").asText() + " \n";
					}
					if (rootNode.path("basic").has("explains")) {
						translation = translation + "基础翻译:" + rootNode.path("basic").path("explains").get(0).asText() + " \n";
					}
				}
				if (rootNode.has("translation")) {
					translation = translation + "网络翻译:" + rootNode.path("translation").get(0).asText() + " \n";
				}
				outXml.setContent(translation);
			}
		}
		return outXml;
	}

	/**
	 * apix的天气预报
	 * 
	 * @param inWeixin
	 * @param city
	 * @return
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static Object apix_weather(Weixin inWeixin, String city) throws JsonProcessingException, IOException {
		String uri = "http://apix.sinaapp.com/weather/?appkey=trialuser&city=" + URLEncoder.encode(city, EncodeType.UTF_8);
		String data = AceUtil.httpConnect("GET", uri, null, null);
		if (data.isEmpty()) {
			return new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, "天气预报歇菜了，请稍后再查！");
		} else {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(data); // 读取Json
			if (!rootNode.isContainerNode()) {
				return new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, rootNode.asText());
			} else {
				List<ArticleXml> oArticleXml = new ArrayList<ArticleXml>();
				int oArtNum = rootNode.size();
				for (int i = 0; i < oArtNum; i++) {
					ArticleXml sArticleXml = new ArticleXml();
					if (rootNode.get(i).has("Title")) {
						sArticleXml.setTitle(rootNode.get(i).path("Title").asText());
					}
					if (i == 0) {
						sArticleXml.setPicUrl("http://wcdn.u.qiniudn.com/img/weatherreport.jpg");
					} else if (rootNode.get(i).has("PicUrl")) {
						sArticleXml.setPicUrl(rootNode.get(i).path("PicUrl").asText());
					}
					if (!"".equals(sArticleXml) && sArticleXml != null) {
						oArticleXml.add(sArticleXml);
					}
				}
				return new PicTextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_NEWS, new Long(oArtNum), oArticleXml);
			}
		}
	}

	/**
	 * apix的菜谱
	 * 
	 * @param inWeixin
	 * @param city
	 * @return
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static Object apix_cookbook(Weixin inWeixin, String food) throws JsonProcessingException, IOException {
		String uri = "http://apix.sinaapp.com/recipe/?appkey=trialuser&name=" + URLEncoder.encode(food, EncodeType.UTF_8);
		String data = AceUtil.httpConnect("GET", uri, null, null);
		if (data.isEmpty()) {
			return new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, "食谱被食神藏起来了，找不到了！");
		} else {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(data); // 读取Json
			if (!rootNode.isContainerNode()) {
				return new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, rootNode.asText());
			} else {
				List<ArticleXml> oArticleXml = new ArrayList<ArticleXml>();
				int oArtNum = rootNode.size();
				for (int i = 0; i < oArtNum; i++) {
					ArticleXml sArticleXml = new ArticleXml();
					if (rootNode.get(i).has("Title")) {
						sArticleXml.setTitle(rootNode.get(i).path("Title").asText());
					}
					if (rootNode.get(i).has("PicUrl")) {
						sArticleXml.setPicUrl(rootNode.get(i).path("PicUrl").asText());
					}
					if (!"".equals(sArticleXml) && sArticleXml != null) {
						oArticleXml.add(sArticleXml);
					}
				}
				return new PicTextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_NEWS, new Long(oArtNum), oArticleXml);
			}
		}
	}

	/**
	 * 百度 Place api
	 * 
	 * @param inWeixin
	 * @param place
	 * @return
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static Object baidu_place_search(Weixin inWeixin, String place) throws JsonProcessingException, IOException {
		CacheService cacheService = CacheServiceFactory.getCacheService("webservice");
		LocationXml locationXml = (LocationXml) cacheService.get(inWeixin.getFromUserName());
		if ("".equals(locationXml) || locationXml == null) {
			return new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, "未获取到您的位置，请点击+号，点击 位置 按钮，将位置发送给我。");
		} else {
			String uri = "http://api.map.baidu.com/place/v2/search?&query=" + URLEncoder.encode(place, EncodeType.UTF_8) + "&location=" + locationXml.getLocation_X() + "," + locationXml.getLocation_Y() + "&page_size=9&radius=2000&output=json&scope=2&ak=LhjeYbkbEYuBNpcfyAx1BeL9";
			String data = AceUtil.httpConnect("GET", uri, null, null);
			if (data.isEmpty()) {
				return new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, "附近查找这个应用有点问题，无法服务！");
			} else {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(data); // 读取Json
				List<ArticleXml> oArticleXml = new ArrayList<ArticleXml>();
				int oArtNum = rootNode.path("results").size();
				if (oArtNum == 0) {
					return new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, "附近荒无人烟，你确定是这？再试一次吧。");
				} else {
					for (int i = 0; i < oArtNum + 1; i++) {
						ArticleXml sArticleXml = new ArticleXml();
						if (i == 0) {
							sArticleXml.setTitle("附近的  " + place + " 列表，点击进入详情");
							sArticleXml.setPicUrl("http://wcdn.u.qiniudn.com/img/nearby.jpg");
						} else {
							String name = "";
							String add = "";
							String tel = "";
							if (rootNode.path("results").get(i - 1).has("name")) {
								name = rootNode.path("results").get(i - 1).path("name").asText();
							}
							if (rootNode.path("results").get(i - 1).has("telephone")) {
								tel = "-" + rootNode.path("results").get(i - 1).path("telephone").asText();
							}
							if (rootNode.path("results").get(i - 1).has("address")) {
								add = "\n地址:" + rootNode.path("results").get(i - 1).path("address").asText();
							}
							sArticleXml.setTitle(name + tel + add);
							if (rootNode.path("results").get(i - 1).path("detail_info").has("detail_url")) {
								sArticleXml.setUrl(rootNode.path("results").get(i - 1).path("detail_info").path("detail_url").asText());
							}
						}
						if (!"".equals(sArticleXml) && sArticleXml != null) {
							oArticleXml.add(sArticleXml);
						}
					}
					return new PicTextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_NEWS, new Long(oArtNum + 1), oArticleXml);
				}
			}
		}
	}

	/**
	 * query查询
	 * 
	 * @param inWeixin
	 * @param param
	 * @return
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static Object query(Weixin inWeixin, String param) throws JsonProcessingException, IOException {
		if ("火车".equals(param)) {
			List<ArticleXml> articleXmls = new ArrayList<ArticleXml>();
			articleXmls.add(new ArticleXml("火车查询", "点击进入 火车查询", "http://wcdn.u.qiniudn.com/img/train.jpg", "http://touch.qunar.com/h5/train/"));
			return new PicTextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_NEWS, (long) 1, articleXmls);
		} else if ("汽车".equals(param)) {
			List<ArticleXml> articleXmls = new ArrayList<ArticleXml>();
			articleXmls.add(new ArticleXml("汽车查询", "点击进入 汽车查询", "http://wcdn.u.qiniudn.com/img/bus.jpg", "http://mbaiduwebapp.trip8080.com/"));
			return new PicTextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_NEWS, (long) 1, articleXmls);
		} else if ("地铁".equals(param)) {
			List<ArticleXml> articleXmls = new ArrayList<ArticleXml>();
			articleXmls.add(new ArticleXml("地铁查询", "点击进入 地铁查询", "http://wcdn.u.qiniudn.com/img/metro.jpg", "http://map.baidu.com/mobile/webapp/subway/show/foo=bar/"));
			return new PicTextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_NEWS, (long) 1, articleXmls);
		} else if ("公交".equals(param.substring(0, 2))) {
			String line = IoUtil.regex(param, "([0-9]+)|([a-zA-Z][0-9]+)|([a-zA-Z][^x00-xff][0-9]+)");
			String city = IoUtil.regex(param, "[^x00-xff]+").replace("公交", "");
			if ("".equals(line)) {
				return new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, "请输入 " + param + " 几路!");
			}
			if ("".equals(city)) {
				return new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, "请输入 " + param + " 城市!");
			}
			String uri = "http://apix.sinaapp.com/busline/?appkey=trailuser&city=" + URLEncoder.encode(city, EncodeType.UTF_8) + "&line=" + URLEncoder.encode(line, EncodeType.UTF_8);
			String data = AceUtil.httpConnect("GET", uri, null, null);
			if (data.isEmpty()) {
				return new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, "公交车查找这个应用有点问题，无法服务！");
			} else {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(data); // 读取Json
				return new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, rootNode.asText());
			}
		} else {
			return new TextXml(inWeixin.getFromUserName(), inWeixin.getToUserName(), Long.parseLong(DateUtil.unixTimestamp(new Date())), WxType.MSG_TYPE_TEXT, "暂不支持 " + param + " 命令!");
		}
	}
}