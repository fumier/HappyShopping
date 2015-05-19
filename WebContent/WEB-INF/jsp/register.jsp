<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户注册</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></LINK>
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/js/dhtmlgoodies_calendar.js?random=20060118"></script>
<style>

</style>
<link href="css/register.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="main">
		<form action="${pageContext.request.contextPath}/RegisterServlet"
			method="post">
			<table id="formtable">
			<tr>
			<th colspan="2">清风书苑注册</th>
			</tr>
				<tr>
					<td>登陆账号：</td>
					<td>
					<input class="userinput" type="text" name="username" value="${form.username}">
					<span class="message">${form.errors.username}</span>
					</td>
				</tr>
				<tr>
					<td class="td1">密码：</td>
					<td>
					<input class="userinput" type="password" name="password" value="${form.password}">
					<span class="message">${form.errors.password}</span>
					</td>
				</tr>
				<tr>
					<td class="td1">确认密码：</td>
					<td>
					<input class="userinput" type="password" name="password1" value="${form.password1}">
					<span class="message">${form.errors.password1}</span>
					</td>
				</tr>

				<tr>
					<td class="td1">电子邮箱：</td>
					<td>
					<input class="userinput" type="text" name="email" value="${form.email}">
					<span class="message">${form.errors.email}</span>
					</td>
				</tr>

				<tr>
					<td class="td1">生日：</td>
					<td>
					<input class="userinput" type="text" name="birthday" value="${form.birthday}" readonly><input type="button" value="选择日期" onclick="displayCalendar(document.forms[0].birthday,'yyyy-mm-dd',this)">
					<span class="message">${form.errors.birthday}</span>
					</td>
				</tr>

				<tr>
					<td class="td1">您的昵称：</td>
					<td>
					<input class="userinput" type="text" name="nickname" value="${form.nickname}">
					<span class="message">${form.errors.nickname}</span>
					</td>
				</tr>
				<tr>
				<td></td>
				<td align="left"colspan="2">
				<input class="btn" type="reset" name="reset" value="重置">
				<input class="btn" type="submit" name="submit" value="注册">
				</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="foot"></div>
</body>
</html>