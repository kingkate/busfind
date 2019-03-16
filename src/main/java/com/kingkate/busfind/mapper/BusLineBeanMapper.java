package com.kingkate.busfind.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kingkate.busfind.bean.BusLineBean;

//@Mapper
public interface BusLineBeanMapper {
    int deleteByPrimaryKey(Integer id);

	int insert(BusLineBean record);

	int insertSelective(BusLineBean record);

	BusLineBean selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(BusLineBean record);

	int updateByPrimaryKey(BusLineBean record);
	
	BusLineBean selectByBusName(String busName);

	List<BusLineBean> selectAll();

	List<BusLineBean> selectBusLine(BusLineBean busLineBean);

}