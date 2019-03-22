package com.kingkate.busfind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kingkate.busfind.bean.BusLineBean;
import com.kingkate.busfind.dto.request.BusLineReq;
import com.kingkate.busfind.service.BusLineService;
import com.kingkate.busfind.util.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/busLine")
@Api(value = "BusLine",tags = "【公交线路】",description = "公交线路管理")
public class BusLineController {
	
	@Autowired
	private BusLineService busLineService;
	
	@RequestMapping("getBusLineList")
	@ApiOperation(value = "查询公交线路信息",httpMethod = "POST")
	public HttpResult getBusLineList(@RequestBody BusLineReq busLineReq) {
		return HttpResult.success(busLineService.getBusLineList(busLineReq));
	}
	
}
