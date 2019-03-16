package com.kingkate.busfind.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kingkate.busfind.bean.BusStopBean;
import com.kingkate.busfind.mapper.BusStopBeanMapper;
import com.kingkate.busfind.service.BusStopService;

@Service
public class BusStopServiceImpl implements BusStopService {

	@Autowired
	private BusStopBeanMapper busStopBeanMapper;
	
	@Override
	public List<BusStopBean> getBusStopList(BusStopBean busStopBean) {
		List<BusStopBean> list = busStopBeanMapper.selectBusStop(busStopBean);
		System.out.println(JSON.toJSONString(list));
		return list;
	}

}
