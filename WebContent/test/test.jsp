<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table id="showbook">
<c:if test="${map!=null}">

<table class="booktable">
<tr><td class="booktd"><img class="bookimage" src="${pageContext.request.contextPath }${entry.value.imagePath}"></td></tr>
<tr><td class="booktd">书名：${entry.value.bookName}</td></tr>
<tr><td class="booktd">价格：${entry.value.bookPrice}</td></tr>
</table>
</c:if>
</table>
</body>
</html>