package com.kingkate.busfind.bean;

import java.io.Serializable;

public class BusLine implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6352302109427885181L;
	private int id;
	private String busLineName;
	private String province;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusLineName() {
		return busLineName;
	}
	public void setBusLineName(String busLineName) {
		this.busLineName = busLineName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
}
