<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
 <head>  
    <title>Login Page</title>
        <link rel="stylesheet" href="/jsp/stylesheet/screen.css">
    
    <style>
    	#login-box {
    		width: 600px;
			height: 250px;
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
		
			.error{
			color: red;
			}
		
		loginform label.error, #commentForm input.submit {
		color: red;
		margin-left: 253px;
	}
    </style>  
    
     <script src="js/jquery-1.11.4.js"></script>
   		  <script src="js/jquery-ui-1.11.4.js"></script>
   		  <script src="js/jquery.validate.js"></script>
   		  <link rel="stylesheet" href="/RateOn Original/WebContent/jsp/stylesheet/styles.css">
   		  
   		  
   		  
	<!-- ----------------------validation---------------------  -->
	<script type="text/javascript">

	
	
$().ready(function() { 
	jQuery.validator.addMethod("emailValidate", function(value, element) {
		var mailTest=/^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$/;
		var mailInput=$('input[name="emailId"]');
	    //return mailTest.test(value);
	    return /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/i.test(value);
	});
	
	
    $("#loginform").validate({
        rules: {
        	"emailId": { 
                required: true,
                emailValidate: true,
            },
            "password": {
                required: true,  
                minlength: 5,
                maxlength: 15
            }
        },
        messages: {
        	emailId: {
        		email: "not a valid email",
        		equalTo: "not a valid email",
                required: "email required!",
                emailValidate: "email not valid",
                
                 
            },
            password: {
                required: "Please enter password",
                minlength: "Password must be at least 5 characters long"
            }
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
	
  </head>  
	
	<body> 
  <script type="text/javascript">
	function registerClick()
	{
		alert('Register...');
		document.getElementById("form_id").action = "showRegister.html";
		document.getElementById("form_id").submit();
	}
	
	/* $(document).ready(function()  */
			function emailv(){
	  //  $('#email').bind('keyup', function() {
	        regex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	       return !regex.test($("#emailId"));
	            /* $(this).next('.error').remove();
	          /*   $(this).after('<div class="error">Wrong</div>'); */
	        /*     return false;
	        } else {
	           /* $(this).next('.error').remove();  */     
	           /* return true; */ 
	       // }
	   // }); 
	}
	//});
	
	//$("#loginform").validate();
</script> 


 
 <div id="login-box">
 <form:form modelAttribute="accountBean" action="processLogin.html" id="loginform" method="POST">
 
		<center>
		<table width="100%">
			<tr colspan="3">
				<th align = "left"><h2>Rating - Login </h2></th>
			</tr>
			<tr >
				<th><FONT color="red">${userNotExist}</FONT></th>
				<th>&nbsp;</th>
				<!-- <th>&nbsp;</th> -->
				<th>&nbsp;</th>
			</tr>
		</table>
	</center>
	<center>
     <table>
  
	<!--<FONT color="red"><form:errors path="*" /></FONT> -->
    				<tr>
    					<td align = "left">Email Id:</td>
    					<td><form:input path="emailId" id="email"  /><FONT color="red"><form:errors path="emailId" /></FONT></td>
    				</tr>
    				<tr>
  						<td align = "left">Password:</td>
    					<td><form:password  path="password"  id="password"/><FONT color="red"><form:errors path="password" /></FONT></td>
    				</tr>
    				<tr>
  						<td>&nbsp;</td>
    					<td>&nbsp;</td>
    				</tr>
    				<tr>
						<!--<td><a href="fileUpload.html">Upload Image</a></td>-->
						<td>&nbsp;</td>
    					<td><a href="showRegister.html">Register</a>&nbsp;&nbsp;&nbsp;<a href="forgotPassword.html">Forgot Password</a> </td>
    				</tr>
    				<tr>
    					<td>&nbsp;</td>
    					<td>&nbsp;</td>
    				</tr>
    				<tr>
    					<td colspan="2" align="right"><input type="submit" name="submit" value="Login" id="submit"/></td>
						
    				</tr>
    			</table>
				</center>
    		</form:form>
	 	</div>  
	 	
	 	 
    
  </body>  
</html>  