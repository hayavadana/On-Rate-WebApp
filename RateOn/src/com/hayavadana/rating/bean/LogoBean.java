package com.hayavadana.rating.bean;

import javax.persistence.Column;

public class LogoBean {
	private Integer logoId;
	private Integer businessId;
	private String logoName;
	private String logoPath;
	private byte[] logoData;
	
	public Integer getLogoId() {
		return logoId;
	}
	public void setLogoId(Integer logoId) {
		this.logoId = logoId;
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
	public byte[] getLogoData() {
		return logoData;
	}
	public void setLogoData(byte[] logoData) {
		this.logoData = logoData;
	}

}
