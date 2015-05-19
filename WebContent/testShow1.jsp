<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/table/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="css/test.css">
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/table/jquery.dataTables.js"></script>
<script type="text/javascript">
$(document).ready( function () {
	var t= $('#addressTable').DataTable();
	var counter = 1;
    $("#addRow").click(function() {
		$("#wrap").fadeIn(300);
		//Set the center alignment padding + border
		var popMargTop = ($("#wrap").height() + 24) / 2;
		var popMargLeft = ($("#wrap").width() + 24) / 2;

		$("#wrap").css({
			'margin-top' : -popMargTop,
			'margin-left' : -popMargLeft
		});

		// Add the mask to body
		$('body').append('<div id="mask"></div>');
		$('#mask').fadeIn(300);

		return false;
	});
    
    $('a.close, #mask,.continue').on('click', function() {
		$('#mask , #wrap').fadeOut(300, function() {
			$('#mask').remove();
		});
		return false;
	});
    
    $("#saveAddress").click(function() {
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
        	{$.post("AddressServlet",
        		{"name":name,
                 "province":province,
                 "city":city,
                 "area":area,
                 "details":details,
                 "zipcode":zipcode,
                 "telephone":telephone,
                 "defaultAddress":defaultAddress
                },function(returnedData,status){
                	var firstColumn="<input type='radio' name='addressradio' checked='checked'>";
                	var address=null;
                	var lastColumn=null;
                	if(defaultAddress==0)
                		{
                	    lastColumn="<span class='linkspan'><a href='#' class='updateAddress' style='display:none'>修改</a>&nbsp;<a href='#' class='setDefaultAddress' style='display:none'>设置默认地址</a></span>";
                		}
                	else
                		{
                	    lastColumn="<span class='linkspan'><a href='#' class='updateAddress' style='display:none'>修改</a></span>";
                		}
                	if(null!=area)
                		{
                		address=province+','+city+','+area+','+details+','+zipcode;
                		}
                	else
                		{
                		address=province+','+province+','+area+','+details+','+zipcode;
                		}
                	t.row.add( [
                	            firstColumn,
                	            address,
                	            name,
                	            telephone,
                	            lastColumn
                	        ] ).draw();
                    $('#mask , #wrap').fadeOut(300, function() {
        				$('#mask').remove();
        			});
                 });
        	}
	});
    
    $(".defaultAddress .payRadio").click(function(){
		if($(this).attr("checked")=="checked")
			{
			$(this).removeAttr("checked");
			$(this).attr("value","0");
			}else if($(this).attr("checked")==undefined)
			{
			$(this).attr("checked","checked");
			$(this).attr("value","1");
			}
	});
    //链接显示方式
    $(".linkspan").mouseover(function()
 	       {
 			$(this).find("a").css("display","inline");
 		   });
 		$(".linkspan").mouseout(function()
 		   {
 			$(this).find("a").css("display","none");
 		   });
} );

</script>
</head>
<body>
<input  id="addRow" type="button" value="add new row">
<div>
	<table id="addressTable" class="displayTable">
	<thead>
	<tr>
	<th width="5%"></th>
	<th width="55%"></th>
	<th width="10%"></th>
	<th width="10%"></th>
	<th width="15%"></th>
	</tr>
	</thead>
		<c:set var="i" value="0" />
		<c:forEach var="info" items="${infoList}">
			<c:set var="defaultAddress" value="${info.defaultAddress}" />
			<c:set var="i" value="${i+1}" />
				<c:if test="${i==1}">
				<tbody>
					<tr>
						<td>
						<input type="radio" name="addressradio" checked="checked">
						</td>
						<td>${info.address.province},${info.address.city},${info.address.area},${info.address.details},${info.address.zipcode}</td>
						<td>${info.personName}</td>
						<td>${info.telephone}</td>
						<td class="orderlink"><span class="linkspan"><a href="#"
								class="updateAddress">修改</a>&nbsp; <c:if test="${defaultAddress==false}">
									<a href="#" class="setDefaultAddress">设为默认地址</a>
								</c:if></span></td>
					</tr>
				</c:if>
				<c:if test="${i!=1}">
					<tr>
						<td><input type="radio"
							name="addressradio"></td>
						<td> ${info.address.province},${info.address.city},${info.address.area},${info.address.details},${info.address.zipcode}</td>
						<td>${info.personName}</td>
						<td>${info.telephone}</td>
						<td class="orderlink"><span class="linkspan"><a href="#" class="updateAddress">修改</a>&nbsp; <c:if
									test="${defaultAddress==false}">
									<a href="#" class="setDefaultAddress">设为默认地址</a>
								</c:if></span></td>
					</tr>
				</c:if>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div id="wrap">
		<a href="#" class="close"><img src="images/close_pop.png"
			class="btn_close" title="Close Window" alt="Close" /></a>
		<fieldset id="city_china_val">
			<legend>添加新地址</legend>
			<table>
				<tr>
					<td><font style="color: red">*</font>姓名:</td>
					<td><input type="text" size="20" class="name"></td>
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
					<td><input type="radio" name="setDefaultAddress" value="0" class="defaultAddress"><font style="font-size: 12px">设置为默认地址</font>
					</td>
					<td><font style="color: red; font-size: 12px">*为必须填写</font></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
					<input type="button" value="保存" id="saveAddress"> 
					<input type="button" value="清空" id="close">
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
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