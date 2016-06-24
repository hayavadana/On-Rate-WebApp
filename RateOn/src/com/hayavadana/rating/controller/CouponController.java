package com.hayavadana.rating.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;

import com.hayavadana.rating.bean.AccountBean;
import com.hayavadana.rating.bean.BusinessBean;
import com.hayavadana.rating.bean.BusinessForm;
import com.hayavadana.rating.bean.CouponBean;
import com.hayavadana.rating.service.BusinessService;
import com.hayavadana.rating.service.CouponService;
import com.hayavadana.rating.validator.CouponValidator;

@Controller
public class CouponController {

	private static final Logger logger = Logger.getLogger(CouponController.class);
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private CouponValidator couponValidator;
	

	@RequestMapping(value="/couponsList",method= RequestMethod.GET)
	public ModelAndView showCouponsListOfBusiness(Map model,@RequestParam("businessSetupId")Integer bussSetupId,HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		AccountBean acctBean = (AccountBean)session.getAttribute("accountBean");

		CouponBean couponBean = new CouponBean();
		
		logger.info("CouponController -- showCouponsListOfBusiness -- businessSetupId : "+bussSetupId);
	
		BusinessBean bussBean = new BusinessBean();
		bussBean.setBusinessId(bussSetupId);
		bussBean.setAcctId(acctBean.getAccountId());
		bussBean = businessService.getBusinessDetails(bussBean);
		
		couponBean.setBusinessId(bussSetupId);
		couponBean.setBusinessName(bussBean.getBusinessName());
		
		List<CouponBean> couponList = couponService.getAllCoupons(bussSetupId);
		
		model.put("couponBean",couponBean);
		model.put("couponsList",couponList);
		model.put("businessSetupId", bussSetupId);
		mav.setViewName("couponsList");
		
		return mav;
	}
	
	@RequestMapping(value="/saveCoupon", method=RequestMethod.POST)
	public ModelAndView saveCoupon(@ModelAttribute("couponBean") CouponBean couponBean, BindingResult result,Map model,HttpSession session){
		ModelAndView mav = new ModelAndView();
	
		
		logger.info("CouponController -- saveCoupon -- businessSetupId : "+couponBean.getBusinessId());
		logger.info("CouponController -- saveCoupon -- CouponDesc : "+couponBean.getCouponDesc());
		logger.info("CouponController -- saveCoupon -- Valid From : "+couponBean.getStartDate());
		logger.info("CouponController -- saveCoupon -- Valid To : "+couponBean.getEndDate());
		
		couponValidator.validate(couponBean, result);
		if(!result.hasErrors()){
			//AccountBean acctBean = (AccountBean)session.getAttribute("accountBean");
			couponBean = couponService.saveCoupon(couponBean);
			mav.setViewName("redirect:couponsList.html?businessSetupId="+couponBean.getBusinessId());
		}
		else{
			mav.setViewName("couponsList");
			List<CouponBean> couponList = couponService.getAllCoupons(couponBean.getBusinessId());
			model.put("couponsList",couponList);
		}
		
		return mav;
		
	}
	
	@RequestMapping(value="/activateCoupon",method=RequestMethod.GET)
	public ModelAndView acitvateCoupon(Map model,@RequestParam("activateCouponId")Integer couponId,HttpSession session){
		
		ModelAndView mav = new ModelAndView();
	
		System.out.println("CouponController -- acitvateCoupon -- activateCouponId : "+couponId);
		logger.info("CouponController -- acitvateCoupon -- activateCouponId : "+couponId);
		
		AccountBean acctBean = (AccountBean)session.getAttribute("accountBean");
		Integer businessId = couponService.activateCoupon(couponId);
		
		mav.setViewName("redirect:couponsList.html?businessSetupId="+businessId);
		
		return mav;
	}
	
}
