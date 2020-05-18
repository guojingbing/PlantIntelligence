package com.pig4cloud.pigx.datacollection.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 周宇
 * @create 2020-04-15 12:50
 * OkHttpUtils 封装的工具类
 */
@Slf4j
public class OkHttpUtils {

	private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
	private static final byte[] LOCKER = new byte[0];
	private static OkHttpUtils instance;
	private OkHttpClient okHttpClient;

	public OkHttpUtils() {
		okHttpClient = new OkHttpClient.Builder()
				.connectTimeout(10, TimeUnit.SECONDS)//10秒连接超时
				.writeTimeout(10, TimeUnit.SECONDS)//10m秒写入超时
				.readTimeout(10, TimeUnit.SECONDS)//10秒读取超时
				.build();
	}

	public static OkHttpUtils getInstance() {
		if (instance == null) {
			synchronized (LOCKER) {
				if (instance == null) {
					instance = new OkHttpUtils();
				}
			}
		}
		return instance;
	}

	public String doGet(String url) {
		if (isBlankUrl(url)) {
			return null;
		}
		Request request = getRequestForGet(url);
		return commonRequest(request);
	}

	public String doGet(String url, HashMap<String, String> params) {
		if (isBlankUrl(url)) {
			return null;
		}
		Request request = getRequestForGet(url, params);
		return commonRequest(request);
	}

	public String doPostJson(String url, String json) {
		if (isBlankUrl(url)) {
			return null;
		}
		Request request = getRequestForPostJson(url, json);
		return commonRequest(request);
	}

	public String doPostForm(String url, Map<String, String> params) {
		if (isBlankUrl(url)) {
			return null;
		}
		Request request = getRequestForPostForm(url, params);
		return commonRequest(request);
	}

	private Boolean isBlankUrl(String url) {
		if (StringUtils.isBlank(url)) {
			log.info("url is not blank");
			return true;
		} else {
			return false;
		}
	}

	private String commonRequest(Request request) {
		String re = "";
		try {
			Call call = okHttpClient.newCall(request);
			Response response = call.execute();
			if (response.isSuccessful()) {
				re = response.body().string();
				log.info("request url:{};response:{}", request.url().toString(), re);
			} else {
				log.info("request failure url:{};message:{}", request.url().toString(), response.message());
			}
		} catch (Exception e) {
			log.error("request execute failure", e);
		}
		return re;
	}

	private Request getRequestForPostJson(String url, String json) {
		RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		return request;
	}

	private Request getRequestForPostForm(String url, Map<String, String> params) {
		if (params == null) {
			params = new HashMap<>();
		}
		FormBody.Builder builder = new FormBody.Builder();
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				builder.addEncoded(entry.getKey(), entry.getValue());
			}
		}
		RequestBody requestBody = builder.build();
		Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.build();
		return request;
	}

	private Request getRequestForGet(String url, HashMap<String, String> params) {
		Request request = new Request.Builder()
				.url(getUrlStringForGet(url, params))
				.build();
		return request;
	}

	private Request getRequestForGet(String url) {
		Request request = new Request.Builder()
				.url(url)
				.build();
		return request;
	}

	private String getUrlStringForGet(String url, HashMap<String, String> params) {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(url);
		urlBuilder.append("?");
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				try {
					urlBuilder.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				} catch (Exception e) {
					urlBuilder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
				}
			}
		}
		return urlBuilder.toString();
	}

	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		OkHttpUtils okHttpUtils = new OkHttpUtils();

		//登录login
		// String url = "http://www.yunplc.com:7080/exlog";
		// Map<String, String> params = new HashMap<>();
		// params.put("GRM", "53229020107");
		// params.put("PASS", "lihui585856");
		// String logim = okHttpUtils.doPostForm(url, params);
		// System.out.println(logim);

		String txt = okHttpUtils.doPostJson("http://120.27.155.137:7080/exdata?SID=55E77EE230D28702&OP=R", "1\r\n$Com1Error");
		System.out.println(txt);
	}
}