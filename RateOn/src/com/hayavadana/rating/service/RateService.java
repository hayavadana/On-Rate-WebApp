package com.hayavadana.rating.service;

import java.util.List;

import com.hayavadana.rating.webservice.rest.json.RateVO;
import com.hayavadana.rating.webservice.rest.vo.movieRateVO;

public interface RateService {
	public boolean saveRate(RateVO rateVO);
	public List<RateVO> getUserComments(Integer businessId);
	public List<movieRateVO> getMovieUserRateList(movieRateVO rateVO);
	public void saveMovieRate(movieRateVO rate);
	
}
