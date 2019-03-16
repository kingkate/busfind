package com.kingkate.busfind.dto.request;

import com.kingkate.busfind.bean.BusLineBean;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "公交线路请求模块",parent = BusLineBean.class)
public class BusLineReq extends BusLineBean {

}
