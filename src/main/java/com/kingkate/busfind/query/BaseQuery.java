package com.kingkate.busfind.query;

import java.util.List;

import com.kingkate.busfind.bean.BusDirBean;
import com.kingkate.busfind.bean.BusLineBean;
import com.kingkate.busfind.bean.BusStopBean;
import com.kingkate.busfind.dto.request.QueryInTimeBusReq;
import com.kingkate.busfind.dto.response.InTimeBusInfoRes;

public interface BaseQuery {
	
	public InTimeBusInfoRes query(QueryInTimeBusReq queryInTimeBusReq);
	public List<BusLineBean> queryBusLine();
	public List<BusDirBean> queryBusDir(QueryInTimeBusReq queryInTimeBusReq);
	public List<BusStopBean> queryBusStop(QueryInTimeBusReq queryInTimeBusReq);
}
