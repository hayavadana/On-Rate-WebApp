package com.hayavadana.rating.controller;

import javax.validation.Valid;
import javax.ws.rs.Produces;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;
import java.util.List;

import com.hayavadana.rating.bean.AccountBean;
import com.hayavadana.rating.service.AccountService;
import com.hayavadana.rating.validator.LoginValidator;
import com.hayavadana.rating.validator.RegistrationValidator;
import com.hayavadana.rating.service.BusinessService;
import com.hayavadana.rating.service.CityService;
import com.hayavadana.rating.service.CountryService;
import com.hayavadana.rating.service.CategoryService;
import com.hayavadana.rating.bean.BusinessBean;
import com.hayavadana.rating.bean.BusinessListForm;
import com.hayavadana.rating.bean.CountryBean;
import com.hayavadana.rating.bean.CategoryBean;


import com.hayavadana.rating.bean.TopicBean;
import com.hayavadana.rating.service.TopicService;

@Controller
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private LoginValidator loginValidator = null;
	
	@Autowired 
	RegistrationValidator registrationValidator = null;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired 
	private CountryService countryService;
	
	@Autowired 
	private CategoryService categoryService;
	
	@Autowired 
	private CityService cityService;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String showLoginPage(Map model){
		//System.out.println("LoginController -- showLoginPage");
		logger.info("LoginController -- showLoginPage");
		AccountBean accountBean = new AccountBean();
		List<CountryBean> countryList = countryService.getCountriesList();
		for(CountryBean ctryBean : countryList){
			logger.info("LoginController -- showLoginPage : "+ctryBean.getCountryCode()+"           " +ctryBean.getCountryDesc());
			//System.out.println(ctryBean.getCountryCode()+"           " +ctryBean.getCountryDesc());
		}
		
		
		model.put("accountBean",accountBean);
		return "accountLogin";
	}
	
	@RequestMapping(value="/showRegister",method=RequestMethod.GET)
	public String showRegister(Map model){
		
		AccountBean accountBean = new AccountBean();
		
		Map<String,String> countryMap   = countryService.getCountriesMap();
		
		model.put("countryMap", countryMap);
		model.put("accountBean",accountBean);
		
		String response ="accountRegister";
		
		//System.out.println("LoginController  -- showRegister");
		logger.info("LoginController  -- showRegister");
		
		
		return response;
	}
	@RequestMapping(value="/forgotPassword",method=RequestMethod.GET)
	public String showForgotPassword(Map model){
		//System.out.println("LoginController  -- showRegister");
		logger.info("LoginController  -- showForgotPassword");
		AccountBean accountBean = new AccountBean();
		model.put("accountBean", accountBean);
		String response = "forgotPassword";
		return response;
		
	}
	
	@RequestMapping(value="/changePassword",method=RequestMethod.GET)
	public String showChangePassword(Map model,HttpSession session){
		String response = "changePassword";
		
		AccountBean accountBean = null;
		
		if(null != session.getAttribute("accountBean"))
		{
			accountBean = (AccountBean)session.getAttribute("accountBean");		
		}
		else
			response ="redirect:index.html";
		model.put("accountBean", accountBean);
		logger.info("LoginController  -- showChangePassword");
		return response;
	}
	
	@RequestMapping(value="/processLogin",method=RequestMethod.POST)
	public String processLogin(@ModelAttribute("accountBean")AccountBean accountBean,BindingResult result, Map model,HttpServletRequest request){
	
		//ModelAndView mv = new ModelAndView();
		AccountBean existingAcctBean = null;
		
		//loginValidator.validate(accountBean,result);
		//ModelAndView mv = new ModelAndView();
		String response = "accountLogin";
		
		logger.info("LoginController -- processLogin : "+accountBean.getEmailId() +"    Password : "+accountBean.getPassword());
		if(!result.hasErrors()){
			
			existingAcctBean = accountService.checkIfAccountExist(accountBean);
			
			if(null != existingAcctBean){
				
				logger.info("LoginController  -- hasErrors  : No");
				logger.info("LoginController  -- Account Bean  Account Id : "+existingAcctBean.getAccountId());
				
				response = "redirect:showBusinessList.html";
				
				HttpSession session = request.getSession(true);
				session.setAttribute("accountBean", existingAcctBean);
			}
			else
			{
				result.rejectValue("emailId","field.user.doesnotexist");
				//result.reject("user.notexist","User doesnt exist or the credentials are invalid");
				//	mv.setViewName("accountLogin");
				response = "accountLogin";
			}
			
		}
		else
		{
			logger.info("LoginController -- hasErrors  : Yes");
			//mv.setViewName("accountLogin");
			response = "accountLogin";
		}
		//mv.addObject("accountBean",accountBean);
		//return mv;
		return response;
	
	}

	
	@RequestMapping(value="/processRegister",method=RequestMethod.POST)
	public ModelAndView processRegister(@ModelAttribute("accountBean")AccountBean accountBean,BindingResult result,HttpServletRequest request){
		
		//String response ="accountRegister";
		//System.out.println("LoginController -- processRegister : "+accountBean.getEmailId());
		logger.info("LoginController -- processRegister : "+accountBean.getEmailId());
		//registrationValidator.validate(accountBean,result);
		ModelAndView mv = new ModelAndView();

		if (result.hasErrors()) {
			mv.setViewName("accountRegister");
			System.out.println("error found:"+result.toString());
	    }
		else
		{
			//registrationValidator.validate(accountBean,result);
		
			if(!result.hasErrors()){
				accountBean =accountService.registerAccount(accountBean);
	            if(null != accountBean){
				//mv.setViewName("result");
				HttpSession session = request.getSession(true);
				session.setAttribute("accountBean", accountBean);
			
				mv.setViewName("redirect:showBusinessList.html");
	            }
	            else
	            {
	            	result.rejectValue("emailId","field.user.alreadyexist");
	            	mv.setViewName("accountRegister");
	            }
			}else
			{
				mv.setViewName("accountRegister");
			}
		}
			mv.addObject("accountBean",accountBean);
		
		
		
		return mv;
	}
	
	@RequestMapping(value="/processForgotPassword",method=RequestMethod.POST)
	public ModelAndView processForgotPassword(@ModelAttribute("accountBean")AccountBean accountBean,BindingResult result){
		//System.out.println("LoginController -- processForgotPassword : "+accountBean.getEmailId());
		logger.info("LoginController -- processForgotPassword : "+accountBean.getEmailId());
		ModelAndView mv = new ModelAndView();
		
		
		AccountBean acctBean = null;
		
		acctBean =accountService.getAccountByEmail(accountBean.getEmailId());
		
		mv.setViewName("result");
		
		mv.addObject("accountBean",accountBean);
		return mv;
		
	}
	
	@RequestMapping(value="/processChangePassword",method=RequestMethod.POST)
	public ModelAndView processChangePassword(@ModelAttribute("accountBean")AccountBean accountBean,BindingResult result,HttpSession session){
		ModelAndView mv = new ModelAndView();
		
		//AccountBean acctBean = new AccountBean();
		
		//System.out.println("------------You are in Change Password");
		
		//System.out.println("------------ Account Name : "+accountBean.getAccountName());
		//System.out.println("------------Email : "+accountBean.getEmailId());
		//System.out.println("------------Password : "+accountBean.getPassword());
		//System.out.println("------------Re-Password : "+accountBean.getReenterPassword());
		
		logger.info("LoginController -- processChangePassword - Account Name : "+accountBean.getAccountName());
		logger.info("LoginController -- processChangePassword - Email : "+accountBean.getEmailId());
		logger.info("LoginController -- processChangePassword - Password : "+accountBean.getPassword());
		logger.info("LoginController -- processChangePassword - Re-Password : "+accountBean.getReenterPassword());
		
//		registrationValidator.validate(accountBean,result);
		
		if(!result.hasErrors()){
			int count = accountService.changePassword(accountBean);
			
			session.invalidate();
			mv.setViewName("redirect:index.html");
			
		}else
		{
			mv.setViewName("changePassword");
		}
		
		mv.addObject("accountBean",accountBean);
		
		
		
		//mv.setViewName("login");
	//	mv.addObject("accountBean",accountBean);
	//	session.invalidate();
	//	mv.setViewName("redirect:index.html");
		return mv;
		
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView logoutProcess(Map model,HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		AccountBean acctBean = (AccountBean)session.getAttribute("accountBean");
		session.invalidate();
		
		mav.setViewName("redirect:index.html");

		return mav;
	}
	
	
	/*@RequestMapping(value="/getCities",method=RequestMethod.GET)
	public @ResponseBody String getCities(@RequestParam("stateCode") String stateCode,@RequestParam("countryCode") String countryCode,Map model) throws JSONException{
		
		
		//return "{\"Hyderabad\":\"Hyderabad\",\"Warangal\":\"Warangal\"}";
		System.out.println("------------LoginController---Selected State : "+stateCode);
		System.out.println("------------LoginController---Selected Country : "+countryCode);
		
		JSONArray jsonItems = new JSONArray();

	    JSONObject jsonItem1 = new JSONObject();
	    jsonItem1.put("id", "1");
	    jsonItem1.put("name", "My Test Project");

	    JSONObject jsonItem2 = new JSONObject();
	    jsonItem2.put("id", "4");
	    jsonItem2.put("name", "Another one");

	    jsonItems.put(jsonItem1);
	    jsonItems.put(jsonItem2);

	    return jsonItems.toString();
		
		
	}*/
	
}
