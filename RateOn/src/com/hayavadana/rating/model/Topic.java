package com.hayavadana.rating.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="TOPIC_DETAILS")
public class Topic implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="topic_id")
	private Integer topicId;
	
	@Column(name="topic_description")
	private String topicDescription;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="topic_category_id")
	private Integer topicCategoryId;
	
	@Column(name="topic_type_code")
	private Integer topicTypeCode;

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getTopicDescription() {
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTopicCategoryId() {
		return topicCategoryId;
	}

	public void setTopicCategoryId(Integer topicCategoryId) {
		this.topicCategoryId = topicCategoryId;
	}

	public Integer getTopicTypeCode() {
		return topicTypeCode;
	}

	public void setTopicTypeCode(Integer topicTypeCode) {
		this.topicTypeCode = topicTypeCode;
	}
	
	
}
