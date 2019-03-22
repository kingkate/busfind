package com.kingkate.busfind.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "单个车站信息")
public class InTimeBus implements Serializable {
	
	
	
	/**
	 * 
	 */
	@ApiModelProperty(hidden = true)
	private static final long serialVersionUID = -353110674123407252L;
	
	@ApiModelProperty(name = "busStopName",value = "公交车站名称",required = false,example = "万泉河桥南")
	private String busStopName;
	
	@ApiModelProperty(name = "busStopId",value = "公交车站ID",required = false,example = "1")
	private int busStopId;
	
	@ApiModelProperty(hidden = true)
	private String selBLine;
	
	@ApiModelProperty(hidden = true)
	private String selBDir;
	
	@ApiModelProperty(hidden = true)
	private String busStopTime;
	
	@ApiModelProperty(name = "busStopStatus",value = "公交车站状态（1：到站，0，在途，-1 等待发车）",required = false,example = "1")
	private String busStopStatus;
	
	@ApiModelProperty(hidden = true)
	private Integer nearestBusTime;
	
	public Integer getNearestBusTime() {
		return nearestBusTime;
	}
	public void setNearestBusTime(Integer nearestBusTime) {
		this.nearestBusTime = nearestBusTime;
	}
	public Integer getNearestBusStopNum() {
		return nearestBusStopNum;
	}
	public void setNearestBusStopNum(Integer nearestBusStopNum) {
		this.nearestBusStopNum = nearestBusStopNum;
	}
	public Double getNearestBusDistance() {
		return nearestBusDistance;
	}
	public void setNearestBusDistance(Double nearestBusDistance) {
		this.nearestBusDistance = nearestBusDistance;
	}
	@ApiModelProperty(hidden = true)
	private Integer nearestBusStopNum;
	
	@ApiModelProperty(hidden = true)
	private Double nearestBusDistance;
	
	
	public String getBusStopName() {
		return busStopName;
	}
	public void setBusStopName(String busStopName) {
		this.busStopName = busStopName;
	}
	public int getBusStopId() {
		return busStopId;
	}
	public void setBusStopId(int busStopId) {
		this.busStopId = busStopId;
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
	public String getBusStopTime() {
		return busStopTime;
	}
	public void setBusStopTime(String busStopTime) {
		this.busStopTime = busStopTime;
	}
	public String getBusStopStatus() {
		return busStopStatus;
	}
	public void setBusStopStatus(String busStopStatus) {
		this.busStopStatus = busStopStatus;
	}
	
}
