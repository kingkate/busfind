package com.kingkate.busfind.util;

import com.kingkate.busfind.constants.ResultCodeEnum;
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

	public Error() {}

	public Error(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Error(ResultCodeEnum result) {
		this.code = result.getCode();
		this.msg = result.getMsg();
	}
}
