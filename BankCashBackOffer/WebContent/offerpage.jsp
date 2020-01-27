<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CLAIM YOUR CASHBACK</title>
</head>
<h1>Your Current Balance is :${balance} </h1>
<h3>Claim Your cash Back here</h3> 
<body>
<%-- 	<%
	if(session.getAttribute("customer id")==null)
	{
		response.sendRedirect("index.jsp");
	}
	%> --%>

	<form action="offer">
	Enter Your Coupon Code:<input type="text" name="couponcode"> <br>
						   <input type="submit" value="claim">
	</form>
		
</body>
</html>