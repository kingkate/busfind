package com.kingkate.busfind.parse.province;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingkate.busfind.bean.BusDirBean;
import com.kingkate.busfind.bean.BusLineBean;
import com.kingkate.busfind.bean.BusStopBean;
import com.kingkate.busfind.bean.InTimeBus;
import com.kingkate.busfind.constants.BUS_PROVINCE_CLASS;
import com.kingkate.busfind.dto.response.InTimeBusInfoRes;
import com.kingkate.busfind.parse.Parse;
import com.kingkate.busfind.util.StringUtils;

public class ParseShangHai implements Parse {

	@Override
	public InTimeBusInfoRes doParse(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusLineBean> parseBusLine(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusDirBean> parseBusDir(String data) {
		List<BusDirBean> busDirBeanList = new ArrayList<>();
		if(StringUtils.isEmpty(data)) {
			return busDirBeanList;
		}
		Document doc = Jsoup.parse(data);
		Element busDirectionEle = doc.getElementsByAttributeValue("class", "busDirection").first();
		Elements busDirEleList = busDirectionEle.getElementsByTag("p");
		for(int i =0 ;i< busDirEleList.size();i++) {
			BusDirBean dirBean = new BusDirBean();
			Element ele = busDirEleList.get(i);
			if(ele.getElementsByTag("span").size() <2) {
				continue;
			}
			String startStop = ele.getElementsByTag("span").first().text();
			String endStop = ele.getElementsByTag("span").get(1).text();
			dirBean.setName(startStop + "-" + endStop);
			dirBean.setValue(String.valueOf(i));
			dirBean.setIsDeleted(false);
			dirBean.setcT(Calendar.getInstance().getTime());
			dirBean.setcU("");
			dirBean.setuU("");
			dirBean.setIsDeleted(false);
			busDirBeanList.add(dirBean);
		}
		return busDirBeanList;
	}

	@Override
	public List<BusStopBean> parseBusStop(String result) {
		List<BusStopBean> busStopBeanList = new ArrayList<>();
		if(StringUtils.isEmpty(result)) {
			return busStopBeanList;
		}
		try {
			Document doc = Jsoup.parse(result);
			Elements busStopBors = doc.getElementsByAttributeValue("class", "stationBor");
			for(int i = 0;i < busStopBors.size();i++) {
				BusStopBean stopBean = new BusStopBean();
				Element station = busStopBors.get(i);
				String stopId = station.getElementsByAttributeValue("class", "num").first().text();
				String name = station.getElementsByAttributeValue("class", "name").first().text();
				stopBean.setName(name);
				stopBean.setStopId(stopId);
				stopBean.setIsDeleted(false);
				stopBean.setcT(Calendar.getInstance().getTime());
				stopBean.setProvince(BUS_PROVINCE_CLASS.SHANGHAI.getProvince());
				stopBean.setcU("");
				stopBean.setuU("");
				busStopBeanList.add(stopBean);
			}
		} catch (Exception e) {
			System.out.println(result);
			e.printStackTrace();
		}
		return busStopBeanList;
	}

	public void parseBusLineName(String data, List<BusLineBean> busLineBeanList) {
		if(StringUtils.isEmpty(data)) {
			return;
		}
		String matcher = "var data = ";
		data = data.substring(data.indexOf("var data = ")+matcher.length());
		data = data.substring(0,data.indexOf(";"));
		
		System.out.println(data);
		JSONArray array = JSONArray.parseArray(data);
		System.out.println(array.toJSONString());
		for(int i =0 ;i<array.size();i++) {
			BusLineBean lineBean = new BusLineBean();
			lineBean.setBusName(array.getString(i));
			busLineBeanList.add(lineBean);
		}
	}

	public void parseBusLine(String data, BusLineBean lineBean) {
		if(StringUtils.isEmpty(data)) {
			return;
		}
		try {
			JSONObject obj = JSON.parseObject(data);
			String busAlias = obj.getString("sid");
			if(StringUtils.isEmpty(busAlias)) {
				return;
			}
			lineBean.setBusAlias(busAlias);
			lineBean.setcT(Calendar.getInstance().getTime());
			lineBean.setIsDeleted(false);
			lineBean.setProvince(BUS_PROVINCE_CLASS.SHANGHAI.getProvince());
			lineBean.setcU("");
			lineBean.setuU("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doParse(String data, String targetStopId, String curStopId, InTimeBusInfoRes inTimeBusInfoRes) {
		
	}

	public InTimeBus parseInTimeBus(String data) {
		
		InTimeBus inTimeBus = new InTimeBus();
		if(StringUtils.isEmpty(data)) {
			return inTimeBus;
		}
		if(data.trim().startsWith("{")) {
			return inTimeBus;
		}
		JSONArray array = JSONArray.parseArray(data);
		JSONObject object = array.getJSONObject(0);
		int stopdis = object.getIntValue("stopdis");
		int time = object.getIntValue("time");
		int distance = object.getIntValue("distance");
		inTimeBus.setNearestBusDistance(determineDistince(distance));
		inTimeBus.setNearestBusStopNum(stopdis);
		inTimeBus.setNearestBusTime(determineTime(time));
		return inTimeBus;
	}

	private int determineTime(int time) {
		return (time % 60 == 0 ? (time / 60) : (time / 60 + 1)) ;
	}

	private double determineDistince(int distance) {
		if(distance <= 0) {
			return 0;
		}
		return distance/1000.0;
	}

}
