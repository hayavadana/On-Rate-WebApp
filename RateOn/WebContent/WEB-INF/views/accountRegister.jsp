<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
 <head>  
    <title>Login Page</title>
    
    <link rel="stylesheet" href="jsp/stylesheet/screen.css">
   <link rel="stylesheet" href="/RateOn Original/WebContent/jsp/stylesheet/styles.css">
   		  
    <style>
        	#login-box {
    		width: 700px;
			height: 300px;
	 		padding: 20px;
    		margin: 100px auto;
    		background: #fff;
    		-webkit-border-radius: 2px;
    		-moz-border-radius: 2px;
    		border: 1px solid #000;
			background-color: #F8F8F8;
			box-shadow: 5px 5px 20px grey;	// shadow
    	}
		#submit {
			background-color: #ccc;
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
		.error{
			color: red;
			}
    </style>  
	  <script src="js/jquery-1.11.4.js"></script>
   <script src="js/jquery-ui-1.11.4.js"></script>
   <script src="js/jquery.validate.js"></script>
   		   
   		      <script type="text/javascript">
   		      
   		   $(document).ready(function() {   		
   	   	     $.getJSON("getCountryListJSON.html", function(mydata){
   			
   	   	            $("#countryDropDown").append("<option value=''>----Select----</option>");
   	   	            $.each(mydata, function(key, value){
   							$("#countryDropDown").append('<option value="'+value.id+'">'+value.name+'</option>');
   						//alert(value.id)
   						
				/*	if(test == value.id)
					{

						$("#startDayDropDown").append('<option selected value="'+value.id+'">'+value.name+'</option>');
					}
					else
					{
   				
					}*/
   	            });
				
   	        });   
   	     	});
  // 	});			//new
   </script> 
 
  <script type="text/javascript">
	/* function registerClick()
	{
		alert('Register...');
		document.getElementById("form_id").action = "showRegister.html";
		document.getElementById("form_id").submit();
	} */
	</script>

	<!-- ----------------------validation---------------------  -->
	<script type="text/javascript">

	jQuery.validator.addMethod("emailValidate", function(value, element) {
		var mailTest=/^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$/;
		var mailInput=$('input[name="emailId"]');
	    //return mailTest.test(value);
	    return /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/i.test(value);
	});
	jQuery.validator.addMethod("mobileValidate", function(value, element) {
	    return /^[0-9]{10}$/i.test(value);
	});
	jQuery.validator.addMethod("passwordVal", function(value, element) {
	  //  return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{5,}$/.test(value);
	  	return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}$/.test(value);
	});
	
$().ready(function() {  
	var mailTest=/^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$/;
    $("#register").validate({
        rules: {
        	'accountName':{
        		required:true,
        		minlength:3,
        		maxlength:15,        		
        	},
        	
        	"emailId": { 
                required: true,
               // email: true,
                emailValidate:true,
            },
            "password": {
                required: true, 
                passwordVal: true,
                minlength: 5,
                maxlength: 15
            },
            "reenterPassword":{
            	required:true,
            	equalTo: "#password",
            	minlength: 5,
            },
            'phoneNumber':{
            	required:true,
            	mobileValidate:true,
            	
            },
            'countryCode':{
            	required:true,
            }
            	
           
            

            
        },
        messages: {
        	'accountName':{
        		required: "Name required",
        		minlength: "min 3 characters",
        		maxlength: "max 15 charecters",        		
        	},
        	'emailId': {
        		email: "not a valid email",
                required: "email required!",
                emailValidate: "enter valid mail id",
                 
            },
            'password': {
                required: "password required",
                minlength: "min 5 characters long",
                passwordVal :"must: A-Z,a-z,0-9, min=5 chars"
            },
            "reenterPassword":{
            	required: "required",
            	equalTo: "passowrd not matched",
            },
            'phoneNumber':{
            	required: "required",
            	minlength: "enter valid number",
            	length: "enter valid number",
            	number: "enter integer only",
            	mobileValidate: "number not valid"
            },
            'countryCode':{
            	required: "required",
            }
            
        },
         // submitHandler is not needed for this case
        submitHandler: function (form) {
            form.submit();  // <-- this is the default
            alert('valid form');
        } 
        
    });

});

</script> 
	
	<!-- ------------------------------------validation-- End------------------------  -->
	
  </head>  

  <body>  

 <form:form modelAttribute="accountBean" id="register" action="processRegister.html" method="POST">
  <div id="login-box">
		<center>
		<table width="100%">
			<tr colspan="3">
				<th align = "left"><h2>Rating - Registration </h2></th>
			</tr>
			<tr >
				<!-- <th>&nbsp;</th> -->
				<th><FONT color="red">${duplicate}</FONT></th>
				<th>&nbsp;</th>
				<th><a href="index.html">Back to Login</a></th>
			</tr>
		</table>
	</center>
	<center>
<!--    <FONT color="red"><form:errors path="*" /></FONT>-->
			<table>
			<tr>
    					<td align = "left">Name :</td>
    					<td align = "left"><form:input path="accountName" /><FONT color="red"><form:errors path="accountName" /></FONT></td>
    				</tr>
					<tr>
    					<td align = "left">Email Id :</td>
    					<td align = "left"><form:input path="emailId" id="email" /><FONT color="red"><form:errors path="emailId" /></FONT></td>
    				</tr>
    				<tr>
  						<td align = "left">Password :</td><span class="ui-icon ui-icon-comment"/>
    					<td align = "left"><form:password id="password" path="password" /><FONT color="red"><form:errors path="password" /></FONT></td>
    				</tr>
    				<tr>
  						<td align = "left">Re-enter Password :</td>
    					<td align = "left"><form:password  path="reenterPassword" /><FONT color="red"><form:errors path="reenterPassword" /></FONT></td>
    				</tr>
					
    				<tr>
  						<td align = "left">Country : </td>
    					<td align = "left"><form:select path="countryCode"   id="countryDropDown">				<%-- items="${countryMap}" --%>
											 <form:option value="" label="-----Select-----"/> 
											<%-- <form:option value="">------select-----</form:option> --%>
											</form:select>
						<FONT color="red"><form:errors path="countryCode" /></FONT> </td>
    				</tr>
					<tr>
  						<td align = "left">Phone Number : </td>
    					<td align = "left"><form:input  path="phoneNumber" /><FONT color="red"><form:errors path="phoneNumber" /></FONT> </td>
    				</tr>
    				<tr>
  						<td>&nbsp;</td>
    					<td>&nbsp;</td>
    				</tr>
    				<tr>
						<td>&nbsp;</td>
    					<td align="right"><input type="submit" name="submit" value="Register"   id="submit"/></td>
    				</tr>
    			</table>
    			<!-- <p><a href="#" id="dialog-link" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-newwin"></span>Open Dialog</a></p>
    			 -->
			</center>
    	</div>  
    		</form:form>
  </body>  
</html>  