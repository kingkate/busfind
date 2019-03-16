package com.kingkate.busfind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kingkate.busfind.dto.request.QueryInTimeBusReq;
import com.kingkate.busfind.service.InTimeBusService;
import com.kingkate.busfind.util.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/inTime")
@Api(value = "InTimeBus",tags = "【实时公交】",description = "实时公交管理")
public class InTimeBusController {
	
	@Autowired
	private InTimeBusService inTimeBusService;
	
	@RequestMapping("/queryBusByInfo")
	@ApiOperation(value = "查询实时公交信息",httpMethod = "POST")
	public HttpResult queryBusByInfo(@RequestBody QueryInTimeBusReq queryInTimeBusReq) {
		return HttpResult.success(inTimeBusService.getInTimeBusInfo(queryInTimeBusReq));
	}
	
	@RequestMapping("/saveBusLine")
	@ApiIgnore
	public HttpResult saveBusLine(@RequestBody QueryInTimeBusReq queryInTimeBusReq) {
		inTimeBusService.saveBusLine(queryInTimeBusReq);
		return HttpResult.success();
	}
}
