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

import com.minws.api.ehome.entity.Device;
import com.minws.api.framework.util.DateUtil;
import com.minws.api.framework.util.EncryptUtil;

public class EhomeListData {

	public static void main(String[] args) throws JsonProcessingException, IOException {
		String timestamp = DateUtil.unixTimestamp(new Date());
		String appkey="ehome";
		String appid ="3";
		String[] str = { appid, appkey, timestamp };
		Arrays.sort(str); // 字典序排序
		String sortStr = str[0] + str[1] + str[2];
		String signatureStr = EncryptUtil.encrypt(sortStr, EncryptUtil.SHA1);
		System.out.println(signatureStr);
		System.out.println(timestamp);
		String uri = "http://webservice.aliapp.com/api/ehome/device/list?signature="+signatureStr+"&timestamp="+timestamp+"&appid="+appid;
		Device device = new Device();
		device.setDee_bur_id(1);
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(uri);
		Builder request = target.request();
		Response response = request.post(Entity.entity(device, MediaType.APPLICATION_JSON));
		String data = response.readEntity(String.class);
		System.out.println(data);
	}
}
