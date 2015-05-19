<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/top.css" rel="stylesheet" type="text/css"> 
</head>
<body>
<div id="tablediv">
<table id="top">
<tr>
<td width="600">
<c:if test="${user!=null}">
清风书苑欢迎您：${user.nickname}<a href="${pageContext.request.contextPath}/LoginOutServlet" class="link">注销</a>
</c:if>
<c:if test="${user==null}">
欢迎光临清风书苑，请<a href="${pageContext.request.contextPath}/LoginUIServlet" class="link"><font color="red">登录</font></a>
<a href="${pageContext.request.contextPath}/RegisterUIServlet" class="link">免费注册</a>
</c:if>
</td>
<td id="id1" width="300">
<a href="${pageContext.request.contextPath}/CartServlet" class="link"><img alt="mart" src="images/mart.png" align="top" class="shopping-cart">购物车</a>
<a href="#" class="link">订单</a>
</td>
</tr>
</table>
</div>
<div class="topdiv">
</div>
<div class="navdiv">
<ul id="topul">
<li class="topli"><a href="${pageContext.request.contextPath}/PageServlet" class="ali">首页</a></li>
<li class="topli"><a href="#" class="ali">新闻</a></li>
<li class="topli"><a href="#" class="ali">后台管理</a></li>
<li class="topli"><a href="#" class="ali">联系我们</a></li>
</ul>
</div>
<div class="searchdiv">
<table width="1002">
<tr>
<td id="searchtd">
<select>
<option>书名</option>
<option>作者</option>
</select>
<input type="text" name="searchbook" size="30" align="left">
<input type="submit" name="submit" value="搜索">
</td>
</tr>
</table>
</div>
</body>
</html>