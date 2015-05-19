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
		$("#add-to-cart").click(function() {
			var userinput=$("input:hidden:eq(0)").val();
			var bookid=$("input:hidden:eq(1)").val();
			var buynum=$("#buynum").val();
			
         if(userinput==null||userinput=="")
        	 {
        	 location.href="LoginUIServlet";
        	 }
         else
        	 {
            $.post("AddToCartServlet",{'bookid':bookid,'buynum':buynum},
                    function(returnedData,status)
                    {
            	$("#dialog-box").fadeIn(300);
            	//Set the center alignment padding + border
        		var popMargTop = ($("#dialog-box").height() + 24) / 2; 
        		var popMargLeft = ($("#dialog-box").width() + 24) / 2; 
        		
        		$("#dialog-box").css({ 
        			'margin-top' : -popMargTop,
        			'margin-left' : -popMargLeft
        		});
        		
        		// Add the mask to body
        		$('body').append('<div id="mask"></div>');
        		$('#mask').fadeIn(300);
        		
        		return false;
       });
        
        	 }
		});
		 // When clicking on the button close or the mask layer the popup closed
    	$('a.close, #mask,.continue').on('click', function() { 
    	  $('#mask , .dialog-popup').fadeOut(300 , function() {
    		$('#mask').remove();  
    	}); 
    	return false;
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
			<td>
			<input type="button" value="放入购物车" id="add-to-cart"> 
			<input type="button" value="立即购买" id="button2">
			</td>
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
<div id="dialog-box" class="dialog-popup">
 <a href="#" class="close"><img src="images/close_pop.png" class="btn_close" title="Close Window" alt="Close" /></a>
<form action="CartServlet" class="choose-dialog">
<fieldset class="textbox">
<table>
<tr>
<th>来自清风书苑的温馨提示</th>
</tr>
<tr>
<td class="dialog-td1" valign="middle">书已经成功添加到购物车，欢迎继续选购~</td>
</tr>
<tr>
<td class="dialog-td2">
<button type="submit" class="entercart">进入购物车</button>
<input type="button" class="continue" value="返回继续购物">
</td>
</tr>
</table>
</fieldset>
</form>
</div>	
</body>
</html>