package com.kingkate.busfind.dto.response;

import java.io.Serializable;
import java.util.List;

import com.kingkate.busfind.bean.InTimeBus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "实时公交响应实体")
public class InTimeBusInfoRes implements Serializable {
	
	/**
	 * 
	 */
	@ApiModelProperty(hidden = true)
	private static final long serialVersionUID = -9010791337623328041L;
	
	@ApiModelProperty(name = "header",value = "实时公交车站信息",required = false,example = "最近一辆车距离此还有 1 站， 2.09 公里，预计到站时间 4 分钟")
	private String header;
	
	@ApiModelProperty(name = "dir",value = "公交行驶方向名称",required = false,example = "2(双庙-宽街路口南)")
	private String dir;
	
	@ApiModelProperty(name = "line",value = "公交线路名称",required = false,example = "362")
	private String line;
	
	@ApiModelProperty(name = "inTimeBusList",value = "当前公交车站信息列表",required = false,example = "list")
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
