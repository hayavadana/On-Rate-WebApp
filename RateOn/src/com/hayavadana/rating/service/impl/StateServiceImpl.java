package com.hayavadana.rating.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.bean.CountryBean;
import com.hayavadana.rating.bean.StateBean;
import com.hayavadana.rating.dao.StateDao;
import com.hayavadana.rating.model.Country;
import com.hayavadana.rating.model.State;
import com.hayavadana.rating.service.StateService;
import com.hayavadana.rating.util.CommonUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class StateServiceImpl implements StateService{

	@Autowired
	private StateDao stateDao;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public List<StateBean> getStateListOfCountry(CountryBean ctryBean){
		List<StateBean> stateBeanList = null;
		Country country = null;
        List<State> stateList = null;
	    StateBean stateBean = null;
		try{
			country = commonUtil.getCountry(ctryBean);
			stateBeanList = new ArrayList<StateBean>();
			
			stateList = stateDao.getStateListByCountry(country);
			
			for(State state : stateList){
				stateBean = commonUtil.getStateBean(state);
				stateBeanList.add(stateBean);
			}
			
		}catch(Exception ex){
			System.out.println("------------StateServiceImpl-------getStateListOfCountry-----------Ex : "+ex);
		}
		return stateBeanList;
	}
	
	public Map<String,String> getStatesMapOfCountry(CountryBean ctryBean){
		List<StateBean> stateBeanList = null;
		Map<String,String> statesMap = null;
        List<State> stateList = null;
	   // StateBean stateBean = null;
		try{
			stateBeanList = getStateListOfCountry(ctryBean);
			statesMap = new HashMap<String,String>();
			for(StateBean stateBean : stateBeanList){
				statesMap.put(stateBean.getStateCode(),stateBean.getStateDesc());
			}
			
		}catch(Exception ex){
			System.out.println("------------StateServiceImpl-------getStateListOfCountry-----------Ex : "+ex);
		}
		return statesMap;
	}
}
