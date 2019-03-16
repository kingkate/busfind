package com.kingkate.busfind.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kingkate.busfind.bean.BusDirBean;
import com.kingkate.busfind.bean.BusLineBean;
import com.kingkate.busfind.bean.BusStopBean;
import com.kingkate.busfind.dto.request.QueryInTimeBusReq;
import com.kingkate.busfind.dto.response.InTimeBusInfoRes;
import com.kingkate.busfind.mapper.BusDirBeanMapper;
import com.kingkate.busfind.mapper.BusLineBeanMapper;
import com.kingkate.busfind.mapper.BusStopBeanMapper;
import com.kingkate.busfind.query.BaseQuery;
import com.kingkate.busfind.query.province.BeijingQuery;
import com.kingkate.busfind.service.InTimeBusService;

@Service
public class InTimeBusServiceImpl implements InTimeBusService {

	@Autowired
	private BusLineBeanMapper busLineBeanMapper;
	
	@Autowired
	private BusDirBeanMapper busDirBeanMapper;
	
	@Autowired
	private BusStopBeanMapper busStopBeanMapper;

	@Override
	public InTimeBusInfoRes getInTimeBusInfo(QueryInTimeBusReq queryInTimeBusReq) {
		InTimeBusInfoRes inTimeBusInfoRes = new InTimeBusInfoRes();
		if(null == queryInTimeBusReq) {
			return inTimeBusInfoRes;
		}
		BaseQuery query = null;
		if(!assembleInTimeBusReq(queryInTimeBusReq)) {
			return inTimeBusInfoRes;
		}
		//默认查询北京
		if(null == queryInTimeBusReq.getProvince() || queryInTimeBusReq.getProvince().equals("北京")) {
			query = new BeijingQuery();
			inTimeBusInfoRes = query.query(queryInTimeBusReq);
		}
		return inTimeBusInfoRes;
	}

	private boolean assembleInTimeBusReq(QueryInTimeBusReq queryInTimeBusReq) {
		BusDirBean busDirBean = busDirBeanMapper.selectByPrimaryKey(queryInTimeBusReq.getBusDirId());
		if(null == busDirBean) {
			return false;
		}
		BusStopBean busStopBean = busStopBeanMapper.selectByPrimaryKey(queryInTimeBusReq.getBusStopId());
		if(null == busStopBean || null == busStopBean.getStopId()) {
			return false;
		}
		queryInTimeBusReq.setSelBLine(busDirBean.getBusLineName());
		queryInTimeBusReq.setSelBDir(busDirBean.getValue());
		queryInTimeBusReq.setSelBStop(busStopBean.getStopId().toString());
		return true;
	}

	@Override
	public void saveBusLine(QueryInTimeBusReq queryInTimeBusReq) {
		BaseQuery query = null;
		//默认查询北京
		if(null == queryInTimeBusReq.getProvince() || queryInTimeBusReq.getProvince().equals("北京")) {
			query = new BeijingQuery();
//			List<BusLineBean> busLineList = query.queryBusLine();
			List<BusLineBean> busLineList = busLineBeanMapper.selectAll();
//			List<BusDirBean> busDirList = busDirBeanMapper.selectAll();
			if(null != busLineList) {
				for(BusLineBean lineBean : busLineList) {
//					BusLineBean busLineBean = busLineBeanMapper.selectByBusName(lineBean.getBusName());
//					if(null == busLineBean || null == busLineBean.getBusName()) {					
//						busLineBeanMapper.insert(lineBean);
//					}
					queryInTimeBusReq.setSelBLine(lineBean.getBusName());
//					queryInTimeBusReq.setAct("getLineDirOption");
//					List<BusDirBean> busDirList = query.queryBusDir(queryInTimeBusReq);
					List<BusDirBean> busDirList = busDirBeanMapper.selectByBusLine(lineBean.getId());
					if(null != busDirList) {
						for(BusDirBean busDirBean : busDirList) {
//							BusDirBean dirBean = busDirBeanMapper.selectByBusLineId(lineBean.getId(),busDirBean.getValue());
//							if(null == dirBean || null == dirBean.getBusLineId()) {
//								busDirBean.setBusLineId(lineBean.getId());
//								busDirBean.setBusLineName(lineBean.getBusName());
//								busDirBeanMapper.insert(busDirBean);
//							}
							queryInTimeBusReq.setAct("getDirStationOption");
							queryInTimeBusReq.setSelBDir(busDirBean.getValue());
							List<BusStopBean> busStopBeanList = query.queryBusStop(queryInTimeBusReq);
							System.out.println(JSON.toJSONString(busStopBeanList));
							if(null != busStopBeanList) {
								for(BusStopBean stopBean : busStopBeanList) {
									stopBean.setBusDirId(busDirBean.getId());
									stopBean.setBusDirName(busDirBean.getName());
									stopBean.setBusLineName(lineBean.getBusName());
									stopBean.setBusLineId(lineBean.getId());
									BusStopBean busStopBean = busStopBeanMapper.selectStop(stopBean);
									if(null == busStopBean) {
										busStopBeanMapper.insert(stopBean);
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
