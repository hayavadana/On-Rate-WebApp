<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
 <head>  
 <link href="jsp/stylesheet/screen.css" rel="stylesheet">
 
    <title>Business Setup</title>
    <style>
    	#login-box {
    		width: 800px;
			height: 550px;
    		padding: 20px;
    		margin: 50px auto;
    		background: #fff;
    		-webkit-border-radius: 2px;
    		-moz-border-radius: 2px;
    		border: 1px solid #000;
			background-color: #F8F8F8;
			box-shadow: 5px 5px 20px grey;	// shadow
			
    	}
		#submit {
			background-color: blue; 		//#ccc;
			-moz-border-radius: 5px;
			-webkit-border-radius: 5px;
			border-radius:6px;
			color: #fff;
			font-family: 'Oswald';
			font-size: 15px;
			font-weight: bold;
			text-decoration: none;
			cursor: pointer;
			border:none;
		}
		.time{
			width: 80px;
			
    		padding: 2px;
		}
		#logo{
			box-shadow: 5px 5px 10px grey;
		}
    </style>  
    
   <script src="js/jquery-1.11.4.js"></script>
   <script src="js/jquery-ui-1.11.4.js"></script>
   <!-- <script src="jquery.validate.js"></script> -->
   <script src="js/jquery.validate.js"></script>
   <script src="js/time/jquery.timepicker.min.js"></script>
   <link rel="stylesheet" href="/RateOn Original/WebContent/jsp/stylesheet/styles.css">
   <link rel="stylesheet" type="text/css" href="js/time/jquery.timepicker.css" />
   
   
   <script type="text/javascript">

   	$(document).ready(function() {   		
   		$('#stateDropDown').change(function(){
   	     $("#cityDropDown").html('');    // clear dropdown 
   	     $.getJSON("getCities.html",{stateCode:$('#stateDropDown').val(),countryCode:$('#countryDropDown').val()}, function(mydata){
   	            $("#cityDropDown").append("<option value=''>---------Select---------</option>");
   	            $.each(mydata, function(key, value){
   	               $("#cityDropDown").append('<option value="'+value.id+'">'+value.name+'</option>');
   	            });
   	        });    
   	    });
	});
   	</script>
   	
   	<script type="text/javascript">
   	$(document).ready(function() {
   		$('#cityDropDown').change(function(){
      	     $("#areaDropDown").html('');    // clear dropdown 
      	     $.getJSON("getAreasJSON.html",{cityCode:$('#cityDropDown').val()}, function(mydata){
      	            $("#areaDropDown").append("<option value=''>---------Select---------</option>");
      	            $.each(mydata, function(key, value){
      	               $("#areaDropDown").append('<option value="'+value.id+'">'+value.name+'</option>');
      	            });
      	        });    
      	    });
   	});
   	</script>
   	
   	<script type="text/javascript">
	$(document).ready(function() {
		$.getJSON("getWeekdaysJSON.html", function(mydata){
			 var test = "${accountBean.accountName}";
	         $("#startDayDropDown").append("<option value=''>---------Select---------</option>");
	         $.each(mydata, function(key, value){
			 $("#startDayDropDown").append('<option value="'+value.id+'">'+value.name+'</option>');   	            });
 	    });
   	});
	</script>
  	
  	<script type="text/javascript">
	function showCouponSetup(){
		alert('Coupon Setup...');
		document.test.action = "couponsList.html";
		document.test.submit();
	}	
	
	document.getElementById("logo").src="data:image/png;base64,"+${logoBean.logoData};
	
	</script> 	
	
	<!-- ----------------------validation---------------------  -->
	<script type="text/javascript">
	

	$.validator.setDefaults({
		submitHandler: function() {
			alert("submitted!");
		}
	});
	jQuery.validator.addMethod("latTest", function(value, element) {
	    if(value!="") {return /^-?([1-8]?[1-9]|[1-9]0)\.{1}\d{1,8}$/i.test(value);}
	    else return true;
	});
	jQuery.validator.addMethod("longTest", function(value, element) {
	    if(value!="") {return /^(-?(?:1[0-7]|[1-9])?\d(?:\.\d{1,8})?|180(?:\.0{1,8})?)$/i.test(value);}
	    else return true;
	});
	jQuery.validator.addMethod("urlTest", function(value, element) {
	    if(value!="") {return /^(http(s)?:\/\/)?(www\.)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/i.test(value);}
	    else return true;
	});
	jQuery.validator.addMethod("pincode", function(value, element) {
	   	return /^\d{6}$/i.test(value);
	});
	 jQuery.validator.setDefaults({
         messages: { 
            required: "Required",
        },
     });
$().ready(function() { 
	
	jQuery.extend(jQuery.validator.messages, {
		  required: 'required',
		  remote: 'required'
		});

	     
    $("#businessForm").validate({
        rules: {
            "businessBean.businessName": {
                required: true,
                minlength: 3,
                maxlength: 25
            },
            "businessBean.businessDesc": {
                required: true, 
                minlength: 6,
                maxlength: 250
            },
            "businessBean.websiteUrl":{
            //	url:true,
            	urlTest: true,
            },
            "addressBean.longitude":{
            	longTest: true,
            },
            "addressBean.latitude":{
            	latTest: true,
            },
            "addressBean.postalCode":{
            	pincode: true,
            },
            
           
        },
        messages: {
            'businessBean.businessName': {
              
                minlength: "min 3 characters"
            },
            'businessBean.businessDesc': {
                
                minlength: "min 6 characters",
                maxlength: "max 250 characters"
            },
            "businessBean.startHours":{
            	required: "",
            },
            "businessBean.endHours":{
            	required: "",
            },
            "addressBean.longitude":{
            	longTest: "not valid",
            },
            "addressBean.latitude":{
            	latTest: "not valid",
            },
            "businessBean.endDay":{
            	required: "",
            },
            "businessBean.startDay":{
            	required: "",
            },
            "addressBean.lineOne":{
            	required: "required!",
            },
            "businessBean.websiteUrl":{
            	urlTest : "URL not valid"
            },
            "addressBean.postalCode":{
            	pincode: "not valid",
            },
            
        }
        , 
        submitHandler: function (form) {
            form.submit();  
            alert('valid form');
        }
        
    });
    
});

</script> 
	
	<!-- ------------------------------------validation-- End------------------------  -->
	<script>
                $(function() {
                    $('.time').timepicker();
                });
                
    		   function readURL(input) {
                       if (input.files && input.files[0]) {
                           var reader = new FileReader();

                           reader.onload = function (e) {
                               $('#logo')
                                   .attr('src', e.target.result)
                                   /* .width(350)
                                   .height(200); */
                           };

                           reader.readAsDataURL(input.files[0]);
                       }
                   }
    </script>
</head> 
  <body>  

 <form:form name="test" modelAttribute="businessForm" id="businessForm" class="businessForm" action="saveBusinessSetup.html" enctype="multipart/form-data" method="POST">
  <div id="login-box">
		<center>
	<%-- 	<c:if test="${not empty businessSetupId}"><form:hidden path="businessBean.isActive"/></c:if>
		<c:if test="${not empty businessSetupId}"><form:hidden path="businessBean.createdDate"/></c:if>
	 --%>	<table width="100%">
			<tr colspan="4">
				<th><h2>Rating - Business Setup -- <c:out value="${accountBean.accountName}"/></h2></th>
				
			</tr>
			<tr >
				<th>&nbsp;</th>
				<th><c:if test="${not empty businessSetupId}"><a href="couponsList.html?businessSetupId=${businessSetupId}">Coupon</a></c:if>
</th>
				<th><a href="showBusinessList.html">Business List</a></th>
				<th><a href="logout.html">Logout</a></th>
			</tr>
		</table>
	</center>
    <FONT color="red"><form:errors path="*" /></FONT>
			<table>
					<tr>
    					<td><b><u>Business :</u></b></td>
    					
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
    				</tr>
					<tr>
						<td>&nbsp;</td>
    					<td>&nbsp;</td><form:hidden path="businessBean.businessId"/>
    					<td>Name : </td> <form:label path="businessBean.businessName"><spring:message code="label.businessName"/> </form:label>
						<td><form:input path="businessBean.businessName" required="true"/><FONT color="red">*</FONT></td>
						<td>&nbsp;</td>
				<%-- <c:if test="${not empty businessSetupId}">	 --%>	
						<c:if test="${image == null}">
							<td rowspan=3><img id="logo" hight="80dp" width="100dp" src="<%=request.getContextPath()%>/jsp/images/noLogo.png" /></td>
						</c:if>
						<c:if test="${image != null}">
							<td rowspan=3><img src='data:image/jpg;base64,${image}' id="logo" alt="Logo" hight="80dp" width="100dp"/></td>
						</c:if>
				<%-- </c:if>	 --%>	
						
						
    				</tr>
					<tr>
						<td>&nbsp;</td>
    					<td>&nbsp;</td>
    					<td>Description : </td>
						<td ><form:textarea  path="businessBean.businessDesc" rows="3" cols='20'  required="true"/><FONT color="red">*</FONT></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
    				</tr>
    				<tr>
						<td>&nbsp;</td>
    					<td>&nbsp;</td>
						<td>Category : </td>
						<td><form:select path="businessBean.categoryCode" items="${categoryList}"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
    				</tr>
    				<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
    					<td>Working Days :</td>
						<td>
						<form:select path="businessBean.startDay" required="true">
								<form:option value="" label="---------Select---------"/>
								<form:option value="MON" label="Monday"/>
								<form:option value="TUE" label="Tuesday"/>
								<form:option value="WED" label="Wednesday"/>
								<form:option value="THU" label="Thursday"/>
								<form:option value="FRI" label="Friday"/>
								<form:option value="SAT" label="Saturday"/>
								<form:option value="SUN" label="Sunday"/>
							</form:select><FONT color="red">*</FONT> <!-- <div class='mandatory'>*</div> -->
						</td> 
						<td>To : </td>
						<td> <form:select path="businessBean.endDay" required="true">
								<form:option value="" label="---------Select---------"/>
								<form:option value="MON" label="Monday"/>
								<form:option value="TUE" label="Tuesday"/>
								<form:option value="WED" label="Wednesday"/>
								<form:option value="THU" label="Thursday"/>
								<form:option value="FRI" label="Friday"/>
								<form:option value="SAT" label="Saturday"/>
								<form:option value="SUN" label="Sunday"/>
							</form:select><FONT color="red">*</FONT><!-- <div class='mandatory'>*</div> -->
						</td> 
					</tr>
    				<tr>
						<td>&nbsp;</td>
    					<td>&nbsp;</td>
						<td>Working Hours :</td>
						<td><form:input path="businessBean.startHours" id="starttime" class="time" 	required="true"/><FONT color="red">*</FONT> &nbsp;To :&nbsp;<form:input path="businessBean.endHours" class="time"  required="true"/><FONT color="red">*</FONT></td>
						<td>Exception String :</td>
						<td><form:input path="businessBean.exceptionStr"/></td>
    				</tr>
				
    				<tr>
						<td>&nbsp;</td>
    					<td>&nbsp;</td>
						<td>Website : </td>
						<td><form:input path="businessBean.websiteUrl"/> </td>
						<td>SMS Required? :</td>
    					<td><form:checkbox path="businessBean.smsRequired" value="Y"/></td>
    				</tr>
    				
					<tr> 
						<td>&nbsp;</td>
    					<td>&nbsp;</td>
						<td>Company Logo: </td>
						<td><input type="file" name="fileBean.fileData" onchange="readURL(this);" /> </td>
						<!-- <td>&nbsp;</td>
    					<td>&nbsp;</td> -->
    					<td>Phone number</td>
    					<td><form:input path="businessBean.phoneNumber" /></td>
						
    				</tr>

					 <tr>
    					<td><b><u>Address : </u></b></td><form:hidden path="addressBean.addressId"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
    				</tr>
					 <tr>
						<td>&nbsp;</td>
    					<td>&nbsp;</td>
						<td>Line 1 :</td>
						<td><form:input path="addressBean.lineOne" required="true"/><FONT color="red">*</FONT></td>
						<td>Country </td>
						<td><form:select path="addressBean.country" items="${countryMap}" id="countryDropDown" disabled="true"/><FONT color="red">*</FONT></td>
					  </tr>
					 <tr>
						<td>&nbsp;</td>
    					<td>&nbsp;</td>
						<td>Line 2 :</td>
						<td><form:input path="addressBean.lineTwo" required="true"/><FONT color="red">*</FONT></td>
						<td>State :</td>
						<td><form:select path="addressBean.state" items="${statesMap}" id="stateDropDown" /></td>
						</tr>
					 <tr>
						<td>&nbsp;</td>
    					<td>&nbsp;</td>
						<td>Longitude :</td>
						<td><form:input path="addressBean.longitude"/></td>
						<td>City :</td>
						<td>
							<form:select path="addressBean.city" items="${citiesMap}"  id="cityDropDown" required="true"/><FONT color="red">*</FONT>
						</td>
    				</tr>
					<tr>
						<td>&nbsp;</td>
    					<td>&nbsp;</td>
						<td>Latitude :</td>
						<td><form:input path="addressBean.latitude"/></td>
						<td>Area :</td>
						<td><form:select path="addressBean.areaCode" items="${areaMap}" id="areaDropDown" required="true"/><FONT color="red">*</FONT></td>
					</tr>
    				<tr>
						<td>&nbsp;</td>
    					<td>&nbsp;</td>
						<td>Landmark :</td>
						<td><form:input path="addressBean.landmark"/></td>
						<td>Postal Code :</td>
    					<td><form:input path="addressBean.postalCode" required="true"/><FONT color="red">*</FONT></td>
    				</tr>
    			</table>
		
		<center>
			<table width="100%">
				<tr >
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th><input type="submit" name="submit" value="Save"  id="submit"/></th>
				
			</tr>
		</table>
		</center>
    	</div>  
    		</form:form>
  </body>  
</html>   


