package com.kingkate.busfind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kingkate.busfind.dto.request.BusStopReq;
import com.kingkate.busfind.service.BusStopService;
import com.kingkate.busfind.util.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/busStop")
@Api(value = "BusStop",tags = "【公交站点】",description = "公交站点管理")
public class BusStopController {
	
	@Autowired
	private BusStopService busStopService;
	
	@RequestMapping("/getBusStopList")
	@ApiOperation(value = "查询站点信息",httpMethod = "POST")
	public HttpResult getBusStopList(@RequestBody BusStopReq busStopReq) {
		return HttpResult.success(busStopService.getBusStopList(busStopReq));
	}

}
