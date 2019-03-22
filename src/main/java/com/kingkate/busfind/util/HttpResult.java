package com.kingkate.busfind.util;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "响应对象")
public class HttpResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1960914088600232715L;
	@ApiModelProperty(value = "响应数据", name = "data", required = true, example = "null")
	private Object data;
	@ApiModelProperty(value = "异常说明", name = "error", required = true, example = "")
	private Error error;
	@ApiModelProperty(value = "响应时间", name = "time", required = true, example = "13456789000")
	private long time;
	
	public static HttpResult success(Object data) {
		HttpResult result = new HttpResult();
		result.setData(data);
		result.setTime(System.currentTimeMillis());
		result.setError(new Error());
		return result;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Error getError() {
		return error;
	}
	public void setError(Error error) {
		this.error = error;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}

	public static HttpResult success() {
		HttpResult result = new HttpResult();
		result.setData(null);
		result.setTime(System.currentTimeMillis());
		result.setError(new Error());
		return result;
	}

	
}
