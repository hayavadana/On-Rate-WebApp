package com.hayavadana.rating.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="TOPIC_REPLIES")
public class Reply implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reply_id")
	private Integer replyId;
	
	@Column(name="reply_description")
	private String replyDescription;
	
	@Column(name="topic_id")
	private Integer topicid;
	
	@Column(name="user_id")
	private Integer userId;

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public String getReplyDescription() {
		return replyDescription;
	}

	public void setReplyDescription(String replyDescription) {
		this.replyDescription = replyDescription;
	}

	public Integer getTopicid() {
		return topicid;
	}

	public void setTopicid(Integer topicid) {
		this.topicid = topicid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
}
