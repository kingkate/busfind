package com.kingkate.busfind.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kingkate.busfind.bean.BusDirBean;
import com.kingkate.busfind.dto.request.BusDirReq;

public interface BusDirBeanMapper {

	int deleteByPrimaryKey(Integer id);

    int insert(BusDirBean record);

    int insertSelective(BusDirBean record);

    BusDirBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusDirBean record);

    int updateByPrimaryKey(BusDirBean record);

	BusDirBean selectByBusLineId(@Param("busLineId") Integer busLineId, @Param("value")String value);

	List<BusDirBean> selectAll();

	List<BusDirBean> selectByBusLine(Integer id);

	List<BusDirBean> selectBusDir(BusDirBean busDirBean);
}