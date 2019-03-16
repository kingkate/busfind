package com.kingkate.busfind.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "实时公交请求模块")
public class QueryInTimeBusReq {
	
	@ApiModelProperty(name = "province",value = "省名称",required = true,example = "北京")
	private String province;
	
	@ApiModelProperty(hidden = true)
	private String act;
	
	@ApiModelProperty(hidden = true)
	private String selBLine;
	
	@ApiModelProperty(hidden = true)
	private String selBDir;
	
	@ApiModelProperty(hidden = true)
	private String selBStop;
	
	@ApiModelProperty(name = "busLineId",value = "公交线路ID",required = true,example = "1")
	private Integer busLineId;
	
	@ApiModelProperty(name = "busDirId",value = "公交行驶方向ID",required = true,example = "1")
	private Integer busDirId;
	
	@ApiModelProperty(name = "busStopId",value = "公交站点ID",required = true,example = "1")
	private Integer busStopId;
	
	
	public Integer getBusLineId() {
		return busLineId;
	}
	public void setBusLineId(Integer busLineId) {
		this.busLineId = busLineId;
	}
	public Integer getBusDirId() {
		return busDirId;
	}
	public void setBusDirId(Integer busDirId) {
		this.busDirId = busDirId;
	}
	public Integer getBusStopId() {
		return busStopId;
	}
	public void setBusStopId(Integer busStopId) {
		this.busStopId = busStopId;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getSelBLine() {
		return selBLine;
	}
	public void setSelBLine(String selBLine) {
		this.selBLine = selBLine;
	}
	public String getSelBDir() {
		return selBDir;
	}
	public void setSelBDir(String selBDir) {
		this.selBDir = selBDir;
	}
	public String getSelBStop() {
		return selBStop;
	}
	public void setSelBStop(String selBStop) {
		this.selBStop = selBStop;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
}
