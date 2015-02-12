/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class IoUtil {

	/**
	 * inputStream转String
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static String inputStream2String(InputStream inputStream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		String result = "";
		while ((line = reader.readLine()) != null) {
			result += line + "\n";
		}
		return result;
	}

	/**
	 * jaxb读取XML到Bean
	 * 
	 * @param clazz
	 * @param inputStream
	 * @return
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jaxbReadXML(Class<T> clazz, InputStream inputStream) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Unmarshaller u = jc.createUnmarshaller();
		return (T) u.unmarshal(inputStream);
	}

	/**
	 * regex正则匹配
	 * 
	 * @param input
	 * @param rule
	 * @return
	 */
	public static String regex(String input, String rule) {
		Pattern pattern = Pattern.compile(rule);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return "";
		}
	}

	/**
	 * Ajax跨域CORS支持
	 * 
	 * @param entity
	 * @return Response
	 */
	public static Response cors(Object entity) {
		return Response.ok(entity).header("Access-Control-Allow-Origin", "*").header("Access-Control-Max-Age", "3600").build();
	}

	/**
	 * Ajax跨域CORS支持
	 * 
	 * @param entity
	 * @return Response
	 */
	public static Response cors(Object entity, String domain) {
		return Response.ok(entity).header("Access-Control-Allow-Origin", domain).header("Access-Control-Max-Age", "3600").build();
	}

	/**
	 * 是否包含在数组
	 * @param stringArray
	 * @param string
	 * @return
	 */
	public static Boolean inArray(String[] stringArray, String string) {
		List<String> tempList = Arrays.asList(stringArray);
		if (tempList.contains(string)) {
			return true;
		} else {
			return false;
		}
	}

}