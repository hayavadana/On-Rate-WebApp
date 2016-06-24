package com.hayavadana.rating.bean;

import javax.persistence.Column;

public class LikesBean {
	private Integer likesId;
	private Integer topicId;
	private Integer replyId;
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
