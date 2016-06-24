package com.hayavadana.rating.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

@Entity
@Table(name="ACCOUNT")
public class Account implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acct_id")
	private Integer acctId;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name="acct_name")
	private String acctName;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="is_active")
	private Character isActive;
	
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="country_code")
	private String countryCode;
	
	
		
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getAcctId() {
		return acctId;
	}

	public void setAcctId(Integer acctId) {
		this.acctId = acctId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	
	
	
}
