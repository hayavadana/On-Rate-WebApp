package com.hayavadana.rating.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BUSINESS_AREA")
public class Area implements Serializable{
	
	@Id
	@Column(name = "area_code")
	private Integer areaCode;
	
	@Column(name = "area_desc")
	private String areaDesc;
	
	@Column(name = "city_code")
	private Integer cityCode;

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}
	
	
}
