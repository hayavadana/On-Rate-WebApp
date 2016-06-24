package com.hayavadana.rating.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "MOVIE_RATING")
public class MovieRate implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rating_id")
	private Integer ratingId;
	
	@Column(name="user_rate")
	private Float userRate;
	
	@Column(name="user_comments")
	private String userComments;
	
	@Column(name="device_info")
	private String deviceInfo;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="movie_id")
	private Integer movieId;
	
	@Column(name="created_date")
	private Date createdDate;

	public Integer getRatingId() {
		return ratingId;
	}

	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}

	public Float getUserRate() {
		return userRate;
	}

	public void setUserRate(Float userRate) {
		this.userRate = userRate;
	}

	public String getUserComments() {
		return userComments;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getmovieId() {
		return movieId;
	}

	public void setmovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	
}
