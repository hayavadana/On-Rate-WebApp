package com.hayavadana.rating.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hayavadana.rating.bean.AccountBean;
import com.hayavadana.rating.bean.AddressBean;
import com.hayavadana.rating.bean.BusinessBean;
import com.hayavadana.rating.bean.BusinessForm;
import com.hayavadana.rating.bean.BusinessListForm;
import com.hayavadana.rating.bean.CityBean;
import com.hayavadana.rating.bean.CountryBean;
import com.hayavadana.rating.bean.FileBean;
import com.hayavadana.rating.bean.LogoBean;
import com.hayavadana.rating.bean.PropertyBean;
import com.hayavadana.rating.service.AddressService;
import com.hayavadana.rating.service.BusinessService;
import com.hayavadana.rating.service.CategoryService;
import com.hayavadana.rating.service.CityService;
import com.hayavadana.rating.service.CommonService;
import com.hayavadana.rating.service.CountryService;
import com.hayavadana.rating.service.LogoService;
import com.hayavadana.rating.service.PropertyService;
import com.hayavadana.rating.service.StateService;
import com.hayavadana.rating.validator.BusinessSetupValidator;

@Controller
public class BusinessController {

	private static final Logger logger = Logger.getLogger(BusinessController.class);

	@Autowired
	private BusinessService businessService;

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired 
	private BusinessSetupValidator businessSetupValidator;
	
	@Autowired 
	private StateService stateService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private LogoService logoService;
	
	@Autowired PropertyService propertyService; 
	
	@RequestMapping(value = "/setupBusiness",method=RequestMethod.GET)
	public String showNewBusinessPage(Map model,HttpSession session){
		logger.info("BusinessController -- showNewBusinessPage");
		String resultPage= "businessSetup";
		BusinessForm busiForm = new BusinessForm();
	
		Map<String,String> categoryList = categoryService.getCategoriesMap();
		Map<String,String> countryMap   = countryService.getCountriesMap();
		
		AccountBean acctBean = null;
		AddressBean addrBean = new AddressBean();
		FileBean fileBean = new FileBean();
		CountryBean ctryBean = new CountryBean();
		
		if(null != session.getAttribute("accountBean"))
		{
			logger.info("BusinessController -- showNewBusinessPage --Session : "+session);
			acctBean = (AccountBean)session.getAttribute("accountBean");
			
			BusinessBean busBean = new BusinessBean();
			busBean.setAcctId(acctBean.getAccountId());
			busiForm.setBusinessBean(busBean);
			
			logger.info("BusinessController -- showNewBusinessPage --acctBean Account Id : "+acctBean.getAccountId());
			addrBean.setCountry(acctBean.getCountryCode());
			busiForm.setAddressBean(addrBean);
			busiForm.setFileBean(fileBean);
			ctryBean.setCountryCode(acctBean.getCountryCode());	
		}
		else
			resultPage = "redirect:index.html";
		
		Map<String,String> statesMap    = stateService.getStatesMapOfCountry(ctryBean);
		
		model.put("categoryList", categoryList);
		model.put("countryMap", countryMap);
		model.put("statesMap",statesMap);
		
		model.put("businessForm",busiForm);
		
		return resultPage;
	}
	
	@RequestMapping(value ="/saveBusinessSetup",method=RequestMethod.POST)
	public ModelAndView saveNewBusiness(@ModelAttribute("businessForm") BusinessForm busiForm,BindingResult result,Map model,HttpSession session){
		ModelAndView mv = new ModelAndView();
		
		AccountBean acctBean = null;
		FileBean fileBean = null;
		
		
		if(null != session.getAttribute("accountBean"))
		{
			acctBean = (AccountBean)session.getAttribute("accountBean");
			logger.info("BusinessController -- saveNewBusiness --acctBean Account Id : "+acctBean.getAccountId());
			busiForm.getBusinessBean().setAcctId(acctBean.getAccountId());
			busiForm.getAddressBean().setCountry(acctBean.getCountryCode());
	//	}
		
	//	businessSetupValidator.validate(busiForm, result);
		
		  if(!result.hasErrors()){		//add !
				
			fileBean = busiForm.getFileBean();
			System.out.println("FileBean : "+fileBean.getFileData());
			System.out.println("------No error saving busness---------BusinessControll----------------Selected City : "+busiForm.getAddressBean().getCity());
					
			BusinessForm bForm = businessService.saveBusiness(busiForm);
			System.out.println("saved busness business: :"+bForm.getBusinessBean().toString());
			System.out.println("saved busness address :"+bForm.getAddressBean().toString());
			System.out.println("saved busness file :"+bForm.getFileBean().toString());
			mv.setViewName("redirect:showBusinessList.html");
			
			 /*fileBean = busiForm.getFileBean();
			  System.out.println("FileBean : "+fileBean.getFileData());
			  // commmented block
			  BusinessForm bForm = businessService.saveBusiness(busiForm);
				mv.setViewName("redirect:showBusinessList.html");
				System.out.println("saved busness is :"+bForm.toString());
			
			mv.setViewName("businessSetup");
			*/
		  }
		  else
		  {
			//mv.setViewName("redirect:showBusinessSetup.html");
		
			CountryBean ctryBean = new CountryBean();
			
			if(null != session.getAttribute("accountBean"))
			{
				acctBean = (AccountBean)session.getAttribute("accountBean");
				
				ctryBean.setCountryCode(acctBean.getCountryCode());	
			}
			Map<String,String> statesMap    = stateService.getStatesMapOfCountry(ctryBean);
			
			
			Map<String,String> categoryList = categoryService.getCategoriesMap();
			Map<String,String> countryMap   = countryService.getCountriesMap();
		
			
			
			List<CityBean> cityBeanList = cityService.getAllCitiesByStateCountry(busiForm.getAddressBean().getState(), busiForm.getAddressBean().getCountry());
			Map<String,String> cityMap = new HashMap<String,String>();
			for(CityBean city: cityBeanList){
				cityMap.put(city.getCityCode() + "", city.getCityDesc());
			}
			
			Map<String,String> areaMap = null;
			
			if(null != busiForm.getAddressBean().getCity())
				areaMap = commonService.getAreaMapByCity(Integer.valueOf(busiForm.getAddressBean().getCity()));
			
			model.put("categoryList", categoryList);
			model.put("countryMap", countryMap);
			model.put("businessForm",busiForm);
			model.put("statesMap",statesMap);
			model.put("citiesMap",cityMap);
			model.put("areaMap",areaMap);
			
			model.put("businessForm",busiForm);
			
			//model.put("businessSetupId", busiForm.getBusinessBean().getBusinessId());

			mv.setViewName("businessSetup");
			
		  }
	
		 /* fileBean = busiForm.getFileBean();
		  System.out.println("FileBean : "+fileBean.getFileData());
		  // commmented block
		  BusinessForm bForm = businessService.saveBusiness(busiForm);
			mv.setViewName("redirect:showBusinessList.html");
			System.out.println("saved busness is :"+bForm.toString());*/
		   //end of commented block
		
		}					// new
		else{
			
			mv.setViewName("redirect:index.html");
		}
		return mv;
		
	}
	
	@RequestMapping(value="/showBusinessSetup",method= RequestMethod.GET)
	public ModelAndView showBusinessDetails(Map model,@RequestParam("businessSetupId")String bussSetupId,HttpSession session,HttpServletResponse response){
		
		ModelAndView mv = new ModelAndView();		//new edit
		
	if(null != session.getAttribute("accountBean"))
	{
		BusinessBean busiBean = new BusinessBean();
		logger.info("BusinessController -- showBusinessSetup -- QueryParam : "+bussSetupId);
		BusinessForm busiForm = new BusinessForm();
		
		 
		AddressBean addrBean = new AddressBean();
		CountryBean ctryBean = new CountryBean();
		
		AccountBean acctBean = (AccountBean)session.getAttribute("accountBean");
		System.out.println("AcctId: in controller:"+acctBean.getAccountId());
		
		busiBean.setBusinessId(Integer.valueOf(bussSetupId));
		busiBean.setAcctId(acctBean.getAccountId());
		busiBean = businessService.getBusinessDetails(busiBean);
		
		if(null==busiBean){
			System.out.println("busiBen is null:");
		//	mv.setViewName("result");
			mv.setViewName("redirect:showBusinessList.html");
			return mv;
		}
		else{
		///////////////////////// logo /////////////////
		
		FileBean fileBean = new FileBean(); 		//fileBean
		LogoBean logoBean =new LogoBean();  
		logoBean=logoService.getLogoBean(Integer.parseInt(bussSetupId));
		/*if(logoBean.getLogoData().length!=0);//System.out.println("logo data:"+logoBean.getLogoData());   //null
		else System.out.println("logo is null");*/
		String encodedImage=null;
		try{
		encodedImage = Base64.encode(logoBean.getLogoData());
		}
		catch(Exception e){
			System.out.println("no logo found");
		}
		model.put("image", encodedImage);
		
		
				
		
	/*	if(null != session.getAttribute("accountBean"))
		{	*/
		/*	logger.info("BusinessController -- showNewBusinessPage --Session : "+session);
			acctBean = (AccountBean)session.getAttribute("accountBean");
		*/	
			BusinessBean busBean = new BusinessBean();
			busBean.setAcctId(acctBean.getAccountId());
			busiForm.setBusinessBean(busBean);
			
			logger.info("BusinessController -- showNewBusinessPage --acctBean Account Id : "+acctBean.getAccountId());
			addrBean.setCountry(acctBean.getCountryCode());
			busiForm.setAddressBean(addrBean);
			ctryBean.setCountryCode(acctBean.getCountryCode());	
	//	}      //if for session
		Map<String,String> statesMap    = stateService.getStatesMapOfCountry(ctryBean);
		
		
		Map<String,String> categoryList = categoryService.getCategoriesMap();
		Map<String,String> countryMap   = countryService.getCountriesMap();
		
		
		/*busiBean.setBusinessId(Integer.valueOf(bussSetupId));
		busiBean.setAcctId(acctBean.getAccountId());
		busiBean = businessService.getBusinessDetails(busiBean);
		*/
		
		
		addrBean = addressService.getAddress(Integer.valueOf(bussSetupId));
		List<CityBean> cityBeanList = cityService.getAllCitiesByStateCountry(addrBean.getState(), addrBean.getCountry());
		Map<String,String> cityMap = new HashMap<String,String>();
		for(CityBean city: cityBeanList){
			cityMap.put(city.getCityCode() + "", city.getCityDesc());
		}
		
		Map<String,String> areaMap = commonService.getAreaMapByCity(Integer.valueOf(addrBean.getCity()));
		
		List<PropertyBean> weekDaysList = propertyService.getPropertyList("WEEK_DAYS");
		Map<String,String> weekDaysMap = new HashMap<String,String>();
		for(PropertyBean bean : weekDaysList)
			weekDaysMap.put(bean.getName(),bean.getValue());
	
		busiForm.setBusinessBean(busiBean);
		busiForm.setAddressBean(addrBean);
		
		//model.put("businessBean",busiBean);
		
		model.put("businessForm",busiForm);
		model.put("weekDaysMap", weekDaysMap);
		model.put("categoryList", categoryList);
		model.put("countryMap", countryMap);
		model.put("statesMap",statesMap);
		model.put("citiesMap",cityMap);
		model.put("areaMap",areaMap);
		
		model.put("businessSetupId", bussSetupId);
		
		
		mv.addObject(model);    //new
		//ModelAndView mv = new ModelAndView();     //old 
		mv.setViewName("businessSetup");
		}
	//	return mv;
		}				//new 
		else{
			
			mv.setViewName("redirect:index.html");
		}
		
		return mv;
						//  /new
	}
	
	@RequestMapping(value ="/imageDisplay", method = RequestMethod.GET)
	  public void showImage(@RequestParam("businessSetupId")Integer bussSetupId, HttpServletResponse response,HttpServletRequest request) 
	          throws ServletException, IOException{

		
		FileBean fileBean = new FileBean(); 		//fileBean
		LogoBean logoBean =new LogoBean();  
		logoBean=logoService.getLogoBean(bussSetupId);
		System.out.println("------in imege controll----------");
		
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	    try {
			response.getOutputStream().write(logoBean.getLogoData());
			System.out.println("------imege added----------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

	    response.getOutputStream().close();
	}
	
	@RequestMapping(value="/showBusinessList",method = RequestMethod.GET)
	public ModelAndView showBusinessSetupsList(Map model,HttpSession session){
		
		ModelAndView mv = new ModelAndView();
		AccountBean acctBean = null;
		if(null != session.getAttribute("accountBean"))
		{
			acctBean = (AccountBean)session.getAttribute("accountBean");

			logger.info("BusinessController -- showBusinessSetupsList -- EmailId : "+acctBean.getEmailId()+"    Account Id : "+acctBean.getAccountId());
			
			List<BusinessBean> busiBeanList = businessService.getBusinessListOfAccount(acctBean);
			
			BusinessListForm businessListForm = new BusinessListForm();
			businessListForm.setBusinessList(busiBeanList);
			//model.put("businessList", busiBeanList);
			model.put("businessListForm", businessListForm);
			mv.setViewName("businessList");
			
		}
		else
		{
			//model.put("accountBean", new AccountBean());
			mv.setViewName("redirect:index.html");
			//	response = "accountLogin";	
		}
		
		return mv;
	}
}
