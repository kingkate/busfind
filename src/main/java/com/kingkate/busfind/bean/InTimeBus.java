package com.kingkate.busfind.bean;

import java.io.Serializable;

public class InTimeBus implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -353110674123407252L;
	private String busStopName;
	private int busStopId;
	private String selBLine;
	private String selBDir;
	private String busStopTime;
	private String busStopStatus;
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
