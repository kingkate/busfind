package com.kingkate.busfind.query.province;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kingkate.busfind.bean.BusDirBean;
import com.kingkate.busfind.bean.BusLineBean;
import com.kingkate.busfind.bean.BusStopBean;
import com.kingkate.busfind.dto.request.QueryInTimeBusReq;
import com.kingkate.busfind.dto.response.InTimeBusInfoRes;
import com.kingkate.busfind.parse.Parse;
import com.kingkate.busfind.parse.province.ParseBeijing;
import com.kingkate.busfind.query.BaseQuery;
import com.kingkate.busfind.util.HttpUtil;
import com.kingkate.busfind.util.StringUtils;

public class BeijingQuery implements BaseQuery {

	private final static String BASE_URL = "http://www.bjbus.com";
	private final static String QUERY_IN_TIME_BUS_URL = "/home/ajax_rtbus_data.php";
	private final static String BUS_LINE_URL = "/home/fun_rtbus.php?uSec=00000160&uSub=00000162";
	private final static String QUERY_IN_TIME_BUS_ACT="busTime";
	private final static String QUERY_BUS_DIR_ACT="getLineDirOption";
	private final static String QUERY_BUS_STOP_ACT="getDirStationOption";
	
	
	public List<BusLineBean> queryBusLine() {
		String url = BASE_URL + BUS_LINE_URL;
		String result = HttpUtil.get(url, null);
		try {
			System.out.println(new String(result.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Parse parser = new ParseBeijing();
		List<BusLineBean> busLineList = parser.parseBusLine(result);
		
		System.out.println(JSON.toJSONString(busLineList));
		return busLineList;
	}
	
	
	
	@Override
	public InTimeBusInfoRes query(QueryInTimeBusReq queryInTimeBusReq) {
		InTimeBusInfoRes inTimeBusInfoRes = new InTimeBusInfoRes();
		String data = queryData(queryInTimeBusReq);
		if(null == data || "".endsWith(data)) {
			System.out.println("查询数据失败！！");
			return inTimeBusInfoRes; 
		}
		Parse parser = new ParseBeijing();
		inTimeBusInfoRes = parser.doParse(data);
		return inTimeBusInfoRes;
	}
	private String queryData(QueryInTimeBusReq queryInTimeBusReq) {
		String url = BASE_URL + QUERY_IN_TIME_BUS_URL + "?act=" + QUERY_IN_TIME_BUS_ACT +"&selBLine=" 
				+ queryInTimeBusReq.getSelBLine() + "&selBDir=" + queryInTimeBusReq.getSelBDir() 
				+ "&selBStop=" + queryInTimeBusReq.getSelBStop();
		System.out.println(url);
		String result = HttpUtil.get(url, null);
		result = StringUtils.unicodeToString(result);
		result.replaceAll("\\\\", "");
		System.out.println(result);
		if(null == result || "".endsWith(result)) {
			return null;
		}
		JSONObject object = (JSONObject) JSONObject.parse(result);
		String html = (String) object.get("html");
		return html;
	}



	@Override
	public List<BusDirBean> queryBusDir(QueryInTimeBusReq queryInTimeBusReq) {
		List<BusDirBean> busDirBeanList = new ArrayList<>();
		String url = BASE_URL + QUERY_IN_TIME_BUS_URL + "?act=" + QUERY_BUS_DIR_ACT +"&selBLine=" 
				+ queryInTimeBusReq.getSelBLine();
		String result = HttpUtil.get(url, null);
		Parse parser = new ParseBeijing();
		busDirBeanList = parser.parseBusDir(result);
		return busDirBeanList;
	}



	@Override
	public List<BusStopBean> queryBusStop(QueryInTimeBusReq queryInTimeBusReq) {
		List<BusStopBean> busStopBeanList = new ArrayList<>();
		String url = BASE_URL + QUERY_IN_TIME_BUS_URL + "?act=" + QUERY_BUS_STOP_ACT +"&selBLine=" 
				+ queryInTimeBusReq.getSelBLine() + "&selBDir=" + queryInTimeBusReq.getSelBDir();
		String result = HttpUtil.get(url, null);
		Parse parser = new ParseBeijing();
		busStopBeanList = parser.parseBusStop(result);
		return busStopBeanList;
	}

}
