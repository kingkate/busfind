package com.kingkate.busfind.bean;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

@ApiModel(value = "公交行驶方向实体")
public class BusDirBean {
	
	@ApiModelProperty(name = "id",value = "行驶方向ID",required = false,example = "1")
    private Integer id;

	@ApiModelProperty(name = "name",value = "行驶方向名称",required = false,example = "362(西直门-地铁西二旗站)")
	private String name;

	@ApiModelProperty(name = "id",value = "公交线路ID",required = false,example = "1")
	private Integer busLineId;

	@ApiModelProperty(name = "busLineName",value = "公交线路名称",required = false,example = "362")
	private String busLineName;

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
	private String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getBusLineId() {
		return busLineId;
	}

	public void setBusLineId(Integer busLineId) {
		this.busLineId = busLineId;
	}

	public String getBusLineName() {
		return busLineName;
	}

	public void setBusLineName(String busLineName) {
		this.busLineName = busLineName == null ? null : busLineName.trim();
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value == null ? null : value.trim();
	}

}