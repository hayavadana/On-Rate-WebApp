package com.hayavadana.rating.bean;

import java.util.Date;

public class NfcBean {
	
	private String guiId;
	private String proId;
	private String productName;
	private String proDesc;
	private String manufactureName;
	private String date;
	private String tagId;
	private String error;
	private String tagIdArr;
	private String status;
	
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getGuiId() {
		return guiId;
	}
	public void setGuiId(String guiId) {
		this.guiId = guiId;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String prductId) {
		this.proId = prductId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public String getManufactureName() {
		return manufactureName;
	}
	public void setManufactureName(String manfactureName) {
		this.manufactureName = manfactureName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String string) {
		this.date = string;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getTagIdArr() {
		return tagIdArr;
	}
	public void setTagIdArr(String tagIdArr) {
		this.tagIdArr = tagIdArr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}

