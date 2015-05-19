<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="cn.sict.domain.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<link href="css/layout.css" rel="stylesheet" type="text/css">
<link href="css/buy.css" rel="stylesheet" type="text/css">
<title>${book.bookName}</title>

<script type="text/javascript">
	$(function() {
		
		$("#button1").click(function() {
			
			var userinput=$("input:hidden:eq(0)").val();
			var bookid=$("input:hidden:eq(1)").val();
			var buynum=$("#buynum").val();
			
         if(userinput==null||userinput=="")
        	 {
        	  
        	 location.href="LoginUIServlet";
        	 //window.open("LoginUIServlet","登陆页面","width=400,height=300,scrollbars=no,resizable=no,location=no");
        	 //window.opener.location.reload();
        	 }
         else
        	 {
            location.href="AddToCartServlet?bookid="+bookid+"&buynum="+buynum;
        	 }
         
		});
	});

</script>
</head>
<body>
<div>
<jsp:include page="top.jsp" flush="true" />
</div>
<div>
	<c:set var="book" value="${book}"></c:set>
	<input type="hidden" id="id1" value="${user}">
	<input type="hidden" id="id2" value="${book.bookID}">
	<table class="booktable">
		<tr>
			<td rowspan="4"><img
				src="${pageContext.request.contextPath }${book.imagePath}"></td>
			<td>书名:${book.bookName}</td>
		</tr>

		<tr>
			<td>价格:${book.bookPrice}</td>
		</tr>

		<tr>
			<td>购买数量:<input type="text" id="buynum" name="buyNum" value="1" size="4"></td>
		</tr>

		<tr>
			<td><input type="button" value="放入购物车" id="button1"> <input
				type="button" value="立即购买" id="button2"></td>
		</tr>
		<tr>
			<td colspan="2">商品详情:<br> ${book.bookDescribe}
			</td>
		</tr>
       
	</table>
</div>
<div>
<jsp:include page="bottom.jsp" flush="true" />
</div>
</body>
</html>