package com.kingkate.busfind.constants;

public enum BUS_STOP_STATUS {
	BUSC("0"),BUSS("1");
	
	private String value;
	
	private BUS_STOP_STATUS(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
