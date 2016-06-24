package com.hayavadana.rating.webservice.rest.json;

import java.util.Date;

public class RateVO {

/*	private int id;
	private String firstName;
	private String lastName;
	private int age;
	*/
	private int userId;
	private String emailId;
	private String phoneNumber;
	private Double    userRate;
	private String userComments;
	private String deviceInfo;
	private int    businessId;
	private Date createdDate;
	
	
	
	/*public Rate(String fname,String lname,int age,int id){
		this.firstName = fname;
		this.lastName  = lname;
		this.age  = age;
		this.id = id;
	}*/
	
	public RateVO(String emailId,String phoneNumber,Double userRate,String userComments,String deviceInfo,int businessId,Date createdDate){
		this.emailId = emailId;
		this.phoneNumber  = phoneNumber;
		this.userRate  = userRate;
		this.userComments = userComments;
		this.deviceInfo  = deviceInfo;
		this.businessId = businessId;
		this.createdDate= createdDate;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public RateVO(){
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

	public Double getUserRate() {
		return userRate;
	}

	public void setUserRate(Double userRate) {
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
		return new StringBuffer("  Email Id : ").append(this.emailId).append("   Phone Number : ").append(this.phoneNumber).append("   User Rating : ").append(this.userRate).toString();
	}

/*	public void setUserRate(Double userRate2) {
		// TODO Auto-generated method stub
		
	}*/
}
