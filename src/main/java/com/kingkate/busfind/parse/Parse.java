package com.kingkate.busfind.parse;

import java.util.List;

import com.kingkate.busfind.bean.BusDirBean;
import com.kingkate.busfind.bean.BusLineBean;
import com.kingkate.busfind.bean.BusStopBean;
import com.kingkate.busfind.dto.response.InTimeBusInfoRes;

public interface Parse {
	public InTimeBusInfoRes doParse(String text);
	public List<BusLineBean> parseBusLine(String text);
	public List<BusDirBean> parseBusDir(String text);
	public List<BusStopBean> parseBusStop(String result);
}
