<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>ToDo Home page</h2>
	<div>
	<% String msg = (String) request.getAttribute("msg");
	if( msg != null) 
		out.println(msg); 
	%>
	</div>
	<form action="/login" method="post">
		Username <input type="text" name="username" /> <br> Password <input
			type="password" name="password" /> <br> <input type="submit"
			value="Login" />
	</form>
</body>
</html>