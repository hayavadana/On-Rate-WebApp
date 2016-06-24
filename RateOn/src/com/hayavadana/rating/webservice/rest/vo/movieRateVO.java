package com.hayavadana.rating.webservice.rest.vo;

import java.util.Date;

public class movieRateVO {
	
	private String emailId;
	private String phoneNumber;
	private Float userRate;
	private String userComments;
	private String deviceInfo;
	private int movieId;
	private Date createdDate;
	
	public movieRateVO(){}
	
	public movieRateVO(String emailId,String phoneNumber,Float userRate,String userComments, String deviceInfo , int MovieId,Date createdDate){
		this.emailId = emailId;
		this.phoneNumber  = phoneNumber;
		this.userRate  = userRate;
		this.userComments = userComments;
		this.deviceInfo = deviceInfo;
		this.movieId = MovieId;
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
	public Float getUserRate() {
		return userRate;
	}
	public void setUserRate(Float float1) {
		this.userRate = float1;
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
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int MovieId) {
		this.movieId = MovieId;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date date) {
		this.createdDate = date;
	}

	@Override
	public String toString(){
		return new StringBuffer("  Email ID : ").append(this.emailId).append("   Phone Number : ").append(this.phoneNumber).append("   User Rate : ").append(this.userRate).toString();
	}
}
