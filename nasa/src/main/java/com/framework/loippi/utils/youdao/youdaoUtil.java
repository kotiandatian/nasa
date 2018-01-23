package com.framework.loippi.utils.youdao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.framework.loippi.utils.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class youdaoUtil {

	public static String appKey = "5afe30c501e66e57";
	public static String from = "zh-CHS";
	public static String to = "EN";

	public static String url = "http://openapi.youdao.com/api";

	public static void main(String[] args) throws Exception {
		// String appKey ="5afe30c501e66e57";
		// String query = "This new, detailed global mosaic color map of Pluto
		// is based on a series of three color filter images obtained by the
		// Ralph/Multispectral Visual Imaging Camera aboard New Horizons during
		// the NASA spacecraft's close flyby of Pluto in July 2015. The mosaic
		// shows how Pluto's large-scale color patterns extend beyond the
		// hemisphere facing New Horizons at closest approach- which were imaged
		// at the highest resolution. North is up; Pluto's equator roughly
		// bisects the band of dark red terrains running across the lower third
		// of the map. Pluto's giant, informally named Sputnik Planitia glacier
		// - the left half of Pluto's signature \"heart\" feature -- is at the
		// center of this map.
		// http://photojournal.jpl.nasa.gov/catalog/PIA11707";
		// String salt = String.valueOf(System.currentTimeMillis());
		// String from = "zh-CHS";
		// String to = "EN";
		// String sign = md5(appKey + query + salt+
		// "lZCfAQ3HqOq4f2V0SsjKp9SYWd89cLfe");
		// Map<String,Object> params = new HashMap<String,Object>();
		// params.put("q", query);
		// params.put("from", to);
		// params.put("to", from);
		// params.put("sign", sign);
		// params.put("salt", salt);
		// params.put("appKey", appKey);
		// System.out.println(requestForHttp("http://openapi.youdao.com/api",
		// params));

		String translation = requestForHttpFromZh2En("This new");

		System.out.println(translation);
	}

	public static String requestForHttpFromZh2En(String query) throws Exception {
		String translation = "";
		String salt = String.valueOf(System.currentTimeMillis());

		String sign = md5(appKey + query + salt + "lZCfAQ3HqOq4f2V0SsjKp9SYWd89cLfe");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("q", query);
		params.put("from", to);
		params.put("to", from);
		params.put("sign", sign);
		params.put("salt", salt);
		params.put("appKey", appKey);
		String requestForHttp = requestForHttp("http://openapi.youdao.com/api", params);

		if (StringUtils.isNotEmpty(requestForHttp)) {

			JSONObject jsonObject = new JSONObject(requestForHttp);
			JSONArray translations = (JSONArray) jsonObject.get("translation");
			translation = (String) translations.get(0);

//			System.out.println(translation);
		}

		return translation;
	}

	public static String requestForHttp(String url, Map requestParams) throws Exception {
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		/** HttpPost */
		HttpPost httpPost = new HttpPost(url);
		System.out.println(new JSONObject(requestParams).toString());
		List params = new ArrayList();
		Iterator<Entry> it = requestParams.entrySet().iterator();
		while (it.hasNext()) {
			Entry en = it.next();
			String key = (String) en.getKey();
			String value = (String) en.getValue();
			if (value != null) {
				params.add(new BasicNameValuePair(key, value));
			}
		}
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		/** HttpResponse */
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		try {
			HttpEntity httpEntity = httpResponse.getEntity();
			result = EntityUtils.toString(httpEntity, "utf-8");
			EntityUtils.consume(httpEntity);
		} finally {
			try {
				if (httpResponse != null) {
					httpResponse.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 生成32位MD5摘要
	 * 
	 * @param string
	 * @return
	 */
	public static String md5(String string) {
		if (string == null) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = string.getBytes("utf-8");
			/** 获得MD5摘要算法的 MessageDigest 对象 */
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			/** 使用指定的字节更新摘要 */
			mdInst.update(btInput);
			/** 获得密文 */
			byte[] md = mdInst.digest();
			/** 把密文转换成十六进制的字符串形式 */
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (byte byte0 : md) {
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 根据api地址和参数生成请求URL
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String getUrlWithQueryString(String url, Map<String, String> params) {
		if (params == null) {
			return url;
		}

		StringBuilder builder = new StringBuilder(url);
		if (url.contains("?")) {
			builder.append("&");
		} else {
			builder.append("?");
		}

		int i = 0;
		for (String key : params.keySet()) {
			String value = params.get(key);
			if (value == null) { // 过滤空的key
				continue;
			}

			if (i != 0) {
				builder.append('&');
			}

			builder.append(key);
			builder.append('=');
			builder.append(encode(value));

			i++;
		}

		return builder.toString();
	}

	/**
	 * 进行URL编码
	 * 
	 * @param input
	 * @return
	 */
	public static String encode(String input) {
		if (input == null) {
			return "";
		}

		try {
			return URLEncoder.encode(input, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return input;
	}
}