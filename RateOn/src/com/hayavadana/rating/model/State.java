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
@Table(name="STATE")
public class State implements Serializable{

	
	/*@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="state_id")
	private Integer stateId;
*/	
	@Id
	@Column(name="state_code")
	private String stateCode;
	
	@Column(name="state_desc")
	private String stateDesc;
	
	@Column(name="country_code")
	private String countryCode;
	
/*	@ManyToOne(cascade = CascadeType.ALL)
	public Country country;*/

	/*public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}*/
	
	

	public String getStateCode() {
		return stateCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

/*	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	*/
	
}
