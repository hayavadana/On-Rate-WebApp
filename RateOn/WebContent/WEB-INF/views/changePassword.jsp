<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
 <head>  
    <title>Change Password</title>
    
    <link rel="stylesheet" href="jsp/stylesheet/screen.css">
   <link rel="stylesheet" href="/RateOn Original/WebContent/jsp/stylesheet/styles.css">
   
    <style>
    	#login-box {
    		width: 600px;
			height: 300px;
	 		padding: 20px;
    		margin: 100px auto;
    		background: #fff;
    		-webkit-border-radius: 2px;
    		-moz-border-radius: 2px;
    		border: 1px solid #000;
			background-color: #F8F8F8;
			box-shadow: 10px 10px 20px grey;	// shadow
    	}
		#submit {
			background-color: blue;  /* #ccc; */
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
    </style>  
    
    <script src="js/jquery-1.11.4.js"></script>
   <script src="js/jquery-ui-1.11.4.js"></script>
   <script src="js/jquery.validate.js"></script>
   	<script type="text/javascript">
   	jQuery.validator.addMethod("passwordVal", function(value, element) {
	    return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]){5,}$/.test(value);		//return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{5,}$/.test(value);
	});
   	
   	$().ready(function() {  
   		var mailTest=/^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$/;
   	    $("#changePassword").validate({
   	        rules: {
   	        
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
   	              
   	        },
   	        messages: {
   	        	
   	            'password': {
   	                required: "password required",
   	                minlength: "min 5 characters long",
   	                passwordVal :"enter valid password"
   	            },
   	            "reenterPassword":{
   	            	required: "required",
   	            	equalTo: "passowrd not matched",
   	            },
    
   	        },
   	         
   	        submitHandler: function (form) {
   	            form.submit();  
   	            alert('valid form');
   	        } 
   	        
   	    });

   	});

	
   	</script>
  </head>  

  <body>  

 <form:form modelAttribute="accountBean" id="changePassword" action="processChangePassword.html" method="POST">
  <div id="login-box">
	<center>
		<table width="100%">
			<tr colspan="3">
				<th align = "left"><h2>Rating - Change Password -- <c:out value="${accountBean.accountName}"/></h2></th>
				
			</tr>
			<tr >
				
				<th>&nbsp;</th>
				<th><a href="showBusinessList.html">Business List</a></th>
				<th><a href="logout.html">Logout</a></th>
			</tr>
		</table>
	</center>
	<center>
  <table>
  
	<FONT color="red"><form:errors path="*" /></FONT>
	
			<tr>
					<form:hidden  path="accountId" />
    				<td align = "left">Account Name : </td>
    				<td><form:input  path="accountName" readonly="true"/></td>
    		</tr>
       		<tr>
    				<td align = "left">Email Id :</td>
    				<td><form:input  path="emailId" readonly="true" /></td>
    		</tr>
    		<tr>
  					<td align = "left">New Password :</td>
    				<td><form:password  path="password" /></td>
    		</tr>
    		<tr>
  					<td align = "left">Re-enter Password:</td>
    				<td><form:password  path="reenterPassword" /></td>
    				</tr>
        	<tr>
  					<td>&nbsp;</td>
    				<td>&nbsp;</td>
    		</tr>
    		<tr>
    				<td colspan="2" align="right"><input type="submit" name="submit" value="Change Password"  id="submit"/></td>
    		</tr>
   </table>
</center>
    	</div>  
    		</form:form>
  </body>  
</html>  