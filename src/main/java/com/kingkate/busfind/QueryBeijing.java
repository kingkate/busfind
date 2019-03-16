package com.kingkate.busfind;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kingkate.busfind.dto.request.QueryInTimeBusReq;
import com.kingkate.busfind.util.HttpUtil;
import com.kingkate.busfind.util.StringUtils;

public class QueryBeijing {
	public static void main(String[] args) {
		QueryInTimeBusReq req = new QueryInTimeBusReq();
		req.setAct("busTime");
		req.setSelBDir("4714638781653057901");
		req.setSelBLine("664");
		req.setSelBStop("9");
		System.out.println(JSON.toJSONString(req));
		String url = "http://www.bjbus.com/home/ajax_rtbus_data.php?act=busTime&selBLine="
				+ req.getSelBLine() + "&selBDir="
						+ req.getSelBDir() + "&selBStop=" 
							+	req.getSelBStop();
		String data = HttpUtil.get(url, null);
		data = StringUtils.unicodeToString(data);
		data.replaceAll("\\\\", "");
		System.out.println(data);
		JSONObject object = (JSONObject) JSONObject.parse(data);
		String html = (String) object.get("html");
		Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByTag("article");
		System.out.println(elements.get(0).text());
		Element ccStopId = doc.getElementById("cc_stop");
		Elements li = ccStopId.getElementsByTag("li");
		for(int i = 0;i<li.size();i++) {
			Element element = li.get(i);
			String id = element.getElementsByTag("div").get(0).id();
			
			String busStatus = element.getElementsByTag("i").get(0).attr("class");
			
			if(!id.contains("m")) {
				System.out.print(id);
				String busStopName = element.getElementsByTag("span").get(0).attr("title");
				System.out.print(" "+busStopName);
			}
			if(null == busStatus) {
				continue;
			}else if("busc".equals(busStatus)) {
				System.out.print(" 在途");
			}else if("buss".equals(busStatus)) {
				System.out.print(" 到站");
			}
			if(id.contains("m")) {
				System.out.print("\r\n");
			}
		}
	}
}
