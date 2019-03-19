package com.kingkate.busfind.query.province;

import java.util.List;

import com.kingkate.busfind.bean.BusDirBean;
import com.kingkate.busfind.bean.BusLineBean;
import com.kingkate.busfind.bean.BusStopBean;
import com.kingkate.busfind.dto.request.QueryInTimeBusReq;
import com.kingkate.busfind.dto.response.InTimeBusInfoRes;
import com.kingkate.busfind.query.BaseQuery;

public class ShangHaiQuery implements BaseQuery {

	@Override
	public InTimeBusInfoRes query(QueryInTimeBusReq queryInTimeBusReq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusLineBean> queryBusLine() {
		
		return null;
	}

	@Override
	public List<BusDirBean> queryBusDir(QueryInTimeBusReq queryInTimeBusReq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusStopBean> queryBusStop(QueryInTimeBusReq queryInTimeBusReq) {
		// TODO Auto-generated method stub
		return null;
	}

}
