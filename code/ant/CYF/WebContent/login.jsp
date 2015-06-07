<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String root = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h1>SECURITY</h1>
<form action="<%=root %>/login" method="post">
	<label for="username">用户名：</label><input type="text" name="j_username" id="username">
	<label for="password">密码：</label><input type="password" name="j_password" id="password">
	<input type="submit" value="登录">
</form>
</center>
</html>