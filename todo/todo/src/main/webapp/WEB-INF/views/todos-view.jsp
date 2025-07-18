<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2> Welcome <%=request.getAttribute("username") %></h2>
<h3> ToDo list</h3>
<%=request.getAttribute("list") %>

<form action="/todo" method="post">
	<input type="text" name="description" />
	<input type="date" name="todoDate" />
	<input type="time" name="todotime" />
	<input type="submit" />

</form>
</body>
</html>