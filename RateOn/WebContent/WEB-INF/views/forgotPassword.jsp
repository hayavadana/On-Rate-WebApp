<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
 <head>  
    <title>Forgot Password</title>
    
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
    </style>  
   
	<script src="js/jquery-1.11.4.js"></script>
   <script src="js/jquery-ui-1.11.4.js"></script>
   <script src="js/jquery.validate.js"></script>
   	
    <script type="text/javascript">
    jQuery.validator.addMethod("emailValidate", function(value, element) {
		var mailTest=/^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$/;
	    return /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/i.test(value);
	});
   
    $().ready(function() {  
    	var mailTest=/^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$/;
        $("#forgot").validate({
            rules: {
            	"emailId": {
                    required: true,
                    emailValidate:true,
                   },
               
            },
            messages: {
            	'emailId': {
                    required: "email required!",
                    emailValidate: "enter proper mail id",
                    //minlength: "username must be at least 5 characters long",
                     
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
  <script type="text/javascript">
	function registerClick()
	{
		alert('Register...');
		document.getElementById("form_id").action = "showRegister.html";
		document.getElementById("form_id").submit();
	}
</script>

  <body>  

 <form:form modelAttribute="accountBean" id="forgot" action="processForgotPassword.html" method="POST">
  <div id="login-box">
		<center>
		<table width="100%">
			<tr colspan="3">
				<th align = "left"><h2>Rating - Forgot Password </h2></th>
			</tr>
			<tr >
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th><a href="index.html">Back to Login</a></th>
			</tr>
		</table>
	</center>
	<center>
     <table>
  
	<FONT color="red"><form:errors path="*" /></FONT>
    	
    		<tr colspan="2">
    				<td align = "center">Email Id : <form:input  path="emailId" /></td>
 
    		</tr>
			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    		</tr>
    		<tr colspan="2">
  					<td align = "center">Your password will be sent to your email.</td>
    		</tr>
        	<tr>
  					<td>&nbsp;</td>
    				<td>&nbsp;</td>
    		</tr>
    		<tr>
					<td>&nbsp;</td>
    				<td align="right"><input type="submit" name="submit" value="Get Password" id ="submit"/></td>
    		</tr>
   </table>
</center>
    	</div>  
    		</form:form>
  </body>  
</html>  