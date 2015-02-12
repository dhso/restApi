/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.weixin.ctrl;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import com.minws.api.framework.util.IoUtil;
import com.minws.api.weixin.base.WxType;
import com.minws.api.weixin.base.WxUtils;
import com.minws.api.weixin.entity.Weixin;

@Path("/weixin")
public class WeixinCtrl {

	private String token = "apphui";// 自定义token

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/apphui")
	public String checkSignature(@QueryParam("signature") @DefaultValue("") String signature,// 微信加密签名
			@QueryParam("timestamp") @DefaultValue("") String timestamp,// 时间戳
			@QueryParam("nonce") @DefaultValue("") String nonce,// 随机数
			@QueryParam("echostr") @DefaultValue("") String echostr) // 验证验证信息
	{
		if (WxUtils.verifySignature(signature, timestamp, nonce, echostr, token)) {
			return echostr;
		} else {
			return "Verify the signature failure!";
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Path("/apphui")
	public Object doOperation(@Context HttpServletRequest request) throws JAXBException, IOException { // 获取请求
		InputStream inXml = request.getInputStream();
		Weixin inWeixin = IoUtil.jaxbReadXML(Weixin.class, inXml);
		if (WxType.MSG_TYPE_TEXT.equalsIgnoreCase(inWeixin.getMsgType())) {
			return TextCtrl.textCtrl(inWeixin);
		} else if (WxType.MSG_TYPE_EVENT.equalsIgnoreCase(inWeixin.getMsgType())) {
			return EventCtrl.eventCtrl(inWeixin);
		} else if (WxType.MSG_TYPE_LOCATION.equalsIgnoreCase(inWeixin.getMsgType())) {
			return LocatinCtrl.saveLocation(inWeixin);
		}
		return null;
	}
}