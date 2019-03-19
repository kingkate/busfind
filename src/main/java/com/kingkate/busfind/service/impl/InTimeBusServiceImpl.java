package com.kingkate.busfind.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kingkate.busfind.bean.BusDirBean;
import com.kingkate.busfind.bean.BusLineBean;
import com.kingkate.busfind.bean.BusStopBean;
import com.kingkate.busfind.constants.BUS_PROVINCE_CLASS;
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
		try {
			if(null == queryInTimeBusReq) {
				return inTimeBusInfoRes;
			}
			BaseQuery query = null;
			if(!assembleInTimeBusReq(queryInTimeBusReq)) {
				return inTimeBusInfoRes;
			}
			//默认查询北京
			if(null == queryInTimeBusReq.getProvince()) {
				query = new BeijingQuery();
			}else {
				query = (BaseQuery) BUS_PROVINCE_CLASS.getQueryClassByProvince(queryInTimeBusReq.getProvince()).newInstance();
			}
			inTimeBusInfoRes = query.query(queryInTimeBusReq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inTimeBusInfoRes;
	}

	private boolean assembleInTimeBusReq(QueryInTimeBusReq queryInTimeBusReq) {
		BusLineBean busLineBean = busLineBeanMapper.selectByPrimaryKey(queryInTimeBusReq.getBusLineId());
		if(null == busLineBean) {
			return false;
		}
		BusDirBean busDirBean = busDirBeanMapper.selectByPrimaryKey(queryInTimeBusReq.getBusDirId());
		if(null == busDirBean) {
			return false;
		}
		BusStopBean busStopBean = busStopBeanMapper.selectByPrimaryKey(queryInTimeBusReq.getBusStopId());
		if(null == busStopBean || null == busStopBean.getStopId()) {
			return false;
		}
		queryInTimeBusReq.setSelBLine(busLineBean.getBusAlias());
		queryInTimeBusReq.setSelBDir(busDirBean.getValue());
		queryInTimeBusReq.setSelBStop(busStopBean.getStopId().toString());
		return true;
	}

	@Override
	public void saveBusLine(QueryInTimeBusReq queryInTimeBusReq) {
		BaseQuery query = null;
		//默认查询北京
		String province = null == queryInTimeBusReq.getProvince() ? "北京":queryInTimeBusReq.getProvince();
		try {
			
			query = (BaseQuery) BUS_PROVINCE_CLASS.getQueryClassByProvince(province).newInstance();
			if(null == query) {
				System.out.println("请求参数不正确！！");
				return;
			}
			List<BusLineBean> busLineList = query.queryBusLine();
			if(null != busLineList) {
				saveBusLine(province, busLineList);
				busLineList = busLineBeanMapper.selectAll(province);
				saveBusDir(queryInTimeBusReq,query,busLineList);
				saveBusStop(queryInTimeBusReq,query,busLineList);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private void saveBusStop(QueryInTimeBusReq queryInTimeBusReq, BaseQuery query, List<BusLineBean> busLineList) {
		if(null == busLineList || busLineList.isEmpty()) {
			System.out.println("获取线路信息失败！！，或者数据为空！！");
			return;
		}
		for(BusLineBean lineBean : busLineList) {
			List<BusDirBean> busDirList = busDirBeanMapper.selectByBusLine(lineBean.getId());
			if(null == busDirList || busDirList.isEmpty()) {
				continue;
			}
			for(BusDirBean busDirBean : busDirList) {
				queryInTimeBusReq.setSelBDir(busDirBean.getValue());
				List<BusStopBean> busStopBeanList = query.queryBusStop(queryInTimeBusReq);
				System.out.println(JSON.toJSONString(busStopBeanList));
				if(null == busStopBeanList || busStopBeanList.isEmpty()) {
					continue;
				}
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

	private void saveBusDir(QueryInTimeBusReq queryInTimeBusReq, BaseQuery query, List<BusLineBean> busLineList) {
		if(null == busLineList || busLineList.isEmpty()) {
			System.out.println("获取线路信息失败！！，或者数据为空！！");
			return;
		}
		for(BusLineBean lineBean : busLineList) {
			queryInTimeBusReq.setSelBLine(lineBean.getBusAlias());
			List<BusDirBean> busDirList = query.queryBusDir(queryInTimeBusReq);
			if(null != busDirList) {
				for(BusDirBean busDirBean : busDirList) {
					BusDirBean dirBean = busDirBeanMapper.selectByBusLineId(lineBean.getId(),busDirBean.getValue());
					if(null == dirBean || null == dirBean.getBusLineId()) {
						busDirBean.setBusLineId(lineBean.getId());
						busDirBean.setBusLineName(lineBean.getBusName());
						busDirBeanMapper.insert(busDirBean);
					}
				}
			}
		}
	}

	private void saveBusLine(String province, List<BusLineBean> busLineList) {
		for(BusLineBean lineBean : busLineList) {
			BusLineBean busLineBean = busLineBeanMapper.selectByBusName(province,lineBean.getBusName());
			if(null == busLineBean || null == busLineBean.getBusName()) {				
				busLineBeanMapper.insert(lineBean);
			}
		}
	}
	

}
