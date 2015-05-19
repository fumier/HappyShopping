<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/layout.css" rel="stylesheet" type="text/css">
<link href="css/order.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	$(function() {
		var legendtext;
		var updateObject;
		$("#showAllAddress").click(function() {
			if ($(this).val() == "展开地址") {
				$(".changeTr").removeAttr("style");
				$("#showAllAddress").attr("value", "收缩地址");
			} else {
				$(".changeTr").css("display", "none");
				$("#showAllAddress").attr("value", "展开地址");
			}
		});

		$("#createAddress").click(function() {
			$("#wrap").fadeIn(300);
			//Set the center alignment padding + border
			var popMargTop = ($("#wrap").height() + 24) / 2;
			var popMargLeft = ($("#wrap").width() + 24) / 2;

			$("#wrap").css({
				'margin-top' : -popMargTop,
				'margin-left' : -popMargLeft
			});
			legendtext=$("#city_china_val").find("legend").text();
			if(legendtext=='修改地址')
				{
				$("#city_china_val").find("legend").text("添加新地址");
				legendtext=$("#city_china_val").find("legend").text();
				}
			emptyAddressInfo();
			// Add the mask to body
			$('body').append('<div id="mask"></div>');
			$('#mask').fadeIn(300);

			return false;
		});
		// When clicking on the button close or the mask layer the popup closed
		$('a.close, #mask,.continue').on('click', function() {
			$('#mask , #wrap').fadeOut(300, function() {
				$('#mask').remove();
			});
			return false;
		});
/*
 * 点击设置默认地址按钮，给该标签的value进行赋值，选中value为1，不选中value为0
 */
		$(".defaultAddress").click(function(){
			if($(this).attr("value")==1)
				{
				$(this).removeAttr("checked");
				$(this).attr("value","0");
				
				}else if($(this).attr("value")==0)
				{
				$(this).attr("checked","checked");
				$(this).attr("value","1");
				}
		});
		//关闭新增地址页
		$("#close").click(function(){
			 $('#mask , #wrap').fadeOut(300, function() {
 				$('#mask').remove();
 			});
		});
		//保存信息
		$("#saveAddress").click(function() {
			var infoid=null;
			if(legendtext=='修改地址')
			{
			infoid=$(".updateAddress").parents("tr").find("input[type='radio']").val();
			}
			var name = $(".name").val();
			var province = $(".province").val();
			var city = $(".city").val();
			var area = $(".area").val();
			var details = $(".details").val();
			var zipcode = $(".zipcode").val();
			var telephone = $(".telephone").val();
			var defaultAddress = $(".defaultAddress").val();
			var flag="true";
			$(".name").parent().next().text("");
			$(".province").parent().next().text("");
			$(".details").parent().next().text("");
			$(".zipcode").parent().next().text("");
			$(".telephone").parent().next().text("");
			
			if (name == "" || name == undefined) {
				$(".name").parent().next().text("姓名不能为空").css({
					'font-size' : '12px',
					'color' : 'red'
				});
				flag="false";
			}
			if (province == null || province == 0 || city == null) {
				$(".province").parent().next().text("请选择省份/直辖市").css({
					'font-size' : '12px',
					'color' : 'red'
				});
				flag="false";
			} else if (city == 0) {
				$(".province").parent().next().text("请选择城市/区域").css({
					'font-size' : '12px',
					'color' : 'red'
				});
				flag="false";
			} else {
				if (city != 0 && city != null && area == 0) {
					$(".province").parent().next().text("请选择地区").css({
						'font-size' : '12px',
						'color' : 'red'
					});
					flag="false";
				}
			}
			if (details == "" || details == undefined) {
				$(".details").parent().next().text("详细地址不能为空").css({
					'font-size' : '12px',
					'color' : 'red'
				});
				flag="false";
			}
			if (zipcode == "" || zipcode == undefined) {
				$(".zipcode").parent().next().text("邮编不能为空").css({
					'font-size' : '12px',
					'color' : 'red'
				});
				flag="false";
			}
			if (telephone == "" || telephone == undefined) {
				$(".telephone").parent().next().text("联系电话不能为空").css({
					'font-size' : '12px',
					'color' : 'red'
				});
				flag="false";
			} else {
				if (isNaN(telephone)) {
					$(".telephone").parent().next().text("请正确输入联系方式").css({
						'font-size' : '12px',
						'color' : 'red'
					});
					flag="false";
				}
			}
            if(flag=="true")
            	{
            	if(infoid==null)
            		{
               $.post("AddressServlet",
            		{"name":name,
	                 "province":province,
	                 "city":city,
	                 "area":area,
	                 "details":details,
	                 "zipcode":zipcode,
	                 "telephone":telephone,
	                 "defaultAddress":defaultAddress
                    },function(returnedData,status){
                    	//返回该地址信息的id
                       var infoid=$(returnedData).find("infoid").text();
                       var firstRow;
                        if(defaultAddress=="1")
                        {
                        	firstRow="<tr><td><input type='radio' name='addressradio' checked='checked' value=''></td><td></td><td></td><td></td><td class='orderlink'><span><a href='#' class='updateAddress' style='display:none;'>修改</a></span></td></tr>"; 
                        	$("#addressTable tr:eq(1) td:eq(4)").find(".setDefaultAddress").css({
                        		"display":"none"
                        	});
                        }else
                        {
                        	firstRow ="<tr><td><input type='radio' name='addressradio' checked='checked' value=''></td><td></td><td></td><td></td><td class='orderlink'><span><a href='#' class='updateAddress' style='display:none;'>修改</a>&nbsp;<a href='#' class='setDefaultAddress' style='display:none;'>设置默认地址</a></span></td></tr>"; 
                        }
                        $("#addressTable tr:eq(0)").before(firstRow);
                        $("#addressTable tr:eq(0) td:eq(0)").css({
                        	"width":"5%",
                        	"text-align":"right"
                        });
                        $("#addressTable tr:eq(0) td:eq(0) input").val(infoid);
                        if(null!=area)
                        	{
                        	  $("#addressTable tr:eq(0) td:eq(1)").css({
                              	"width":"50%",
                              	"padding-left": "10px"
                              }).addClass("addresstd").text(province+","+city+","+area+","+details+","+zipcode);
                        	}
                        else
                        	{
                        	 $("#addressTable tr:eq(0) td:eq(1)").css({
                               	"width":"50%",
                               	"padding-left": "10px"
                               }).addClass("addresstd").text(province+","+province+","+city+","+details+","+zipcode);
                        	}
                      
                        $("#addressTable tr:eq(0) td:eq(2)").css({
                        	"width":"10%",
                        	"align":"left"
                        }).addClass("nametd").text(name);
                        $("#addressTable tr:eq(0) td:eq(3)").css({
                        	"width":"10%",
                        	"align":"left"
                        }).addClass("telephonetd").text(telephone); 
                        $("#addressTable tr:eq(0) td:eq(4)").css({
                        	"width":"25%",
                        	"align":"left"
                        });
                        $("#addressTable tr:eq(5)").css({
                        	"display":"none"
                        }).addClass("changeTr");
	                 });
            		}else
            			{
            			 $.post("UpdateAddressServlet",
            	            		{
            				         "infoid":infoid,
            				         "name":name,
            		                 "province":province,
            		                 "city":city,
            		                 "area":area,
            		                 "details":details,
            		                 "zipcode":zipcode,
            		                 "telephone":telephone,
            		                 "defaultAddress":defaultAddress
            	                    },function(returnedData,status){
            	                    	var result=$(returnedData).find("result").text();
            	                    	if(result=='success')
            	                    		{
            	                    		  if(null!=area)
                        	                    {
            	                    			  updateObject.parents("tr").find(".addresstd").text(province+","+city+","+area+","+details+","+zipcode);
                        	                    }
                                             else
                                             	{
                                            	 updateObject.parents("tr").find(".addresstd").text(province+","+province+","+city+","+details+","+zipcode);
                        	                    }
            	                    		  updateObject.parents("tr").find(".nametd").text(name);
            	                    		  updateObject.parents("tr").find(".telephonetd").text(telephone);
            	                    		}
            	                    		alert(result);
            	                    });
            			}
            	    $('#mask , #wrap').fadeOut(300, function() {
      				$('#mask').remove();
      			    });
            	}
		});
/*
 * 鼠标经过时显示和隐藏超链接
 * mouseover show hidden link and mouseout hide link
 */
        $("#addressTable tbody").on('mouseover','tr',function(){
        	$(this).find(".orderlink").find("a").css("display","inline");
        });
        $("#addressTable tbody").on('mouseout','tr',function(){
        	$(this).find(".orderlink").find("a").css("display","none");
        });
		
		//$(".updateAddress").click(function()
		  $("#addressTable").on('click','.updateAddress',function()
		   {
			//先获取页面上各字段的信息
			updateObject=$(this);
			var infoID=$(this).parents("tr").find("input[type='radio']").val();
			var name=$(this).parents("tr").find(".nametd").text();
			var address=$(this).parents("tr").find(".addresstd").text();
			var addressSplit=address.split(",");
			var province=addressSplit[0];
			var city=addressSplit[1];
			var area=addressSplit[2];
			var details=addressSplit[3];
			var zipcode=addressSplit[4];
			var telephone=$(this).parents("tr").find(".telephonetd").text();
			//打开新增地址管理页
			$("#wrap").fadeIn(300);
			//Set the center alignment padding + border
			var popMargTop = ($("#wrap").height() + 24) / 2;
			var popMargLeft = ($("#wrap").width() + 24) / 2;

			$("#wrap").css({
				'margin-top' : -popMargTop,
				'margin-left' : -popMargLeft
			});
			legendtext=$("#city_china_val").find("legend").text();
			if(legendtext=='添加新地址')
				{
				$("#city_china_val").find("legend").text("修改地址");
				legendtext=$("#city_china_val").find("legend").text();
				}
			
			// Add the mask to body
			$('body').append('<div id="mask"></div>');
			$('#mask').fadeIn(300);
            $(".name").val(name);
            $(".province").val(0);
            $(".city").val(0);
            $(".area").val(0);
            if(province!=city)
            	{
                   $(".province").val(province);
                   $(".city").val(city);
                   $(".area").val(area);
            	}
            else
            	{
            		$(".province").val(city);
            		$(".city").val(area);
            	}
            $(".details").val(details);
            $(".zipcode").val(zipcode);
            $(".telephone").val(telephone);
			return false;
		   });
		//select的级联选项，当province改变时，先将city和area清空
		$(".province").change(function()
				{
				$(".city").empty();
				$(".area").empty();
				});
	});
	function emptyAddressInfo()
	{
		$(".name").val("");
		$(".province").val("辽宁省");
		$(".city").val("沈阳市");
		$(".area").val("东陵区");
		$(".details").val("");
		$(".zipcode").val("");
		$(".telephone").val("");
	}
</script>
<title>确认订单</title>
</head>
<body>
	<jsp:include page="top.jsp" />
	<div id="addressDiv" class="orderDiv">
		<table id="createAddressTable">
			<tr>
				<td align="left"><h2>选择收货地址</h2></td>
				<td align="right" width="300"><input type="button"
					value="新建收货地址" id="createAddress"></td>
			</tr>
		</table>
		<table id="addressTable">
			<c:set var="i" value="0"/>
			<c:forEach var="info" items="${infoList}">
			<c:set var="defaultAddress" value="${info.defaultAddress}"/>
				<c:set var="i" value="${i+1}" />
				<c:if test="${i<6}">
					<c:if test="${i==1}">
						<tr>
							<td width="5%" align="right"><input type="radio" name="addressradio" checked="checked" value="${info.infoID}"></td>
							<td width="50%" style="padding-left: 10px" class="addresstd">${info.address.province},${info.address.city},${info.address.area},${info.address.details},${info.address.zipcode}</td>
							<td width="10%" align="left" class="nametd">${info.personName}</td>
							<td width="10%" align="left" class="telephonetd">${info.telephone}</td>
							<td width="25%" class="orderlink"><span><a href="#" class="updateAddress">修改</a>&nbsp;
							<c:if test="${defaultAddress==false}">
							<a href="#" class="setDefaultAddress">设为默认地址</a></c:if></span></td>
						</tr>
					</c:if>
					<c:if test="${i!=1}">
						<tr>
							<td width="5%" align="right"><input type="radio" class=""
								name="addressradio" value="${info.infoID}"></td>
							<td width="50%" style="padding-left: 10px" class="addresstd">${info.address.province},${info.address.city},${info.address.area},${info.address.details},${info.address.zipcode}</td>
							<td width="10%" align="left" class="nametd">${info.personName}</td>
							<td width="10%" align="left" class="telephonetd">${info.telephone}</td>
							<td width="25%" class="orderlink"><span><a href="#" class="updateAddress">修改</a>&nbsp;
							<c:if test="${defaultAddress==false}">
							<a href="#" class="setDefaultAddress">设为默认地址</a></c:if></span></td>
						</tr>
					</c:if>
				</c:if>
				<c:if test="${i>=6}">
					<tr style="display: none;" class="changeTr">
						<td width="5%" align="right"><input type="radio" class=""
							name="addressradio"></td>
						<td width="50%" style="padding-left: 10px" class="addresstd">${info.address.province},${info.address.city},${info.address.area},${info.address.details},${info.address.zipcode}</td>
						<td width="10%" align="left" class="nametd">${info.personName}</td>
						<td width="10%" align="left" class="telephonetd">${info.telephone}</td>
						<td width="25%" class="orderlink"><span><a href="#" class="updateAddress">修改</a>&nbsp;
							<c:if test="${defaultAddress==false}">
							<a href="#" class="setDefaultAddress">设为默认地址</a></c:if></span></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<c:set var="listLength" value="${infoLength}" />
				<c:if test="${infoLength>5}">
					<td colspan="5" align="right"><input type="button"
						value="展开地址" id="showAllAddress"></td>
				</c:if>
			</tr>
		</table>
	</div>
	<div class="orderDiv">
		<h2>选择支付方式</h2>
		<table id="choosePayTable">
		<tr>
		   <td width="50%" align="center"><input type="radio" value="1" name="payWay" class="payRadio" checked="checked">支付宝支付</td>
		   <td width="50%" align="center"><input type="radio" value="2" name="payWay" class="payRadio">网银支付</td>
		</tr>
		</table>
     </div>
	<div class="orderDiv">
		<h2>确认订单信息</h2>
		<table id="orderTable">
			<tr>
				<th>图片</th>
				<th>书名</th>
				<th>单价</th>
				<th>购买数量</th>
				<th>小计</th>
			</tr>
			<c:forEach var="cartItem" items="${ChoosedCartItems}">
				<tr>
					<td width="20%"><input type="hidden"
						value="${cartItem.book.bookID}" class="bookidinput"><a
						href="BuyServlet?id=${cartItem.book.bookID}" class="pagelink"><img
							class="image"
							src="${pageContext.request.contextPath }${cartItem.book.imagePath}"></a></td>
					<td width="40%">${cartItem.book.bookName}</td>
					<td width="10%">${cartItem.book.bookPrice}</td>
					<td width="10%">${cartItem.buyQuantity}</td>
					<td width="20%">${(cartItem.buyQuantity)*(cartItem.book.bookPrice)}</td>
				</tr>
			</c:forEach>
			<tr>
			<td colspan="4">总计</td>
			<td>${totalPrices}</td>
			</tr>
		</table>
		<p align="right">
		   <input type="button" value="确认支付" id="makePay">
	    </p>
	</div>
	<jsp:include page="bottom.jsp" flush="true" />
	<div id="wrap">
		<a href="#" class="close"><img src="images/close_pop.png"
			class="btn_close" title="Close Window" alt="Close" /></a>
		<fieldset id="city_china_val">
			<legend>添加新地址</legend>
			<table>
				<tr>
					<td><font style="color: red">*</font>姓名:</td>
					<td><input type="text" size="20" class="name" value=""></td>
					<td></td>
				</tr>
				<tr>
					<td><font style="color: red">*</font>地址:</td>
					<td>
					<select class="province" data-value="辽宁省" data-first-title="选择省" disabled="disabled"></select> 
					<select class="city" data-value="沈阳市" data-first-title="选择市" disabled="disabled"></select> 
					<select class="area" data-value="东陵区" data-first-title="选择地区" disabled="disabled"></select>
					</td>
					<td></td>
				<tr>
					<td></td>
					<td><input type="text" maxlength="50" size="41" value=""
						class="details"></td>
					<td></td>
				</tr>
				<tr>
					<td><font style="color: red">*</font>邮编:</td>
					<td><input type="text" maxlength="6" size="6" value=""
						class="zipcode"></td>
					<td></td>
				</tr>
				<tr>
					<td><font style="color: red">*</font>联系电话:</td>
					<td><input type="text" maxlength="12" size="20" value=""
						class="telephone"></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="radio" name="setDefaultAddress" value="0"
						class="defaultAddress"><font style="font-size: 12px">设置为默认地址</font>
					</td>
					<td><font style="color: red; font-size: 12px">*为必须填写</font></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="button" value="保存"
						id="saveAddress"> <input type="button" value="清空"
						id="close"></td>
				</tr>
			</table>
		</fieldset>
	</div>
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script src="js/jquery.cxselect.js" charset="UTF-8"></script>
	<script type="text/javascript" charset="UTF-8">
		$.cxSelect.defaults.url = 'js/cityData.json';
		$('#city_china_val').cxSelect({
			selects : [ 'province', 'city', 'area' ],
			nodata : 'none'
		});
	</script>
</body>
</html>