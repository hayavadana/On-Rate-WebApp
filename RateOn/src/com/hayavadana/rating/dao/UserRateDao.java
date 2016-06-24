package com.hayavadana.rating.dao;

import java.util.List;

import com.hayavadana.rating.model.MovieRate;
import com.hayavadana.rating.model.UserRate;
public interface UserRateDao {
	 public UserRate saveEndUser(UserRate rate);
	 public Double getAverageUserRating(Integer businessId);
	 public List<UserRate> getUserComments(Integer businessId);
	 public List<MovieRate> getMovieUserRateList(Integer movieid);
	 public MovieRate saveMovieEndUser(MovieRate movieRate);
	 public Float getAverageMovieRating(Integer movieId);
}
