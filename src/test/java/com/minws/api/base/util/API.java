package com.minws.api.base.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonProcessingException;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.minws.api.framework.util.DateUtil;
import com.minws.api.framework.util.EncryptUtil;
import com.minws.api.user.entity.User;

public class API {

	public static void main(String[] args) throws JsonProcessingException, IOException {
		String timestamp = DateUtil.unixTimestamp(new Date());
		String appkey="minws";
		String appid ="1";
		String[] str = { appid, appkey, timestamp };
		Arrays.sort(str); // 字典序排序
		String sortStr = str[0] + str[1] + str[2];
		String signatureStr = EncryptUtil.encrypt(sortStr, EncryptUtil.SHA1);
		String uri = "http://127.0.0.1:8080/api/api/user/add?signature="+signatureStr+"&timestamp="+timestamp+"&appid="+appid;
		User user = new User();
		user.setNick("WestStreet");
		user.setName("孙祥耀");
		user.setMobile("17095011121");
		user.setPassword("WestStreet");
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(uri);
		Builder request = target.request();
		Response response = request.post(Entity.entity(user, MediaType.APPLICATION_JSON));
		String data = response.readEntity(String.class);
		System.out.println(data);
	}
}
