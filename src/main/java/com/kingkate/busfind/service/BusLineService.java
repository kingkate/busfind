package com.kingkate.busfind.service;

import com.kingkate.busfind.bean.BusLineBean;

import java.util.List;

public interface BusLineService {
	public List<BusLineBean> getBusLineList(BusLineBean busLineBean);
}
