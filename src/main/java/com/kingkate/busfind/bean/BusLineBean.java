package com.kingkate.busfind.bean;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "公交线路请求模块")
public class BusLineBean {
	
	@ApiModelProperty(name = "id",value = "公交线路ID",required = false,example = "1")
    private Integer id;
	@ApiModelProperty(name = "busName",value = "公交线路名称",required = false,example = "362")
	private String busName;

	@ApiModelProperty(name = "province",value = "省名称",required = false,example = "北京")
	private String province;

	@ApiModelProperty(hidden = true)
	private Date cT;

	@ApiModelProperty(hidden = true)
	private Date uT;

	@ApiModelProperty(hidden = true)
	private String cU;

	@ApiModelProperty(hidden = true)
	private String uU;

	@ApiModelProperty(hidden = true)
	private Boolean isDeleted;
	
	@ApiModelProperty(hidden = true)
	private String busAlias;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName == null ? null : busName.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public Date getcT() {
		return cT;
	}

	public void setcT(Date cT) {
		this.cT = cT;
	}

	public Date getuT() {
		return uT;
	}

	public void setuT(Date uT) {
		this.uT = uT;
	}

	public String getcU() {
		return cU;
	}

	public void setcU(String cU) {
		this.cU = cU == null ? null : cU.trim();
	}

	public String getuU() {
		return uU;
	}

	public void setuU(String uU) {
		this.uU = uU == null ? null : uU.trim();
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getBusAlias() {
		return busAlias;
	}

	public void setBusAlias(String busAlias) {
		this.busAlias = busAlias;
	}
}