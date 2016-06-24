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

import java.util.Date;

@Entity
@Table(name="BUSINESS")
public class Business implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "business_id")
	private Integer businessId;
	
	@Column(name="business_name")
	private String businessName;
	
	@Column(name = "business_desc")
	private String businessDesc;
	
	@Column(name = "start_day")
	private String startDay;
	
	@Column(name = "end_day")
	private String endDay;
	
	@Column(name = "start_hours")
	private String startHours;
	
	@Column(name = "end_hours")
	private String endHours;
	
	@Column(name = "exception_str")
	private String exceptionString;
	
	@Column(name = "website_url")
	private String websiteUrl;
	
	@Column(name = "sms_required")
	private String smsRequired;
	
	@Column(name="is_active")
	private Character isActive;
	
	@Column(name="created_date")
	private Date createdDate;

	@Column(name = "category_code")
	private String categoryCode;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	
	@Column(name = "acct_id")
	private Integer acctId;
	
	public Integer getAcctId() {
		return acctId;
	}

	public void setAcctId(Integer acctId) {
		this.acctId = acctId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/*	@ManyToOne(cascade = CascadeType.ALL)
	public Category category;
	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
*/
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

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getStartHours() {
		return startHours;
	}

	public void setStartHours(String startHours) {
		this.startHours = startHours;
	}

	public String getEndHours() {
		return endHours;
	}

	public void setEndHours(String endHours) {
		this.endHours = endHours;
	}

	public String getExceptionString() {
		return exceptionString;
	}

	public void setExceptionString(String exceptionString) {
		this.exceptionString = exceptionString;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getSmsRequired() {
		return smsRequired;
	}

	public void setSmsRequired(String smsRequired) {
		this.smsRequired = smsRequired;
	}

	public Character getIsActive() {
		return isActive;
	}

	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
}
