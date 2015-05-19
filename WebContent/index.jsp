<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cn.sict.web.formbean.PageBean"%>
<%@page import="cn.sict.utils.PageSplitUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/layout.css" rel="stylesheet" type="text/css">
<title>清风书苑</title>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="container">

		<jsp:include page="top.jsp" flush="true" />

		<div id="mainContent">

			<div id="sidebar">
				<jsp:include page="left.jsp" flush="true" /><!-- 左侧导航栏 -->
			</div>

			<div id="content" >
				<!-- 搜索栏和商品显示，这其中包括一个表格-->
				<jsp:include page="content.jsp" flush="true" />
			</div>
		</div>
	<div id="footer">
		<jsp:include page="bottom.jsp" flush="true" />
	</div>
</div>
</body>
</html>