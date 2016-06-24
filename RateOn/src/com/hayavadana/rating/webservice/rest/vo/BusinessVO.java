package com.hayavadana.rating.webservice.rest.vo;

public class BusinessVO {
	private Integer businessId;
	private String businessName;
	private String businessDesc;
	private String businessAddress;
	private String businessLogoUrl;
	private byte[] businessLogo;
	private Float  averageRating;
	private Double Longitude;
	private Double Latitude;
	
	
	public byte[] getBusinessLogo() {
		return businessLogo;
	}
	public void setBusinessLogo(byte[] businessLogo) {
		this.businessLogo = businessLogo;
	}
	
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessDesc() {
		return businessDesc;
	}
	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getBusinessLogoUrl() {
		return businessLogoUrl;
	}
	public void setBusinessLogoUrl(String businessLogoUrl) {
		this.businessLogoUrl = businessLogoUrl;
	}
	public Float getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}
	public Double getLongitude() {
		return Longitude;
	}
	public void setLongitude(Double longitude) {
		Longitude = longitude;
	}
	public Double getLatitude() {
		return Latitude;
	}
	public void setLatitude(Double latitude) {
		Latitude = latitude;
	}
	
	
}
