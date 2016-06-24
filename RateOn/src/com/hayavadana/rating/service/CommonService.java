package com.hayavadana.rating.service;

import java.util.List;
import java.util.Map;

import com.hayavadana.rating.bean.AreaBean;
import com.hayavadana.rating.bean.MovieBean;
import com.hayavadana.rating.webservice.rest.vo.BusinessVO;
import com.hayavadana.rating.webservice.rest.vo.MovieVO;

public interface CommonService {

	public List<AreaBean> getAreaListByCity(Integer cityCode);
	public Map<String,String> getAreaMapByCity(Integer cityCode);
	public MovieVO getMovieVODetails(MovieBean movieBean);
	public List<MovieVO> getMovieVOList(String language);
	void saveMovie(MovieBean movieBean);
		
}
