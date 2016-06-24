package com.hayavadana.rating.bean;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileBean {
	private int businessId;
	private String fileName;
	private CommonsMultipartFile fileData;
	
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

	
}
