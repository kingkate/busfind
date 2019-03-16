package com.kingkate.busfind.mapper;

import java.util.List;

import com.kingkate.busfind.bean.BusStopBean;

public interface BusStopBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusStopBean record);

    int insertSelective(BusStopBean record);

    BusStopBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusStopBean record);

    int updateByPrimaryKey(BusStopBean record);

	BusStopBean selectStop(BusStopBean stopBean);

	List<BusStopBean> selectBusStop(BusStopBean busStopBean);
}