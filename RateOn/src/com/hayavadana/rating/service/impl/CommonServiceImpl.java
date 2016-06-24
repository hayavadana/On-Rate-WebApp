package com.hayavadana.rating.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.bean.AddressBean;
import com.hayavadana.rating.bean.AreaBean;
import com.hayavadana.rating.bean.MovieBean;
import com.hayavadana.rating.dao.CommonDao;
import com.hayavadana.rating.dao.UserRateDao;
import com.hayavadana.rating.model.Address;
import com.hayavadana.rating.model.Area;
import com.hayavadana.rating.model.Business;
import com.hayavadana.rating.model.Logo;
import com.hayavadana.rating.model.Movie;
import com.hayavadana.rating.service.CommonService;
import com.hayavadana.rating.util.CommonUtil;
import com.hayavadana.rating.webservice.rest.vo.BusinessVO;
import com.hayavadana.rating.webservice.rest.vo.MovieVO;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)

public class CommonServiceImpl implements CommonService{

	@Autowired
	private CommonDao commonDao;
	
	@Autowired 
	private CommonUtil commonUtil;
	
	@Autowired
	private UserRateDao userRateDao;
	
	
	public List<AreaBean> getAreaListByCity(Integer cityCode){
		
		List<Area> areaList = null;
		List<AreaBean> beanList = null;
		AreaBean bean = null;
		
		areaList =  commonDao.getAreaListByCity(cityCode);
		beanList = new ArrayList<AreaBean>();
		for(Area area : areaList)
		{
			bean = commonUtil.getAreaBean(area);
			beanList.add(bean);
		}
		return beanList;

	}
	
	public Map<String,String> getAreaMapByCity(Integer cityCode){
		Map<String,String> areaMap = new HashMap<String,String>();
		List<Area> areaList = commonDao.getAreaListByCity(cityCode);
		
		for(Area area : areaList){
			areaMap.put(area.getAreaCode()+"", area.getAreaDesc());
		}
		
		return areaMap;
	}
	
	//------start----movie------------//
	
	@Override
	public List<MovieVO> getMovieVOList(String language) {

		List<MovieVO> MovieVOList = new ArrayList<MovieVO>();
		MovieVO movieVO = null;
		System.out.println("CommonServiceImpl----getMovieVOList ---Before DAO invocation -- language : "+language);
		List<Movie> movieList =commonDao.getMovieListByLanguage(language);
		
		System.out.println("CommonServiceImpl----getMovieVOList ---After DAO invocation  -- addressList Size : "+movieList.size());
		for(Movie movie : movieList){

			System.out.println("CommonServiceImpl----getMovieVOList ---with in Loop ");

				movieVO = commonUtil.getMovieVO(movie);
				if(null!=userRateDao.getAverageMovieRating(movie.getMovieId())){
				movieVO.setAvgRating(userRateDao.getAverageMovieRating(movie.getMovieId()));
				}
				MovieVOList.add(movieVO);
			}
		System.out.println("CommonServiceImpl----getMovieVOList ---Before returning -- List Size : "+MovieVOList.size());
		return MovieVOList;

	}
	
	@Override
	public MovieVO getMovieVODetails(MovieBean movieBean) {
		MovieVO movieVO=new MovieVO();
		Movie movie=new Movie();
		movie.setMovieId(movieBean.getMovieId());
		movie=commonDao.getMovieDetails(movie);
		movieVO=commonUtil.getMovieVO(movie);
		if(userRateDao.getAverageMovieRating(movieBean.getMovieId())!=null){
		movieVO.setAvgRating(userRateDao.getAverageMovieRating(movieBean.getMovieId()));
		}
		return movieVO;
	}

	@Override
	public void saveMovie(MovieBean movieBean) {
		
		System.out.println("------CommonServiceImpl----saveMovie ----- ");
		Movie movie=new Movie();
		movie=commonUtil.getMovieFromBean(movieBean);
		
		System.out.println("movie details: "+movie.getMovieName()+","+movie.getActor()+","+movie.getLanguage()+","+movie.getDirector()+",");

		commonDao.saveMovie(movie);
		
		
	}

	//----end------movie--------------//
	
	
}
