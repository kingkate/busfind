package com.kingkate.busfind.parse.province;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.kingkate.busfind.bean.BusDirBean;
import com.kingkate.busfind.bean.BusLineBean;
import com.kingkate.busfind.bean.BusStopBean;
import com.kingkate.busfind.bean.InTimeBus;
import com.kingkate.busfind.constants.BUS_STOP_STATUS;
import com.kingkate.busfind.dto.response.InTimeBusInfoRes;
import com.kingkate.busfind.parse.Parse;

public class ParseBeijing implements Parse {

	@Override
	public InTimeBusInfoRes doParse(String html) {
		InTimeBusInfoRes res = new InTimeBusInfoRes();
		List<String> busStopList = new ArrayList<>();
		parseHeader(res,html);
		busStopList = parseBody(html);
		System.out.println(JSON.toJSONString(busStopList));
		parseBody(res, busStopList);
		return res;
	}

	private void parseBody(InTimeBusInfoRes res, List<String> busStopList) {
		List<InTimeBus> inTimeBusList = new ArrayList<>();
		for(String busStop : busStopList) {
			InTimeBus bus = new InTimeBus();
			bus.setSelBDir(res.getDir());
			bus.setSelBLine(res.getLine());
			String busArray[] = busStop.split(" ");
			bus.setBusStopId(Integer.valueOf(busArray[0]));
			bus.setBusStopName(busArray[1]);
			if(busArray.length > 2) {
				bus.setBusStopStatus(busArray[2]);
			}else {
				bus.setBusStopStatus("-1");
			}
			inTimeBusList.add(bus);
		}
		res.setInTimeBusList(inTimeBusList);
	}

	private List<String> parseBody(String html) {
		List<String> busStopList = new ArrayList<>();
		Document doc = Jsoup.parse(html);
		Element ccStopId = doc.getElementById("cc_stop");
		Elements li = ccStopId.getElementsByTag("li");
		String data = "";
		for(int i = 0;i<li.size();i++) {
			Element element = li.get(i);
			String id = element.getElementsByTag("div").get(0).id();
			String busStatus = element.getElementsByTag("i").get(0).attr("class");
			if(!id.contains("m")) {
				data = data + id;
				String busStopName = element.getElementsByTag("span").get(0).attr("title");
				data = data + " " + busStopName;
			}
			if(null == busStatus) {
				continue;
			}else if("busc".equals(busStatus)) {
				data = data + " " + BUS_STOP_STATUS.BUSC.getValue();
			}else if("buss".equals(busStatus)) {
				data = data + " " + BUS_STOP_STATUS.BUSS.getValue();
			}
			if(id.contains("m")) {
				busStopList.add(data);
				data = "";
			}
		}
		return busStopList;
	}

	private void parseHeader(InTimeBusInfoRes res, String html) {
		Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByTag("article");
		res.setHeader(elements.get(0).text());
		String line = doc.getElementsByTag("h3").get(0).text();
		res.setLine(line);
		String dir = doc.getElementsByTag("h2").get(0).text();
		res.setLine(dir);
		
	}

	@Override
	public List<BusLineBean> parseBusLine(String text) {
		List<BusLineBean> busLineList = new ArrayList<>();
		Document doc = Jsoup.parse(text);
		Element select = doc.getElementById("selBLine");
		Elements a = select.getElementsByTag("a");
		for(int i=1;i<a.size();i++) {
			Element option = a.get(i);
			BusLineBean line = new BusLineBean();
			line.setProvince("北京");
			line.setBusName(option.text());
			line.setBusAlias(option.text());
			line.setcT(Calendar.getInstance().getTime());
			line.setcU("");
			line.setuU("");
			line.setIsDeleted(false);
			busLineList.add(line);
		}
		return busLineList;
	}

	@Override
	public List<BusDirBean> parseBusDir(String text) {
		List<BusDirBean> busDirBeanList = new ArrayList<>();
		Document doc = Jsoup.parse(text);
		Elements a = doc.getElementsByTag("option");
		for(int i=1;i<a.size();i++) {
			Element option = a.get(i);
			BusDirBean dir = new BusDirBean();
			dir.setName(option.text());
			dir.setValue(option.attr("value"));
			dir.setcT(Calendar.getInstance().getTime());
			dir.setcU("");
			dir.setuU("");
			dir.setIsDeleted(false);
			busDirBeanList.add(dir);
		}
		return busDirBeanList;
	}

	@Override
	public List<BusStopBean> parseBusStop(String text) {
		List<BusStopBean> busStopBeanList = new ArrayList<>();
		Document doc = Jsoup.parse(text);
		Elements a = doc.getElementsByTag("option");
		for(int i=1;i<a.size();i++) {
			Element option = a.get(i);
			BusStopBean stopBean = new BusStopBean();
			stopBean.setName(option.text());
			stopBean.setStopId(Integer.valueOf(option.attr("value")));
			stopBean.setcT(Calendar.getInstance().getTime());
			stopBean.setcU("");
			stopBean.setuU("");
			stopBean.setIsDeleted(false);
			busStopBeanList.add(stopBean);
		}
		return busStopBeanList;
	}

}
