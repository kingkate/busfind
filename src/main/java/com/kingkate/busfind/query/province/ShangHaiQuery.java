package com.kingkate.busfind.query.province;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import com.alibaba.fastjson.JSON;
import com.kingkate.busfind.bean.BusDirBean;
import com.kingkate.busfind.bean.BusLineBean;
import com.kingkate.busfind.bean.BusStopBean;
import com.kingkate.busfind.bean.InTimeBus;
import com.kingkate.busfind.constants.BUS_STOP_STATUS;
import com.kingkate.busfind.dto.request.QueryInTimeBusReq;
import com.kingkate.busfind.dto.response.InTimeBusInfoRes;
import com.kingkate.busfind.parse.province.ParseShangHai;
import com.kingkate.busfind.query.BaseQuery;
import com.kingkate.busfind.util.HttpUtil;

public class ShangHaiQuery implements BaseQuery {
	
	private final static String BASE_URL = "https://shanghaicity.openservice.kankanews.com";
	private final static String QUERY_IN_TIME_BUS_URL = "/public/bus/Getstop";
	private final static String BUS_LINE_NAME_URL = "/public/bus";
	private final static String BUS_LINE_ALIAS_URL = "/public/bus/get";
	private final static String QUERY_BUS_DIR_URL="/public/bus/mes/sid";
	private final static String BUS_LINE_ID_QUERY="idnum";
	

	@Override
	public InTimeBusInfoRes query(QueryInTimeBusReq queryInTimeBusReq,List<BusStopBean> list) {
		InTimeBusInfoRes inTimeBusInfoRes = new InTimeBusInfoRes();
		if(null == list || list.isEmpty()) {
			return inTimeBusInfoRes;
		}
		String stopId = queryInTimeBusReq.getSelBStop();
		String data = "";
		ParseShangHai parser = new ParseShangHai();
		List<InTimeBus> inTimeBusList = new ArrayList<>();
		for(BusStopBean stopBean : list) {
			InTimeBus inTimeBus = new InTimeBus();
			
			queryBusAlias(stopBean.getBusLineName());
			queryInTimeBusReq.setSelBStop(stopBean.getStopId());
			data = query(queryInTimeBusReq);
			try {
				inTimeBus = parser.parseInTimeBus(data);
			} catch (Exception e) {
				Map<String, String> map = new HashMap<>();
				map.put("stoptype", queryInTimeBusReq.getSelBDir());
				map.put("stopid", queryInTimeBusReq.getSelBStop());
				map.put("sid", queryInTimeBusReq.getSelBLine());
				System.out.println(map + "\r\n" + data);
			}
			if(stopId.equals(stopBean.getStopId())) {
				String header = stopBean.getName()  + "	最近一辆车距离此还有 " + inTimeBus.getNearestBusStopNum() + " 站， " + inTimeBus.getNearestBusDistance() + " 公里，预计到站时间 " + inTimeBus.getNearestBusTime() + " 分钟";
				inTimeBusInfoRes.setHeader(header);
				inTimeBusInfoRes.setDir(stopBean.getBusDirName());
				inTimeBusInfoRes.setLine(stopBean.getBusLineName());
			}
			inTimeBus.setBusStopId(stopBean.getId());
			inTimeBus.setBusStopName(stopBean.getName());
			inTimeBus.setSelBDir(stopBean.getBusDirName());
			inTimeBus.setSelBLine(stopBean.getBusLineName());
			inTimeBus.setBusStopStatus(determineStopStatus(inTimeBusList,inTimeBus));
			inTimeBusList.add(inTimeBus);
		}
		inTimeBusInfoRes.setInTimeBusList(inTimeBusList);
		return inTimeBusInfoRes;
	}

	
	
	private String determineStopStatus(List<InTimeBus> inTimeBusList, InTimeBus inTimeBus) {
		if(null == inTimeBus.getNearestBusTime() || null == inTimeBus.getNearestBusStopNum()) {
			return BUS_STOP_STATUS.BUSW.getValue();
		}
		
		if(inTimeBus.getNearestBusTime() <= 0.5) {
			return BUS_STOP_STATUS.BUSS.getValue();
		}
		if(inTimeBus.getNearestBusTime() > 0.5) {
			if(inTimeBusList.size() >= inTimeBus.getNearestBusStopNum()) {
				inTimeBusList.get(inTimeBusList.size() - inTimeBus.getNearestBusStopNum()).setBusStopStatus(BUS_STOP_STATUS.BUSC.getValue());
			}
			return BUS_STOP_STATUS.BUSW.getValue();
		}
		
		return BUS_STOP_STATUS.BUSW.getValue();
	}



	private String query(QueryInTimeBusReq queryInTimeBusReq) {
		String url = BASE_URL + QUERY_IN_TIME_BUS_URL;
		Map<String, String> map = new HashMap<>();
		map.put("stoptype", queryInTimeBusReq.getSelBDir());
		map.put("stopid", queryInTimeBusReq.getSelBStop());
		map.put("sid", queryInTimeBusReq.getSelBLine());
		String data = HttpUtil.post(url, map, getPostHeader());
		return data;
	}



	@Override
	public List<BusLineBean> queryBusLine() {
		List<BusLineBean> busLineBeanList = new ArrayList<BusLineBean>();
		String url = BASE_URL + BUS_LINE_NAME_URL;
		Header[] headers = getHeader();
		String data = HttpUtil.get(url, headers);
		ParseShangHai parser = new ParseShangHai();
		parser.parseBusLineName(data,busLineBeanList);
		for(BusLineBean lineBean : busLineBeanList ) {
			data = queryBusAlias(lineBean.getBusName());
			parser.parseBusLine(data,lineBean);
			System.out.println(url + "\r\n" + JSON.toJSONString(lineBean));
		}
		System.out.println(url + "\r\n" + JSON.toJSONString(busLineBeanList));
		return busLineBeanList;
	}



	private String queryBusAlias(String busName) {
		String busAliasUrl = BASE_URL + BUS_LINE_ALIAS_URL;
		Map<String, String> map = new HashMap<>();
		map.put(BUS_LINE_ID_QUERY, busName);
		String data = HttpUtil.post(busAliasUrl, map, getHeader());
		return data;
	}

	private Header[] getPostHeader() {
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
		return headers;
	}

	private Header[] getHeader() {
		Header[] headers = new Header[5];
//		headers[0] = new BasicHeader("Host", "shanghaicity.openservice.kankanews.com");
//		headers[1] = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		headers[2] = new BasicHeader("Cookie", "_ga=GA1.2.825472178.1552894274; acw_tc=2f624a7715528942728653281e766b1607e52efaa4ff1c1068e9f7990b057c");
		headers[3] = new BasicHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16C101 MicroMessenger/6.7.1 NetType/WIFI Language/zh_CN");
//		headers[4] = new BasicHeader("Accept-Language", "zh-cn");
		return headers;
	}

	@Override
	public List<BusDirBean> queryBusDir(QueryInTimeBusReq queryInTimeBusReq) {
		List<BusDirBean> busDirBeanList = new ArrayList<>();
		String url = BASE_URL + QUERY_BUS_DIR_URL + "/" + queryInTimeBusReq.getSelBLine();
		Header[] headers = getHeader();
		String data = HttpUtil.get(url, headers);
		ParseShangHai parser = new ParseShangHai();
		try {
			busDirBeanList = parser.parseBusDir(data);
		} catch (Exception e) {
			System.out.println(url + "\r\n" + data);
		}
		System.out.println(url + "\r\n" + JSON.toJSONString(busDirBeanList));
		return busDirBeanList;
	}

	@Override
	public List<BusStopBean> queryBusStop(QueryInTimeBusReq queryInTimeBusReq) {
		List<BusStopBean> busStopBeanList = new ArrayList<>();
		String url = BASE_URL + QUERY_BUS_DIR_URL + "/" + queryInTimeBusReq.getSelBLine() + "?stoptype=" +queryInTimeBusReq.getSelBDir();
		Header[] headers = getHeader();
		String data = HttpUtil.get(url, headers);
		ParseShangHai parser = new ParseShangHai();
		try {
			busStopBeanList = parser.parseBusStop(data);
		} catch (Exception e) {
			System.out.println(url + "\r\n" + data);
			e.printStackTrace();
		}
		System.out.println(url + "\r\n" + JSON.toJSONString(busStopBeanList));
		return busStopBeanList;
	}

}
