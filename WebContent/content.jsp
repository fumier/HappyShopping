<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cn.sict.web.formbean.PageBean" %>
<%@page import="cn.sict.utils.PageSplitUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/content.css" rel="stylesheet" type="text/css"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function getPageNum()
{
	var pageNum=document.getElementsByName("inputPageNum")[0].value;
	var url=document.URL;
	url=url.split("index.jsp");
	window.location.href=url[0]+"PageServlet?curPageNum="+pageNum;
}
</script>
</head>
<body>
<table id="booktable">
<tr>
<th id="showth">推荐书籍</th>
</tr>
<%
PageBean pageBean=new PageBean();
int firstPage=PageSplitUtils.getTopPage();
int bottomPage=(Integer)session.getAttribute("totalPages");
int nextPage=(Integer)session.getAttribute("nextPage");
int lastPage=(Integer)session.getAttribute("lastPage");	
%>
<c:set var="curPageNum" value="${curPageNum}"/>
<c:set var="totalPages" value="${totalPageNum}"></c:set>
<c:set var="pageData" value="${pageData}"></c:set>
<c:set var="message" value="${message}"></c:set>
<c:set var="rowPerPage" value="${rowPerPage}" ></c:set>
<tr>
<td id="showtd">
<c:if test="${pageData!=null}">
<table class="tablebook">
<c:forEach var="i" begin="1" end="${rowPerPage}" step="1">
<tr>
<c:forEach var="book" items="${pageData}" begin="${((i-1)*5)}" end="${((i-1)*5)+4}" step="1">
<td>
<table class="showbook">
<tr><td class="booktd1" align="center" valign="middle"><a href="BuyServlet?id=${book.bookID}" class="pagelink"><img class="bookimage" src="${pageContext.request.contextPath }${book.imagePath}"></a></td></tr>
<tr><td class="booktd">书名：<a href="BuyServlet?id=${book.bookID}" class="pagelink">${book.bookName}</a></td></tr>
<tr><td class="booktd">价格：${book.bookPrice}</td></tr>
</table>
<td>
</c:forEach>
</tr>
</c:forEach>
</table>
</c:if>
</td>
</tr>
<tr>
<td id="pagetd" align="right">
<a href="PageServlet?curPageNum=<%=firstPage%>" class="pagelink">第一页</a>
<a href="PageServlet?curPageNum=<%=nextPage%>" class="pagelink">下一页</a> 
<a href="PageServlet?curPageNum=<%=lastPage%>" class="pagelink">上一页</a>
<a href="PageServlet?curPageNum=<%=bottomPage%>" class="pagelink">尾页</a>
第<input type="text" name="inputPageNum" size="2"/>页<a href=javascript:onclick=getPageNum(); class="pagelink">跳转</a>
</td>
</tr>
</table>
</body>
</html>