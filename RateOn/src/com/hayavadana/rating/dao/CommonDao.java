package com.hayavadana.rating.dao;

import java.util.List;

import com.hayavadana.rating.model.Area;
import com.hayavadana.rating.model.Logo;
import com.hayavadana.rating.model.Movie;

public interface CommonDao {
	public Logo saveLogoDetails(Logo logo);
	public Logo getLogoDetails(Integer businessId);
	public List<Area> getAreaListByCity(Integer cityCode);
	
	public List<Movie> getMovieListByLanguage(String language);
	public Movie getMovieDetails(Movie movie);
	public void saveMovie(Movie movie);
	
}
