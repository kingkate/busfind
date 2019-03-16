package com.kingkate.busfind.dto.request;

import com.kingkate.busfind.bean.BusDirBean;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "公交行驶方向请求模块",parent = BusDirBean.class)
public class BusDirReq extends BusDirBean {

}
