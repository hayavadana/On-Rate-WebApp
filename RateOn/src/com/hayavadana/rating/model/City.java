package com.hayavadana.rating.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="CITY")
public class City implements Serializable{

	
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "city_code")
	private Integer cityCode;
	
	@Column(name = "city_desc")
	private String cityDesc;
	
	@Column(name="state_code")
	private String stateCode;
	
	@Column(name="country_code")
	private String countryCode;

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityDesc() {
		return cityDesc;
	}

	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
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
	

	/*	@ManyToOne(cascade = CascadeType.ALL)
	public State   state;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Country country;
*/


	
	
}
