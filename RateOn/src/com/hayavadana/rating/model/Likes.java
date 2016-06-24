package com.hayavadana.rating.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="LIKES_DETAILS")
public class Likes implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="likes_id")
	private Integer likesId;
	
	@Column(name="topic_id")
	private Integer topicId;
	
	@Column(name="reply_id")
	private Integer replyId;
	
	@Column(name="user_id")
	private Integer userId;

	public Integer getLikesId() {
		return likesId;
	}

	public void setLikesId(Integer likesId) {
		this.likesId = likesId;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
		
}

