<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<style type="text/css">
/*自定义弹出框样式style_1
*/
#popup_container.style_1 {
	font-family: Georgia, serif;
	color: #A4C6E2;
	background: #005294;
	border-color: #113F66;
}

#popup_container.style_1 #popup_title {
	color: #FFF;
	font-weight: normal;
	text-align: left;
	background: #76A5CC;
	border: solid 1px #005294;
	padding-left: 1em;
}

#popup_container.style_1 #popup_content {
	background: none;
}

#popup_container.style_1 #popup_message {
	padding-left: 0em;
}

#popup_container.style_1 INPUT[type='button'] {
	border: outset 2px #76A5CC;
	color: #A4C6E2;
	background: #3778AE;
}
</style>
<link href="css/cart.css" rel="stylesheet" type="text/css">
<!--基础文件，分别是jQuery基库和拖拽UI插件-->
<script type="text/javascript" src="js/jquery-1.11.1.js" charset="UTF-8"></script>
<script src="js/jquery.ui.draggable.js" type="text/javascript" charset="UTF-8"></script>
		
<!-- 对话框核心JS文件和对应的CSS文件-->
<script src="js/jquery.alerts.js" type="text/javascript"  charset="UTF-8"></script>
<link href="css/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript">
var buynum;
var price;
var bookid;
$(function()
		{
	       $(":checkbox").click(function(){//checkbox触发点击属性
	    	//判断checkall的属性checked的值是否是checked。
	    	//如果是所有的选项框都打上对号
	    	//如果不是，将所有的选项框的对号清空
	    	    buynum=[0];
	    	    price=[0];
	    	    bookid=[0];
	    	    var i=0;
	    	    var allcheckLength=$(".allcheck[checked='checked']").length;
	    	    if(allcheckLength==0)//全选框没被选中时，点击全选框，所有的checkbox都被选中
	    	    {
	    	    $(".allcheck").each(function(){
	    	    	if($(this).prop("checked")==true)
	    	    		{
	    	    		$("input:checkbox").attr("checked","checked");
	    	    		$("input:checkbox").prop("checked",true);
	    	    	   
	    	    		}
	    	    });
	    	    }else if(allcheckLength==2)//全选框被选中时，点击全选框取消选中
	    	    	{
	    	    	$(".allcheck").each(function(){
		    	    	if($(this).prop("checked")==false)
		    	    		{
		    	    		$("input:checkbox").removeAttr("checked");
		    	    		$("input:checkbox").prop("checked",false);
		    	    		}
		  	      			  });
	    	    	}
	    	    $(".checkcell").each(function()//遍历每个.checkcell的checkbox
	  	      			  {
	    	    	    if($(this).prop("checked")==true)
	    	    	    	{
	    	    	     $(this).attr("checked","checked");
	  	    			 buynum[i]=$(this).parent().parent().find(".buyQuantityInput").val();
	  	    			 price[i]=$(this).parent().parent().find(".price").text();
	  	    			 bookid[i]=$(this).parent().parent().find("input:hidden").val();
	  	    			 i++;
	    	    	    	}
	    	    	    else
	    	    	    	{
	    	    	    	$(this).removeAttr("checked");
	    	    	    	}
	  	      			  });
	    	  //子checkbox全选中，allcheckbox全选中;子checkbox任意一个取消选中，allcheckbox取消选中。
	    	 var checkedLength=$(".checkcell[checked='checked']").length;
	       	 var subLength=$(".checkcell").length;
	       	 //如果所有的子checkbox个数不等于选中的checkbox的个数，就取消全选框的对号
	       	 if(subLength!=checkedLength)
	       		 {
	       		 if(allcheckLength==2)
	       		 {
	       		 $(".allcheck").each(function()
	       				 {
	       			 $(this).prop("checked",false);
	       			 $(this).removeAttr("checked");
	       				 });
	       		 }
	       		 }
	       	 else
	       		 {//如果所有的子checkbox被选中，全选框也全选中
	       		 $(".allcheck").each(function()
	       				 {
	       			 $(this).prop("checked",true);
	       			 $(this).attr("checked","checked");
	       				 });
	       		 }
	    	postService(bookid,buynum,price); 
	    });
      //以上程序的功能是完成复选框选中或取消之间的逻辑关系，已经获得被选中书籍的购买数和价格的拼接字符串，将拼接的字符串传给servlet,然后进行处理
       
      
	       $(".sub").click(function(){
	    	 var bookid1=$(this).parent().parent().find("input:hidden").val(); 
	    	 var oldbuynum= parseInt($(this).next().val());
	    	 var buyprice=Number($(this).parent().prev().text()).toFixed(1);
	    	 if(oldbuynum>1)
	    	{
	    	$(this).next().val(oldbuynum-1);
	    	$(this).parent().next().text(((oldbuynum-1)*buyprice).toFixed(1));
	    	var totalbooknum=parseInt($("#buyNumTD").text());
	    	var totalprice=Number($("#priceTD").text());
	    	if($(this).parent().parent().find(":checkbox").prop("checked")==true)
	    	{
	    	$("#buyNumTD").text(totalbooknum-1);
	        $("#priceTD").text((totalprice-buyprice).toFixed(1));
	    	}
	  		 $.post("AddToCartServlet",{'bookid':bookid1,'buynum':'-1'});
	    	}
	    	 
	  	 });
      
	       $(".add").click(function(){
		    	 var bookid1=$(this).parent().parent().find("input:hidden").val(); 
		    	 var oldbuynum= parseInt($(this).prev().val());
		    	 var buyprice=Number($(this).parent().prev().text()).toFixed(1);
		    	 if(oldbuynum<100)
		    	{
		    	 $(this).prev().val(oldbuynum+1);
		         $(this).parent().next().text(((oldbuynum+1)*buyprice).toFixed(1));
			     var totalbooknum=parseInt($("#buyNumTD").text());
			     var totalprice=Number($("#priceTD").text()).toFixed(1);
			     var newtotalprice=(totalprice*1+buyprice*1).toFixed(1);
			     if($(this).parent().parent().find(":checkbox").prop("checked")==true)
			    	{
			    	$("#buyNumTD").text(totalbooknum+1);
			        $("#priceTD").text(newtotalprice);
			    	}
		  		 $.post("AddToCartServlet",{'bookid':bookid1,'buynum':'1'});
		    	}
		  	 });
	       
	       $("#payment").click(function(){
	    	  var totalprice1=$("#priceTD").text();
	    	  if(bookid==undefined)
	    	 {
	    		  jAlert('亲，你还没有选择商品~~', '提醒对话框');  
	         }
	    	  else
	    	 {
	    	  location.href="OrderServlet?bookid="+bookid.join(",")+"&totalprice="+totalprice1;
	    	 }
	       }); 
	       
	       
      });
    
function postService(bookid,buynum,price)
{
 	 $.post("ComputeServlet",{'buynum':buynum.join(","),'price':price.join(",")},
             function(returnedData,status)
             {
    		 var result1=$(returnedData).find("buyNum").text();
    		 var result2=$(returnedData).find("totalPrice").text();
    		 $("#buyNumTD").text(result1);
    		 $("#priceTD").text(result2);
    		
             $("#deleteByID").children("a").attr("href","DeleteBookServlet?bookid="+bookid.join(","));
             });
}
</script>
</head>
<body>
<jsp:include page="top.jsp"/>
<div id="cartdiv">
<h1>购物车列表</h1>
<c:if test="${empty(cart.map)}">
购物车还没有添加任何产品，赶紧购买吧~
</c:if>
<c:if test="${!empty(cart.map)}">
<table id="carttable">
<tr>
<th><input class="allcheck" type="checkbox">全选</th>
<th>图片</th>
<th>书名</th>
<th>作者</th>
<th>单价</th>
<th>购买数量</th>
<th>小计</th>
<th>操作</th>
</tr>
<c:set var="i" value="0"/>
<c:forEach var="entry" items="${cart.map}">
<tr>
<td width="50px"><input type="checkbox" class="checkcell"></td>
<td><input type="hidden" value="${entry.value.book.bookID}" class="bookidinput"><a href="BuyServlet?id=${entry.value.book.bookID}" class="pagelink"><img class="image" src="${pageContext.request.contextPath }${entry.value.book.imagePath}"></a></td>
<td>${entry.value.book.bookName}</td>
<td>${entry.value.book.bookAuthor}</td>
<td class="price">${entry.value.book.bookPrice}</td>
<td><input type="button" value="-" class="sub"/><input type="text" value="${entry.value.buyQuantity}" class="buyQuantityInput" size="3" disabled="disabled"><input type="button" value="+" class="add"/></td>
<td>${(entry.value.buyQuantity)*(entry.value.book.bookPrice)}</td>
<td><a href="DeleteBookServlet?bookid=${entry.value.book.bookID}">删除</a></td>
</tr>
</c:forEach>
</table>
<table id="paytable">
<tr>
<td width="5%" align="center"><input type="checkbox" class="allcheck">全选<td>
<td width="10%" align="center" id="deleteByID"><a href="#">删除选中项</a></td>
<td width="55%" align="right">已选书籍数</td>
<td width="5%" align="center" id="buyNumTD">0</td>
<td width="5%" align="left">本</td>
<td width="10%" align="right">总计:</td>
<td width="5%" align="center" id="priceTD">0.00</td>
<td width="5%" align="left">元</td>
<td><input type="button" value="结算" id="payment"></td>
</tr>
</table>
</c:if>
</div>
</body>
</html>