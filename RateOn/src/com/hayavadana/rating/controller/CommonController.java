package com.hayavadana.rating.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hayavadana.rating.bean.AccountBean;
import com.hayavadana.rating.bean.AreaBean;
import com.hayavadana.rating.bean.BusinessForm;
import com.hayavadana.rating.bean.CityBean;
import com.hayavadana.rating.bean.CountryBean;
import com.hayavadana.rating.bean.FileBean;
import com.hayavadana.rating.bean.MovieBean;
import com.hayavadana.rating.bean.PropertyBean;
import com.hayavadana.rating.bean.StateBean;
import com.hayavadana.rating.service.CityService;
import com.hayavadana.rating.service.CommonService;
import com.hayavadana.rating.service.CountryService;
import com.hayavadana.rating.service.PropertyService;

@Controller
public class CommonController {
	private static final Logger logger = Logger.getLogger(CommonController.class);

	@Autowired
	private CityService cityService;
	
	@Autowired 
	private PropertyService propertyService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired CommonService commonService;
	

	@RequestMapping(value="/getCountryListJSON",method=RequestMethod.GET)
	public @ResponseBody String getCountryListJSON(Map model) throws JSONException{
			System.out.println("CommonController -- getCountryListJSON");
	
			JSONArray jsonItems = new JSONArray();

			List<CountryBean> ctryBeanList = countryService.getCountriesList();
			
			JSONObject jsonItem = null;
			
			for(CountryBean ctryBean : ctryBeanList){
				System.out.println("Country Code : "+ctryBean.getCountryCode()+"   Country Desc : "+ctryBean.getCountryDesc());

				jsonItem = new JSONObject();
			    jsonItem.put("id", ctryBean.getCountryCode());
			    jsonItem.put("name", ctryBean.getCountryDesc());
			    jsonItems.put(jsonItem);

			}
			
			
		    return jsonItems.toString();
			
	}

	
	@RequestMapping(value="/getWeekdaysJSON",method=RequestMethod.GET)
	public @ResponseBody String getWeekdaysJSON(Map model) throws JSONException{
			System.out.println("CommonController -- getWeekdaysJSON");
	
			JSONArray jsonItems = new JSONArray();

			List<PropertyBean> weekdaysList = null;
			
			weekdaysList = propertyService.getPropertyList("WEEK_DAYS");
			
			JSONObject jsonItem = null;
			
			for(PropertyBean bean : weekdaysList){
				System.out.println("Category : "+bean.getCategory()+"   Name : "+bean.getName()+"    Value : "+bean.getValue());

				jsonItem = new JSONObject();
			    jsonItem.put("id", bean.getName());
			    jsonItem.put("name", bean.getValue());
			    jsonItems.put(jsonItem);

			}
			
			
			
/*		    JSONObject jsonItem1 = new JSONObject();
		    jsonItem1.put("id", "MON");
		    jsonItem1.put("name", "Monday");

		    JSONObject jsonItem2 = new JSONObject();
		    jsonItem2.put("id", "TUE");
		    jsonItem2.put("name", "Tuesday");

		    JSONObject jsonItem3 = new JSONObject();
		    jsonItem3.put("id", "WED");
		    jsonItem3.put("name", "Wednesday");

		    
		    jsonItems.put(jsonItem1);
		    jsonItems.put(jsonItem2);
		    jsonItems.put(jsonItem3);
		    */
		    return jsonItems.toString();
			
			
		
		}
	
	@RequestMapping(value="/getCities",method=RequestMethod.GET)
	public @ResponseBody String getCities(@RequestParam("stateCode") String stateCode,@RequestParam("countryCode") String countryCode,Map model) throws JSONException{
		
		
		//return "{\"Hyderabad\":\"Hyderabad\",\"Warangal\":\"Warangal\"}";
		System.out.println("------------LoginController---Selected State : "+stateCode);
		System.out.println("------------LoginController---Selected Country : "+countryCode);
		
		JSONArray jsonItems = new JSONArray();

		List<CityBean> cityBeanList = cityService.getAllCitiesByStateCountry(stateCode, countryCode);
		
		JSONObject jsonItem = null;
		
		for(CityBean cityBean : cityBeanList){
			System.out.println("City Code : "+cityBean.getCityCode()+"   City Desc : "+cityBean.getCityDesc()+"    State : "+cityBean.getStateCode()+"   Country : "+cityBean.getCountryCode());

			jsonItem = new JSONObject();
		    jsonItem.put("id", cityBean.getCityCode());
		    jsonItem.put("name", cityBean.getCityDesc());
		    jsonItems.put(jsonItem);

		}
		
	    return jsonItems.toString();
	}
	@RequestMapping(value="/getAreasJSON",method=RequestMethod.GET)
	public @ResponseBody String getCities(@RequestParam("cityCode") String cityCode,Map model) throws JSONException{
		
		
		//return "{\"Hyderabad\":\"Hyderabad\",\"Warangal\":\"Warangal\"}";
		System.out.println("------------CommonController---Selected City : "+cityCode);
			
		JSONArray jsonItems = new JSONArray();

		List<AreaBean> areaBeanList = commonService.getAreaListByCity(Integer.valueOf(cityCode));
		
		
		JSONObject jsonItem = null;
		
		for(AreaBean areaBean : areaBeanList){
			System.out.println("City Code : "+areaBean.getCityCode()+"   Area Code : "+areaBean.getAreaCode()+"    Area Desc : "+areaBean.getAreaDesc());

			jsonItem = new JSONObject();
		    jsonItem.put("id", areaBean.getAreaCode());
		    jsonItem.put("name", areaBean.getAreaDesc());
		    jsonItems.put(jsonItem);

		}
		
	    return jsonItems.toString();
	}
	
	//-------movie-------//
	
	@RequestMapping(value="/setupMovie",method = RequestMethod.GET)
	public ModelAndView showMovieSetup(Model model,HttpSession session){
		
		ModelAndView mv = new ModelAndView();
		AccountBean acctBean = null;
		if(null != session.getAttribute("accountBean"))
		{
			MovieBean movieBean=new MovieBean();
			FileBean fileBean = new FileBean();
			
			model.addAttribute("fileBean", fileBean);
			model.addAttribute("movieBean", movieBean);
			mv.setViewName("movieSetup");
			
		}
		else
		{
			//model.put("accountBean", new AccountBean());
			mv.setViewName("redirect:index.html");
			//	response = "accountLogin";	
		}
		
		return mv;
	}
	
	@RequestMapping(value="/saveMovieSetup",method = RequestMethod.POST)
	public String saveMovieSetup(@ModelAttribute MovieBean movieBean,FileBean fileBean,Model model,HttpSession session){
		if(null != session.getAttribute("accountBean"))
		{
			System.out.println("---------------in saveMovieSetup mehod------------" );
		//	if(null!=fileBean.getFileData())
		//	movieBean.setPoster(fileBean.getFileData().getBytes());
			
			System.out.println("movie details: "+movieBean.getMovieName()+","+movieBean.getActor()+","+movieBean.getLanguage()+","+movieBean.getDirector()+",");
			
			commonService.saveMovie(movieBean);
			
		//	String response="redirect:showBusinessList.html";
			
		}
		else
		{
			//model.put("accountBean", new AccountBean());
		//	mv.setViewName("redirect:index.html");
			return  "redirect:index.html";	
		}
		return "redirect:showBusinessList.html";
	}
	
}
