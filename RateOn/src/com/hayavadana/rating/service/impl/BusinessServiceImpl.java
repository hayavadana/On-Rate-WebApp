package com.hayavadana.rating.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hayavadana.rating.bean.AccountBean;
import com.hayavadana.rating.bean.AddressBean;
import com.hayavadana.rating.bean.BusinessBean;
import com.hayavadana.rating.bean.BusinessForm;
import com.hayavadana.rating.bean.FileBean;
import com.hayavadana.rating.dao.AddressDao;
import com.hayavadana.rating.dao.BusinessDao;
import com.hayavadana.rating.dao.CommonDao;
import com.hayavadana.rating.dao.UserRateDao;
import com.hayavadana.rating.model.Address;
import com.hayavadana.rating.model.Business;
import com.hayavadana.rating.model.Logo;
import com.hayavadana.rating.service.AccountService;
import com.hayavadana.rating.service.AddressService;
import com.hayavadana.rating.service.BusinessService;
import com.hayavadana.rating.service.CategoryService;
import com.hayavadana.rating.service.PropertyService;
import com.hayavadana.rating.util.BusinessUtil;
import com.hayavadana.rating.util.CommonUtil;
import com.hayavadana.rating.util.MailUtil;
import com.hayavadana.rating.webservice.rest.vo.BusinessVO;

import org.apache.log4j.Logger;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class BusinessServiceImpl implements BusinessService {
	private static final Logger logger = Logger.getLogger(BusinessServiceImpl.class);

	@Autowired
	private CommonUtil commonUtil;

	@Autowired
	private BusinessUtil businessUtil;
	
	@Autowired
	private MailUtil mailUtil;
	
	@Autowired
	private BusinessDao businessDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private UserRateDao userRateDao;
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired 
	private AccountService accountService;
	
	@Autowired
	private CategoryService categoryService;
	
	public BusinessForm saveBusiness(BusinessForm busiForm){
		BusinessBean bussBean = null;
		AddressBean addrBean  = null;
		Business business = null;
		Address  address = null;
		try{
		bussBean = busiForm.getBusinessBean();
		addrBean = busiForm.getAddressBean();
		
		business = commonUtil.getBusiness(bussBean);
		address  = commonUtil.getAddress(addrBean);
		
	
		logger.info("BusinessServiceImpl -- saveBusiness -- Saving Business setup ");
		if(business.getBusinessId() != null){
			/*logger.info("BusinessServiceImpl -- updateBusiness -- UPDATING Business setup ");*/
			System.out.println("---------------------UPDATING BUSINESS-------------------------");
			business = businessDao.updateBusiness(business);
			address  = addressDao.updateAddress(address);
			
		}
		else
		{
		business = businessDao.saveBusiness(business);
		
		address.setBusinessId(business.getBusinessId());
		//address = addressService.saveAddress(address);
		address  = addressDao.saveAddress(address);
		logger.info("BusinessServiceImpl -- saveBusiness -- Address of Business setup - AddressId : "+address.getAddressId());
		
		bussBean = commonUtil.getBusinessBean(business);
		addrBean = commonUtil.getAddressBean(address);
		
		FileBean fileBean = busiForm.getFileBean();
		CommonsMultipartFile file = fileBean.getFileData();
		
		InputStream inputStream = null;
		OutputStream outputStream = null;

		if(file.getSize() > 0){
			/*inputStream = file.getInputStream();
			//outputStream = new FileOutputStream("E:\\UploadedFiles\\"+file.getOriginalFilename());
	
			String  fileLoc = propertyService.getProperty("IMAGE_UPLOAD", "FILE_LOCATION").getValue() ;
		
			System.out.println(" File Loc : "+ fileLoc);
			
			File dirFile = new File(fileLoc);
			if(!dirFile.exists()){
				dirFile.mkdir();
			}
			
			outputStream = new FileOutputStream(fileLoc+file.getOriginalFilename());
			
			System.out.println("Uploaded File Name : "+ file.getOriginalFilename());
			
			int readBytes = 0;
			byte[] buffer = new byte[8192];
			while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
				outputStream.write(buffer, 0, readBytes);
			}
			outputStream.close();
			inputStream.close();*/
			
			Logo logo = new Logo();
			logo.setBusinessId(bussBean.getBusinessId());
			logo.setLogoName(file.getOriginalFilename());
			logo.setLogoPath("FileLocation");
			logo.setLogoData(file.getBytes());
				
			System.out.println("Logo BusinessId : "+ logo.getBusinessId());
			System.out.println("Logo File Name : "+ logo.getLogoName());
			System.out.println("Logo Path Name : "+ logo.getLogoPath());
			
			logo = commonDao.saveLogoDetails(logo);
		}
		}
		
		/*Logo logo = new Logo();
		logo.setBusinessId(bussBean.getBusinessId());
		logo.setLogoName(file.getOriginalFilename());
		logo.setLogoPath("FileLocation");
			
		System.out.println("Logo BusinessId : "+ logo.getBusinessId());
		System.out.println("Logo File Name : "+ logo.getLogoName());
		System.out.println("Logo Path Name : "+ logo.getLogoPath());
		
		logo = commonDao.saveLogoDetails(logo);
		*/
		busiForm.setBusinessBean(bussBean);
		busiForm.setAddressBean(addrBean);
		
		}catch(Exception ex){
		System.out.println("-----------Exception in saveBusiness of BusinessServiceImpl - "+ex);	
		}
		return busiForm;
	}
	
	public List<BusinessBean> getBusinessListOfAccount(AccountBean account){
		List<BusinessBean> busiBeanList = new ArrayList<BusinessBean>();
		BusinessBean bussBean = null;
	  //  AddressBean  addrBean = null;
		
		List<Business> businessList = businessDao.getBusinessList();
		
		for(Business business : businessList){
			if(business.getAcctId() == account.getAccountId()){
			bussBean = commonUtil.getBusinessBean(business);	
			bussBean.setCategoryCode(categoryService.getCategoyBeanByCode(bussBean.getCategoryCode()).getCategoryDesc());
			
			Double avg=null;
			avg=userRateDao.getAverageUserRating(business.getBusinessId());
			if(null!=avg){
				//avg=(double) (Math.round (avg * 100) / 100); 
				avg=Math.floor(avg * 100) / 100;
			}
		//	bussBean.setAverageRating(userRateDao.getAverageUserRating(business.getBusinessId()));
			bussBean.setAverageRating(avg);
		 //   addrBean = commonUtil.getAddressBean(addressDao.getAddress(business));   
			busiBeanList.add(bussBean);
			}
		}
		
		return busiBeanList;
		
	}
	
	public List<BusinessVO> getBusinessVOList(String countryCode,String stateCode,String cityCode,String areaCode,String catCode){
			List<BusinessVO> busiBeanVOList = new ArrayList<BusinessVO>();
			BusinessVO bussVO = null;
			AddressBean addrBean = null;
			Address addr = null;
			Logo logo = null;
			Business business = null;
			//List<Business> businessList = businessDao.getBusinessList();
			//List<Business> businessList = businessDao.getBusinessList(countryCode,stateCode,cityCode,areaCode,catCode);
			System.out.println("BusinessServiceImpl----getBusinessVOList ---Before DAO invocation -- AreaCode : "+areaCode);
			List<Address> addressList =addressDao.getAddressListByCityStateAndCity(countryCode, stateCode, cityCode, areaCode);
			//List<Business> businessList = businessDao.getBusinessList(,catCode);
			System.out.println("BusinessServiceImpl----getBusinessVOList ---After DAO invocation  -- addressList Size : "+addressList.size());
		//	for(Business business : businessList){
			for(Address address : addressList){

				System.out.println("BusinessServiceImpl----getBusinessVOList ---with in Loop ");

				addr = address;
				//address  = addressDao.getAddress(business.getBusinessId());

				System.out.println("BusinessServiceImpl----getBusinessVOList ---with in Loop -- Business Id : "+addr.getBusinessId()+"   "+address.getBusinessId());

				business  = businessDao.getBusiness(address.getBusinessId(),catCode);
				if(null != business){
					System.out.println("BusinessServiceImpl----getBusinessVOList ---with in Loop -- Business Id : "+business.getBusinessId()+" Name : "+business.getBusinessName());
					logo = commonDao.getLogoDetails(business.getBusinessId());
					if(null != null){
						System.out.println("BusinessServiceImpl----getBusinessVOList ---with in Loop -- Business Logo Id : "+logo.getBusinessId()+" Name : "+logo.getLogoName());
					}
					bussVO	 = businessUtil.getBusinessVO(business, addr,logo);
					
					//new
					Float avg=null;
					try{
						avg=userRateDao.getAverageUserRating(business.getBusinessId()).floatValue();
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
					bussVO.setAverageRating(avg);
					//new
					busiBeanVOList.add(bussVO);
					}
				}
			System.out.println("BusinessServiceImpl----getBusinessVOList ---Before returning -- List Size : "+busiBeanVOList.size());
			return busiBeanVOList;
	}
	
	public BusinessBean getBusinessDetails(BusinessBean bean){
		Business model = new Business();
		BusinessBean bussBean = null;
		model.setBusinessId(bean.getBusinessId());
		
		model = businessDao.getBusiness(model);
		if(null!=model){
			if(model.getAcctId().equals(bean.getAcctId()))
			bussBean = commonUtil.getBusinessBean(model);
		}
		return bussBean;
		
	}
	
	public BusinessVO getBusinessVODetails(BusinessBean bean){

		Business business = new Business();
		BusinessVO bussVO = null;
		Address address = null;
		Logo logo = null;
		Float avg=null;
		
		
		business.setBusinessId(bean.getBusinessId());
		
		business = businessDao.getBusiness(business);
		address  = addressDao.getAddress(business.getBusinessId());
		logo = commonDao.getLogoDetails(business.getBusinessId());
		bussVO	 = businessUtil.getBusinessVO(business, address,logo);
		try{
			avg=userRateDao.getAverageUserRating(business.getBusinessId()).floatValue();
		}
		catch(Exception ex){
		//	ex.printStackTrace();
		}
		bussVO.setAverageRating(avg); 		//new
		return bussVO;
	}
	
	public List<BusinessBean> getBusinessListByCategory(String catCode){
		
		List<BusinessBean> busiBeanList = new ArrayList<BusinessBean>();
		BusinessBean bussBean = null;
		
		List<Business> businessList = businessDao.getBusinessListByCategory(catCode);
		
		for(Business business : businessList){
			bussBean = commonUtil.getBusinessBean(business);	
			busiBeanList.add(bussBean);
		}
		
		return busiBeanList;
		
	}
	public boolean inactivateBusiness(Integer businessId){
		
		boolean inactivated = false;
		
		inactivated = businessDao.inactivateBusiness(businessId);
		
		return inactivated;
	}
	
	public List<BusinessBean> getBusinessListOfTrailPeriodExpires(){
		List<BusinessBean> busiBeanList = new ArrayList<BusinessBean>();
		BusinessBean bussBean = null;
		boolean inactivateBusiness = false;
		Integer validityDays = 30;
		AccountBean accountBean = null;
		
		//List<Business> businessList = businessDao.getBusinessListByCategory(catCode);
		List<Business> businessList = businessDao.getBusinessListOfTrailPeriodExpires(validityDays);
		
		for(Business business : businessList){
			bussBean = commonUtil.getBusinessBean(business);	
			busiBeanList.add(bussBean);
			inactivateBusiness = false;
			inactivateBusiness = inactivateBusiness(bussBean.getBusinessId());
			if(inactivateBusiness){
			//	sendmail
				accountBean = accountService.getAccount(bussBean.getAcctId());
				mailUtil.sendMailForTrialExpires(accountBean, bussBean.getBusinessName());
				System.out.println("-------------Sending mail for the account of Business...");
			}
				
			System.out.println("-------------Trail Expired Business : "+bussBean.getBusinessId()+" Business Name : "+bussBean.getBusinessName());
		}
		
		return busiBeanList;
	}
}
