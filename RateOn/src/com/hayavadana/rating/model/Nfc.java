package com.hayavadana.rating.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nike")
public class Nfc {
	@Id
	@Column(name = "gui_id")
	private String guiId;
	@Column(name = "prd_id")
	private String productId;
	@Column(name = "prd_name")
	private String productName;
	@Column(name = "prd_desc")
	private String proDesc;
	@Column(name = "manfacturer_name")
	private String manfactureName;
	@Column(name = "time")
	private Date date;
	@Column(name = "tag_id")
	private String tagId;
	@Column(name="write_status")
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String string) {
		this.productId = string;
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
	public String getManfactureName() {
		return manfactureName;
	}
	public void setManfactureName(String manfactureName) {
		this.manfactureName = manfactureName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
