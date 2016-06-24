package com.hayavadana.rating.bean;

public class PropertyBean {

	private int Id;
	private String category;
	private String name;
	private String value;
	private Character activeFlag;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Character getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Character activeFlag) {
		this.activeFlag = activeFlag;
	}

	
}
