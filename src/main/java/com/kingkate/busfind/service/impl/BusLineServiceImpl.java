package com.kingkate.busfind.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingkate.busfind.bean.BusLineBean;
import com.kingkate.busfind.mapper.BusLineBeanMapper;
import com.kingkate.busfind.service.BusLineService;

@Service
public class BusLineServiceImpl implements BusLineService {

	@Autowired
	private BusLineBeanMapper busLineBeanMapper;
	@Override
	public List<BusLineBean> getBusLineList(BusLineBean busLineBean) {
		return busLineBeanMapper.selectBusLine(busLineBean);
	}

}
