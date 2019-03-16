package com.kingkate.busfind.dto.response;

import java.io.Serializable;
import java.util.List;

import com.kingkate.busfind.bean.InTimeBus;

public class InTimeBusInfoRes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9010791337623328041L;
	private String header;
	private String dir;
	private String line;
	private List<InTimeBus> inTimeBusList;
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public List<InTimeBus> getInTimeBusList() {
		return inTimeBusList;
	}
	public void setInTimeBusList(List<InTimeBus> inTimeBusList) {
		this.inTimeBusList = inTimeBusList;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	
}
