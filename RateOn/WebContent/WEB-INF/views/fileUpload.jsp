<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Upload Example</title>
<script language="JavaScript">
function Validate()
{
	var image =document.getElementById("image").value;
	if(image!=''){
		var checkimg = image.toLowerCase();
		if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)){
		alert("Please enter Image File Extensions .jpg,.png,.jpeg");
		document.getElementById("image").focus();
		return false;
	}
}
	return true;
} 
</script>

</head>
	<body>
	<form:form modelAttribute="fileBean"  method="POST" action="saveFile.html" enctype="multipart/form-data" onSubmit="return Validate();">

			<table>
			<tr>
				<td>File Name : <form:input path="fileName"/></td>
				<td>><input type="file" name="fileData" />  </td>
			</tr>
			<tr>
				<td><br /></td>
				<td><input type="submit" value="Upload" /></td>
			</tr>
		</table>
	</form:form>
	</body>
</html>
 
