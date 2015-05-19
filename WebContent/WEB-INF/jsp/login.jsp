<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登陆页面</title>
<link href="css/login.css" rel="stylesheet" type="text/css"> 
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(function(){
	$("#image").click(function(){
		window.location.reload();
	});		
});
</script>
</head>
<body>
<div id="logindiv">
<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
<table id="logintable">
<c:set var="errormessage1" value="${message1}"/>
<c:set var="errormessage2" value="${message2}"/>
<tr>
<th colspan="3">清风书苑欢迎您，请登录</th>
</tr>
<tr>
<td align="right">用户名：</td>
<td><input class="inputtext" type="text" name="username" /></td>
<td align="left" style="color:blue;">${errormessage1}</td>
</tr>
<tr>
<td align="right">密码：</td>
<td><input class="inputtext" type="password" name="password" /></td>
<td align="left" style="color:blue;">${errormessage2}</td>
</tr>
<tr>
<td align="right">验证码：</td>
<td><input type="text" size="6" align="middle">
<img alt="验证码" src="ValidateCodeServlet" width="90px" height="24px" align="top" id="image"></td>
</tr>
<tr>
<td colspan="3" align="center"><input class="btn" type="button" value="注册"
onclick="window.location.href='WEB-INF/jsp/register.jsp'" />
<input class="btn" type="submit" value="登陆" /></td>
</tr>
</table>
</form>
</div>
</html>