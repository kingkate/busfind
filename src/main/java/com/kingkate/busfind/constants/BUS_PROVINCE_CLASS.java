package com.kingkate.busfind.constants;

import com.kingkate.busfind.parse.province.ParseBeijing;
import com.kingkate.busfind.parse.province.ParseShangHai;
import com.kingkate.busfind.query.province.BeijingQuery;
import com.kingkate.busfind.query.province.ShangHaiQuery;

public enum BUS_PROVINCE_CLASS {
	BEIJING("北京",BeijingQuery.class,ParseBeijing.class),SHANGHAI("上海",ShangHaiQuery.class,ParseShangHai.class);
	
	
	private String province;
	private Class queryClass;
	public Class getQueryClass() {
		return queryClass;
	}


	public void setQueryClass(Class queryClass) {
		this.queryClass = queryClass;
	}


	public Class getParseClass() {
		return parseClass;
	}


	public void setParseClass(Class parseClass) {
		this.parseClass = parseClass;
	}

	private Class parseClass;
	
	BUS_PROVINCE_CLASS(String province,Class queryClass,Class parseClass) {
		this.setProvince(province);
		this.queryClass = queryClass;
		this.parseClass = parseClass;
	}

	
	public static Class getQueryClassByProvince(String province) {
		for(BUS_PROVINCE_CLASS bClass : BUS_PROVINCE_CLASS.values()) {
			if(bClass.getProvince().equals(province)) {
				return bClass.getQueryClass();
			}
		}
		return null;
	}
	
	public static Class getParseClassByProvince(String province) {
		for(BUS_PROVINCE_CLASS bClass : BUS_PROVINCE_CLASS.values()) {
			if(bClass.getProvince().equals(province)) {
				return bClass.getParseClass();
			}
		}
		return null;
	}
	

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
}
