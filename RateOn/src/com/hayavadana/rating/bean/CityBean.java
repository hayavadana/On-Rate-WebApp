package com.hayavadana.rating.bean;

public class CityBean {

	private int CityCode;
	private String CityDesc;
	private String stateCode;
	private String countryCode;
	
	public int getCityCode() {
		return CityCode;
	}
	public void setCityCode(int cityCode) {
		CityCode = cityCode;
	}
	public String getCityDesc() {
		return CityDesc;
	}
	public void setCityDesc(String cityDesc) {
		CityDesc = cityDesc;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
}
