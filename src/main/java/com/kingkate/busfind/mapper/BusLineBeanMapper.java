package com.kingkate.busfind.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kingkate.busfind.bean.BusLineBean;

//@Mapper
public interface BusLineBeanMapper {
    int deleteByPrimaryKey(Integer id);

	int insert(BusLineBean record);

	int insertSelective(BusLineBean record);

	BusLineBean selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(BusLineBean record);

	int updateByPrimaryKey(BusLineBean record);
	
	BusLineBean selectByBusName(@Param("busName")String busName, @Param("province")String province);

	List<BusLineBean> selectAll(String province);

	List<BusLineBean> selectBusLine(BusLineBean busLineBean);

}