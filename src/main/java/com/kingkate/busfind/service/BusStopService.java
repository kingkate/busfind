package com.kingkate.busfind.service;

import java.util.List;

import com.kingkate.busfind.bean.BusStopBean;

public interface BusStopService {

	List<BusStopBean> getBusStopList(BusStopBean busStopBean);

}
