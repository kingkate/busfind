package com.kingkate.busfind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kingkate.busfind.dto.request.BusDirReq;
import com.kingkate.busfind.service.BusDirService;
import com.kingkate.busfind.util.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.HttpMethod;

@RestController
@RequestMapping("/busDir")
@Api(value = "BusDir",tags = "【公交行驶方向】",description = "公交行驶方向管理")
public class BusDirController {
	
	@Autowired
	private BusDirService busDirService;
	
	@RequestMapping(value = "/getBusDirList",method = RequestMethod.POST)
	@ApiOperation(value = "查询公交行驶方向",httpMethod = "POST")
	public HttpResult getBusDirList(@RequestBody BusDirReq busDirReq) {
		return HttpResult.success(busDirService.getBusDirList(busDirReq));
	}

}
