package com.hayavadana.rating.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.hayavadana.rating.bean.AccountBean;
import com.hayavadana.rating.bean.FileBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;
import java.util.List;

@Controller
public class UploadController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value="/fileUpload",method=RequestMethod.GET)
	public String showUploadPage(Map model){
		System.out.println("UploadController -- showUploadPage");
		logger.info("UploadController -- showUploadPage");
		FileBean fileBean = new FileBean();
		
				
		model.put("fileBean",fileBean);
		return "fileUpload";
	}
	
	@RequestMapping(value="/saveFile",method=RequestMethod.POST)
	public String saveUploadedFile(@ModelAttribute("fileBean")FileBean fileBean,BindingResult result, Map model){
		System.out.println("UploadController -- saveUploadedFile");
		logger.info("UploadController -- saveUploadedFile");
		
		System.out.println("UploadController -- saveUploadedFile  --  FileName : "+fileBean.getFileName());
		System.out.println("UploadController -- saveUploadedFile  --  FileData : "+fileBean.getFileData().toString());
			
		
		return "index";
	}
	
	
	
}
