package com.kingkate.busfind.util;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	private static final int DEFAULT_SOCKET_TIMEOUT = 60000;
	private static final int DEFAULT_CONNECT_TIMEOUT = 30000;

	private static CloseableHttpClient httpClient;
	private static int socketTimeout = DEFAULT_SOCKET_TIMEOUT;
	private static int connectTimeout = DEFAULT_CONNECT_TIMEOUT;


	private static Object obj = new Object();
	public static String get(String url,Header ...headers){
		HttpGet get = new HttpGet(url);
		if(null != headers&&headers.length>0){
			for(Header header : headers){
				get.addHeader(header);
			}
		}
		HttpClient httpClient = HttpClients.createDefault();
		try {
			HttpResponse response = httpClient.execute(get);
			return EntityUtils.toString(response.getEntity(), "utf-8");
//			return EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String get(String url,String params,Header ...headers){
		url = url.contains("?") ? url + "&" + params:url + "?" +params;
		HttpGet get = new HttpGet(url);
		
		if(null != headers&&headers.length>0){
			for(Header header : headers){
				get.addHeader(header);
			}
		}
		HttpClient httpClient = HttpClients.createDefault();
		try {
			HttpResponse response = httpClient.execute(get);
			return EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String post(String url, Map<String, String> map) {
		BasicNameValuePair[] params = new BasicNameValuePair[map.size()];
		int i = 0;
		for (Entry<String, String> entry : map.entrySet()) {
			params[i] = new BasicNameValuePair(entry.getKey(), entry.getValue());
			i++;
		}
		return post(url, params);
	}
	
	public static String post(String url, Map<String, String> map,Header[] headers) {
		BasicNameValuePair[] params = new BasicNameValuePair[map.size()];
		int i = 0;
		for (Entry<String, String> entry : map.entrySet()) {
			params[i] = new BasicNameValuePair(entry.getKey(), entry.getValue());
			i++;
		}
		return post(url,headers, params);
	}
	
	public static String post(String url, BasicNameValuePair... params) {
		HttpPost post = getHttpPost(url);
		try {
			if (params.length > 0) {
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				for (BasicNameValuePair param : params) {
					list.add(param);
				}
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
						Consts.UTF_8);
				post.setEntity(entity);
			}
			return sendHttpRequest(post, "");
		} finally {
			post.releaseConnection();
		}
	}
	
	public static String post(String url,Header[] headers, BasicNameValuePair... params) {
		HttpPost post = getHttpPost(url,headers);
		try {
			if (params.length > 0) {
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				for (BasicNameValuePair param : params) {
					list.add(param);
				}
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
						Consts.UTF_8);
				post.setEntity(entity);
			}
			return sendHttpRequest(post, "");
		} finally {
			post.releaseConnection();
		}
	}
	
	public static String sendHttpRequest(HttpUriRequest request, String key) {
		CloseableHttpResponse response = null;
		String strs = null;
		try {
			response = getHttpClient().execute(request);
			// 正确返回200
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				strs = EntityUtils.toString(entity, Consts.UTF_8);
				EntityUtils.consume(entity);//释放底层连接
				
			} else {
				throw new IOException("返回HTTP状态码异常："
						+ response.getStatusLine().getStatusCode() + "\r\n" + response.getStatusLine().getReasonPhrase() + "\r\n");
			}
			return strs;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return strs;
		} catch (NoHttpResponseException e) {
			e.printStackTrace();
			return strs;
		} catch (IOException e) {
			e.printStackTrace();
			return strs;
		} catch (Exception e) {
			e.printStackTrace();
			return strs;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static HttpPost getHttpPost(String url) {
		HttpPost post = new HttpPost(url);
		post.setHeader("charset", "UTF-8");
		RequestConfig config = RequestConfig.custom()
				.setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();
		post.setConfig(config);
		return post;
	}
	
	
	private static HttpPost getHttpPost(String url,Header[] headers) {
		HttpPost post = new HttpPost(url);
		if(null != headers&&headers.length>0){
			for(Header header : headers){
				post.addHeader(header);
			}
		}
		RequestConfig config = RequestConfig.custom()
				.setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();
		post.setConfig(config);
		return post;
	}
	
	
	public HttpUtil() {
		// httpClient = HttpClients.createDefault();
		getHttpClient();
	}

	public static CloseableHttpClient getHttpClient() {
		if (null == httpClient) {
			synchronized (obj) {
				if (null == httpClient) {
					PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
					cm.setMaxTotal(10000);
					cm.setDefaultMaxPerRoute(50);
					httpClient = HttpClients.custom().setConnectionManager(cm)
							.setRetryHandler(new RetryHandler())
							.build();
				}
			}
		}
		return httpClient;
	}

	
	static class RetryHandler implements HttpRequestRetryHandler{

		public boolean retryRequest(IOException exception, int executionCount,
				HttpContext context) {
			if(executionCount > 5){
				return false;
			}
			if(exception instanceof NoHttpResponseException){
				return true;
			}
			if(exception instanceof SocketTimeoutException){
				return true;
			}
			String msg = exception.getMessage();
			if(!Helper.isEmpty(msg) && msg.contains("Connection reset")){
				return true;
			}
			if(!Helper.isEmpty(msg)&& msg.contains("failed to respond")){
				return true;
			}
			return false;
		}
		
	}
}
