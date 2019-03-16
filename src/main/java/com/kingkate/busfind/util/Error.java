package com.kingkate.busfind.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "异常信息模块")
public class Error {
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@ApiModelProperty(name = "code",value = "异常代码",required = false,example = "200")
	private Integer code = 200;
	
	@ApiModelProperty(name = "msg",value = "异常说明",required = false,example = "")
	private String msg = "";
	
	
}
