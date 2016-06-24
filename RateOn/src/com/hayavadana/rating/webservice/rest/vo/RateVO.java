package com.hayavadana.rating.webservice.rest.vo;

import java.util.Date;

public class RateVO {
	
	private String emailId;
	private String phoneNumber;
	private int userRate;
	private String userComments;
	private String deviceInfo;
	private int businessId;
	private Date createdDate;
	
	public RateVO(){}
	
	public RateVO(String emailId,String phoneNumber,int userRate,String userComments, String deviceInfo , int businessId,Date createdDate){
		this.emailId = emailId;
		this.phoneNumber  = phoneNumber;
		this.userRate  = userRate;
		this.userComments = userComments;
		this.deviceInfo = deviceInfo;
		this.businessId = businessId;
		this.createdDate= createdDate;
	}

	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getUserRate() {
		return userRate;
	}
	public void setUserRate(int userRate) {
		this.userRate = userRate;
	}
	public String getUserComments() {
		return userComments;
	}
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString(){
		return new StringBuffer("  Email ID : ").append(this.emailId).append("   Phone Number : ").append(this.phoneNumber).append("   User Rate : ").append(this.userRate).toString();
	}
}
