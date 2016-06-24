<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
 <head>  
    <title>Business List</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/s/dt/dt-1.10.10/datatables.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.10/css/dataTables.jqueryui.min.css"/>
    <link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css"/>
    <style>
    	#login-box {
    		width: 850px;
			height: auto;
    		padding: 20px;
    		margin: 100px auto;
    		border: 1px solid #000;			
    		box-shadow: 5px 5px 20px grey;	// shadow
    	}
    </style> 
    
  </head>    
  <body>  

 <form:form modelAttribute="businessListForm" action="processRegister.html" method="POST">
  <div id="login-box">

	<center>
		<table width="90%">
			<tr >
				<th><h2>Rating - Business List :<c:out value="${accountBean.accountName}"/></h2></th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
			<tr >
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th><a href="setupBusiness.html">New Business</a></th>
				<th><a href="changePassword.html">Change Password</a></th>
				<th><a href="logout.html">Logout</a></th>
			</tr>
			<tr><td>&nbsp;</td></tr>
		</table>
	</center>
    <FONT color="red"><form:errors path="*" /></FONT>
				<table id="businessList">
				<thead>
				<tr>
					<th>Id</th>
					<th>Business Name</th>
					<th>Business Desc</th>
					<th>Category Code</th>
					<th>User Rating</th>
					<th>Active?</th>
					<th>Indicator</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${businessListForm.businessList}"  var="bussBean">
				<tr id="${bussBean.businessId}">	
					<input:hidden path="bussBean.businessId"/>
    					<td><c:out value="${bussBean.businessId}"/></td>
    					<td><a href="showBusinessSetup.html?businessSetupId=${bussBean.businessId}"><c:out value="${bussBean.businessName}"/></a></td>
    					<td><c:out value="${bussBean.businessDesc}"/></td>
						<td><c:out value="${bussBean.categoryCode}"/></td>
						<td><c:out value="${bussBean.averageRating}"/></td>
						<td><c:out value="${bussBean.isActive}"/></td>
						<td>
						<c:if test="${bussBean.isActive == 'N'}">
							<img src="<%=request.getContextPath()%>/jsp/images/red_image.png" />
						</c:if>
						<c:if test="${bussBean.isActive == 'Y'}">
							<img src="<%=request.getContextPath()%>/jsp/images/green_image.png" />
						</c:if>
						</td>
    				</tr>
				</c:forEach>  				
				</tbody>
    			</table>
    	</div>  
    		</form:form>
    <script src="js/jquery-1.11.4.js"></script>
    <script src="js/jquery-ui-1.11.4.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/dataTables.jqueryui.min.js"></script>
	 <script type="text/javascript">	
		$(document).ready(function() {   
			$("#businessList").dataTable({
				"sPaginationType": "full_numbers",
				"bJQueryUI": true
			});
		});
	</script> 
  </body>  
</html>  