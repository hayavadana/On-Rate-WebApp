<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
 <head>  
    <title>Login -- Page</title>
    <style>
    	#login-box {
    		width: 300px;
    		padding: 20px;
    		margin: 100px auto;
    		background: blue;  /* #fff; */
    		-webkit-border-radius: 2px;
    		-moz-border-radius: 2px;
    		border: 1px solid #000;
    	}

		#container {
			position: fixed;
			width: 340px;
			height: 280px;
			top: 50%;
			left: 50%;
			margin-top: -140px;
			margin-left: -170px;
		}
		
		.button{
			background:blue;
		}

    </style>  
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
  <div id="container">
 <form:form modelAttribute="loginBean" action="processLogin.html" method="POST">

		<h3>Rating -<c:out value="${loginBean.loginType}"/> Login</h3>
     <table>
     <form:hidden path="loginType"/>
	<FONT color="red"><form:errors path="*" /></FONT>
    				<tr>
    					<td>User test:</td>
    					<td><form:input path="loginId" /></td>
    				</tr>
    				<tr>
  						<td>Password:</td>
    					<td><form:password  path="password" /></td>
    				</tr>
    				<tr>
  						<td>&nbsp;</td>
    					<td>&nbsp;</td>
    				</tr>
    				<tr>
    					<td>&nbsp;</td>
    					<td>Click <a href="javascript:registerClick()">here</a> to Register</td>
    				</tr>
    				<tr>
  						<td>&nbsp;</td>
    					<td>&nbsp;</td>
    				</tr>
    				<tr>
    					<td colspan="2" align="right"><input type="submit" name="submit" class="button" value="submit"/></td>
    				</tr>
    			</table>

     		</form:form>
    	</div>  
  </body>  
</html>  