package com.kingkate.busfind;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingkate.busfind.util.HttpUtil;

public class QueryShangHai {
	public static void main(String[] args) {
		
		String json = "{\"test\":\"123\"}";
		JSONObject object = JSONObject.parseObject(json);
		System.out.println(object.getIntValue("test"));
		
//		getBusName();
//		getBusAlias();
//		getBusInTime();
//		QueryInTimeBusReq req = new QueryInTimeBusReq();
//		req.setAct("busTime");
//		req.setSelBDir("4714638781653057901");
//		req.setSelBLine("664");
//		req.setSelBStop("9");
//		System.out.println(JSON.toJSONString(req));
//		String url = "http://www.bjbus.com/home/ajax_rtbus_data.php?act=busTime&selBLine="
//				+ req.getSelBLine() + "&selBDir="
//						+ req.getSelBDir() + "&selBStop=" 
//							+	req.getSelBStop();
//		String data = HttpUtil.get(url, null);
//		data = StringUtils.unicodeToString(data);
//		data.replaceAll("\\\\", "");
//		System.out.println(data);
//		JSONObject object = (JSONObject) JSONObject.parse(data);
//		String html = (String) object.get("html");
//		Document doc = Jsoup.parse(html);
//		Elements elements = doc.getElementsByTag("article");
//		System.out.println(elements.get(0).text());
//		Element ccStopId = doc.getElementById("cc_stop");
//		Elements li = ccStopId.getElementsByTag("li");
//		for(int i = 0;i<li.size();i++) {
//			Element element = li.get(i);
//			String id = element.getElementsByTag("div").get(0).id();
//			
//			String busStatus = element.getElementsByTag("i").get(0).attr("class");
//			
//			if(!id.contains("m")) {
//				System.out.print(id);
//				String busStopName = element.getElementsByTag("span").get(0).attr("title");
//				System.out.print(" "+busStopName);
//			}
//			if(null == busStatus) {
//				continue;
//			}else if("busc".equals(busStatus)) {
//				System.out.print(" 在途");
//			}else if("buss".equals(busStatus)) {
//				System.out.print(" 到站");
//			}
//			if(id.contains("m")) {
//				System.out.print("\r\n");
//			}
//		}
	}
	private static void getBusInTime() {
		String url = "https://shanghaicity.openservice.kankanews.com/public/bus/Getstop";
		Header[] headers = new Header[6];
//		headers[0] = new BasicHeader("Host", "shanghaicity.openservice.kankanews.com");
//		headers[1] = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		headers[2] = new BasicHeader("Cookie", "_ga=GA1.2.825472178.1552894274; acw_tc=2f624a7715528942728653281e766b1607e52efaa4ff1c1068e9f7990b057c");
		headers[3] = new BasicHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16C101 MicroMessenger/6.7.1 NetType/WIFI Language/zh_CN");
//		headers[4] = new BasicHeader("Accept-Language", "zh-cn");
//		headers[1] = new BasicHeader("Referer","https://shanghaicity.openservice.kankanews.com/public/bus/mes/sid/6934748cd9637645b5b41fd08bba5690");
		headers[0] = new BasicHeader("Content-Type","application/x-www-form-urlencoded");
//		headers[2] = new BasicHeader("Origin","https://shanghaicity.openservice.kankanews.com");
//		headers[4] = new BasicHeader("X-Requested-With","XMLHttpRequest");
		headers[5] = new BasicHeader("Cookie","SERVERID=c6de652ace7b1e6134145ef11b72ccf7|1553068318|1553068284; XSRF-TOKEN=eyJpdiI6IllwNVpsUXFIdWltaTlNRTVoU0JiVkE9PSIsInZhbHVlIjoienRrTGkwOCsrNmZ1OE9uWURPemxCellVZUc4V25FMUkwNklGekFyTnoyNG1zQnhWVzNVTzBqZ1MrTnRtVjg0V1htUDlCdXZ5OVdwZTFBSW1EVk5MVHc9PSIsIm1hYyI6ImFmYzExYTY5ZWE1YzYwOGZmOTI1NjE5NzQ0NDBmMjkyZjg1NmE1YjVkNzc1ZmZhY2QxZGYzOWFiMzJiZGJhODQifQ%3D%3D; _session=eyJpdiI6ImZ2elY4ZVwvYXQyTU9WN1V6K25sY2FnPT0iLCJ2YWx1ZSI6IjJMYjFDQytRTzROWXFVMTVHb08yU1VDNUVHT0FQbnVwc3lodytNcHhsZFlRM2xPbHVkbE50SlRKb1VpZXFcL2d4TXU3Qnd6Y1dhQmtaY1wvV0tjNEVYQ1E9PSIsIm1hYyI6IjUxYTczN2ZiY2M3MDY4OWNkYTc0NWRiMGI0OGRlZjdkMjQ4OWU4NmFlNGU5Mzk3ZWQ2YmNmNDAyZWIwMTNmNzEifQ%3D%3D; _ga=GA1.2.825472178.1552894274; _gat=1; Hm_p1vt_6f69830ae7173059e935b61372431b35=eSgsNFyR8PyYsQc/DzxlAg==; last_search_records=eyJpdiI6InI1VytmbUlSa3NHTDBQVnY1cnV2VlE9PSIsInZhbHVlIjoialBtTzNneEI4aytwdGt1cnVHTGhwZjhiKzdydHQ5SFZwRjJDUjQ3b3RGND0iLCJtYWMiOiJhNmYyZTQ2NTE5MmQyNGJjZTJiNjZiZWJhMTk0NDBlYjI5YjA0NTc3ZGIyNjYxYTg0OTIyODcyMjE1YWU4ZmU2In0%3D; aliyungf_tc=AQAAAMxcjjyOAwUAMiGP2yBsC84AXsi5; souid=wKgBEVyRpWWqtFz3VZjvAg==; acw_tc=2f624a7715528942728653281e766b1607e52efaa4ff1c1068e9f7990b057c");
		Map<String, String> map = new HashMap<>();
		map.put("stoptype", "0");
		map.put("stopid", "7.");
		map.put("sid", "6934748cd9637645b5b41fd08bba5690");
		String data = HttpUtil.post(url, map, headers);
//		String params = "stoptype=0&stopid=7.&sid=6934748cd9637645b5b41fd08bba5690";
//		String data = HttpUtil.post(url + "?" + params, new HashMap<String, String>(), headers);
//		String matcher = "var data = ";
//		data = data.substring(data.indexOf("var data = ")+matcher.length());
//		data = data.substring(0,data.indexOf(";"));
		
		System.out.println(data);
	}
	
	private static void getBusAlias() {
		String url = "https://shanghaicity.openservice.kankanews.com/public/bus/get";
		Header[] headers = new Header[5];
//		headers[0] = new BasicHeader("Host", "shanghaicity.openservice.kankanews.com");
//		headers[1] = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		headers[2] = new BasicHeader("Cookie", "_ga=GA1.2.825472178.1552894274; acw_tc=2f624a7715528942728653281e766b1607e52efaa4ff1c1068e9f7990b057c");
		headers[3] = new BasicHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16C101 MicroMessenger/6.7.1 NetType/WIFI Language/zh_CN");
//		headers[4] = new BasicHeader("Accept-Language", "zh-cn");
		Map<String, String> map = new HashMap<>();
		map.put("idnum", "933路");
		String data = HttpUtil.post(url, map, headers);
//		String matcher = "var data = ";
//		data = data.substring(data.indexOf("var data = ")+matcher.length());
//		data = data.substring(0,data.indexOf(";"));
		
		System.out.println(data);
	}

	private static void getBusName() {
		String url = "https://shanghaicity.openservice.kankanews.com/public/bus";
		Header[] headers = new Header[5];
//		headers[0] = new BasicHeader("Host", "shanghaicity.openservice.kankanews.com");
//		headers[1] = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		headers[2] = new BasicHeader("Cookie", "_ga=GA1.2.825472178.1552894274; acw_tc=2f624a7715528942728653281e766b1607e52efaa4ff1c1068e9f7990b057c");
		headers[3] = new BasicHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16C101 MicroMessenger/6.7.1 NetType/WIFI Language/zh_CN");
//		headers[4] = new BasicHeader("Accept-Language", "zh-cn");
		String data = HttpUtil.get(url, headers);
		String matcher = "var data = ";
		data = data.substring(data.indexOf("var data = ")+matcher.length());
		data = data.substring(0,data.indexOf(";"));
		
		System.out.println(data);
		JSONArray array = JSONArray.parseArray(data);
		System.out.println(array.toJSONString());
	}
}
