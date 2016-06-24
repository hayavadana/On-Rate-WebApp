<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>movie setup</title>
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
	}
	
	
	#login-box {
    		width: 75%;
			height: 60%;
    		padding: 20px;
    		margin: 50px auto;
    		background: #fff;
    		-webkit-border-radius: 2px;
    		-moz-border-radius: 2px;
    		border: 1px solid #000;
			background-color: #F8F8F8;
			box-shadow: 5px 5px 20px grey;	// shadow
			
    	}
</style>

<script type="text/javascript">
     function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#blah')
                        .attr('src', e.target.result)
                        .width(350)
                        .height(200);
                };

                reader.readAsDataURL(input.files[0]);
            }
        }
</script>

</head>
<body textalign="center">
<div style="hight:10%;text-align:center;">
		<!-- <h2><font color="blue">Add Movie</font></h2>   -->
	</div>
<div id="login-box">
		<center>
		<h2><font color="blue">Add Movie</font></h2>  
<div id='form' style="float:left; width:50%;text-align:center;" >
	<form:form modelAttribute="movieBean" id="movieSetup" action="saveMovieSetup.html" enctype="multipart/form-data" method="POST" >
		<br>
		<table align="center">
			<tr>
				<td><label for="name">Name:</label></td>
				<td><form:input path="movieName" id="name" required="true"/></td>
			</tr>
			<tr>
				<td><label for="language">Language:</label></td>
				<td><form:input path="language" id="language" required="true"/></td>
			</tr>
			<tr>
				<td><label for="actor">Actor:</label></td>
				<td><form:input path="actor" id="actor" required="true"/></td>
			</tr>
			<tr>
				<td><label for="director">Director:</label></td>
				<td><form:input path="director" id="director" /></td>
			</tr>
			<tr>
				<td><label for="poster">upload poster:</label></td>
				  <!-- <td><input type="file" name="fileBean.fileData" onChange="" /></td>  -->
				  <td><form:input path="fileData" type="file" onchange="readURL(this);"/></td>
			<%-- 	<td><form:input type="file" path="fileBean.poster"/> --%>
			</tr>
			<tr><td><input type="submit" name="submit" value="Save"  id="submit"/></td></tr>
		</table>
	</form:form>
	</div>
	<div id='poster' style="float:right; width:50%;text-align:center;">
			<img id="blah" src="#" alt="poster" />
		</div>
</center>
</div>
</body>
</html>