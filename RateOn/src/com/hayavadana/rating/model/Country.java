package com.hayavadana.rating.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="COUNTRY")
public class Country implements Serializable {
	
	
/*	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="country_id")
	private Integer countryId;
*/	
	@Id
	@Column(name="country_code")
	private String countryCode;
	
	@Column(name="country_desc")
	private String countryDesc;

	
	/*public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}*/

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryDesc() {
		return countryDesc;
	}

	public void setCountryDesc(String countryDesc) {
		this.countryDesc = countryDesc;
	}
	
	

}
