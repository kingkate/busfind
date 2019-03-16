package com.kingkate.busfind.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingkate.busfind.bean.BusDirBean;
import com.kingkate.busfind.mapper.BusDirBeanMapper;
import com.kingkate.busfind.service.BusDirService;

@Service
public class BusDirServiceImpl implements BusDirService {

	@Autowired
	private BusDirBeanMapper busDirBeanMapper;
	@Override
	public List<BusDirBean> getBusDirList(BusDirBean busDirBean) {
		List<BusDirBean> list = busDirBeanMapper.selectBusDir(busDirBean);
		return list;
	}

}
