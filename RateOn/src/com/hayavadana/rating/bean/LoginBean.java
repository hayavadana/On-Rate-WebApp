package com.hayavadana.rating.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class LoginBean {

	@NotNull
	@Size(min=2,max=10)
	String loginId;
	@NotNull
	@Size(min=2,max=10)
	String password;
	
	String loginType;
	
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLoginType(){
		return loginType;
	}
	public void setLoginType(String loginType){
		this.loginType = loginType;
	}
	
}
