package com.kingkate.busfind.bean;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "公交站点实体")
public class BusStopBean {
	
	@ApiModelProperty(name = "id",value = "车站ID",required = false,example = "1")
    private Integer id;

	@ApiModelProperty(name = "province",value = "省名称",required = false,example = "北京")
    private String province;

	@ApiModelProperty(name = "busLineId",value = "公交ID",required = false,example = "1")
    private Integer busLineId;

	@ApiModelProperty(name = "busLineName",value = "公交名称",required = false,example = "664")
    private String busLineName;

	@ApiModelProperty(name = "busDirId",value = "公交行驶方向ID",required = false,example = "1")
    private Integer busDirId;

	@ApiModelProperty(name = "busDirName",value = "公交行驶方向名称",required = false,example = "362(西直门-地铁西二旗站)")
    private String busDirName;

	@ApiModelProperty(name = "name",value = "公交行驶方向ID",required = false,example = "万泉河桥南")
    private String name;

	@ApiModelProperty(hidden = true)
    private String stopId;

	public String getStopId() {
		return stopId;
	}

	public void setStopId(String stopId) {
		this.stopId = stopId;
	}

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
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

    public Integer getBusDirId() {
        return busDirId;
    }

    public void setBusDirId(Integer busDirId) {
        this.busDirId = busDirId;
    }

    public String getBusDirName() {
        return busDirName;
    }

    public void setBusDirName(String busDirName) {
        this.busDirName = busDirName == null ? null : busDirName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
}