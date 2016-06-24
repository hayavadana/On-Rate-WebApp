package com.hayavadana.rating.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "BUSINESS_LOGO")
public class Logo implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="logo_id")
	private Integer logoId;
	
	@Column(name="business_id")
	private Integer businessId;
	
	@Column(name="logo_name")
	private String logoName;
	
	@Column(name ="logo_path")
	private String logoPath;
	
	@Column(name="logo_data")
	private byte[] logoData;

	public Integer getLogoId() {
		return logoId;
	}

	public void setLogoId(Integer logoId) {
		this.logoId = logoId;
	}

	public byte[] getLogoData() {
		return logoData;
	}

	public void setLogoData(byte[] logoData) {
		this.logoData = logoData;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	
	
}
