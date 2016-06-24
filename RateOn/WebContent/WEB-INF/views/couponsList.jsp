<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
 <head>  
    <title>Coupon List</title>
     <link rel="stylesheet" href="jsp/stylesheet/screen.css">
   <link rel="stylesheet" href="/RateOn Original/WebContent/jsp/stylesheet/styles.css">
   	
	<link href="jsp/stylesheet/calendar.css" rel="stylesheet" type="text/css">
	<script src="jsp/Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
	<script src="jsp/Scripts/calendar.js" type="text/javascript"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  	<!-- <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>  -->
  	<script src="js/jquery-1.11.4.js"></script>
   <script src="js/jquery-ui-1.11.4.js"></script>
	<script src="js/jquery.validate.js"></script>
    <style>
    	
    #login-box {
    		width: 750px;
			height: 250px;
    		padding: 10px;
    		margin: 50px auto;
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
		.ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all{
			width: 150px;
			height: 100px;
		}
    </style>  
    
    
<script>
$(function() {
	$(".date").datepicker();
});

/*  validation */
  
 jQuery.validator.addMethod("validate", function(value, element) {
	     if(value>=$("#startDate").val()) return true;
	     else return false;
	}); 


$().ready(function() {  

    $("#coupon").validate({
        rules: {
            couponDesc: { 
                minlength: 3,
                maxlength: 40,
            },
            endDate: {
                //required: true,  
                validate: true,
                
            }, 
            
        },
        messages: {
        	couponDesc: {
                required: "required!",
                minlength: "min 3 characters"
            },
            endDate: {
                required: "required!",
                validate: "enter correct date"
            },  
            startDate:{
            	required:"required",
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
  </head>  
  <script type="text/javascript">
	function registerClick()
	{
		alert('Register...');
		document.getElementById("form_id").action = "showRegister.html";
		document.getElementById("form_id").submit();
	}
</script>

<script>

</script>

  <body>  

 <form:form modelAttribute="couponBean" id="coupon" action="saveCoupon.html" method="POST">
  <div id="login-box">
		<center>
		<table width="90%">
			<tr >
				<th><h2>Rating - Coupon : <c:out value="${accountBean.accountName}"/></h2></th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
			<tr >
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th><a href="showBusinessList.html">Business List</a></th>
				<th><a href="changePassword.html">Change Password</a></th>
				<th><a href="logout.html">Logout</a></th>
			</tr>
		</table>
	</center>
<center>
	<table>
   
    				<tr><form:hidden path="businessId"/>
						<td align = "left">Business Id :</td>
    					<td align="left"><form:input path="businessName" disabled = "true"/></td>
						<td>&nbsp;</td>
    				</tr>
					<tr>
						<td align = "left">Coupon Desc :</td>
    					<td align="left"><form:input path="couponDesc" required="true"/></td>
						<td align="right"><FONT color="red"><form:errors path="couponDesc" /></FONT></td>
    				</tr>
    				<tr>
						<td align = "left">Valid From :</td>
    					<td align="left"><form:input  path="startDate" class="date" required="true"/>
						<!-- <a href="#" onClick="setYears(2000, 2030);showCalender(this, 'startDate');"><img src="jsp/images/calender.png"></a> -->
						</td>
						<td align="right"><FONT color="red"><form:errors path="startDate" /></FONT></td>
    				</tr>
    				<tr>
						<td align = "left">Valid To :</td>
    					<td align="left"><form:input  path="endDate" class="date" required="true"/>
					<!-- 	<a href="#" onClick="setYears(2000, 2030);showCalender(this, 'endDate');"><img src="jsp/images/calender.png"></a> -->
						</td>
						<td align="right"><FONT color="red"><form:errors path="endDate" /></FONT></td>
    				</tr>
    				<tr>
						<td>&nbsp;</td>
  						<td>&nbsp;</td>
    					<td align="right"><input type="submit" name="submit" value="submit" id="submit"/></td>
    				</tr>
			</table>
</center>
			<c:if test="${!empty couponsList}">

			<br />
			<center>
				<table width="100%">
					<tr style="background-color: silver;">
						<th>Coupon</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Status</th>
						<th>Active?</th>
					</tr>
					<c:forEach items="${couponsList}" var="bean">
						<tr style="background-color: Gainsboro;" id="${bean.couponId}">
						<td><c:out value="${bean.couponDesc}"/></td>
						<td><c:out value="${bean.startDate}"/></td>
						<td><c:out value="${bean.endDate}"/></td>
						<td><c:out value="${bean.isActive}"/></td>
						<td><a href="activateCoupon.html?activateCouponId=${bean.couponId}">Click Here</a></td>
						</tr>
					</c:forEach>
				</table>
				</center>
			<br />

		</c:if>

    	</div>  
    		</form:form>
  </body>  
</html>  


<!-- Calender Script  --> 

    <table id="calenderTable">
        <tbody id="calenderTableHead">
          <tr>
            <td colspan="4" align="center">
	          <select onChange="showCalenderBody(createCalender(document.getElementById('selectYear').value,
	           this.selectedIndex, false));" id="selectMonth">
	              <option value="1">Jan</option>
	              <option value="2">Feb</option>
	              <option value="3">Mar</option>
	              <option value="4">Apr</option>
	              <option value="5">May</option>
	              <option value="6">Jun</option>
	              <option value="7">Jul</option>
	              <option value="8">Aug</option>
	              <option value="9">Sep</option>
	              <option value="10">Oct</option>
	              <option value="11">Nov</option>
	              <option value="12">Dec</option>
	          </select>
            </td>
            <td colspan="2" align="center">
			    <select onChange="showCalenderBody(createCalender(this.value, 
				document.getElementById('selectMonth').selectedIndex, false));" id="selectYear">
				</select>
			</td>
            <td align="center">
			    <a href="#" onClick="closeCalender();"><font color="#003333" size="+1">X</font></a>
			</td>
		  </tr>
       </tbody>
       <tbody id="calenderTableDays">
         <tr style="">
           <td>Sun</td><td>Mon</td><td>Tue</td><td>Wed</td><td>Thu</td><td>Fri</td><td>Sat</td>
         </tr>
       </tbody>
       <tbody id="calender"></tbody>
    </table>

<!-- End Calender Script  --> 
</body>
</html>
