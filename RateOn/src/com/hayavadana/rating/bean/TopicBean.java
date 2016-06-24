package com.hayavadana.rating.bean;

import javax.validation.constraints.NotNull;

public class TopicBean {

	@NotNull
	private Integer topicType;
	
	@NotNull
	private String topicTitle;
	
	@NotNull
	private String description;

	@NotNull
	private Integer domain;

	private Integer userId;

	public Integer getTopicType() {
		return topicType;
	}

	public void setTopicType(Integer topicType) {
		this.topicType = topicType;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDomain() {
		return domain;
	}

	public void setDomain(Integer domain) {
		this.domain = domain;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
		
}
