package com.hayavadana.rating.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hayavadana.rating.bean.AddressBean;
import com.hayavadana.rating.bean.AreaBean;
import com.hayavadana.rating.bean.BusinessBean;
import com.hayavadana.rating.bean.CategoryBean;
import com.hayavadana.rating.bean.CityBean;
import com.hayavadana.rating.bean.CountryBean;
import com.hayavadana.rating.bean.CouponBean;
import com.hayavadana.rating.bean.FileBean;
import com.hayavadana.rating.bean.LogoBean;
import com.hayavadana.rating.bean.MovieBean;
import com.hayavadana.rating.bean.PropertyBean;
//import com.hayavadana.rating.bean.MailBean;
import com.hayavadana.rating.bean.StateBean;
import com.hayavadana.rating.model.Address;
import com.hayavadana.rating.model.Area;
import com.hayavadana.rating.model.Business;
import com.hayavadana.rating.model.Category;
import com.hayavadana.rating.model.City;
import com.hayavadana.rating.model.Country;
import com.hayavadana.rating.model.Coupon;
import com.hayavadana.rating.model.EndUser;
import com.hayavadana.rating.model.Logo;
import com.hayavadana.rating.model.Movie;
import com.hayavadana.rating.model.MovieRate;
import com.hayavadana.rating.model.Property;
import com.hayavadana.rating.model.State;
import com.hayavadana.rating.model.UserRate;
import com.hayavadana.rating.webservice.rest.json.RateVO;
import com.hayavadana.rating.webservice.rest.vo.MovieVO;
import com.hayavadana.rating.webservice.rest.vo.movieRateVO;

//@Component("commonUtil")
public class CommonUtil {
	
	/*@Autowired
	private JavaMailSender mailSender;*/
	
	public Property getProperty(PropertyBean bean){
		Property prop = new Property();
		
		//prop.setId(id);
		prop.setCategory(bean.getCategory());
		prop.setName(bean.getName());
		prop.setValue(bean.getValue());
		prop.setActiveFlag(bean.getActiveFlag());
		
		return prop;
	}
	public PropertyBean getPropertyBean(Property prop){
		PropertyBean bean = new PropertyBean();
		
		bean.setCategory(prop.getCategory());
		bean.setName(prop.getName());
		bean.setValue(prop.getValue());
		bean.setActiveFlag(bean.getActiveFlag());
		
		return bean;
	}
	public EndUser getEndUser(RateVO rateVO)
	{
		EndUser endUser = new EndUser();
		
		endUser.setEmailId(rateVO.getEmailId());
		endUser.setPhoneNumber(rateVO.getPhoneNumber());
		
		return endUser;
	}
	public UserRate getUserRate(RateVO rateVO)
	{
		UserRate userRate = new UserRate();
		
		userRate.setUserComments(rateVO.getUserComments());
		userRate.setDeviceInfo(rateVO.getDeviceInfo());
		userRate.setUserRate(rateVO.getUserRate());
		userRate.setBusinessId(rateVO.getBusinessId());
		
		return userRate;
	}
	public CouponBean getCouponBean(Coupon coupon){
		CouponBean bean = new CouponBean();
		
		bean.setCouponId(coupon.getCouponId());
		bean.setCouponDesc(coupon.getCouponDesc());
		bean.setStartDate(DateToStringFormatter(coupon.getStartDate()));
		bean.setEndDate(DateToStringFormatter(coupon.getEndDate()));
		bean.setBusinessId(coupon.getBusinessId());
		bean.setIsActive(coupon.getIsActive());
		bean.setCreatedDate(coupon.getCreatedDate());
		
		return bean;
	}
	
	public Coupon getCoupon(CouponBean bean){
		Coupon coupon = new Coupon();

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		try{
		coupon.setCouponId(bean.getCouponId());
		coupon.setCouponDesc(bean.getCouponDesc());
		coupon.setStartDate(formatter.parse(bean.getStartDate()));
		coupon.setEndDate(formatter.parse(bean.getEndDate()));
		coupon.setBusinessId(bean.getBusinessId());
		coupon.setIsActive(bean.getIsActive());
		coupon.setCreatedDate(bean.getCreatedDate());
		}catch(Exception ex){
			System.out.println("Exception : "+ex);
		}
		return coupon;
	}
	public CountryBean getCountryBean(Country country){
		CountryBean ctryBean = new CountryBean();
		ctryBean.setCountryCode(country.getCountryCode());
		ctryBean.setCountryDesc(country.getCountryDesc());
		return ctryBean;
	}
	
	public CategoryBean getCategoryBean(Category category){
		CategoryBean catBean = new CategoryBean();
		catBean.setCategoryCode(category.getCategoryCode());
		catBean.setCategoryDesc(category.getCategoryDesc());
		
		return catBean;
	}
	
	public StateBean getStateBean(State state){
		StateBean stateBean = new StateBean();
		stateBean.setStateCode(state.getStateCode());
		stateBean.setStateDesc(state.getStateDesc());
		
		return stateBean;
	}
	
	public Country getCountry(CountryBean ctryBean){
		Country country = new Country();
		country.setCountryCode(ctryBean.getCountryCode());
		country.setCountryDesc(ctryBean.getCountryDesc());
		return country;
	}
	

	
	public Business getBusiness(BusinessBean bean){
			Business bussModel = new Business();
		//	bussModel.setBusinessId()

			bussModel.setBusinessId(bean.getBusinessId());
			bussModel.setBusinessName(bean.getBusinessName());
			bussModel.setBusinessDesc(bean.getBusinessDesc());
			bussModel.setCategoryCode(bean.getCategoryCode());
			bussModel.setStartDay(bean.getStartDay());
			bussModel.setEndDay(bean.getEndDay());
			bussModel.setStartHours(bean.getStartHours());
			bussModel.setEndHours(bean.getEndHours());
			bussModel.setExceptionString(bean.getExceptionStr());
			bussModel.setSmsRequired(bean.getSmsRequired());
			bussModel.setWebsiteUrl(bean.getWebsiteUrl());
			bussModel.setAcctId(bean.getAcctId());
			bussModel.setPhoneNumber(bean.getPhoneNumber());
			
			return bussModel;
	}
	public BusinessBean getBusinessBean(Business business){
		
		BusinessBean bussBean = new BusinessBean();
		bussBean.setBusinessId(business.getBusinessId());
		bussBean.setBusinessName(business.getBusinessName());
		bussBean.setBusinessDesc(business.getBusinessDesc());
		bussBean.setCategoryCode(business.getCategoryCode());
		bussBean.setStartDay(business.getStartDay());
		bussBean.setEndDay(business.getEndDay());
		bussBean.setStartHours(business.getStartHours());
		bussBean.setEndHours(business.getEndHours());
		bussBean.setExceptionStr(business.getExceptionString());
		bussBean.setSmsRequired(business.getSmsRequired());
		bussBean.setWebsiteUrl(business.getWebsiteUrl());
		bussBean.setAcctId(business.getAcctId());
		bussBean.setIsActive(business.getIsActive()+"");
		bussBean.setPhoneNumber(business.getPhoneNumber());
		
		return bussBean;
	}
	public Address getAddress(AddressBean addrBean){
		Address address = new Address();
		
		address.setAddressId(addrBean.getAddressId());
		address.setLineOne(addrBean.getLineOne());
		address.setLineTwo(addrBean.getLineTwo());
		address.setCityCode(Integer.valueOf(addrBean.getCity()));
		address.setStateCode(addrBean.getState());
		address.setCountryCode(addrBean.getCountry());
		address.setLandmark(addrBean.getLandmark());
		address.setPostalCode(addrBean.getPostalCode());
		address.setBusinessId(addrBean.getBusinessId());
		address.setLongitude(addrBean.getLongitude());
		address.setLatitude(addrBean.getLatitude());
		address.setAreaCode(addrBean.getAreaCode());
		return address;
	}
	
	public AddressBean getAddressBean(Address address){
		AddressBean addrBean = new AddressBean();
		
		addrBean.setAddressId(address.getAddressId());
		addrBean.setLineOne(address.getLineOne());
		addrBean.setLineTwo(address.getLineTwo());
		addrBean.setCity(address.getCityCode()+"");
		addrBean.setState(address.getStateCode());
		addrBean.setCountry(address.getCountryCode());
		addrBean.setLandmark(address.getLandmark());
		addrBean.setPostalCode(address.getPostalCode());
		addrBean.setLongitude(address.getLongitude());
		addrBean.setLatitude(address.getLatitude());
		addrBean.setAreaCode(address.getAreaCode());
		addrBean.setBusinessId(address.getBusinessId());
		
		return addrBean;
	}
	
	public CityBean getCityBean(City city){
		CityBean cityBean = new CityBean();
		
		cityBean.setCityCode(city.getCityCode());
		cityBean.setCityDesc(city.getCityDesc());
		cityBean.setStateCode(city.getStateCode());
		cityBean.setCountryCode(city.getCountryCode());
		
		return cityBean;
	}
	
	public AreaBean getAreaBean(Area area){
		AreaBean bean = new AreaBean();
		
		bean.setCityCode(area.getCityCode());
		bean.setAreaDesc(area.getAreaDesc());
		bean.setAreaCode(area.getAreaCode());
			
		return bean;
	}
	
	public RateVO getRateVO(UserRate userRate){
		RateVO rateVo=new RateVO();
		rateVo.setBusinessId(userRate.getBusinessId());
		rateVo.setDeviceInfo(userRate.getDeviceInfo());
		rateVo.setUserComments(userRate.getUserComments());
		rateVo.setUserRate(userRate.getUserRate());
		rateVo.setCreatedDate(userRate.getCreatedDate());
		
		//rateVo.setEmailId(userRate.get);
		//rateVo.setPhoneNumber(userRate.get);
		return rateVo;
	}
/*	
    private boolean sendMail(MailBean mailBean){
       
        SimpleMailMessage emailMsg = new SimpleMailMessage();
        emailMsg.setTo(mailBean.getMailTo());
        emailMsg.setFrom(mailBean.getMailFrom());
        emailMsg.setSubject(mailBean.getMailSub());
        emailMsg.setText(mailBean.getMailMessage());
       
        mailSender.send(emailMsg);
   
		return true;
	}
    */
    private String DateToStringFormatter(Date date){
    	String strDate = "";
    	
    	strDate = date.getMonth()+"/"+date.getDate()+"/"+date.getYear();
    	
    	return strDate;
    }
    
    ///////////// logo //////////////////
    public Logo getLogo(LogoBean logoBean){
    	Logo logo=new Logo();
    	logo.setBusinessId(logoBean.getBusinessId());
    	logo.setLogoData(logoBean.getLogoData());
    	logo.setLogoName(logoBean.getLogoName());
    	logo.setLogoPath(logoBean.getLogoPath());
    	logo.setLogoId(logoBean.getLogoId());
    	
    	return logo;	
    }
    
    public LogoBean getLogoBean(Logo logo){
    	LogoBean logoBean = new LogoBean();
    	logoBean.setBusinessId(logo.getBusinessId());
    	logoBean.setLogoData(logo.getLogoData());
    	logoBean.setLogoName(logo.getLogoName());
    	logoBean.setLogoPath(logo.getLogoPath());
    	logoBean.setLogoId(logo.getLogoId());
    	return logoBean;
    }
    
    public Logo getLogoFromFileBean(FileBean file){
    	CommonsMultipartFile data = file.getFileData();
    	Logo logo=new Logo();
    	logo.setBusinessId(file.getBusinessId());
    	logo.setLogoData(data.getBytes());
    	logo.setLogoName(data.getOriginalFilename());
    	logo.setLogoPath("FileLocation");
    	return logo;
    }
    
    public FileBean getFileBeanFromLogo(){
		return null;
		}
    
    
    //-------------------------------movie------------------------------//
    
	public MovieVO getMovieVO(Movie movie) {
		MovieVO movieVO=new MovieVO();
		movieVO.setMovie_id(movie.getMovieId());
		movieVO.setMovieName(movie.getMovieName());
		movieVO.setActor(movie.getActor());
		movieVO.setDirector(movie.getDirector());
		movieVO.setLanguage(movie.getLanguage());
		movieVO.setPoster(movie.getPoster());
		return movieVO;
	}
	public movieRateVO getMovieRateVO(MovieRate test) {
		movieRateVO movierateVo= new movieRateVO();
		movierateVo.setDeviceInfo(test.getDeviceInfo());
		movierateVo.setMovieId(test.getmovieId());
		movierateVo.setUserComments(test.getUserComments());
		movierateVo.setUserRate(test.getUserRate());
		movierateVo.setCreatedDate(test.getCreatedDate());
		System.out.println("created date in common util class :"+movierateVo.getCreatedDate().toString());
		
		return movierateVo;
	}
	public EndUser getMovieEndUser(movieRateVO rateVO) {
		EndUser endUser = new EndUser();
		
		endUser.setEmailId(rateVO.getEmailId());
		endUser.setPhoneNumber(rateVO.getPhoneNumber());
		endUser.setCreatedDate(rateVO.getCreatedDate());
		
		return endUser;
	}
	public MovieRate getMovieRate(movieRateVO mRateVO) {
		MovieRate movieRate = new MovieRate();
		
		movieRate.setUserComments(mRateVO.getUserComments());
		movieRate.setDeviceInfo(mRateVO.getDeviceInfo());
		movieRate.setUserRate(mRateVO.getUserRate());
		movieRate.setmovieId(mRateVO.getMovieId());
		
		return movieRate;
	}
	public Movie getMovieFromBean(MovieBean movieBean) {
		Movie movie=new Movie();
		movie.setActor(movieBean.getActor());
		movie.setDirector(movieBean.getDirector());
		movie.setLanguage(movieBean.getLanguage());
	//	movie.setMovieId(movieBean.getMovieId());
		movie.setMovieName(movieBean.getMovieName());
	//	movie.setPoster(movieBean.getPoster());
		movie.setPoster(movieBean.getFileData().getBytes());
		return movie;
	}
}
